package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Usuario;


/**
 * implementacion de patron dao para usuario
 * 
 * @author juan
 *
 */
@Stateless
public class UsuarioDAO {
	@PersistenceContext(name = "conciliacionBancaria")
	private EntityManager em;

	/**
	 * funcion que guarda usuario
	 */
	public void guardar(Usuario usuario) {
		em.persist(usuario);
	}

	/**
	 * Funcion que cuenta la cantidad de usuarios
	 * 
	 * @return el total de usuarios
	 */
	public Long countAll(String id) {
		Query query = em.createNamedQuery("Usuario.countAll");
		if (id.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreId", true);
		} else {
			query.setParameter("ignoreId", false);
		}
		query.setParameter("id", "%" + id.toUpperCase() + "%");
		return (Long) query.getSingleResult();
	}

	/**
	 * 
	 * @param inicio
	 * @param fin
	 * @return Lista de usuarios
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Usuario> getAll(Integer inicio, Integer fin, String id) {
		Query query = em.createNamedQuery("Usuario.getAll");
		if (id.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreId", true);
		} else {
			query.setParameter("ignoreId", false);
		}
		query.setParameter("id", "%" + id.toUpperCase() + "%");
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
	public void update(Usuario u) {
		em.merge(u);
	}

	/**
	 * funcion que actualiza datos de usuario
	 * 
	 * @param u objeto Usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updatePass(Usuario u) {
		em.merge(u);
	}

	/**
	 * funcion para obtener un usuario
	 * 
	 * @param id objeto usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario getById(String id) {
		return em.find(Usuario.class, id);
	}

	/**
	 * funcion que elimina a una usuario
	 * 
	 * @param p objeto usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Usuario u) {
		em.remove(u);

	}
}
