package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.GrupoCuentaJson;
import cl.certificadoradelsur.byecontabilidad.restdao.GrupoCuentaRD;



/**
 * Clase que implementa la interfaz GrupoCuentaSvc
 * 
 * @author juan
 *
 */
public class GrupoCuentaSvcBean implements GrupoCuentaSvc {
	
	@Inject
	private GrupoCuentaRD grupord;
	
	@Override
	public Response getLista() {
		Gson gson = new GsonBuilder().create();
		List<GrupoCuentaJson> lbj = grupord.getAllLista();
		String json = gson.toJson(lbj, new TypeToken<List<GrupoCuentaJson>>() {}.getType());
		return Response.ok(json).build();
	}

	
	@Override
	public Response getByIdClaseCuenta(String datos) {
		Gson gson = new GsonBuilder().create();
		GrupoCuentaJson cj = gson.fromJson(datos, GrupoCuentaJson.class);
		List<GrupoCuentaJson> lcj = grupord.getByIdClaseCuenta(cj.getIdClaseCuenta());
		String json = gson.toJson(lcj, new TypeToken<List<GrupoCuentaJson>>() {}.getType());
		return Response.ok(json).build();
		
	}
}
