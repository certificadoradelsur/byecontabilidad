package cl.certificadoradelsur.byecontabilidad.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import cl.certificadoradelsur.byecontabilidad.entities.Bitacora;


/**
 * implementacion de patron dao para Bitacora
 * 
 * @author juan
 *
 */

@Stateless
public class BitacoraDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que guarda Bitacora
	 */
	public void guardar(Bitacora b) {
		em.persist(b);
	}
}
