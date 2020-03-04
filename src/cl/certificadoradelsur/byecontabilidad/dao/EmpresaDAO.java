package cl.certificadoradelsur.byecontabilidad.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	 * Busca Empresa por id
	 * @param id
	 * @return objeto empresa
	 */
	public Empresa getById(Long id) {
		return	em.find(Empresa.class, id);
	}
 
	
}
