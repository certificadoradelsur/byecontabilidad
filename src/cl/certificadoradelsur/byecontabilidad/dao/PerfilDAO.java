package cl.certificadoradelsur.byecontabilidad.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cl.certificadoradelsur.byecontabilidad.entities.Perfil;

/**
 * clase que implementa las funciones dao para perfil
 * 
 * @author juan
 *
 */
@Stateless
public class PerfilDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * Busca perfil por id
	 * 
	 * @param idPerfil
	 * @return objeto perfil
	 */
	public Perfil getById(String id) {
		return em.find(Perfil.class, id);
	}

}
