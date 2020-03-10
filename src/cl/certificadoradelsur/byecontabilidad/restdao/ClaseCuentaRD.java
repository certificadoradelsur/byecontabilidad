package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.ClaseCuentaDAO;
import cl.certificadoradelsur.byecontabilidad.entities.ClaseCuenta;
import cl.certificadoradelsur.byecontabilidad.json.ClaseCuentaJson;


/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class ClaseCuentaRD {
	private static Logger log = Logger.getLogger(ClaseCuentaRD.class);
	@Inject
	private ClaseCuentaDAO clasedao;

	
	/*
	 * funcion que trae todas las clasecuenta para llenar select
	 * 
	 */
	public List<ClaseCuentaJson> getAllLista() {

		List<ClaseCuentaJson> lgj = new ArrayList<>();
		try {
			List<ClaseCuenta> g = clasedao.getLista();
			for (int i = 0; i < g.size(); i++) {
				ClaseCuentaJson gj = new ClaseCuentaJson();
				gj.setId(g.get(i).getId());
				gj.setNombre(g.get(i).getNombre());
				lgj.add(gj);
			}
			return lgj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de clase cuenta ", e);
			return lgj;
		}

	}

}
