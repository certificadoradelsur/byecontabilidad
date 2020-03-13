package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.ClienteJson;
import cl.certificadoradelsur.byecontabilidad.restdao.ClienteRD;





/**
 * Clase que implementa la interfaz BancoSvc
 * 
 * @author juan
 *
 */
public class ClienteSvcBean implements BancoSvc {
	@Inject
	private ClienteRD crd;
	
	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		ClienteJson cj = gson.fromJson(datos, ClienteJson.class);
		String respuesta = gson.toJson(crd.save(cj), new TypeToken<String>() {}.getType());
		return Response.ok(respuesta).build();
			
	}

	@Override
	public Response list(Integer inicio, Integer fin , String nombre) {
		Gson gson = new GsonBuilder().create();
		List<ClienteJson> lcj = crd.getAll(inicio, fin);
		String json = "{\"records\": " + gson.toJson(lcj, new TypeToken<List<ClienteJson>>() {
		}.getType()) + ", \"total\": " + crd.countAll() + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		ClienteJson cj = gson.fromJson(datos, ClienteJson.class);
		String respuesta = gson.toJson(crd.update(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		ClienteJson cj = gson.fromJson(datos, ClienteJson.class);
		String respuesta = gson.toJson(crd.getById(cj), new TypeToken<ClienteJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		ClienteJson cj = gson.fromJson(datos, ClienteJson.class);
		String respuesta = gson.toJson(crd.eliminar(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	@Override
	public Response getLista(String datos) {
		Gson gson = new GsonBuilder().create();
		List<ClienteJson> lcj = crd.getAllLista();
		String json = gson.toJson(lcj, new TypeToken<List<ClienteJson>>() {}.getType());
		return Response.ok(json).build();
		
	}



}
