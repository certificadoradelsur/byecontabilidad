package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import cl.certificadoradelsur.byecontabilidad.entities.Cliente;


/**
 * implementacion de patron dao para Cliente
 * 
 * @author juan
 *
 */

@Stateless
public class ClienteDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda Cliente
	 */
	public void guardar(Cliente c) {
		em.persist(c);
	}

	/**
	 * Funcion que cuenta la cantidad de Clientes
	 * 
	 * @return el total de Clientes
	 */
	public Long countAll() {
		Query query = em.createNamedQuery("Cliente.countAll");
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
	public List<Cliente> getAll(Integer inicio, Integer fin, String nombre) {
		Query query = em.createNamedQuery("Cliente.getAll");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de Cliente
	 * 
	 * @param c objeto Cliente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Cliente c) {
		em.merge(c);
	}

	/**
	 * funcion para obtener un Cliente
	 * 
	 * @param id objeto Cliente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente getById(Long id) {
		return em.find(Cliente.class, id);
	}

	/**
	 * funcion que elimina un Cliente
	 * 
	 * @param p objeto Cliente
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Cliente c) {
		em.remove(c);
	}

	/**
	 * Obtiene todos los Clientes
	 * 
	 * @return la lista de Clientes
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> getLista() {
		Query query = em.createNamedQuery("Cliente.getAllLista");
		return query.getResultList();
	}
}
