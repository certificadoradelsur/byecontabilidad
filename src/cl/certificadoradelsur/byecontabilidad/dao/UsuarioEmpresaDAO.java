package cl.certificadoradelsur.byecontabilidad.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cl.certificadoradelsur.byecontabilidad.entities.UsuarioEmpresa;

/**
 * clase que implementa las funciones dao para el UsuarioEmpresa
 * 
 * @author juan
 *
 */
@Stateless
public class UsuarioEmpresaDAO {
	@PersistenceContext(name = "byeContabilidad")
	private EntityManager em;

	/**
	 * funcion que actualiza datos de UsuarioEmpresa
	 * 
	 * @param ue objeto UsuarioEmpresa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(UsuarioEmpresa ue) {
		em.merge(ue);
	}

	/**
	 * funcion para obtener una UsuarioEmpresa
	 * 
	 * @param id objeto UsuarioEmpresa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UsuarioEmpresa getById(Long id) {
		return em.find(UsuarioEmpresa.class, id);
	}

	/**
	 * Funcion para buscar UsuarioEmpresa por idUsuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UsuarioEmpresa> getByIdUsuario(String idUsuario) {
		Query query = em.createNamedQuery("UsuarioEmpresa.getByIdUsuario");
		try {
			query.setParameter("idUsuario", idUsuario);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * funcion que elimina a una Empresa
	 * 
	 * @param p objeto usuario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(UsuarioEmpresa ue) {
		em.remove(ue);
	}

}
