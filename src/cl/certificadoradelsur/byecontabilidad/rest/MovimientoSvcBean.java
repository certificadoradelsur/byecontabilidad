package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.MovimientoJson;
import cl.certificadoradelsur.byecontabilidad.json.TransaccionJson;
import cl.certificadoradelsur.byecontabilidad.restdao.MovimientoRD;


/**
 * Clase que implementa la interfaz MovimientoSvc
 * 
 * @author juan
 *
 */
public class MovimientoSvcBean implements MovimientoSvc {
	@Inject
	private MovimientoRD mrd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		MovimientoJson mj = gson.fromJson(datos, MovimientoJson.class);
		String respuestaRD = mrd.save(mj);
		String respuesta = gson.toJson(respuestaRD, new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		MovimientoJson tm = gson.fromJson(datos, MovimientoJson.class);
		String respuesta = gson.toJson(mrd.eliminar(tm), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		MovimientoJson mj = gson.fromJson(datos, MovimientoJson.class);
		String respuesta = gson.toJson(mrd.update(mj),
				new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		MovimientoJson mj = gson.fromJson(datos, MovimientoJson.class);
		String respuesta = gson.toJson(mrd.getById(mj), new TypeToken<MovimientoJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response listarMovimiento(String datos) {
		Gson gson = new GsonBuilder().create();
		TransaccionJson tj = gson.fromJson(datos, TransaccionJson.class);
		List<MovimientoJson> lmj = mrd.getAllMovimiento(tj);
		String json = gson.toJson(lmj, new TypeToken<List<MovimientoJson>>() {
		}.getType());
		return Response.ok(json).build();
	}

	@Override
	public Response list(Integer inicio, Integer fin) {
		Gson gson = new GsonBuilder().create();
		List<MovimientoJson> lmj = mrd.getAll(inicio, fin);
		String json = "{\"records\": " + gson.toJson(lmj, new TypeToken<List<MovimientoJson>>() {
		}.getType()) + ", \"total\": " + mrd.countAll() + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response listM(Integer inicio, Integer fin, Long id) {
		Gson gson = new GsonBuilder().create();
		List<MovimientoJson> lmj = mrd.getAllM(inicio, fin, id);
		String json = "{\"records\": " + gson.toJson(lmj, new TypeToken<List<MovimientoJson>>() {
		}.getType()) + ", \"total\": " + mrd.countAllM(id) + "}";
		return Response.ok(json).build();
	}
	
	@Override
	public Response getMovById(Integer inicio, Integer fin, Long id) {
		Gson gson = new GsonBuilder().create();
		List<MovimientoJson> lmj = mrd.getMovById(inicio, fin, id);
		String json = "{\"records\": " + gson.toJson(lmj, new TypeToken<List<MovimientoJson>>() {
		}.getType()) + ", \"total\": " + mrd.countAllM(id) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response listTotalMovimientos(String datos) {
		Gson gson = new GsonBuilder().create();
		MovimientoJson mj = gson.fromJson(datos, MovimientoJson.class);
		String respuesta = gson.toJson(mrd.getAllLista(mj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminarMovimientosComprobante(String datos) {
		Gson gson = new GsonBuilder().create();
		MovimientoJson mj = gson.fromJson(datos, MovimientoJson.class);
		String respuesta = gson.toJson(mrd.delete(mj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

}
