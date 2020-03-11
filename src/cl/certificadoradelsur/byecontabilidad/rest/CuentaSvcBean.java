package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.CuentaJson;
import cl.certificadoradelsur.byecontabilidad.restdao.CuentaRD;





/**
 * Clase que implementa la interfaz CuentaSvc
 * 
 * @author juan
 *
 */
public class CuentaSvcBean implements CuentaSvc {
	@Inject
	private CuentaRD crd;
	
	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaJson cj = gson.fromJson(datos, CuentaJson.class);
		String respuesta = gson.toJson(crd.save(cj), new TypeToken<String>() {}.getType());
		return Response.ok(respuesta).build();
			
	}

	@Override
	public Response list(Integer inicio, Integer fin , String numCuenta) {
		Gson gson = new GsonBuilder().create();
		List<CuentaJson> lbj = crd.getAll(inicio, fin, numCuenta);
		String json = "{\"records\": " + gson.toJson(lbj, new TypeToken<List<CuentaJson>>() {
		}.getType()) + ", \"total\": " + crd.countAll(numCuenta) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaJson cj = gson.fromJson(datos, CuentaJson.class);
		String respuesta = gson.toJson(crd.update(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaJson cj = gson.fromJson(datos, CuentaJson.class);
		String respuesta = gson.toJson(crd.getById(cj), new TypeToken<CuentaJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaJson cj = gson.fromJson(datos, CuentaJson.class);
		String respuesta = gson.toJson(crd.eliminar(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	@Override
	public Response getByIdBanco(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaJson cj = gson.fromJson(datos, CuentaJson.class);
		List<CuentaJson> lcj = crd.getByIdBanco(cj.getIdBanco());
		String json = gson.toJson(lcj, new TypeToken<List<CuentaJson>>() {}.getType());
		return Response.ok(json).build();
		
	}



}
