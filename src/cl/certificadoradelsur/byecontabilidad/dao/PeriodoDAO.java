package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Periodo;


@Stateless
public class PeriodoDAO {
	@PersistenceContext(name = "byecontabilidad")
	private EntityManager em;
	
	/**
	 * obtiene un periodo por id
	 * @param id
	 * @return 
	 */
	public Periodo getById(Long id) {
		return em.find(Periodo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Periodo> getLista() {
		Query query = em.createNamedQuery("Periodo.getAll");
		return query.getResultList();
	}
	
	/**
	 * 	Funcion que guarda periodo
	 * @param periodo
	 */
	public void guardar(Periodo periodo) {
		em.persist(periodo);
	}
	
	/**
	 * funcion que actualiza datos de periodo
	 * 
	 * @param u objeto periodo
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Periodo p) {
		em.merge(p);
	}

	/**
	 * funcion que elimina un Periodo
	 * 
	 * @param p objeto periodo
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Periodo p) {
		em.remove(p);

	}
	
	/**
	 * 
	 * @param inicio
	 * @param fin
	 * @return Lista de periodo
	 */
	@SuppressWarnings("unchecked")
	public List<Periodo> getAll(Integer inicio, Integer fin,Long anio,Long empresaId) {
		Query query = em.createNamedQuery("Periodo.getAll");
		query.setParameter("anio", anio);
		query.setParameter("idEmpresa", empresaId);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}
	
	/**
	 * Funcion que cuenta la cantidad de periodo
	 * 
	 * @return el total de periodo
	 */
	public Long countAll(Long anio,Long empresaId) {
		Query query = em.createNamedQuery("Periodo.countAll");
		query.setParameter("anio", anio);
		query.setParameter("idEmpresa", empresaId);
		return (Long) query.getSingleResult();
	}
	
	/**
	 * Funcion para traer periodo por mes y anio
	 * @param imes
	 * @param ianio
	 * @return periodo
	 */
	public Periodo getBymesPeriodo(Long imes,Long ianio,Long empresaId) {
		try {
			Query query = em.createNamedQuery("Periodo.getbyPeriodo");
			query.setParameter("mes", imes);
			query.setParameter("anio", ianio);
			query.setParameter("idEmpresa", empresaId);
			return (Periodo) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Funcion para traer periodo por anio
	 * @param ianio
	 * @return lista de periodos por anio
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Periodo> getByAnio(Long ianio,Long empresaId) {

			Query query = em.createNamedQuery("Periodo.getByAnio");
			query.setParameter("anio", ianio);
			query.setParameter("idEmpresa", empresaId);
			return  query.getResultList();
		
	}
	

}
