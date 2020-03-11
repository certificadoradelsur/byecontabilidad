package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.Banco;


/**
 * implementacion de patron dao para banco
 * 
 * @author juan
 *
 */

@Stateless
public class BancoDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda banco
	 */
	public void guardar(Banco banco) {
		em.persist(banco);
	}

	/**
	 * Funcion que cuenta la cantidad de bancos
	 * 
	 * @return el total de bancos
	 */
	public Long countAll(String nombre) {
		Query query = em.createNamedQuery("Banco.countAll");
		if (nombre.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreNombre", true);
		} else {
			query.setParameter("ignoreNombre", false);
		}
		query.setParameter("nombre", "%" + nombre.toUpperCase() + "%");
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
	public List<Banco> getAll(Integer inicio, Integer fin, String nombre) {
		Query query = em.createNamedQuery("Banco.getAll");
		if (nombre.trim().equalsIgnoreCase("")) {
			query.setParameter("ignoreNombre", true);
		} else {
			query.setParameter("ignoreNombre", false);
		}
		query.setParameter("nombre", "%" + nombre.toUpperCase() + "%");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * funcion que actualiza datos de banco
	 * 
	 * @param b objeto Banco
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Banco b) {
		em.merge(b);
	}

	/**
	 * funcion para obtener un banco
	 * 
	 * @param id objeto Banco
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Banco getById(Long id) {
		return em.find(Banco.class, id);
	}

	/**
	 * funcion que elimina un banco
	 * 
	 * @param p objeto banco
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(Banco b) {
		em.remove(b);
	}

	/**
	 * Obtiene todos los bancos
	 * 
	 * @return la lista de bancos
	 */
	@SuppressWarnings("unchecked")
	public List<Banco> getLista() {
		Query query = em.createNamedQuery("Banco.getAllLista");
		return query.getResultList();
	}
}
