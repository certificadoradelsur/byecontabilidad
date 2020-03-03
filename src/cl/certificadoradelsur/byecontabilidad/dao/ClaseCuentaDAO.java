package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.ClaseCuenta;

/**
 * implementacion de patron dao para claseCuenta
 * 
 * @author juan
 *
 */

@Stateless
public class ClaseCuentaDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda ClaseCuenta
	 */
	public void guardar(ClaseCuenta c) {
		em.persist(c);
	}

	/**
	 * Funcion que cuenta la cantidad de ClaseCuenta
	 * 
	 * @return el total de claseCuenta
	 */
	public Long countAll() {
		Query query = em.createNamedQuery("ClaseCuenta.countAll");
		return (Long) query.getSingleResult();
	}

	/**
	 * FUncion para obtener todas las ClasesCuentas
	 * @param inicio
	 * @param fin
	 * @param nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<ClaseCuenta> getAll(Integer inicio, Integer fin) {
		Query query = em.createNamedQuery("ClaseCuenta.getAll");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * Funcion para modificar una ClaseCuenta
	 * @param c
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(ClaseCuenta c) {
		em.merge(c);
	}

	/**
	 * funcion para obtener una ClaseCuenta
	 * 
	 * @param id objeto ClaseCuenta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ClaseCuenta getById(Long id) {
		return em.find(ClaseCuenta.class, id);
	}

	/**
	 * funcion que elimina un ClaseCuenta
	 * 
	 * @param p objeto ClaseCuenta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(ClaseCuenta c) {
		em.remove(c);
	}

}
