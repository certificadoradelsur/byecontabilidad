package cl.certificadoradelsur.byecontabilidad.dao;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.NoConciliado;

/**
 * implementacion de patron dao para no conciliado
 * 
 * @author juan
 *
 */
@Stateless
public class NoConciliadoDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda no conciliado
	 */
	public void guardar(NoConciliado noConciliado) {
		em.persist(noConciliado);
	}

	/**
	 * Funcion que cuenta la cantidad de no conciliados
	 * 
	 * @return el total de conciliaciones
	 */
	public Long countAll(Timestamp fechaInicial, Timestamp fechaFinal, Long idCuenta, Long idBanco, Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("NoConciliado.countAll");
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
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setParameter("idBanco", idBanco);
		query.setParameter("idEmpresa", idEmpresa);
		return (Long) query.getSingleResult();
	}

	/**
	 * * Funcion que cuenta la cantidad de no conciliados por monto
	 * 
	 * 
	 * @param monto
	 * @return el total de conciliaciones
	 */
	public Long countAllMNC(Long monto) {
		Query query = em.createNamedQuery("NoConciliado.countAllMNC");
		try {
			query.setParameter("monto", monto);

			return (Long) query.getSingleResult();
		} catch (Exception e) {
			return 0L;
		}

	}

	/**
	 * funcion que trae todos los movimientos no conciliados entre fecha, por banco
	 * y por cuenta
	 * 
	 * @param inicio
	 * @param fin
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param idCuenta
	 * @param idBanco
	 * @return lista de noconciliados
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<NoConciliado> getAll(Integer inicio, Integer fin, Timestamp fechaInicial, Timestamp fechaFinal,
			Long idCuenta, Long idBanco, Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("NoConciliado.getAll");
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
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de no conciliado
	 * 
	 * @param b objeto conciliacion
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(NoConciliado c) {
		em.merge(c);
	}

	/**
	 * funcion para obtener un no conciliado
	 * 
	 * @param id objeto No Conciliado
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NoConciliado getById(Long id) {
		try {
			return em.find(NoConciliado.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * funcion que elimina un no conciliado
	 * 
	 * @param c objeto no conciliado
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(NoConciliado c) {
		em.remove(c);
	}

	/**
	 * funcion para obtener un NoConciliado
	 * 
	 * @param número de objeto NoConciliado
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NoConciliado getByIdMovimiento(Long idMovimiento) {
		try {
			Query query = em.createNamedQuery("NoConciliado.getByIdMovimiento");
			query.setParameter("idMovimiento", idMovimiento);
			query.setMaxResults(1);
			return (NoConciliado) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Funcion que devuelve Movimientos no conciliados por fecha
	 * 
	 * @param inicio
	 * @param fin
	 * @param fecha
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<NoConciliado> getNoConciliadoMonto(Integer inicio, Integer fin, Long monto) {
		Query query = em.createNamedQuery("NoConciliado.getNoConciliadoMonto");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		query.setParameter("monto", monto);

		return query.getResultList();
	}

	/**
	 * Funcion que obtiene datos desde BD para alimentar los reportes
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idBanco
	 * @param idCuenta
	 * @return lista de movimientos no conciliados
	 */
	@SuppressWarnings("unchecked")
	public List<NoConciliado> getByIdReporteBancoCuenta(Timestamp fechaInicial, Timestamp fechaFinal, Long idBanco,
			Long idCuenta) {
		Query query = em.createNamedQuery("NoConciliado.getByIdReporteBancoCuenta");

		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idBanco", idBanco);
		query.setParameter("idCuenta", idCuenta);
		return query.getResultList();
	}

	/**
	 * busca NoConciliado por id_transaccion
	 * 
	 * @param idTransaccion
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NoConciliado getByIdTransaccion(Long idTransaccion) {
		Query query = em.createNamedQuery("NoConciliado.getByIdTransaccion");
		try {
			query.setParameter("idTransaccion", idTransaccion);
			query.setMaxResults(1);
			return (NoConciliado) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * busca NoConciliado por idComprobante
	 * 
	 * @param idComprobante
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NoConciliado getByIdComprobante(Long idComprobante) {
		Query query = em.createNamedQuery("NoConciliado.getByIdComprobante");
		try {
			query.setParameter("idComprobante", idComprobante);
			query.setMaxResults(1);
			return (NoConciliado) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}


}
