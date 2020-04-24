package cl.certificadoradelsur.byecontabilidad.dao;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Cartola;

/**
 * implementacion de patron dao para cartola
 * 
 * @author juan
 *
 */
@Stateless
public class CartolaDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda cartola
	 */
	public void guardar(Cartola cartola) {
		em.persist(cartola);
	}

	/**
	 * Funcion que cuenta la cantidad de cartolas
	 * 
	 * @return el total de cartolas
	 */
	public Long countAll(Timestamp fechaInicial, Timestamp fechaFinal, Long idCuenta, Long idBanco, Long idEmpresa) {
		Query query = em.createNamedQuery("Cartola.countAll");
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
		query.setParameter("idEmpresa", idEmpresa);
		query.setParameter("idBanco", idBanco);
		return (Long) query.getSingleResult();
	}

	/**
	 * 
	 * @param inicio
	 * @param fin
	 * @return Lista de cartolas
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cartola> getAll(Integer inicio, Integer fin, Timestamp fechaInicial, Timestamp fechaFinal,
			Long idCuenta, Long idBanco, Long idEmpresa) {
		Query query = em.createNamedQuery("Cartola.getAll");
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
		query.setParameter("idEmpresa", idEmpresa);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de cartolas
	 * 
	 * @param c objeto cartola
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Cartola c) {
		em.merge(c);
	}

	/**
	 * funcion que elimina una Cartola
	 * 
	 * @param p objeto Cartola
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Cartola c) {
		em.remove(c);
	}

	/**
	 * Obtiene todos las cartolas
	 * 
	 * @return la lista de las cartolas
	 */
	@SuppressWarnings("unchecked")
	public List<Cartola> getLista() {
		Query query = em.createNamedQuery("Cartola.getAll");
		return query.getResultList();
	}

	/**
	 * funcion para obtener un Cartola
	 * 
	 * @param id objeto Cartola
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cartola getById(Long id) {
		return em.find(Cartola.class, id);
	}

	/**
	 * funcion para obtener un Cartola
	 * 
	 * @param número de objeto Cartola
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cartola getByNumDocumento(String numDocumento) {
		try {
			Query query = em.createNamedQuery("Cartola.getByNumDocumento");
			query.setParameter("numDocumento", numDocumento);
			return (Cartola) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * funcion para obtener un Cartola
	 * 
	 * @param fecha, monto, tipoMovimiento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cartola getByFecha(Timestamp fecha, long monto, String tipoMovimiento, Long idEmpresa) {
		try {
			Query query = em.createNamedQuery("Cartola.getByFecha");
			query.setParameter("fecha", fecha);
			query.setParameter("monto", monto);
			query.setParameter("tipoMovimiento", tipoMovimiento);
			query.setParameter("idEmpresa", idEmpresa);
			return (Cartola) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * funcion para obtener una lista de Movimientos entre fechas
	 * 
	 * @param fecha
	 * @return Lista de Movimiento
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cartola> getAllLista(Timestamp fechaI, Timestamp fechaF, Long idBanco, Long idCuenta, Long idEmpresa) {
		Query query = em.createNamedQuery("Cartola.getAllFecha");
		query.setParameter("fechaI", fechaI);
		query.setParameter("fechaF", fechaF);
		query.setParameter("idBanco", idBanco);
		query.setParameter("idCuenta", idCuenta);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

	/**
	 * Funcion que cuenta la cantidad de cartolas
	 * 
	 * @return el total de cartolas
	 */
	public Long countAllResumen(Timestamp fechaI, Timestamp fechaF) {
		Query query = em.createNamedQuery("Cartola.countAllResumen");
		query.setParameter("fechaI", fechaI);
		query.setParameter("fechaF", fechaF);
		return (Long) query.getSingleResult();
	}

	/*
	 * Funcion para buscar una cartola
	 * 
	 * @param idCartola
	 * 
	 * @return cartola
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cartola getByIdCuenta(Long idCuenta) {
		Query query = em.createNamedQuery("Cartola.getByIdCuenta");
		try {
			query.setParameter("idCuenta", idCuenta);
			query.setMaxResults(1);
			return (Cartola) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
