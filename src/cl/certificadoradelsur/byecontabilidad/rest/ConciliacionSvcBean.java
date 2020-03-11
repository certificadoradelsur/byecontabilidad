package cl.certificadoradelsur.byecontabilidad.rest;


import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.ConciliacionJson;
import cl.certificadoradelsur.byecontabilidad.restdao.ConciliacionRD;



/**
 * Clase que implementa la interfaz ConciliacionSvc
 * 
 * @author juan
 *
 */
public class ConciliacionSvcBean implements ConciliacionSvc {
	@Inject
	private ConciliacionRD crd;
	
	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		ConciliacionJson cj = gson.fromJson(datos, ConciliacionJson.class);
		String respuesta = gson.toJson(crd.save(cj), new TypeToken<String>() {}.getType());
		return Response.ok(respuesta).build();		
	}

	@Override
	public Response list(Integer inicio, Integer fin, String fechaInicial, String fechaFinal, Long idCuenta, Long idBanco) {
		Gson gson = new GsonBuilder().create();
		List<ConciliacionJson> lbj = crd.getAll(inicio, fin, fechaInicial, fechaFinal,  idCuenta,idBanco);
		String json = "{\"records\": " + gson.toJson(lbj, new TypeToken<List<ConciliacionJson>>() {
		}.getType()) + ", \"total\": " + crd.countAll(fechaInicial, fechaFinal, idCuenta, idBanco) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		ConciliacionJson cj = gson.fromJson(datos, ConciliacionJson.class);
		String respuesta = gson.toJson(crd.update(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		ConciliacionJson bj = gson.fromJson(datos, ConciliacionJson.class);
		String respuesta = gson.toJson(crd.getById(bj), new TypeToken<ConciliacionJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		ConciliacionJson cj = gson.fromJson(datos, ConciliacionJson.class);
		String respuesta = gson.toJson(crd.eliminar(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}



}
