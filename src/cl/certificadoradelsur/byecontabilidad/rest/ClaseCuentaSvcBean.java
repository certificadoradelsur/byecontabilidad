package cl.certificadoradelsur.byecontabilidad.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cl.certificadoradelsur.byecontabilidad.json.ClaseCuentaJson;
import cl.certificadoradelsur.byecontabilidad.restdao.ClaseCuentaRD;

/**
 * Clase que implementa la interfaz ClaseCuentaSvc
 * 
 * @author juan
 *
 */
public class ClaseCuentaSvcBean implements ClaseCuentaSvc {
	
	@Inject
	private ClaseCuentaRD claserd;
	
	@Override
	public Response getLista() {
		Gson gson = new GsonBuilder().create();
		List<ClaseCuentaJson> lbj = claserd.getAllLista();
		String json = gson.toJson(lbj, new TypeToken<List<ClaseCuentaJson>>() {}.getType());
		return Response.ok(json).build();
	}
}
