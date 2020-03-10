package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.PerfilDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Usuario;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.UsuarioJson;
import cl.certificadoradelsur.utils.Constantes;
import cl.certificadoradelsur.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class UsuarioRD {
	private static Logger log = Logger.getLogger(UsuarioRD.class);
	@Inject
	private UsuarioDAO udao;
	@Inject
	private PerfilDAO pdao;

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */

	public String save(UsuarioJson uj) {
		try {
			Usuario uold = udao.getById(uj.getId());
			if (uold != null) {
				throw new ByeContabilidadException("Usuario ya existe");
			}
			Usuario usuario = new Usuario();
			if (Utilidades.containsScripting(uj.getId()).compareTo(true) == 0
					|| Utilidades.containsScripting(uj.getPassword()).compareTo(true) == 0
					|| Utilidades.containsScripting(uj.getEmail()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				usuario.setId(uj.getId());
				usuario.setPassword(Utilidades.encriptar(uj.getPassword()));
				usuario.setEmail(uj.getEmail());
				usuario.setActivo(uj.isActivo());
				usuario.setOficinaContable(udao.getById(uj.getIdUsuario()).getOficinaContable());
				usuario.setPerfil(pdao.getById(uj.getPerfil()));
				usuario.setEliminado(false);
				udao.guardar(usuario);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo guardar el usuario ", e);
			return e.getMessage();
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String id) {
		try {
			if(id==null) {
				id="";
			}
			return udao.countAll(id);
		} catch (Exception e) {
			log.error("No se puede contar el total de usuarios ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de usuarios en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de usuarios
	 */
	public List<UsuarioJson> getAll(Integer page, Integer limit, String id) {
		List<UsuarioJson> lpj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if(id==null) {
				id="";
			}

			List<Usuario> lu = udao.getAll(inicio, limit, id);
			for (int i = 0; i < lu.size(); i++) {
				UsuarioJson uj = new UsuarioJson();
				uj.setId(lu.get(i).getId());
				uj.setEmail(lu.get(i).getEmail());
				uj.setRazonSocial(lu.get(i).getOficinaContable().getRazonSocial());
				uj.setActivo(lu.get(i).isActivo());
				uj.setPerfil(lu.get(i).getPerfil().getDescripcion());
				lpj.add(uj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de usuarios ", e);
		}
		return lpj;
	}

	/**
	 * metodo modifica Usuario
	 * 
	 * @param pj json de usuario
	 * @return mensaje de exito o error
	 */
	public String update(UsuarioJson uj) {
		try {
			Usuario usuario = udao.getById(uj.getId());
			if (Utilidades.containsScripting(uj.getId()).compareTo(true) == 0
					|| Utilidades.containsScripting(uj.getEmail()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				usuario.setId(uj.getId());
				usuario.setEmail(uj.getEmail());
				usuario.setActivo(uj.isActivo());
				usuario.setPerfil(pdao.getById(uj.getPerfil()));
				udao.update(usuario);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar el usuario");
			return e.getMessage();
		}
	}

	/**
	 * metodo modifica las credenciales de seguridad del usuario
	 * 
	 * @param pj json de usuario
	 * @return mensaje de exito o error
	 */
	public String updatePass(UsuarioJson uj) {
		try {
			Usuario usuario = udao.getById(uj.getId());
			if (Utilidades.containsScripting(uj.getPassword()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				usuario.setPassword(Utilidades.encriptar(uj.getPassword()));
				udao.updatePass(usuario);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudieron modificar las credenciales de seguridad");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener un Usuario
	 * 
	 * @param id de ususario
	 * @return mensaje de exito o error
	 */
	public UsuarioJson getById(UsuarioJson pj) {
		Usuario usuario = udao.getById(pj.getId());
		UsuarioJson uJson = new UsuarioJson();
		uJson.setId(usuario.getId());
		uJson.setEmail(usuario.getEmail());
		uJson.setActivo(usuario.isActivo());
		uJson.setPerfil(usuario.getPerfil().getId());
		return uJson;
	}

	/**
	 * metodo modifica Usuario
	 * 
	 * @param pj json de Usuario
	 * @return mensaje de exito o error
	 */
	public String eliminar(UsuarioJson pj) {
		try {
			Usuario usuario = udao.getById(pj.getId());
			usuario.setEliminado(true);
			udao.update(usuario);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar el usuario");
			return e.getMessage();
		}
	}
}
