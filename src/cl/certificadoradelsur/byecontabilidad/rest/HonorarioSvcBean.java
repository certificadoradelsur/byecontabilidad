package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cl.certificadoradelsur.byecontabilidad.json.HonorarioJson;
import cl.certificadoradelsur.byecontabilidad.restdao.HonorarioRD;

/**
 * Clase que implementa la interfaz HonorarioSvc
 * 
 * @author juan
 *
 */
public class HonorarioSvcBean implements HonorarioSvc {
	@Inject
	private HonorarioRD hrd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		HonorarioJson cj = gson.fromJson(datos, HonorarioJson.class);
		String respuesta = gson.toJson(hrd.save(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();

	}

	@Override
	public Response list(Integer inicio, Integer fin) {
		Gson gson = new GsonBuilder().create();
		List<HonorarioJson> lcj = hrd.getAll(inicio, fin);
		String json = "{\"records\": " + gson.toJson(lcj, new TypeToken<List<HonorarioJson>>() {
		}.getType()) + ", \"total\": " + hrd.countAll() + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		HonorarioJson cj = gson.fromJson(datos, HonorarioJson.class);
		String respuesta = gson.toJson(hrd.update(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		HonorarioJson cj = gson.fromJson(datos, HonorarioJson.class);
		String respuesta = gson.toJson(hrd.getById(cj), new TypeToken<HonorarioJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		HonorarioJson cj = gson.fromJson(datos, HonorarioJson.class);
		String respuesta = gson.toJson(hrd.eliminar(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

}
