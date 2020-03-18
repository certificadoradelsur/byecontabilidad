package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cl.certificadoradelsur.byecontabilidad.json.NoConciliadoCartolaJson;
import cl.certificadoradelsur.byecontabilidad.restdao.NoConciliadoCartolaRD;


/**
 * Clase que implementa la interfaz NoConciliadoCartolaSvc
 * 
 * @author juan
 *
 */
public class NoConciliadoCartolaSvcBean implements NoConciliadoCartolaSvc {
	@Inject
	private NoConciliadoCartolaRD nccrd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		NoConciliadoCartolaJson ncj = gson.fromJson(datos, NoConciliadoCartolaJson.class);
		String respuesta = gson.toJson(nccrd.save(ncj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response list(Integer inicio, Integer fin, String fechaInicial, String fechaFinal, Long idCuenta, Long idBanco) {
		Gson gson = new GsonBuilder().create();
		List<NoConciliadoCartolaJson> lncj = nccrd.getAll(inicio, fin, fechaInicial, fechaFinal,  idCuenta,idBanco);
		String json = "{\"records\": " + gson.toJson(lncj, new TypeToken<List<NoConciliadoCartolaJson>>() {
		}.getType()) + ", \"total\": " + nccrd.countAll(fechaInicial, fechaFinal, idCuenta, idBanco) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		NoConciliadoCartolaJson ncj = gson.fromJson(datos, NoConciliadoCartolaJson.class);
		String respuesta = gson.toJson(nccrd.update(ncj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		NoConciliadoCartolaJson ncj = gson.fromJson(datos, NoConciliadoCartolaJson.class);
		String respuesta = gson.toJson(nccrd.getById(ncj), new TypeToken<NoConciliadoCartolaJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		NoConciliadoCartolaJson ncj = gson.fromJson(datos, NoConciliadoCartolaJson.class);
		String respuesta = gson.toJson(nccrd.eliminar(ncj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public  Response listM(Integer inicio, Integer fin, Long monto, Long idNoConciliado) {
		Gson gson = new GsonBuilder().create();
		List<NoConciliadoCartolaJson> lncj = nccrd.getNoConciliadoCartolaMonto(inicio, fin, monto, idNoConciliado);
		String json = "{\"records\": " + gson.toJson(lncj, new TypeToken<List<NoConciliadoCartolaJson>>() {
		}.getType()) + ", \"total\": " + nccrd.countAllNCC(monto) + "}";
		return Response.ok(json).build();
	}
	
	@Override
	public  Response listD(Integer inicio, Integer fin, Long numDocumento, Long idNoConciliado) {
		Gson gson = new GsonBuilder().create();
		List<NoConciliadoCartolaJson> lncj = nccrd.getNoConciliadoCartolaDoc(inicio, fin, numDocumento, idNoConciliado);
		String json = "{\"records\": " + gson.toJson(lncj, new TypeToken<List<NoConciliadoCartolaJson>>() {
		}.getType()) + ", \"total\": " + nccrd.countAllNCCDoc(numDocumento) + "}";
		return Response.ok(json).build();
	}

}