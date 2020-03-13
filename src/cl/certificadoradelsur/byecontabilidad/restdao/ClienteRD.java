package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import cl.certificadoradelsur.byecontabilidad.dao.ClienteDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Cliente;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.ClienteJson;
import cl.certificadoradelsur.utils.Constantes;
import cl.certificadoradelsur.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class ClienteRD {
	private static Logger log = Logger.getLogger(ClienteRD.class);
	@Inject
	private ClienteDAO clidao;

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(ClienteJson cj) {
		try {
			Cliente c = new Cliente();
			if (Utilidades.containsScripting(cj.getNombre()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getRut()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getDireccion()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getGiro()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getEmail()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getTelefono()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getCiudad()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				c.setRut(cj.getRut());
				c.setNombre(cj.getNombre());
				c.setDireccion(cj.getDireccion());
				c.setCiudad(cj.getCiudad());
				c.setGiro(cj.getGiro());
				c.setEmail(cj.getEmail());
				c.setTelefono(cj.getTelefono());
				c.setActivo(true);
				clidao.guardar(c);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (
		Exception e) {
			log.error("No se pudo guardar el cliente ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String rut) {
		try {
			if(rut==null) {
				rut="";
			}

			return clidao.countAll(rut);
		} catch (Exception e) {
			log.error("No se puede contar el total de cliente ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de Bancos en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de Bancos
	 */
	public List<ClienteJson> getAll(Integer page, Integer limit, String rut) {
		List<ClienteJson> lcj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if(rut==null) {
				rut="";
			}
			List<Cliente> lc = clidao.getAll(inicio, limit, rut);
			for (int i = 0; i < lc.size(); i++) {
				ClienteJson cj = new ClienteJson();
				cj.setId(lc.get(i).getId());
				cj.setRut(lc.get(i).getRut());
				cj.setNombre(lc.get(i).getNombre());
				cj.setDireccion(lc.get(i).getDireccion());
				cj.setCiudad(lc.get(i).getCiudad());
				cj.setGiro(lc.get(i).getGiro());
				cj.setEmail(lc.get(i).getEmail());
				cj.setTelefono(lc.get(i).getTelefono());
				cj.setActivo(lc.get(i).isActivo());
			    lcj.add(cj);
			}
		} catch (Exception e) {
			log.error("No se puede obtener la lista de clientes ", e);
		}
		return lcj;
	}

	/**
	 * metodo que modifica cliente
	 * 
	 * @param pj json de clienteJson
	 * @return mensaje de exito o error
	 */
	public String update(ClienteJson cj) {
		try {
			Cliente c = clidao.getById(cj.getId());
			if (Utilidades.containsScripting(cj.getNombre()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getRut()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getDireccion()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getGiro()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getEmail()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getTelefono()).compareTo(true) == 0
					||Utilidades.containsScripting(cj.getCiudad()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				c.setRut(cj.getRut());
				c.setNombre(cj.getNombre());
				c.setDireccion(cj.getDireccion());
				c.setCiudad(cj.getCiudad());
				c.setGiro(cj.getGiro());
				c.setEmail(cj.getEmail());
				c.setTelefono(cj.getTelefono());
				c.setActivo(cj.isActivo());
				clidao.update(c);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar el CLiente");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener un Cliente
	 * 
	 * @param id de Cliente
	 * @return mensaje de exito o error
	 */
	public ClienteJson getById(ClienteJson cj) {
		Cliente c = clidao.getById(cj.getId());
		ClienteJson cJson = new ClienteJson();
		cJson.setId(c.getId());
		cJson.setRut(c.getRut());
		cJson.setNombre(c.getNombre());
		cJson.setDireccion(c.getDireccion());
		cJson.setCiudad(c.getCiudad());
		cJson.setGiro(c.getGiro());
		cJson.setEmail(c.getEmail());
		cJson.setTelefono(c.getTelefono());
		cJson.setActivo(c.isActivo());
		return cJson;
	}

	/**
	 * metodo elimina un Cliente
	 * 
	 * @param pj json de Banco
	 * @return mensaje de exito o error
	 */
	public String eliminar(ClienteJson cj) {
		try {
			Cliente c = clidao.getById(cj.getId());
			c.setActivo(false);
			clidao.update(c);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar el Cliente");
			return e.getMessage();
		}
	}
	
	
	/**
	 * Metodo para traer una lista de cliente (select)
	 * @return
	 */
	public List<ClienteJson> getAllLista() {

		List<ClienteJson> lcj = new ArrayList<>();
		try {
			List<Cliente> c = clidao.getLista();
			for (int i = 0; i < c.size(); i++) {
				ClienteJson cjj = new ClienteJson();
				cjj.setId(c.get(i).getId());
				cjj.setNombre(c.get(i).getNombre());
				cjj.setRut(c.get(i).getRut());
				lcj.add(cjj);
			}
			return lcj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de clientes ", e);
			return lcj;
		}

	}
}
