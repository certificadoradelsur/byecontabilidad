package cl.certificadoradelsur.byecontabilidad.dao;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import cl.certificadoradelsur.byecontabilidad.entities.Compra;

/**
 * implementacion de patron dao para compra
 * 
 * @author juan
 *
 */

@Stateless
public class CompraDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda compra
	 */
	public void guardar(Compra c) {
		em.persist(c);
	}

	/**
	 * Funcion que cuenta la cantidad de compras
	 * 
	 * @return el total de compras
	 */
	public Long countAll(Timestamp fechaInicial, Timestamp fechaFinal, Long idOficinaContable,
			Long idEmpresa) {
		Query query = em.createNamedQuery("Compra.countAll");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		return (Long) query.getSingleResult();
	}

	/**
	 * funcion que trae todos las compras
	 * 
	 * @param inicio
	 * @param fin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Compra> getAll(Integer inicio, Integer fin,Timestamp fechaInicial,
			Timestamp fechaFinal, Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("Compra.getAll");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de compra
	 * 
	 * @param c objeto compra
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Compra c) {
		em.merge(c);
	}

	/**
	 * funcion para obtener un compra
	 * 
	 * @param id objeto compra
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Compra getById(Long id) {
		return em.find(Compra.class, id);
	}

	/**
	 * funcion que elimina una compra
	 * 
	 * @param c objeto compra
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Compra c) {
		em.remove(c);
	}

}
