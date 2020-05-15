package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.CodigoImpuesto;



/**
 * implementacion de patron dao para CodigoImpuesto
 * 
 * @author juan
 *
 */

@Stateless
public class CodigoImpuestoDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion para obtener un CodigoImpuesto
	 * 
	 * @param id objeto CodigoImpuesto
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CodigoImpuesto getById(Long id) {
		return em.find(CodigoImpuesto.class, id);
	}

	/**
	 * Obtiene todos los CodigoImpuesto
	 * 
	 * @return la lista de CodigoImpuesto
	 */
	@SuppressWarnings("unchecked")
	public List<CodigoImpuesto> getLista() {
		Query query = em.createNamedQuery("CodigoImpuesto.getAllLista");
		return query.getResultList();
	}
}
