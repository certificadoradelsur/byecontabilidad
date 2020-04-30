package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.ClaseCuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ClasificacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.GrupoCuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Clasificacion;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.ClasificacionJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class ClasificacionRD {
	private static Logger log = Logger.getLogger(ClasificacionRD.class);
	@Inject
	private ClasificacionDAO cladao;
	@Inject
	private GrupoCuentaDAO grupodao;
	@Inject
	private ClaseCuentaDAO clasedao;
	@Inject
	private UsuarioDAO udao;
	@Inject
	private EmpresaDAO edao;
	@Inject
	private CuentaContableDAO cuentaCondao;
	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(ClasificacionJson ccj) {
		try {
			Clasificacion clasificacion = new Clasificacion();
			if (Utilidades.containsScripting(ccj.getNombre()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				clasificacion.setNombre(ccj.getNombre());
				clasificacion.setGrupoCuenta(grupodao.getById(ccj.getIdGrupoCuenta()));
				clasificacion.setClaseCuenta(clasedao.getById(ccj.getIdClaseCuenta()));
				clasificacion.setEmpresa(edao.getById(ccj.getIdEmpresa()));

				cladao.guardar(clasificacion);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo guardar la clasificación ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String nombre, Long idClaseCuenta, Long idGrupoCuenta, String idUsuario, Long idEmpresa) {
		try {
			if (nombre == null) {
				nombre = "";
			}
			return cladao.countAll(nombre, idClaseCuenta, idGrupoCuenta,
					udao.getById(idUsuario).getOficinaContable().getId(), idEmpresa);
		} catch (Exception e) {
			log.error("No se puede contar el total de clasificacines ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de clasificacion en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de clasificaciones
	 */
	public List<ClasificacionJson> getAll(Integer page, Integer limit, String nombre, Long idClaseCuenta,
			Long idGrupoCuenta, String idUsuario, Long idEmpresa) {
		List<ClasificacionJson> lbj = new ArrayList<>();
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
			List<Clasificacion> lcc = cladao.getAll(inicio, limit, nombre, idClaseCuenta, idGrupoCuenta,
					udao.getById(idUsuario).getOficinaContable().getId(), idEmpresa);
			for (int i = 0; i < lcc.size(); i++) {
				ClasificacionJson ccj = new ClasificacionJson();
				ccj.setId(lcc.get(i).getId());
				ccj.setNombre(lcc.get(i).getNombre());
				ccj.setNombreGrupoCuenta(grupodao.getById(lcc.get(i).getGrupoCuenta().getId()).getNombre());
				ccj.setNombreClaseCuenta(clasedao.getById(lcc.get(i).getClaseCuenta().getId()).getNombre());
				ccj.setRazonSocialEmpresa(edao.getById(lcc.get(i).getEmpresa().getId()).getRazonSocial());
				lbj.add(ccj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de clasificaciones ", e);
		}
		return lbj;
	}

	/**
	 * metodo que modifica una clasificacion
	 * 
	 * @param pj json de clasificacionJson
	 * @return mensaje de exito o error
	 */
	public String update(ClasificacionJson ccj) {
		try {
			Clasificacion clasificacion = cladao.getById(ccj.getId());
			if (Utilidades.containsScripting(ccj.getNombre()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				clasificacion.setNombre(ccj.getNombre());
				clasificacion.setGrupoCuenta(grupodao.getById(ccj.getIdGrupoCuenta()));
				clasificacion.setClaseCuenta(clasedao.getById(ccj.getIdClaseCuenta()));
				clasificacion.setEmpresa(edao.getById(ccj.getIdEmpresa()));
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
	public ClasificacionJson getById(ClasificacionJson bj) {
		Clasificacion clasificacion = cladao.getById(bj.getId());
		ClasificacionJson ccJson = new ClasificacionJson();
		ccJson.setId(clasificacion.getId());
		ccJson.setNombre(clasificacion.getNombre());
		ccJson.setIdGrupoCuenta(clasificacion.getGrupoCuenta().getId());
		ccJson.setIdClaseCuenta(clasificacion.getClaseCuenta().getId());
		ccJson.setIdEmpresa(clasificacion.getEmpresa().getId());
		return ccJson;
	}

	/**
	 * metodo elimina una clasificacion
	 * 
	 * @param pj json de clasificacion
	 * @return mensaje de exito o error
	 */
	public String eliminar(ClasificacionJson bj) {
		try {
			if (cuentaCondao.getbyIdClasificacion(bj.getId()) == null) {
				Clasificacion clasificacion = cladao.getById(bj.getId());
				cladao.eliminar(clasificacion);
				return Constantes.MENSAJE_REST_OK;
			} else {
				return "No se puede eliminar la clasificación, ya que está asociada a una cuenta contable";
			}
		} catch (Exception e) {
			log.error("No se pudo eliminar la clasificación");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener una lista de clasificaciones
	 * 
	 * @param idGrupoCuenta
	 * @return lista clasificacion
	 */
	public List<ClasificacionJson> getByIdGrupoCuenta(ClasificacionJson cj) {

		List<ClasificacionJson> lcj = new ArrayList<>();
		try {
			List<Clasificacion> c = cladao.getByIdGrupoCuenta(cj.getIdGrupoCuenta(),
					udao.getById(cj.getIdUsuario()).getOficinaContable().getId());
			for (int i = 0; i < c.size(); i++) {
				ClasificacionJson cjj = new ClasificacionJson();
				cjj.setId(c.get(i).getId());
				cjj.setNombre(c.get(i).getNombre());
				lcj.add(cjj);
			}
			return lcj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de clasificación ", e);
			return lcj;
		}

	}

}
