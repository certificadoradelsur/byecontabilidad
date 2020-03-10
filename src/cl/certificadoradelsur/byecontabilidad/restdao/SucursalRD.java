package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.SucursalDAO;
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
	private SucursalDAO sudao;
	@Inject
	private EmpresaDAO edao;

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(SucursalJson ccj) {
		try {
			Sucursal s = new Sucursal();
			if (Utilidades.containsScripting(ccj.getDireccion()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				s.setDireccion(ccj.getDireccion());
				s.setEmpresa(edao.getById(ccj.getIdEmpresa()));
				sudao.guardar(s);
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
	public Long countAll(String nombreEmpresa) {
		try {
			if(nombreEmpresa==null) {
				nombreEmpresa="";
			}
			return sudao.countAll(nombreEmpresa);
		} catch (Exception e) {
			log.error("No se puede contar el total de sucursal ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de Sucursales en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de Bancos
	 */
	public List<SucursalJson> getAll(Integer page, Integer limit, String nombreEmpresa) {
		List<SucursalJson> lbj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}

			if(nombreEmpresa==null) {
				nombreEmpresa="";
			}
			List<Sucursal> lcc = sudao.getAll(inicio, limit, nombreEmpresa);
			for (int i = 0; i < lcc.size(); i++) {
				SucursalJson ccj = new SucursalJson();
				ccj.setCodigo(lcc.get(i).getCodigo());
				ccj.setDireccion(lcc.get(i).getDireccion());
				ccj.setNombreEmpresa(edao.getById(lcc.get(i).getEmpresa().getId()).getRazonSocial());			
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
			Sucursal s = sudao.getById(ccj.getCodigo());
			if (Utilidades.containsScripting(ccj.getDireccion()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				s.setDireccion(ccj.getDireccion());
				s.setEmpresa(edao.getById(ccj.getIdEmpresa()));
				sudao.update(s);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar la sucursal");
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
		Sucursal s = sudao.getById(bj.getCodigo());
		SucursalJson ccJson = new SucursalJson();
		ccJson.setCodigo(s.getCodigo());
		ccJson.setDireccion(s.getDireccion());	
		ccJson.setIdEmpresa(s.getEmpresa().getId());
		return ccJson;
	}

	/**
	 * metodo elimina una sucursal
	 * 
	 * @param pj json de sucursal
	 * @return mensaje de exito o error
	 */
	public String eliminar(SucursalJson bj) {
		try {
			Sucursal s = sudao.getById(bj.getCodigo());
			sudao.eliminar(s);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la sucursal");
			return e.getMessage();
		}
	}
		
}
