package cl.certificadoradelsur.byecontabilidad.dao;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Conciliacion;

/**
 * implementacion de patron dao para conciliacion
 * 
 * @author juan
 *
 */
@Stateless
public class ConciliacionDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda conciliacion
	 */
	public void guardar(Conciliacion conciliacion) {
		em.persist(conciliacion);
	}

	/**
	 * Funcion que cuenta la cantidad de conciliaciones
	 * 
	 * @return el total de conciliaciones
	 */
	public Long countAll(Timestamp fechaInicial, Timestamp fechaFinal, Long idCuenta, Long idBanco) {
		Query query = em.createNamedQuery("Conciliacion.countAll");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		if (idCuenta == null) {
			query.setParameter("ignoreIdCuenta", true);
		} else {
			query.setParameter("ignoreIdCuenta", false);
		}
		if (idBanco == null) {
			query.setParameter("ignoreIdBanco", true);
		} else {
			query.setParameter("ignoreIdBanco", false);
		}
		query.setParameter("idCuenta", idCuenta);
		query.setParameter("idBanco", idBanco);
		return (Long) query.getSingleResult();
	}

	/**
	 * 
	 * @param inicio
	 * @param fin
	 * @return Lista de conciliaciones
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Conciliacion> getAll(Integer inicio, Integer fin, Timestamp fechaInicial, Timestamp fechaFinal,
			Long idCuenta, Long idBanco) {
		Query query = em.createNamedQuery("Conciliacion.getAll");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		if (idCuenta == null) {
			query.setParameter("ignoreIdCuenta", true);
		} else {
			query.setParameter("ignoreIdCuenta", false);
		}
		if (idBanco == null) {
			query.setParameter("ignoreIdBanco", true);
		} else {
			query.setParameter("ignoreIdBanco", false);
		}
		query.setParameter("idCuenta", idCuenta);
		query.setParameter("idBanco", idBanco);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de Conciliacion
	 * 
	 * @param b objeto conciliacion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Conciliacion c) {
		em.merge(c);
	}

	/**
	 * funcion para obtener un Conciliacion
	 * 
	 * @param id objeto Conciliacion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Conciliacion getById(Long id) {
		return em.find(Conciliacion.class, id);
	}

	/**
	 * funcion que elimina un Conciliacion
	 * 
	 * @param c objeto Conciliacion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Conciliacion c) {
		em.remove(c);
	}

	/**
	 * Funcion para buscar una conciliacion
	 * 
	 * @param idMovimiento
	 * @param idCartola
	 * @return conciliacion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Conciliacion getByMovCar(Long idMovimiento, Long idCartola) {
		Query query = em.createNamedQuery("Conciliacion.getByMovCar");
		try {
			query.setParameter("idMovimiento", idMovimiento);
			query.setParameter("idCartola", idCartola);
			return (Conciliacion) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Funcion para buscar una conciliacion
	 * 
	 * @param idMovimiento
	 * @return conciliacion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Conciliacion getByMov(Long idMovimiento) {
		Query query = em.createNamedQuery("Conciliacion.getByMov");
		try {
			query.setParameter("idMovimiento", idMovimiento);
			return (Conciliacion) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Funcion para buscar una conciliacion
	 * 
	 * @param idcartola
	 * @return conciliacion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Conciliacion getByCart(Long idCartola) {
		Query query = em.createNamedQuery("Conciliacion.getByCart");
		try {
			query.setParameter("idCartola", idCartola);
			return (Conciliacion) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * busca conciliacion por id_transaccion
	 * 
	 * @param idTransaccion
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Conciliacion getByIdTransaccion(Long idTransaccion) {
		Query query = em.createNamedQuery("Conciliacion.getByIdTransaccion");
		try {
			query.setParameter("idTransaccion", idTransaccion);
			query.setMaxResults(1);
			return (Conciliacion) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * busca conciliacion por idComprobante
	 * 
	 * @param idComprobante
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Conciliacion getByIdComprobante(Long idComprobante) {
		Query query = em.createNamedQuery("Conciliacion.idComprobante");
		try {
			query.setParameter("idComprobante", idComprobante);
			query.setMaxResults(1);
			return (Conciliacion) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * Funcion que obtiene datos desde BD para alimentar los reportes
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idBanco
	 * @param idCuenta
	 * @return lista de conciliaciones
	 */
	@SuppressWarnings("unchecked")
	public List<Conciliacion> getByIdReporteBancoCuenta(Timestamp fechaInicial, Timestamp fechaFinal, Long idBanco,
			Long idCuenta) {
		Query query = em.createNamedQuery("Conciliacion.getByIdReporteBancoCuenta");

		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idBanco", idBanco);
		query.setParameter("idCuenta", idCuenta);
		return query.getResultList();
	}

	/**
	 * Funcion que cuenta la cantidad de movimientos conciliados
	 * 
	 * @return el total de movimientos conciliados
	 */
	public Long countAllResumen(Timestamp fechaI, Timestamp fechaF) {
		Query query = em.createNamedQuery("Conciliacion.countAllResumen");
		query.setParameter("fechaI", fechaI);
		query.setParameter("fechaF", fechaF);
		return (Long) query.getSingleResult();
	}

}
