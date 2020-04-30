package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.CuentaContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.SucursalDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Sucursal;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.SucursalJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

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
	@Inject
	private UsuarioDAO udao;
	@Inject
	private CuentaContableDAO cuentaCondao;

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
	public Long countAll(Long idEmpresa, String idUsuario) {
		try {

			return sudao.countAll(idEmpresa, udao.getById(idUsuario).getOficinaContable().getId());
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
	public List<SucursalJson> getAll(Integer page, Integer limit, Long idEmpresa, String idUsuario) {
		List<SucursalJson> lsj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}

			List<Sucursal> ls = sudao.getAll(inicio, limit, idEmpresa,
					udao.getById(idUsuario).getOficinaContable().getId());
			for (int i = 0; i < ls.size(); i++) {
				SucursalJson sj = new SucursalJson();
				sj.setCodigo(ls.get(i).getCodigo());
				sj.setDireccion(ls.get(i).getDireccion());
				sj.setNombreEmpresa(edao.getById(ls.get(i).getEmpresa().getId()).getRazonSocial());
				lsj.add(sj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de clasificaciones ", e);
		}
		return lsj;
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
		SucursalJson sJson = new SucursalJson();
		sJson.setCodigo(s.getCodigo());
		sJson.setDireccion(s.getDireccion());
		sJson.setIdEmpresa(s.getEmpresa().getId());
		return sJson;
	}

	/**
	 * metodo elimina una sucursal
	 * 
	 * @param pj json de sucursal
	 * @return mensaje de exito o error
	 */
	public String eliminar(SucursalJson bj) {
		try {
			if(cuentaCondao.getbyIdSucursal(bj.getCodigo())==null){
			Sucursal s = sudao.getById(bj.getCodigo());
			sudao.eliminar(s);
			return Constantes.MENSAJE_REST_OK;
			} else {
				return "No se puede eliminar la sucursal, ya que está asociada a una cuenta contable";
			}
		} catch (Exception e) {
			log.error("No se pudo eliminar la sucursal");
			return e.getMessage();
			
		}
	}

	/**
	 * funcion que busca sucursal por idEmpresa
	 * 
	 * @param idEmpresa
	 * @return
	 */
	public List<SucursalJson> getByIdEmpresa(SucursalJson sj) {

		List<SucursalJson> lcj = new ArrayList<>();
		try {
			List<Sucursal> s = sudao.getByIdEmpresa(sj.getIdEmpresa(),
					udao.getById(sj.getIdUsuario()).getOficinaContable().getId());
			for (int i = 0; i < s.size(); i++) {
				SucursalJson bjj = new SucursalJson();
				bjj.setCodigo(s.get(i).getCodigo());
				bjj.setDireccion(s.get(i).getDireccion());
				lcj.add(bjj);
			}
			return lcj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de cuenta ", e);
			return lcj;
		}

	}
}
