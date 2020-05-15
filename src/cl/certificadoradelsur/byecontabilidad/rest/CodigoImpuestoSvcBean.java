package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.CodigoImpuestoJson;
import cl.certificadoradelsur.byecontabilidad.restdao.CodigoImpuestoRD;

/**
 * Clase que implementa la interfaz BancoSvc
 * 
 * @author juan
 *
 */
public class CodigoImpuestoSvcBean implements CodigoImpuestoSvc {
	@Inject
	private CodigoImpuestoRD cird;

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		CodigoImpuestoJson bj = gson.fromJson(datos, CodigoImpuestoJson.class);
		String respuesta = gson.toJson(cird.getById(bj), new TypeToken<CodigoImpuestoJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getLista(String datos) {
		Gson gson = new GsonBuilder().create();
		List<CodigoImpuestoJson> lbj = cird.getAllLista();
		String json = gson.toJson(lbj, new TypeToken<List<CodigoImpuestoJson>>() {
		}.getType());
		return Response.ok(json).build();
	}

}
