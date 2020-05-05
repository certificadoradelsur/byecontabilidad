package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cl.certificadoradelsur.byecontabilidad.json.VentaJson;
import cl.certificadoradelsur.byecontabilidad.restdao.VentaRD;


/**
 * Clase que implementa la interfaz VentaSvc
 * 
 * @author juan
 *
 */
public class VentaSvcBean implements VentaSvc {
	@Inject
	private VentaRD vrd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		VentaJson cj = gson.fromJson(datos, VentaJson.class);
		String respuesta = gson.toJson(vrd.save(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();

	}

	@Override
	public Response list(Integer inicio, Integer fin) {
		Gson gson = new GsonBuilder().create();
		List<VentaJson> lcj = vrd.getAll(inicio, fin);
		String json = "{\"records\": " + gson.toJson(lcj, new TypeToken<List<VentaJson>>() {
		}.getType()) + ", \"total\": " + vrd.countAll() + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		VentaJson cj = gson.fromJson(datos, VentaJson.class);
		String respuesta = gson.toJson(vrd.update(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		VentaJson cj = gson.fromJson(datos, VentaJson.class);
		String respuesta = gson.toJson(vrd.getById(cj), new TypeToken<VentaJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		VentaJson cj = gson.fromJson(datos, VentaJson.class);
		String respuesta = gson.toJson(vrd.eliminar(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

}
