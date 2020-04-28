package cl.certificadoradelsur.byecontabilidad.dao;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Movimiento;

/**
 * implementacion de patron dao para Movimiento
 * 
 * @author juan
 *
 */
@Stateless
public class MovimientoDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda movimiento
	 */
	public void guardar(Movimiento movimiento) {
		em.persist(movimiento);
	}

	/**
	 * Funcion que cuenta la cantidad de es
	 * 
	 * @return el total de movimientos
	 */
	public Long countAll() {
		Query query = em.createNamedQuery("Movimiento.countAll");
		return (Long) query.getSingleResult();
	}

	/**
	 * Funcion que cuenta la cantidad de es
	 * 
	 * @return el total de movimientos por id
	 */
	public Long countAllM(Long id) {
		Query query = em.createNamedQuery("Movimiento.countAllM");
		if (id == null) {
			query.setParameter("ignoreId", true);
		} else {
			query.setParameter("ignoreId", false);
		}
		query.setParameter("id", id);
		return (Long) query.getSingleResult();
	}

	/**
	 * 
	 * @param inicio
	 * @param fin
	 * @return Lista de Movimiento
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getAll(Integer inicio, Integer fin) {
		Query query = em.createNamedQuery("Movimiento.getAll");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * Funcion que trae todos los movimientos
	 * 
	 * @param inicio
	 * @param fin
	 * @param idComprobante
	 * @return Lista de Movimiento
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getAllM(Long id) {
		Query query = em.createNamedQuery("Movimiento.getAllM");
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * Funcion que trae todos los movimientos relacionados a un comprobante
	 * 
	 * @param inicio
	 * @param fin
	 * @param idComprobante
	 * @return Lista de Movimiento
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getAllMov(Integer inicio, Integer fin, Long id) {
		Query query = em.createNamedQuery("Movimiento.getAllM");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de Movimiento
	 * 
	 * @param u objeto Movimiento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Movimiento m) {
		em.merge(m);
	}

	/**
	 * funcion para obtener una Movimiento
	 * 
	 * @param id objeto Movimiento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Movimiento getById(Long id) {
		return em.find(Movimiento.class, id);
	}

	/**
	 * funcion que elimina a una Movimiento
	 * 
	 * @param p objeto Movimiento
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Movimiento m) {
		em.remove(m);

	}

	/**
	 * 
	 * @param id
	 * @return Lista de Movimiento
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getAllMovimiento(Long id) {
		Query query = em.createNamedQuery("Movimiento.filtro");
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * funcion que busca movimientos por id de transaccion
	 * 
	 * @param idTransaccion
	 * @return lista de movimientos
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getByIdTransaccion(Long idTransaccion) {
		Query query = em.createNamedQuery("Movimiento.getByIdTransaccion");
		query.setParameter("idTransaccion", idTransaccion);
		return query.getResultList();
	}

	/**
	 * funcion que busca movimientos por idComprobante
	 * 
	 * @param idComprobante
	 * @return lista de movimientos
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getByIdComprobante(Long idComprobante) {
		Query query = em.createNamedQuery("Movimiento.getByIdComprobante");
		query.setParameter("idComprobante", idComprobante);
		return query.getResultList();
	}

	/**
	 * funcion que busca movimientos por idComprobante PARA REPORTE
	 * 
	 * @param idComprobante
	 * @return lista de movimientos
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getByIdComprobanteReporte(Long idComprobante, Long idEmpresa) {
		Query query = em.createNamedQuery("Movimiento.getByIdComprobanteReporte");
		query.setParameter("idComprobante", idComprobante);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

	/**
	 * Funcion que cuenta la cantidad de movimientos
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param idCuenta
	 * @param idBanco
	 * @return
	 */
	public Long countAllResumen(Timestamp fechaInicial, Timestamp fechaFinal, Long idCuenta, Long idBanco) {
		Query query = em.createNamedQuery("Movimiento.countAllResumen");
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
	 * funcion para obtener una lista de Movimientos entre fechas
	 * 
	 * @param fecha
	 * @return Lista de Movimiento
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getAllLista(Timestamp fechaI, Timestamp fechaF, Long idBanco, Long idCuenta, Long idEmpresa) {
		Query query = em.createNamedQuery("Movimiento.getAllFecha");
		query.setParameter("fechaI", fechaI);
		query.setParameter("fechaF", fechaF);
		query.setParameter("idBanco", idBanco);
		query.setParameter("idCuenta", idCuenta);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

	/*
	 * Funcion para buscar una cartola
	 * 
	 * @param idCuenta
	 * 
	 * @return cartola
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Movimiento getByIdCuenta(Long idCuenta) {
		Query query = em.createNamedQuery("Movimiento.getByIdCuenta");
		try {
			query.setParameter("idCuenta", idCuenta);
			query.setMaxResults(1);
			return (Movimiento) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * funcion para obtener una lista de Movimientos entre entre cuentas y fechas
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param inicialMayor
	 * @param finalMayor
	 * @param idOficinaContable
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getByMovEntreCuentas(Timestamp fechaInicial, Timestamp fechaFinal, Long inicialMayor,
			Long finalMayor, Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("Movimiento.getByMovEntreCuentas");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("inicialMayor", inicialMayor);
		query.setParameter("finalMayor", finalMayor);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

	/**
	 * funcion para obtener una lista de Movimientos entre entre fechas para balance
	 * general
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param idOficinaContable
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getBalanceGeneral(Timestamp fechaInicial, Timestamp fechaFinal, Long idCuentaContable,
			Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("Movimiento.getBalanceGeneral");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idCuentaContable", idCuentaContable);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

	/**
	 * funcion para obtener una lista de Movimientos entre entre fechas para balance
	 * clasificado (diferente query)
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param idOficinaContable
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Movimiento> getBalanceClasificado(Timestamp fechaInicial, Timestamp fechaFinal, Long idCuentaContable,
			Long idOficinaContable) {
		Query query = em.createNamedQuery("Movimiento.getBalanceClasificado");
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idCuentaContable", idCuentaContable);
		query.setParameter("idOficinaContable", idOficinaContable);
		return query.getResultList();
	}
}
