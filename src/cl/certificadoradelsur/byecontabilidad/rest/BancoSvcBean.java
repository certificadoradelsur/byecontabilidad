package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.BancoJson;
import cl.certificadoradelsur.byecontabilidad.restdao.BancoRD;



/**
 * Clase que implementa la interfaz BancoSvc
 * 
 * @author juan
 *
 */
public class BancoSvcBean implements BancoSvc {
	@Inject
	private BancoRD brd;
	
	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		BancoJson uj = gson.fromJson(datos, BancoJson.class);
		String respuesta = gson.toJson(brd.save(uj), new TypeToken<String>() {}.getType());
		return Response.ok(respuesta).build();
			
	}

	@Override
	public Response list(Integer inicio, Integer fin , String nombre) {
		Gson gson = new GsonBuilder().create();
		List<BancoJson> lbj = brd.getAll(inicio, fin, nombre);
		String json = "{\"records\": " + gson.toJson(lbj, new TypeToken<List<BancoJson>>() {
		}.getType()) + ", \"total\": " + brd.countAll(nombre) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		BancoJson bj = gson.fromJson(datos, BancoJson.class);
		String respuesta = gson.toJson(brd.update(bj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		BancoJson bj = gson.fromJson(datos, BancoJson.class);
		String respuesta = gson.toJson(brd.getById(bj), new TypeToken<BancoJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		BancoJson bj = gson.fromJson(datos, BancoJson.class);
		String respuesta = gson.toJson(brd.eliminar(bj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	@Override
	public Response getLista(String datos) {
		Gson gson = new GsonBuilder().create();
		List<BancoJson> lbj = brd.getAllLista();
		String json = gson.toJson(lbj, new TypeToken<List<BancoJson>>() {}.getType());
		return Response.ok(json).build();
		
	}



}
