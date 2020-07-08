package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.PeriodoJson;
import cl.certificadoradelsur.byecontabilidad.restdao.PeriodoRD;
/**
 * 
 * @author ernesto
 *
 */

public class PeriodoSvcBean implements PeriodoSvc{
	@Inject
	private PeriodoRD prd;
	
	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		PeriodoJson uj = gson.fromJson(datos, PeriodoJson.class);
		String respuesta = gson.toJson(prd.save(uj), new TypeToken<String>() {}.getType());
		return Response.ok(respuesta).build();
			
	}
	
	@Override
	public Response periodoAnio(String datos) {
		Gson gson = new GsonBuilder().create();
		PeriodoJson uj = gson.fromJson(datos, PeriodoJson.class);
		String respuesta = gson.toJson(prd.periodoAnio(uj), new TypeToken<String>() {}.getType());
		return Response.ok(respuesta).build();
			
	}
	
	@Override
	public Response getByPeriodoAnio(String datos) {
		Gson gson = new GsonBuilder().create();
		PeriodoJson pjs = gson.fromJson(datos, PeriodoJson.class);
		String json = gson.toJson(prd.getByAnio(pjs), new TypeToken<List<PeriodoJson>>() {}.getType());
		return Response.ok(json).build();
			
	}

	
	@Override
	public Response list(Integer inicio, Integer fin,Long anio,Long idEmpresa) {
		Gson gson = new GsonBuilder().create();
		List<PeriodoJson> luj = prd.getAll(inicio, fin,anio,idEmpresa);
		String json = "{\"records\": " + gson.toJson(luj, new TypeToken<List<PeriodoJson>>() {
		}.getType()) + ", \"total\": " + prd.countAll(anio,idEmpresa) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		PeriodoJson uj = gson.fromJson(datos, PeriodoJson.class);
		String respuesta = gson.toJson(prd.update(uj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	@Override
	public Response updateEstado(String datos) {
		Gson gson = new GsonBuilder().create();
		PeriodoJson uj = gson.fromJson(datos, PeriodoJson.class);
		String respuesta = gson.toJson(prd.updateEstado(uj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		PeriodoJson uj = gson.fromJson(datos, PeriodoJson.class);
		String respuesta = gson.toJson(prd.getById(uj), new TypeToken<PeriodoJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		PeriodoJson uj = gson.fromJson(datos, PeriodoJson.class);
		String respuesta = gson.toJson(prd.eliminar(uj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

}
