package cl.certificadoradelsur.byecontabilidad.dao;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import cl.certificadoradelsur.byecontabilidad.entities.ComprobanteContable;

/**
 * implementacion de patron dao para Comprobante Contable
 * 
 * @author juan
 *
 */

@Stateless
public class ComprobanteContableDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda banco
	 */
	public void guardar(ComprobanteContable c) {
		em.persist(c);
	}

	/**
	 * Funcion que cuenta la cantidad de Comprobantes Contables
	 * 
	 * @return el total de Comprobantes Contables
	 */
	public Long countAll(String glosaGeneral, Timestamp fechaInicial, Timestamp fechaFinal, Long idOficinaContable,
			Long idEmpresa) {
		Query query = em.createNamedQuery("ComprobanteContable.countAll");
		if (glosaGeneral.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreGlosaGeneral", true);
		} else {
			query.setParameter("ignoreGlosaGeneral", false);
		}
		query.setParameter("glosaGeneral", "%" + glosaGeneral.toUpperCase() + "%");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		return (Long) query.getSingleResult();
	}

	/**
	 * funcion que trae todos los bancos por nombre
	 * 
	 * @param inicio
	 * @param fin
	 * @param nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<ComprobanteContable> getAll(Integer inicio, Integer fin, String glosaGeneral, Timestamp fechaInicial,
			Timestamp fechaFinal, Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("ComprobanteContable.getAll");
		if (glosaGeneral.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreGlosaGeneral", true);
		} else {
			query.setParameter("ignoreGlosaGeneral", false);
		}
		query.setParameter("glosaGeneral", "%" + glosaGeneral.toUpperCase() + "%");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de Comprobante Contable
	 * 
	 * @param c objeto ComprobanteContable
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(ComprobanteContable c) {
		em.merge(c);
	}

	/**
	 * funcion para obtener un Comprobante Contable
	 * 
	 * @param id objeto Comprobante Contable
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComprobanteContable getById(Long id) {
		return em.find(ComprobanteContable.class, id);
	}

	/**
	 * funcion que elimina un Comprobante Contable
	 * 
	 * @param p objeto Comprobante Contable
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(ComprobanteContable c) {
		em.remove(c);
	}

	/**
	 * Obtiene todos los Comprobantes Contables
	 * 
	 * @return la lista de Comprobantes Contables
	 */
	@SuppressWarnings("unchecked")
	public List<ComprobanteContable> getLista() {
		Query query = em.createNamedQuery("ComprobanteContable.getAllLista");
		return query.getResultList();
	}

	/**
	 * Funcion que trae el numero más alto
	 * 
	 * @return max numero
	 */
	public Long getMaxNumero() {
		Query query = em.createNamedQuery("ComprobanteContable.getMaxNumero");
		return (Long) query.getSingleResult();
	}

	/**
	 * busca Comprobante Contable por numero
	 * 
	 * @param numero
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComprobanteContable getByNumero(Long numero, Long idEmpresa) {
		Query query = em.createNamedQuery("ComprobanteContable.getByNumero");
		try {
			query.setParameter("numero", numero);
			query.setParameter("idEmpresa", idEmpresa);
			query.setMaxResults(1);
			return (ComprobanteContable) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Funcion que obtiene datos desde BD para alimentar los reportes
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param idOficinaContable
	 * @return lista comporbantes contables
	 */
	@SuppressWarnings("unchecked")
	public List<ComprobanteContable> getLibroDiario(Timestamp fechaInicial, Timestamp fechaFinal,
			Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("ComprobanteContable.getLibroDiario");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}
	
}
