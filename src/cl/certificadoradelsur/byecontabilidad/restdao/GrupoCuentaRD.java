package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import cl.certificadoradelsur.byecontabilidad.dao.GrupoCuentaDAO;
import cl.certificadoradelsur.byecontabilidad.entities.GrupoCuenta;
import cl.certificadoradelsur.byecontabilidad.json.GrupoCuentaJson;


/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class GrupoCuentaRD {
	private static Logger log = Logger.getLogger(GrupoCuentaRD.class);
	@Inject
	private GrupoCuentaDAO grupodao;

	public List<GrupoCuentaJson> getAllLista() {

		List<GrupoCuentaJson> lgj = new ArrayList<>();
		try {
			List<GrupoCuenta> g = grupodao.getLista();
			for (int i = 0; i < g.size(); i++) {
				GrupoCuentaJson gj = new GrupoCuentaJson();
				gj.setId(g.get(i).getId());
				gj.setNombre(g.get(i).getNombre());
				gj.setNombreClaseCuenta(g.get(i).getClaseCuenta().getNombre());
				lgj.add(gj);
			}
			return lgj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de grupo cuenta ", e);
			return lgj;
		}

	}
	
	
	/**
	 * metodo que busca grupoCuenta segun el id de claseCuenta 
	 * @param idClaseCuenta
	 * @return lista grupo cuenta
	 */
	public List<GrupoCuentaJson> getByIdClaseCuenta(Long idClaseCuenta) {

		List<GrupoCuentaJson> lcj = new ArrayList<>();
		try {
			List<GrupoCuenta> b = grupodao.getByIdClaseCuenta(idClaseCuenta);
			for (int i = 0; i < b.size(); i++) {
				GrupoCuentaJson bjj = new GrupoCuentaJson();
				bjj.setId(b.get(i).getId());
				bjj.setNombre(b.get(i).getNombre());
				lcj.add(bjj);
			}
			return lcj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de cuenta ", e);
			return lcj;
		}

	}

}
