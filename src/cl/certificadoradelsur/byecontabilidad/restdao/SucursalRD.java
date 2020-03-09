package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.ClaseCuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ClasificacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.GrupoCuentaDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Sucursal;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.SucursalJson;
import cl.certificadoradelsur.utils.Constantes;
import cl.certificadoradelsur.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class SucursalRD {
	private static Logger log = Logger.getLogger(SucursalRD.class);
	@Inject
	private EmpresaDAO edao;
//	@Inject
//	private SucursalDAO sudao;

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(SucursalJson ccj) {
		try {
			Sucursal sucursal = new Sucursal();
			if (Utilidades.containsScripting(ccj.getDireccion()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				sucursal.setDireccion(ccj.getDireccion());
//				sucursal.setGrupoCuenta(grupodao.getById(ccj.getIdGrupoCuenta()));
				sudao.guardar(sucursal);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo guardar la sucursal ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll() {
		try {
			return sudao.countAll();
		} catch (Exception e) {
			log.error("No se puede contar el total de sucursal ", e);
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
	public List<SucursalJson> getAll(Integer page, Integer limit) {
		List<SucursalJson> lbj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if(nombre==null) {
				nombre="";
			}
			List<Clasificacion> lcc = cladao.getAll(inicio, limit);
			for (int i = 0; i < lcc.size(); i++) {
				SucursalJson ccj = new SucursalJson();
				ccj.setId(lcc.get(i).getId());
				ccj.setNombre(lcc.get(i).getNombre());
				ccj.setNombreGrupoCuenta(grupodao.getById(lcc.get(i).getGrupoCuenta().getId()).getNombre());
				ccj.setNombreClaseCuenta(clasedao.getById(lcc.get(i).getClaseCuenta().getId()).getNombre());
				lbj.add(ccj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de clasificaciones ", e);
		}
		return lbj;
	}

	/**
	 * metodo que modifica Banco
	 * 
	 * @param pj json de BancoJson
	 * @return mensaje de exito o error
	 */
	public String update(SucursalJson ccj) {
		try {
			Clasificacion clasificacion = cladao.getById(ccj.getId());
			if (Utilidades.containsScripting(ccj.getNombre()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				clasificacion.setNombre(ccj.getNombre());
				clasificacion.setGrupoCuenta(grupodao.getById(ccj.getIdGrupoCuenta()));
				clasificacion.setClaseCuenta(clasedao.getById(ccj.getIdClaseCuenta()));
				cladao.update(clasificacion);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar la clasificación");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener una clasificacion
	 * 
	 * @param id de clasificacion
	 * @return mensaje de exito o error
	 */
	public SucursalJson getById(SucursalJson bj) {
		Clasificacion clasificacion = cladao.getById(bj.getId());
		SucursalJson ccJson = new SucursalJson();
		ccJson.setId(clasificacion.getId());
		ccJson.setNombre(clasificacion.getNombre());
		ccJson.setIdGrupoCuenta(clasificacion.getGrupoCuenta().getId());
		ccJson.setIdClaseCuenta(clasificacion.getClaseCuenta().getId());
		return ccJson;
	}

	/**
	 * metodo elimina una clasificacion
	 * 
	 * @param pj json de clasificacion
	 * @return mensaje de exito o error
	 */
	public String eliminar(SucursalJson bj) {
		try {
			Clasificacion clasificacion = cladao.getById(bj.getId());
			cladao.eliminar(clasificacion);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la clasificación");
			return e.getMessage();
		}
	}
	



}
