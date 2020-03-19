package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Cuenta;

/**
 * implementacion de patron dao para cuenta
 * 
 * @author juan
 *
 */

@Stateless
public class CuentaDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda cuenta
	 */
	public void guardar(Cuenta cuenta) {
		em.persist(cuenta);
	}

	/**
	 * Funcion que cuenta la cantidad de cuentas
	 * 
	 * @return el total de cuentas
	 */
	public Long countAll(String numCuenta, Long idOficinaContable) {
		Query query = em.createNamedQuery("Cuenta.countAll");
		if (numCuenta.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreNumCuenta", true);
		} else {
			query.setParameter("ignoreNumCuenta", false);
		}
		query.setParameter("numCuenta", "%" + numCuenta.toUpperCase() + "%");
		query.setParameter("idOficinaContable", idOficinaContable);
		return (Long) query.getSingleResult();
	}

	/**
	 * 
	 * @param inicio
	 * @param fin
	 * @return Lista de cuentas
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cuenta> getAll(Integer inicio, Integer fin, String numCuenta, Long idOficinaContable) {
		Query query = em.createNamedQuery("Cuenta.getAll");
		if (numCuenta.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreNumCuenta", true);
		} else {
			query.setParameter("ignoreNumCuenta", false);
		}
		query.setParameter("numCuenta", "%" + numCuenta.toUpperCase() + "%");
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de cuenta
	 * 
	 * @param b objeto cuenta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Cuenta c) {
		em.merge(c);
	}

	/**
	 * funcion para obtener un cuenta
	 * 
	 * @param id objeto cuenta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cuenta getById(Long id) {
		return em.find(Cuenta.class, id);
	}

	/**
	 * funcion que elimina un cuenta
	 * 
	 * @param p objeto cuenta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Cuenta c) {
		em.remove(c);
	}

	/**
	 * Obtiene una lista de cuentas segun el banco seleccionado
	 * 
	 * @return lista de cuentas
	 */
	@SuppressWarnings("unchecked")
	public List<Cuenta> getByIdBanco(Long idBanco, Long idOficinaContable) {
		Query query = em.createNamedQuery("Cuenta.getByIdBanco");
		query.setParameter("idBanco", idBanco);
		query.setParameter("idOficinaContable", idOficinaContable);
		return query.getResultList();
	}
}
