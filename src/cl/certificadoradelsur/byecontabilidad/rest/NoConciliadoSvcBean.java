package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.NoConciliadoJson;
import cl.certificadoradelsur.byecontabilidad.restdao.NoConciliadoRD;


/**
 * Clase que implementa la interfaz NoConciliadoSvc
 * 
 * @author juan
 *
 */
public class NoConciliadoSvcBean implements NoConciliadoSvc {
	@Inject
	private NoConciliadoRD ncrd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		NoConciliadoJson ncj = gson.fromJson(datos, NoConciliadoJson.class);
		String respuesta = gson.toJson(ncrd.save(ncj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response list(Integer inicio, Integer fin, String fechaInicial, String fechaFinal, Long idCuenta,
			Long idBanco, String idUsuario, Long idEmpresa) {
		Gson gson = new GsonBuilder().create();
		List<NoConciliadoJson> lncj = ncrd.getAll(inicio, fin, fechaInicial, fechaFinal, idCuenta, idBanco, idUsuario, idEmpresa);
		String json = "{\"records\": " + gson.toJson(lncj, new TypeToken<List<NoConciliadoJson>>() {
		}.getType()) + ", \"total\": " + ncrd.countAll(fechaInicial, fechaFinal, idCuenta, idBanco, idUsuario, idEmpresa) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response listM(Integer inicio, Integer fin, Long monto, Long idNoConciliadoCartola) {
		Gson gson = new GsonBuilder().create();
		List<NoConciliadoJson> lncj = ncrd.getNoConciliadoMonto(inicio, fin, monto, idNoConciliadoCartola);
		String json = "{\"records\": " + gson.toJson(lncj, new TypeToken<List<NoConciliadoJson>>() {
		}.getType()) + ", \"total\": " + ncrd.countAllMNC(monto) + "}";
		return Response.ok(json).build();
	}


	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		NoConciliadoJson ncj = gson.fromJson(datos, NoConciliadoJson.class);
		String respuesta = gson.toJson(ncrd.update(ncj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		NoConciliadoJson ncj = gson.fromJson(datos, NoConciliadoJson.class);
		String respuesta = gson.toJson(ncrd.getById(ncj), new TypeToken<NoConciliadoJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		NoConciliadoJson ncj = gson.fromJson(datos, NoConciliadoJson.class);
		String respuesta = gson.toJson(ncrd.eliminar(ncj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

}
