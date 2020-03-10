package cl.certificadoradelsur.byecontabilidad.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import cl.certificadoradelsur.byecontabilidad.entities.OficinaContable;


/**
 * clase que implementa las funciones dao para la  OficinaContable
 * @author juan
 *
 */
@Stateless
public class OficinaContableDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;
	
	

	/**
	 * funcion para obtener una Empresa
	 * 
	 * @param id objeto Empresa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public OficinaContable getById(Long id) {
		return em.find(OficinaContable.class, id);
	}


}
