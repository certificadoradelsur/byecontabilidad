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

		List<ClaseCuentaJson> lcj = new ArrayList<>();
		try {
			List<ClaseCuenta> c = clasedao.getLista();
			for (int i = 0; i < c.size(); i++) {
				ClaseCuentaJson cj = new ClaseCuentaJson();
				cj.setId(c.get(i).getId());
				cj.setNombre(c.get(i).getNombre());
				lcj.add(cj);
			}
			return lcj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de clase cuenta ", e);
			return lcj;
		}

	}

}
