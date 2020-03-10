package cl.certificadoradelsur.byecontabilidad.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Empresa;


/**
 * clase que implementa las funciones dao para la  empresa
 * @author juan
 *
 */
@Stateless
public class EmpresaDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;
	
	/**
	 * funcion que guarda Empresa
	 */
	public void guardar(Empresa e) {
		em.persist(e);
	}

	/**
	 * Funcion que cuenta la cantidad de Empresas
	 * 
	 * @return el total de Empresas
	 */
	public Long countAll() {
		Query query = em.createNamedQuery("Empresa.countAll");
		return (Long) query.getSingleResult();
	}

	/**
	 * 
	 * @param inicio
	 * @param fin
	 * @return Lista de Empresas
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Empresa> getAll(Integer inicio, Integer fin) {
		Query query = em.createNamedQuery("Usuario.getAll");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de Empresa
	 * 
	 * @param u objeto Empresa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Empresa e) {
		em.merge(e);
	}

	/**
	 * funcion que actualiza datos de Empresa
	 * 
	 * @param u objeto Empresa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updatePass(Empresa e) {
		em.merge(e);
	}

	/**
	 * funcion para obtener una Empresa
	 * 
	 * @param id objeto Empresa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa getById(Long id) {
		return em.find(Empresa.class, id);
	}

	/**
	 * funcion que elimina a una Empresa
	 * 
	 * @param p objeto usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Empresa e) {
		em.remove(e);

	}
}
