package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cl.certificadoradelsur.byecontabilidad.json.ClasificacionJson;
import cl.certificadoradelsur.byecontabilidad.restdao.ClasificacionRD;

/**
 * Clase que implementa la interfaz ClasificacionSvc
 * 
 * @author juan
 *
 */
public class ClasificacionSvcBean implements ClasificacionSvc {
	@Inject
	private ClasificacionRD clard;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		ClasificacionJson ccj = gson.fromJson(datos, ClasificacionJson.class);
		String respuesta = gson.toJson(clard.save(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response list(Integer inicio, Integer fin, String nombre, Long idClaseCuenta, Long idGrupoCuenta,
			String idUsuario, Long idEmpresa) {
		Gson gson = new GsonBuilder().create();
		List<ClasificacionJson> lccj = clard.getAll(inicio, fin, nombre, idClaseCuenta, idGrupoCuenta, idUsuario,
				idEmpresa);
		String json = "{\"records\": " + gson.toJson(lccj, new TypeToken<List<ClasificacionJson>>() {
		}.getType()) + ", \"total\": " + clard.countAll(nombre, idClaseCuenta, idGrupoCuenta, idUsuario, idEmpresa)
				+ "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		ClasificacionJson ccj = gson.fromJson(datos, ClasificacionJson.class);
		String respuesta = gson.toJson(clard.update(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		ClasificacionJson ccj = gson.fromJson(datos, ClasificacionJson.class);
		String respuesta = gson.toJson(clard.getById(ccj), new TypeToken<ClasificacionJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		ClasificacionJson ccj = gson.fromJson(datos, ClasificacionJson.class);
		String respuesta = gson.toJson(clard.eliminar(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getByIdGrupoCuenta(String datos) {
		Gson gson = new GsonBuilder().create();
		ClasificacionJson cj = gson.fromJson(datos, ClasificacionJson.class);
		List<ClasificacionJson> lcj = clard.getByIdGrupoCuenta(cj);
		String json = gson.toJson(lcj, new TypeToken<List<ClasificacionJson>>() {
		}.getType());
		return Response.ok(json).build();

	}

}
