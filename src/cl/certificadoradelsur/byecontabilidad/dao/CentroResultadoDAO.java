package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.CentroResultado;


/**
 * 
 * @author ernesto
 *
 */

@Stateless
public class CentroResultadoDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda centroResultado
	 */
	public void guardar(CentroResultado centro) {
		em.persist(centro);
	}
	
	/**
	 * Funcion que cuenta la cantidad de Centro Resultado
	 * 
	 * @return el total de centro resultado
	 */
	public Long countAll() {
		Query query = em.createNamedQuery("CentroResultado.countAll");
		
		return (Long) query.getSingleResult();
	}

	/**
	 * funcion que trae todos los CentroResultado
	 * 
	 * @param inicio
	 * @param fin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<CentroResultado> getAll(Integer inicio, Integer fin) {
		Query query = em.createNamedQuery("CentroResultado.getAll");
		
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de centro Resultado
	 * 
	 * @param c objeto CentroResultado
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(CentroResultado c) {
		em.merge(c);
	}
	
	/**
	 * funcion para obtener un centro resultado
	 * 
	 * @param id objeto Centro Resultado
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CentroResultado getById(Long id) {
		return em.find(CentroResultado.class, id);
	}

	/**
	 * funcion que elimina un Centro Resultado
	 * 
	 * @param c objeto centro Resultado
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(CentroResultado c) {
		em.remove(c);
	}

}
