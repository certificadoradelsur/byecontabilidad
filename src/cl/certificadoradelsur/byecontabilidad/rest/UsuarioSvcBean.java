package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.UsuarioJson;
import cl.certificadoradelsur.byecontabilidad.restdao.UsuarioRD;


/**
 * Clase que implementa la interfaz UsuarioSvc
 * 
 * @author juan
 *
 */
public class UsuarioSvcBean implements UsuarioSvc {
	@Inject
	private UsuarioRD urd;
	
	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		UsuarioJson uj = gson.fromJson(datos, UsuarioJson.class);
		String respuesta = gson.toJson(urd.save(uj), new TypeToken<String>() {}.getType());
		return Response.ok(respuesta).build();
			
	}

	@Override
	public Response list(Integer inicio, Integer fin, String id,String idUsuario) {
		Gson gson = new GsonBuilder().create();
		List<UsuarioJson> luj = urd.getAll(inicio, fin, id,idUsuario);
		String json = "{\"records\": " + gson.toJson(luj, new TypeToken<List<UsuarioJson>>() {
		}.getType()) + ", \"total\": " + urd.countAll(id,idUsuario) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		UsuarioJson uj = gson.fromJson(datos, UsuarioJson.class);
		String respuesta = gson.toJson(urd.update(uj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	@Override
	public Response updatePass(String datos) {
		Gson gson = new GsonBuilder().create();
		UsuarioJson uj = gson.fromJson(datos, UsuarioJson.class);
		String respuesta = gson.toJson(urd.updatePass(uj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		UsuarioJson uj = gson.fromJson(datos, UsuarioJson.class);
		String respuesta = gson.toJson(urd.getById(uj), new TypeToken<UsuarioJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		UsuarioJson uj = gson.fromJson(datos, UsuarioJson.class);
		String respuesta = gson.toJson(urd.eliminar(uj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}


}
