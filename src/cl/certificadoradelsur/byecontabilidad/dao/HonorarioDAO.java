package cl.certificadoradelsur.byecontabilidad.dao;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Honorario;

/**
 * implementacion de patron dao para honorario
 * 
 * @author juan
 *
 */

@Stateless
public class HonorarioDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda Honorario
	 */
	public void guardar(Honorario h) {
		em.persist(h);
	}

	/**
	 * Funcion que cuenta la cantidad de Honorarios
	 * 
	 * @return el total de honorario
	 */
	public Long countAll(Timestamp fechaInicial, Timestamp fechaFinal, Long idOficinaContable,
			Long idEmpresa) {
		Query query = em.createNamedQuery("Honorario.countAll");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		return (Long) query.getSingleResult();
	}

	/**
	 * funcion que trae todos los Honorarios
	 * 
	 * @param inicio
	 * @param fin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Honorario> getAll(Integer inicio, Integer fin,Timestamp fechaInicial,
			Timestamp fechaFinal, Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("Honorario.getAll");

		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de Honorario
	 * 
	 * @param h objeto Honorario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Honorario h) {
		em.merge(h);
	}

	/**
	 * funcion para obtener un Honorario
	 * 
	 * @param id objeto Honorario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Honorario getById(Long id) {
		return em.find(Honorario.class, id);
	}

	/**
	 * funcion que elimina un Honorario
	 * 
	 * @param h objeto Honorario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Honorario h) {
		em.remove(h);
	}

}
