package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Sucursal;



/**
 * implementacion de patron dao para Sucursal
 * 
 * @author juan
 *
 */
@Stateless
public class SucursalDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda Sucursal
	 */
	public void guardar(Sucursal s) {
		em.persist(s);
	}

	/**
	 * Funcion que cuenta la cantidad de Sucursales
	 * 
	 * @return el total de Sucursales
	 */
	public Long countAll(Long idEmpresa, Long idOficinaContable) {
		Query query = em.createNamedQuery("Sucursal.countAll");
		query.setParameter("idEmpresa",idEmpresa);
		query.setParameter("idOficinaContable", idOficinaContable);

		return (Long) query.getSingleResult();
	}

	/**
	 * 
	 * @param inicio
	 * @param fin
	 * @return Lista de Sucursales
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Sucursal> getAll(Integer inicio, Integer fin, Long idEmpresa, Long idOficinaContable) {
		Query query = em.createNamedQuery("Sucursal.getAll");
		query.setParameter("idEmpresa",idEmpresa);
		query.setParameter("idOficinaContable", idOficinaContable);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de usuario
	 * 
	 * @param u objeto Usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Sucursal s) {
		em.merge(s);
	}

	/**
	 * funcion que actualiza datos de Sucursal
	 * 
	 * @param u objeto Usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updatePass(Sucursal s) {
		em.merge(s);
	}

	/**
	 * funcion para obtener un usuario
	 * 
	 * @param id objeto usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Sucursal getById(Long codigo) {
		return em.find(Sucursal.class, codigo);
	}

	/**
	 * funcion que elimina a una usuario
	 * 
	 * @param p objeto usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Sucursal s) {
		em.remove(s);

	}
	
	/**
	 * Obtiene una lista de sucursales segun la empresa seleccionada
	 * 
	 * @return lista de sucursales
	 */
	@SuppressWarnings("unchecked")
	public List<Sucursal> getByIdEmpresa(Long idEmpresa, Long idOficinaContable) {
		Query query = em.createNamedQuery("Sucursal.getByIdEmpresa");
		query.setParameter("idEmpresa", idEmpresa);
		query.setParameter("idOficinaContable", idOficinaContable);
		return query.getResultList();
	}
	
	/**
	 * Funcion que trae el id más grande
	 * 
	 * @return max(id)
	 */
	public Long maxId(Long idEmpresa) {
		Query query = em.createNamedQuery("Sucursal.maxId");
		query.setParameter("idEmpresa", idEmpresa);
		return (Long) query.getSingleResult();
	}
}
