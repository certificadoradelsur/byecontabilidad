package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.SucursalJson;
import cl.certificadoradelsur.byecontabilidad.restdao.SucursalRD;

/**
 * Clase que implementa la interfaz ClasificacionSvc
 * 
 * @author juan
 *
 */
public class SucursalSvcBean implements SucursalSvc {
	@Inject
	private SucursalRD sucrd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		SucursalJson ccj = gson.fromJson(datos, SucursalJson.class);
		String respuesta = gson.toJson(sucrd.save(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response list(Integer inicio, Integer fin, Long idEmpresa, String idUsuario) {
		Gson gson = new GsonBuilder().create();
		List<SucursalJson> lccj = sucrd.getAll(inicio, fin, idEmpresa, idUsuario);
		String json = "{\"records\": " + gson.toJson(lccj, new TypeToken<List<SucursalJson>>() {
		}.getType()) + ", \"total\": " + sucrd.countAll(idEmpresa,idUsuario) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		SucursalJson ccj = gson.fromJson(datos, SucursalJson.class);
		String respuesta = gson.toJson(sucrd.update(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		SucursalJson ccj = gson.fromJson(datos, SucursalJson.class);
		String respuesta = gson.toJson(sucrd.getById(ccj), new TypeToken<SucursalJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		SucursalJson ccj = gson.fromJson(datos, SucursalJson.class);
		String respuesta = gson.toJson(sucrd.eliminar(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	

	@Override
	public Response getByIdEmpresa(String datos) {
		Gson gson = new GsonBuilder().create();
		SucursalJson cj = gson.fromJson(datos, SucursalJson.class);
		List<SucursalJson> lcj = sucrd.getByIdEmpresa(cj);
		String json = gson.toJson(lcj, new TypeToken<List<SucursalJson>>() {}.getType());
		return Response.ok(json).build();
		
	}

}
