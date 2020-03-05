package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import cl.certificadoradelsur.byecontabilidad.entities.CuentaContable;



/**
 * implementacion de patron dao para CuentaContable
 * 
 * @author juan
 *
 */

@Stateless
public class CuentaContableDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda  CuentaContable
	 */
	public void guardar(CuentaContable c) {
		em.persist(c);
	}

	/**
	 * Funcion que cuenta la cantidad de  CuentaContable
	 * 
	 * @return el total de  CuentaContable
	 */
	public Long countAll(String glosaGeneral) {
		Query query = em.createNamedQuery("CuentaContable.countAll");
		if (glosaGeneral.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreGlosaGeneral", true);
		} else {
			query.setParameter("ignoreGlosaGeneral", false);
		}
		query.setParameter("glosaGeneral", "%" + glosaGeneral.toUpperCase() + "%");
		return (Long) query.getSingleResult();
	}

	/**
	 * FUncion para obtener todas las  CuentaContable
	 * @param inicio
	 * @param fin
	 * @param nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<CuentaContable> getAll(Integer inicio, Integer fin, String glosaGeneral) {
		Query query = em.createNamedQuery("CuentaContable.getAll");
		if (glosaGeneral.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreGlosaGeneral", true);
		} else {
			query.setParameter("ignoreGlosaGeneral", false);
		}
		query.setParameter("glosaGeneral", "%" + glosaGeneral.toUpperCase() + "%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * Funcion para modificar una CuentaContable
	 * @param c
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(CuentaContable c) {
		em.merge(c);
	}

	/**
	 * funcion para obtener una CuentaContable
	 * 
	 * @param id objeto CuentaContable
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CuentaContable getById(Long id) {
		return em.find(CuentaContable.class, id);
	}

	/**
	 * funcion que elimina un CuentaContable
	 * 
	 * @param p objeto CuentaContable
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(CuentaContable c) {
		em.remove(c);
	}

	
}
