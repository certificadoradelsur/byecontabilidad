package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import cl.certificadoradelsur.byecontabilidad.entities.GrupoCuenta;


/**
 * implementacion de patron dao para GrupoCuenta
 * 
 * @author juan
 *
 */

@Stateless
public class GrupoCuentaDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda GrupoCuenta
	 */
	public void guardar(GrupoCuenta g) {
		em.persist(g);
	}

	/**
	 * Funcion que cuenta la cantidad de  GrupoCuenta
	 * 
	 * @return el total de  GrupoCuenta
	 */
	public Long countAll() {
		Query query = em.createNamedQuery("GrupoCuenta.countAll");
		return (Long) query.getSingleResult();
	}

	/**
	 * FUncion para obtener todas las  GrupoCuenta
	 * @param inicio
	 * @param fin
	 * @param nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<GrupoCuenta> getAll(Integer inicio, Integer fin) {
		Query query = em.createNamedQuery("GrupoCuenta.getAll");
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		return query.getResultList();
	}

	/**
	 * Funcion para modificar un GrupoCuenta
	 * @param c
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(GrupoCuenta g) {
		em.merge(g);
	}

	/**
	 * funcion para obtener un GrupoCuenta
	 * 
	 * @param id objeto GrupoCuenta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public GrupoCuenta getById(Long id) {
		return em.find(GrupoCuenta.class, id);
	}

	/**
	 * funcion que elimina un GrupoCuenta
	 * 
	 * @param p objeto GrupoCuenta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(GrupoCuenta g) {
		em.remove(g);
	}
	
	/**
	 * Obtiene todos los GrupoCuenta
	 * @return la lista de GrupoCuenta
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoCuenta> getLista() {
		Query query=em.createNamedQuery("GrupoCuenta.getAllLista");
		return query.getResultList();
	}
	

	/**
	 * Obtiene una lista de grupocuenta segun el clasecuenta seleccionada
	 * @return lista de cuentas
	 */
	@SuppressWarnings("unchecked")
	public List<GrupoCuenta> getByIdClaseCuenta(Long idClaseCuenta) {
		Query query=em.createNamedQuery("GrupoCuenta.getByIdClaseCuenta");
		query.setParameter("idClaseCuenta",idClaseCuenta);
		return query.getResultList();
	}	

}
