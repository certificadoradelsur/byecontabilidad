package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.TransaccionJson;
import cl.certificadoradelsur.byecontabilidad.restdao.TransaccionRD;




/**
 * Clase que implementa la interfaz TransaccionSvc
 * 
 * @author juan
 *
 */
public class TransaccionSvcBean implements TransaccionSvc {
	@Inject
	private TransaccionRD trd;
	
	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		TransaccionJson tj = gson.fromJson(datos, TransaccionJson.class);
		String respuestaRD = trd.save(tj);
		String respuesta = gson.toJson(respuestaRD, new TypeToken<String>() {}.getType());
		return Response.ok(respuesta).build();
		}

	@Override
	public Response list(Integer inicio, Integer fin, String glosaTransaccion) {
		Gson gson = new GsonBuilder().create();
		List<TransaccionJson> ltj = trd.getAll(inicio, fin, glosaTransaccion);
		String json = "{\"records\": " + gson.toJson(ltj, new TypeToken<List<TransaccionJson>>() {
		}.getType()) + ", \"total\": " + trd.countAll(glosaTransaccion) + "}";
		return Response.ok(json).build();
	}


	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		TransaccionJson tj = gson.fromJson(datos, TransaccionJson.class);
		String respuesta = gson.toJson(trd.getById(tj), new TypeToken<TransaccionJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		TransaccionJson tj = gson.fromJson(datos, TransaccionJson.class);
		String respuesta = gson.toJson(trd.eliminar(tj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getByT() {
		Long respuesta = trd.getByT();
		return Response.ok(respuesta).build();
	}



}
