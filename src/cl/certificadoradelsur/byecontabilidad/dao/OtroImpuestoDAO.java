package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.OtroImpuesto;

/**
 * clase que implementa las funciones dao para el OtroImpuesto
 * 
 * @author juan
 *
 */
@Stateless
public class OtroImpuestoDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que actualiza datos de OtroImpuesto
	 * 
	 * @param ue objeto OtroImpuesto
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(OtroImpuesto oi) {
		em.merge(oi);
	}

	/**
	 * funcion para obtener OtroImpuesto
	 * 
	 * @param id objeto OtroImpuesto
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public OtroImpuesto getById(Long id) {
		return em.find(OtroImpuesto.class, id);
	}

	/**
	 * Funcion para buscar OtroImpuesto por idCompra
	 * 
	 * @param idCompra
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OtroImpuesto> getByIdCompra(Long idCompra) {
		Query query = em.createNamedQuery("OtroImpuesto.getByIdCompra");
		try {
			query.setParameter("idCompra", idCompra);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Funcion para buscar OtroImpuesto por idVenta
	 * 
	 * @param idCompra
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OtroImpuesto> getByIdVenta(Long idVenta) {
		Query query = em.createNamedQuery("OtroImpuesto.getByIdVenta");
		try {
			query.setParameter("idVenta", idVenta);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * funcion que elimina a un OtroImpuesto
	 * 
	 * @param p objeto usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(OtroImpuesto oi) {
		em.remove(oi);
	}

}
