package cl.certificadoradelsur.byecontabilidad.restdao;



import javax.ejb.Stateless;
import javax.inject.Inject;

import cl.certificadoradelsur.byecontabilidad.dao.PerfilDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Perfil;
import cl.certificadoradelsur.byecontabilidad.json.PerfilJson;




/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class PerfilRD {
	@Inject
	private PerfilDAO pdao;
	

	/**
	 * metodo obtener un Perfil
	 * 
	 * @param id de Perfil
	 * @return mensaje de exito o error
	 */
	
	public PerfilJson getById(PerfilJson pj) {
		Perfil perfil = pdao.getById(pj.getId());
		PerfilJson pJson = new PerfilJson();
		pJson.setId(perfil.getId());
		pJson.setDescripcion(perfil.getDescripcion());
		return pJson;
	}

}
