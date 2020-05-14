package cl.certificadoradelsur.byecontabilidad.dao;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Venta;

/**
 * implementacion de patron dao para venta
 * 
 * @author juan
 *
 */

@Stateless
public class VentaDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda venta
	 */
	public void guardar(Venta v) {
		em.persist(v);
	}

	/**
	 * Funcion que cuenta la cantidad de ventas
	 * 
	 * @return el total de venta
	 */
	public Long countAll(Timestamp fechaInicial, Timestamp fechaFinal, Long idOficinaContable,
			Long idEmpresa) {
		Query query = em.createNamedQuery("Venta.countAll");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		return (Long) query.getSingleResult();
	}

	/**
	 * funcion que trae todos las ventas
	 * 
	 * @param inicio
	 * @param fin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Venta> getAll(Integer inicio, Integer fin,Timestamp fechaInicial,
			Timestamp fechaFinal, Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("Venta.getAll");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de venta
	 * 
	 * @param v objeto venta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Venta v) {
		em.merge(v);
	}

	/**
	 * funcion para obtener un venta
	 * 
	 * @param id objeto venta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Venta getById(Long id) {
		return em.find(Venta.class, id);
	}

	/**
	 * funcion que elimina una venta
	 * 
	 * @param v objeto venta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Venta v) {
		em.remove(v);
	}

}
