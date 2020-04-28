package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.CuentaContableJson;
import cl.certificadoradelsur.byecontabilidad.restdao.CuentaContableRD;

/**
 * Clase que implementa la interfaz CuentaContableSvc
 * 
 * @author juan
 *
 */
public class CuentaContableSvcBean implements CuentaContableSvc {
	@Inject
	private CuentaContableRD cuentard;

	@Override
	public Response add(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaContableJson ccj = gson.fromJson(datos, CuentaContableJson.class);
		String respuesta = gson.toJson(cuentard.save(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();

	}

	@Override
	public Response list(Integer inicio, Integer fin, String glosaGeneral, Long idClaseCuenta, Long idGrupoCuenta,
			String idUsuario, Long idEmpresa) {
		Gson gson = new GsonBuilder().create();
		List<CuentaContableJson> lccj = cuentard.getAll(inicio, fin, glosaGeneral, idClaseCuenta, idGrupoCuenta,
				idUsuario, idEmpresa);
		String json = "{\"records\": " + gson.toJson(lccj, new TypeToken<List<CuentaContableJson>>() {
		}.getType()) + ", \"total\": "
				+ cuentard.countAll(glosaGeneral, idClaseCuenta, idGrupoCuenta, idUsuario, idEmpresa) + "}";
		return Response.ok(json).build();
	}

	@Override
	public Response update(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaContableJson ccj = gson.fromJson(datos, CuentaContableJson.class);
		String respuesta = gson.toJson(cuentard.update(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getById(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaContableJson ccj = gson.fromJson(datos, CuentaContableJson.class);
		String respuesta = gson.toJson(cuentard.getById(ccj), new TypeToken<CuentaContableJson>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response eliminar(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaContableJson ccj = gson.fromJson(datos, CuentaContableJson.class);
		String respuesta = gson.toJson(cuentard.eliminar(ccj), new TypeToken<String>() {
		}.getType());
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getLista(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaContableJson ccj = gson.fromJson(datos, CuentaContableJson.class);
		List<CuentaContableJson> lcj = cuentard.getAllLista(ccj);
		String json = gson.toJson(lcj, new TypeToken<List<CuentaContableJson>>() {
		}.getType());
		return Response.ok(json).build();
	}

	@Override
	public Response getMaxCodigo() {
		Long respuesta = cuentard.getMaxCodigo();
		return Response.ok(respuesta).build();
	}

	@Override
	public Response getByIdEmpresa(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaContableJson cj = gson.fromJson(datos, CuentaContableJson.class);
		List<CuentaContableJson> lcj = cuentard.getByIdEmpresa(cj);
		String json = gson.toJson(lcj, new TypeToken<List<CuentaContableJson>>() {
		}.getType());
		return Response.ok(json).build();

	}
	
	@Override
	public Response getByIdEmpresaList(String datos) {
		Gson gson = new GsonBuilder().create();
		CuentaContableJson cj = gson.fromJson(datos, CuentaContableJson.class);
		List<CuentaContableJson> lcj = cuentard.getByIdEmpresaList(cj);
		String json = gson.toJson(lcj, new TypeToken<List<CuentaContableJson>>() {
		}.getType());
		return Response.ok(json).build();

	}

}
