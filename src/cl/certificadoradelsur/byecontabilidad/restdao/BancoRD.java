package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BancoDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Banco;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.BancoJson;
import cl.certificadoradelsur.utils.Constantes;
import cl.certificadoradelsur.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class BancoRD {
	private static Logger log = Logger.getLogger(BancoRD.class);
	@Inject
	private BancoDAO bdao;

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(BancoJson bj) {
		try {
			Banco banco = new Banco();
			if (Utilidades.containsScripting(bj.getNombre()).compareTo(true) == 0
					|| Utilidades.containsScripting(bj.getCuenta()).compareTo(true) == 0
					|| Utilidades.containsScripting(bj.getNombreEjecutivo()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				banco.setNombre(bj.getNombre());
				banco.setEliminado(false);
				bdao.guardar(banco);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (

		Exception e) {
			log.error("No se pudo guardar el banco ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String nombre) {
		try {
			if (nombre == null) {
				nombre = "";
			}
			return bdao.countAll(nombre);
		} catch (Exception e) {
			log.error("No se puede contar el total de bancos ", e);
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
	public List<BancoJson> getAll(Integer page, Integer limit, String nombre) {
		List<BancoJson> lbj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if (nombre == null) {
				nombre = "";
			}
			List<Banco> lb = bdao.getAll(inicio, limit, nombre);
			for (int i = 0; i < lb.size(); i++) {
				BancoJson bj = new BancoJson();
				bj.setId(lb.get(i).getId());
				bj.setNombre(lb.get(i).getNombre());
				lbj.add(bj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de Bancos ", e);
		}
		return lbj;
	}

	/**
	 * metodo que modifica Banco
	 * 
	 * @param pj json de BancoJson
	 * @return mensaje de exito o error
	 */
	public String update(BancoJson bj) {
		try {
			Banco banco = bdao.getById(bj.getId());
			if (Utilidades.containsScripting(bj.getNombre()).compareTo(true) == 0
					|| Utilidades.containsScripting(bj.getCuenta()).compareTo(true) == 0
					|| Utilidades.containsScripting(bj.getNombreEjecutivo()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				banco.setNombre(bj.getNombre());
				bdao.update(banco);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar el banco");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener un Banco
	 * 
	 * @param id de Banco
	 * @return mensaje de exito o error
	 */
	public BancoJson getById(BancoJson bj) {
		Banco banco = bdao.getById(bj.getId());
		BancoJson bJson = new BancoJson();
		bJson.setId(banco.getId());
		bJson.setNombre(banco.getNombre());
		return bJson;
	}

	/**
	 * metodo elimina un Banco
	 * 
	 * @param pj json de Banco
	 * @return mensaje de exito o error
	 */
	public String eliminar(BancoJson bj) {
		try {
			Banco banco = bdao.getById(bj.getId());
			banco.setEliminado(true);
			bdao.update(banco);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar el banco");
			return e.getMessage();
		}
	}
	
	
	/**
	 * Metodo para traer una lista de bancos (select)
	 * @return
	 */
	public List<BancoJson> getAllLista() {

		List<BancoJson> lbj = new ArrayList<>();
		try {
			List<Banco> b = bdao.getLista();
			for (int i = 0; i < b.size(); i++) {
				BancoJson bjj = new BancoJson();
				bjj.setId(b.get(i).getId());
				bjj.setNombre(b.get(i).getNombre());
				lbj.add(bjj);
			}
			return lbj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de banco ", e);
			return lbj;
		}

	}
}
