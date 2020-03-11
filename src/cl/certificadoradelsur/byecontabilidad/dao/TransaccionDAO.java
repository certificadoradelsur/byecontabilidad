package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Transaccion;

/**
 * implementacion de patron dao para transaccion
 * 
 * @author juan
 *
 */
@Stateless
public class TransaccionDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda transaccion
	 */
	public void guardar(Transaccion transaccion) {
		em.persist(transaccion);
	}

	/**
	 * Funcion que cuenta la cantidad de es
	 * 
	 * @return el total de transacciones
	 */
	public Long countAll(String glosaTransaccion) {
		Query query = em.createNamedQuery("Transaccion.countAll");
		if (glosaTransaccion.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreGlosaTransaccion", true);
		} else {
			query.setParameter("ignoreGlosaTransaccion", false);
		}
		query.setParameter("glosaTransaccion", "%" + glosaTransaccion.toUpperCase() + "%");
		return (Long) query.getSingleResult();
	}

	/**
	 * 
	 * @param inicio
	 * @param fin
	 * @return Lista de Transacciones
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Transaccion> getAll(Integer inicio, Integer fin, String glosaTransaccion) {
		Query query = em.createNamedQuery("Transaccion.getAll");
		if (glosaTransaccion.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreGlosaTransaccion", true);
		} else {
			query.setParameter("ignoreGlosaTransaccion", false);
		}
		query.setParameter("glosaTransaccion", "%" + glosaTransaccion.toUpperCase() + "%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de Transaccion
	 * 
	 * @param u objeto Transaccion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Transaccion t) {
		em.merge(t);
	}

	/**
	 * funcion para obtener una Transaccion
	 * 
	 * @param id objeto transaccion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Transaccion getById(Long id) {
		return em.find(Transaccion.class, id);
	}

	/**
	 * funcion que elimina a una Transaccion
	 * 
	 * @param p objeto Transaccion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Transaccion t) {
		em.remove(t);

	}

	/**
	 * Funcion que trae el numTransaccion más alto
	 * 
	 * @return el max(numTransaccion)
	 */
	public Long getByT() {
		Query query = em.createNamedQuery("Transaccion.maxNum");
		return (Long) query.getSingleResult();
	}
}
