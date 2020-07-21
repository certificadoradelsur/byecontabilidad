package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.CentroResultadoJson;
import cl.certificadoradelsur.byecontabilidad.restdao.CentroResultadoRD;

public class CentroResultadoSvcBean implements CentroResultadoSvc {
	
	@Inject
	private CentroResultadoRD crd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		CentroResultadoJson cj = gson.fromJson(datos, CentroResultadoJson.class);
		String respuesta = gson.toJson(crd.save(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		CentroResultadoJson cj = gson.fromJson(datos, CentroResultadoJson.class);
		String respuesta = gson.toJson(crd.update(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	@Override
	public Response list(Integer inicio, Integer fin,Long idSucursal) {
		Gson gson = new GsonBuilder().create();
		List<CentroResultadoJson> luj = crd.getAll(inicio, fin,idSucursal);
		String json = "{\"records\": " + gson.toJson(luj, new TypeToken<List<CentroResultadoJson>>() {
		}.getType()) + ", \"total\": " + crd.countAll(idSucursal) + "}";
		return Response.ok(json).build();
	}
	
	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		CentroResultadoJson uj = gson.fromJson(datos, CentroResultadoJson.class);
		String respuesta = gson.toJson(crd.getById(uj), new TypeToken<CentroResultadoJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		CentroResultadoJson uj = gson.fromJson(datos, CentroResultadoJson.class);
		String respuesta = gson.toJson(crd.eliminar(uj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}


}
