package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.CodigoImpuestoDAO;
import cl.certificadoradelsur.byecontabilidad.entities.CodigoImpuesto;
import cl.certificadoradelsur.byecontabilidad.json.CodigoImpuestoJson;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class CodigoImpuestoRD {
	private static Logger log = Logger.getLogger(CodigoImpuestoRD.class);
	@Inject
	private CodigoImpuestoDAO cidao;

	/**
	 * metodo obtener un CodigoImpuesto
	 * 
	 * @param id de CodigoImpuesto
	 * @return mensaje de exito o error
	 */
	public CodigoImpuestoJson getById(CodigoImpuestoJson bj) {
		CodigoImpuesto ci = cidao.getById(bj.getId());
		CodigoImpuestoJson cJson = new CodigoImpuestoJson();
		cJson.setId(ci.getId());
		cJson.setCodigo(ci.getCodigo());
		cJson.setDescripcion(ci.getDescripcion());
		return cJson;
	}

	/**
	 * Metodo para traer una lista de CodigoImpuesto (select)
	 * 
	 * @return
	 */
	public List<CodigoImpuestoJson> getAllLista() {

		List<CodigoImpuestoJson> lcj = new ArrayList<>();
		try {
			List<CodigoImpuesto> c = cidao.getLista();
			for (int i = 0; i < c.size(); i++) {
				CodigoImpuestoJson cij = new CodigoImpuestoJson();
				cij.setId(c.get(i).getId());
				cij.setCodigo(c.get(i).getCodigo());
				cij.setDescripcion(c.get(i).getDescripcion());
				lcj.add(cij);
			}
			return lcj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de Codigo Impuesto ", e);
			return lcj;
		}

	}
}
