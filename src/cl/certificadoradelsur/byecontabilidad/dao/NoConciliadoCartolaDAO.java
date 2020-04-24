package cl.certificadoradelsur.byecontabilidad.dao;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.NoConciliadoCartola;


/**
 * implementacion de patron dao para no conciliado cartola
 * 
 * @author juan
 *
 */
@Stateless
public class NoConciliadoCartolaDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda cartola no conciliada
	 */
	public void guardar(NoConciliadoCartola noConciliadoCartola) {
		em.persist(noConciliadoCartola);
	}

	/**
	 * Funcion que cuenta la cantidad de cartola no conciliadas
	 * 
	 * @return el total de conciliaciones
	 */
	public Long countAll(Timestamp fechaInicial, Timestamp fechaFinal, Long idCuenta, Long idBanco, Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("NoConciliadoCartola.countAll");
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
		query.setParameter("idOficinaContable", idOficinaContable);
		return (Long) query.getSingleResult();
	}

	/**
	 * Funcion que cuenta la cantidad de no conciliados
	 * 
	 * @param monto
	 * @return el total de conciliaciones
	 */
	public Long countAllNCC(Long monto) {
		Query query = em.createNamedQuery("NoConciliadoCartola.countAllNCC");
		try {
			query.setParameter("monto", monto);

			return (Long) query.getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}

	/**
	 * Funcion que cuenta la cantidad de no conciliados
	 * 
	 * @param numDocumento
	 * @return el total de conciliaciones
	 */
	public Long countAllNCCDoc(Long numDocumento) {
		Query query = em.createNamedQuery("NoConciliadoCartola.countAllNCCDoc");
		try {
			query.setParameter("numDocumento", numDocumento);

			return (Long) query.getSingleResult();
		} catch (Exception e) {
			return 0L;
		}
	}

	/**
	 * funcion que trae todos las cartolas no conciliados entre fecha, por banco y
	 * por cuenta
	 * 
	 * @param inicio
	 * @param fin
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param idCuenta
	 * @param idBanco
	 * @return lista de noconciliadoscartola
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<NoConciliadoCartola> getAll(Integer inicio, Integer fin, Timestamp fechaInicial, Timestamp fechaFinal,
			Long idCuenta, Long idBanco, Long idOficinaContable, Long idEmpresa) {
		Query query = em.createNamedQuery("NoConciliadoCartola.getAll");
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
	 * funcion que actualiza datos de cartola no conciliada
	 * 
	 * @param c objeto cartola no conciliada
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(NoConciliadoCartola c) {
		em.merge(c);
	}

	/**
	 * funcion para obtener una cartola no conciliada
	 * 
	 * @param id objeto cartola no conciliada
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NoConciliadoCartola getById(Long id) {
		try {
			return em.find(NoConciliadoCartola.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * funcion que elimina cartola no conciliada
	 * 
	 * @param c objeto cartola no conciliada
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(NoConciliadoCartola c) {
		em.remove(c);
	}

	/**
	 * Obtiene todos las cartolas no conciliadas
	 * 
	 * @return la lista de cartolas no conciliadas
	 */
	@SuppressWarnings("unchecked")
	public List<NoConciliadoCartola> getLista() {
		Query query = em.createNamedQuery("NoConciliadoCartola.getAll");
		return query.getResultList();
	}

	/**
	 * funcion para obtener un NoConciliadoCartola
	 * 
	 * @param número de objeto NoConciliadoCartola
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NoConciliadoCartola getByIdCartola(Long idCartola) {
		try {
			Query query = em.createNamedQuery("NoConciliadoCartola.getByIdCartola");
			query.setParameter("idCartola", idCartola);
			query.setMaxResults(1);
			return (NoConciliadoCartola) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Funcion que devuelve las cartolas no conciliadas por fecha
	 * 
	 * @param inicio
	 * @param fin
	 * @param monto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<NoConciliadoCartola> getNoConciliadoCartolaMonto(Integer inicio, Integer fin, Long monto) {
		Query query = em.createNamedQuery("NoConciliadoCartola.getNoConciliadoCartolaMonto");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		query.setParameter("monto", monto);
		return query.getResultList();
	}

	/**
	 * Funcion que devuelve las cartolas no conciliadas por fecha
	 * 
	 * @param inicio
	 * @param fin
	 * @param numDocumento
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<NoConciliadoCartola> getNoConciliadoCartolaDoc(Integer inicio, Integer fin, String numDocumento) {
		Query query = em.createNamedQuery("NoConciliadoCartola.getNoConciliadoCartolaDoc");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		query.setParameter("numDocumento", numDocumento);
		return query.getResultList();
	}

	/**
	 * Funcion que obtiene datos desde BD para alimentar los reportes
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idBanco
	 * @param idCuenta
	 * @return lista de cartolas no conciliados
	 */
	@SuppressWarnings("unchecked")
	public List<NoConciliadoCartola> getByIdReporteBancoCuenta(Timestamp fechaInicial, Timestamp fechaFinal,
			Long idBanco, Long idCuenta, Long idEmpresa) {
		Query query = em.createNamedQuery("NoConciliadoCartola.getByIdReporteBancoCuenta");

		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("idBanco", idBanco);
		query.setParameter("idCuenta", idCuenta);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

}
