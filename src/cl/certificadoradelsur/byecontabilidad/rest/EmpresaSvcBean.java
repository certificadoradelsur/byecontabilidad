package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cl.certificadoradelsur.byecontabilidad.json.EmpresaJson;
import cl.certificadoradelsur.byecontabilidad.restdao.EmpresaRD;


/**
 * Clase que implementa la interfaz EmpresaSvc
 * 
 * @author juan
 *
 */
public class EmpresaSvcBean implements EmpresaSvc {
	@Inject
	private EmpresaRD erd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		EmpresaJson ccj = gson.fromJson(datos, EmpresaJson.class);
		String respuesta = gson.toJson(erd.save(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response list(Integer inicio, Integer fin,String razonSocial, String idUsuario) {
		Gson gson = new GsonBuilder().create();
		List<EmpresaJson> lccj = erd.getAll(inicio, fin,razonSocial, idUsuario);
		String json = "{\"records\": " + gson.toJson(lccj, new TypeToken<List<EmpresaJson>>() {
		}.getType()) + ", \"total\": " + erd.countAll(razonSocial, idUsuario) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		EmpresaJson ccj = gson.fromJson(datos, EmpresaJson.class);
		String respuesta = gson.toJson(erd.update(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		EmpresaJson ccj = gson.fromJson(datos, EmpresaJson.class);
		String respuesta = gson.toJson(erd.getById(ccj), new TypeToken<EmpresaJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		EmpresaJson ccj = gson.fromJson(datos, EmpresaJson.class);
		String respuesta = gson.toJson(erd.eliminar(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}
	
	@Override
	public Response getLista(String datos) {
		Gson gson = new GsonBuilder().create();
		EmpresaJson ccj = gson.fromJson(datos, EmpresaJson.class);
		List<EmpresaJson> lbj = erd.getAllLista(ccj);
		String json = gson.toJson(lbj, new TypeToken<List<EmpresaJson>>() {}.getType());
		return Response.ok(json).build();
	}
	

}
