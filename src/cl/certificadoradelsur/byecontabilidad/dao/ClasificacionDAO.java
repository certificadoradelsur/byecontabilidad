package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Clasificacion;


/**
 * implementacion de patron dao para Clasificacion
 * 
 * @author juan
 *
 */

@Stateless
public class ClasificacionDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda Clasificacion
	 */
	public void guardar(Clasificacion c) {
		em.persist(c);
	}

	/**
	 * Funcion que cuenta la cantidad de Clasificacion
	 * 
	 * @return el total de Clasificacion
	 */
	public Long countAll(String nombre) {
		Query query = em.createNamedQuery("Clasificacion.countAll");
		if (nombre.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreNombre", true);
		} else {
			query.setParameter("ignoreNombre", false);
		}
		query.setParameter("nombre", "%" + nombre.toUpperCase() + "%");
		return (Long) query.getSingleResult();
	}

	/**
	 * FUncion para obtener todas las Clasificacion
	 * @param inicio
	 * @param fin
	 * @param nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Clasificacion> getAll(Integer inicio, Integer fin, String nombre) {
		Query query = em.createNamedQuery("Clasificacion.getAll");
		if (nombre.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreNombre", true);
		} else {
			query.setParameter("ignoreNombre", false);
		}
		query.setParameter("nombre", "%" + nombre.toUpperCase() + "%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * Funcion para modificar una Clasificacion
	 * @param c
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Clasificacion c) {
		em.merge(c);
	}

	/**
	 * funcion para obtener una Clasificacion
	 * 
	 * @param id objeto ClaseCuenta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Clasificacion getById(Long id) {
		return em.find(Clasificacion.class, id);
	}

	/**
	 * funcion que elimina un Clasificacion
	 * 
	 * @param p objeto Clasificacion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Clasificacion c) {
		em.remove(c);
	}

}
