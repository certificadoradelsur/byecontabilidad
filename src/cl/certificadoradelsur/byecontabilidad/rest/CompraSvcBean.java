package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cl.certificadoradelsur.byecontabilidad.json.CompraJson;
import cl.certificadoradelsur.byecontabilidad.restdao.CompraRD;

/**
 * Clase que implementa la interfaz CompraSvc
 * 
 * @author juan
 *
 */
public class CompraSvcBean implements CompraSvc {
	@Inject
	private CompraRD crd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		CompraJson cj = gson.fromJson(datos, CompraJson.class);
		String respuesta = gson.toJson(crd.save(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();

	}

	@Override
	public Response list(Integer inicio, Integer fin,String fechaDesde, String fechaHasta,
			String idUsuario, Long idEmpresa) {
		Gson gson = new GsonBuilder().create();
		List<CompraJson> lcj = crd.getAll(inicio, fin, fechaDesde, fechaHasta, idUsuario, idEmpresa);
		String json = "{\"records\": " + gson.toJson(lcj, new TypeToken<List<CompraJson>>() {
		}.getType()) + ", \"total\": " + crd.countAll(fechaDesde, fechaHasta, idUsuario, idEmpresa) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		CompraJson cj = gson.fromJson(datos, CompraJson.class);
		String respuesta = gson.toJson(crd.update(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		CompraJson cj = gson.fromJson(datos, CompraJson.class);
		String respuesta = gson.toJson(crd.getById(cj), new TypeToken<CompraJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		CompraJson cj = gson.fromJson(datos, CompraJson.class);
		String respuesta = gson.toJson(crd.eliminar(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

}
