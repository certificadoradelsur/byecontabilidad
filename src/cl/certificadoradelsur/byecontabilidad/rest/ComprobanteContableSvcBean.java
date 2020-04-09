package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cl.certificadoradelsur.byecontabilidad.json.ComprobanteContableJson;
import cl.certificadoradelsur.byecontabilidad.restdao.ComprobanteContableRD;


/**
 * Clase que implementa la interfaz CuentaContableSvc
 * 
 * @author juan
 *
 */
public class ComprobanteContableSvcBean implements ComprobanteContableSvc {
	@Inject
	private ComprobanteContableRD comrd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		ComprobanteContableJson ccj = gson.fromJson(datos, ComprobanteContableJson.class);
		String respuesta = gson.toJson(comrd.save(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();

	}

	@Override
	public Response list(Integer inicio, Integer fin,String glosaGeneral, String idUsuario) {
		Gson gson = new GsonBuilder().create();
		List<ComprobanteContableJson> lccj = comrd.getAll(inicio, fin,glosaGeneral, idUsuario);
		String json = "{\"records\": " + gson.toJson(lccj, new TypeToken<List<ComprobanteContableJson>>() {
		}.getType()) + ", \"total\": " + comrd.countAll(glosaGeneral , idUsuario) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		ComprobanteContableJson ccj = gson.fromJson(datos, ComprobanteContableJson.class);
		String respuesta = gson.toJson(comrd.update(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	@Override
	public Response modificar(String datos) {
		Gson gson = new GsonBuilder().create();
		ComprobanteContableJson ccj = gson.fromJson(datos, ComprobanteContableJson.class);
		String respuesta = gson.toJson(comrd.modificar(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		ComprobanteContableJson ccj = gson.fromJson(datos, ComprobanteContableJson.class);
		String respuesta = gson.toJson(comrd.getById(ccj), new TypeToken<ComprobanteContableJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		ComprobanteContableJson ccj = gson.fromJson(datos, ComprobanteContableJson.class);
		String respuesta = gson.toJson(comrd.eliminar(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	

	@Override
	public Response getMaxNumero() {
		Long respuesta = comrd.getMaxNumero();
		return Response.ok(respuesta).build();
	}

}
