package cl.certificadoradelsur.byecontabilidad.rest;

import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.CartolaJson;
import cl.certificadoradelsur.byecontabilidad.json.ResultadoImportacionJson;
import cl.certificadoradelsur.byecontabilidad.restdao.CartolaRD;


/**
 * Clase que implementa la interfaz CartolaSvc
 * 
 * @author juan
 *
 */
public class CartolaSvcBean implements CartolaSvc {
	@Inject
	private CartolaRD crd;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		CartolaJson cj = gson.fromJson(datos, CartolaJson.class);
		String respuesta = gson.toJson(crd.save(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response list(Integer inicio, Integer fin, String fechaInicial, String fechaFinal, Long idCuenta,
			Long idBanco) {
		Gson gson = new GsonBuilder().create();
		List<CartolaJson> lcj = crd.getAll(inicio, fin, fechaInicial, fechaFinal, idCuenta, idBanco);
		String json = "{\"records\": " + gson.toJson(lcj, new TypeToken<List<CartolaJson>>() {
		}.getType()) + ", \"total\": " + crd.countAll(fechaInicial, fechaFinal, idCuenta, idBanco) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		CartolaJson cj = gson.fromJson(datos, CartolaJson.class);
		String respuesta = gson.toJson(crd.update(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		CartolaJson cj = gson.fromJson(datos, CartolaJson.class);
		String respuesta = gson.toJson(crd.eliminar(cj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response importar(MultipartFormDataInput input, Long banco, Long cuenta, String numCartola, String anio) {
		Gson gson = new GsonBuilder().create();
		if (banco == 1) {
			List<ResultadoImportacionJson> resultado = crd.leerEstado(input, banco, cuenta, numCartola, anio);
			String json = gson.toJson(resultado, new TypeToken<List<ResultadoImportacionJson>>() {
			}.getType());
			return Response.ok(json).build();
		} else if (banco == 2) {
			List<ResultadoImportacionJson> resultado = crd.leerChile(input, banco, cuenta, numCartola, anio);
			String json = gson.toJson(resultado, new TypeToken<List<ResultadoImportacionJson>>() {
			}.getType());
			return Response.ok(json).build();
		} else if (banco == 3) {
			List<ResultadoImportacionJson> resultado = crd.leerSantander(input, banco, cuenta, numCartola, anio);
			String json = gson.toJson(resultado, new TypeToken<List<ResultadoImportacionJson>>() {
			}.getType());
			return Response.ok(json).build();
		}

		return Response.ok("La importación no esta disponible para el banco seleccionado").build();

	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		CartolaJson cj = gson.fromJson(datos, CartolaJson.class);
		String respuesta = gson.toJson(crd.getById(cj), new TypeToken<CartolaJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getByNumDocumento(String datos) {
		Gson gson = new GsonBuilder().create();
		CartolaJson cj = gson.fromJson(datos, CartolaJson.class);
		String respuesta = gson.toJson(crd.getByNumDocumento(cj), new TypeToken<CartolaJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response listTotalCartolas(Timestamp fechaI, Timestamp fechaF) {
		return null;
	}

}
