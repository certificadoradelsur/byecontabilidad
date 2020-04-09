package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BancoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CartolaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.MovimientoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Cuenta;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.CuentaJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class CuentaRD {
	private static Logger log = Logger.getLogger(CuentaRD.class);
	@Inject
	private CuentaDAO cdao;
	@Inject
	private BancoDAO bdao;
	@Inject
	private CartolaDAO cardao;
	@Inject
	private MovimientoDAO mdao;
	@Inject
	private EmpresaDAO edao;
	@Inject
	private UsuarioDAO udao;

	

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(CuentaJson cj) {
		try {
			Cuenta cuenta = new Cuenta();
			if (Utilidades.containsScripting(cj.getNumCuenta()).compareTo(true) == 0
					|| Utilidades.containsScripting(cj.getNombreEjecutivo()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				cuenta.setNumCuenta(cj.getNumCuenta());
				cuenta.setNombreEjecutivo(cj.getNombreEjecutivo());
				cuenta.setSaldoInicial(cj.getSaldoInicial());
				cuenta.setBanco(bdao.getById(cj.getIdBanco()));
				cuenta.setEmpresa(edao.getById(cj.getIdEmpresa()));
				cuenta.setEliminado(false);
				cdao.guardar(cuenta);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (

		Exception e) {
			log.error("No se pudo guardar la cuenta ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String numCuenta,Long idEmpresa, String idUsuario) {
		try {
			if (numCuenta == null) {
				numCuenta = "";
			}
			return cdao.countAll(numCuenta,idEmpresa, udao.getById(idUsuario).getOficinaContable().getId());
		} catch (Exception e) {
			log.error("No se puede contar el total de cuentas ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de cuentas en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de cuentas
	 */
	public List<CuentaJson> getAll(Integer page, Integer limit, String numCuenta,Long idEmpresa, String idUsuario) {
		List<CuentaJson> lcj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if (numCuenta == null) {
				numCuenta = "";
			}
			List<Cuenta> lc = cdao.getAll(inicio, limit, numCuenta,idEmpresa, udao.getById(idUsuario).getOficinaContable().getId());
			for (int i = 0; i < lc.size(); i++) {
				CuentaJson cj = new CuentaJson();
				cj.setId(lc.get(i).getId());
				cj.setNombreBanco(bdao.getById(lc.get(i).getBanco().getId()).getNombre());
				cj.setNumCuenta(lc.get(i).getNumCuenta());
				cj.setNombreEjecutivo(lc.get(i).getNombreEjecutivo());
				cj.setSaldoInicial(lc.get(i).getSaldoInicial());
				cj.setRazonSocialEmpresa(lc.get(i).getEmpresa().getRazonSocial());
				lcj.add(cj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de cuentas ", e);
		}
		return lcj;
	}

	/**
	 * metodo que modifica cuenta
	 * 
	 * @param pj json de cuentaJson
	 * @return mensaje de exito o error
	 */
	public String update(CuentaJson cj) {
		try {
			Cuenta cuenta = cdao.getById(cj.getId());
			if (Utilidades.containsScripting(cj.getNumCuenta()).compareTo(true) == 0
					|| Utilidades.containsScripting(cj.getNombreEjecutivo()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {

				cuenta.setBanco(bdao.getById(cj.getIdBanco()));
				cuenta.setNumCuenta(cj.getNumCuenta());
				cuenta.setNombreEjecutivo(cj.getNombreEjecutivo());
				cuenta.setSaldoInicial(cj.getSaldoInicial());
				cuenta.setEmpresa(edao.getById(cj.getIdEmpresa()));
				cdao.update(cuenta);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar la cuenta");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener una Cuenta
	 * 
	 * @param id de Cuenta
	 * @return mensaje de exito o error
	 */
	public CuentaJson getById(CuentaJson cj) {
		Cuenta cuenta = cdao.getById(cj.getId());
		CuentaJson cJson = new CuentaJson();
		cJson.setId(cuenta.getId());
		cJson.setIdBanco(cuenta.getBanco().getId());
		cJson.setNumCuenta(cuenta.getNumCuenta());
		cJson.setNombreEjecutivo(cuenta.getNombreEjecutivo());
		cJson.setSaldoInicial(cuenta.getSaldoInicial());
		cJson.setIdEmpresa(cuenta.getEmpresa().getId());
		return cJson;
	}

	/**
	 * metodo elimina un cuenta
	 * 
	 * @param pj json de cuenta
	 * @return mensaje de exito o error
	 */
	public String eliminar(CuentaJson cj) {
		try {
			
			if (mdao.getByIdCuenta(cj.getId())==null && cardao.getByIdCuenta(cj.getId())==null) {
			Cuenta cuenta = cdao.getById(cj.getId());
			cuenta.setEliminado(true);
			cdao.update(cuenta);
			return Constantes.MENSAJE_REST_OK;
			} else {
				return "No se puede eliminar la cuenta, ya que esta en uso";
			}
		} catch (Exception e) {
			log.error("No se pudo eliminar la cuenta");
			return e.getMessage();
		}
	}

	/**
	 * funcion que busca cuenta por idbanco
	 * @param idBanco
	 * @return
	 */
	public List<CuentaJson> getByIdBanco(CuentaJson bj) {

		List<CuentaJson> lcj = new ArrayList<>();
		try {
			List<Cuenta> b = cdao.getByIdBanco(bj.getIdBanco(),udao.getById(bj.getIdUsuario()).getOficinaContable().getId());
			for (int i = 0; i < b.size(); i++) {
				CuentaJson bjj = new CuentaJson();
				bjj.setId(b.get(i).getId());
				bjj.setNumCuenta(b.get(i).getNumCuenta());
				lcj.add(bjj);
			}
			return lcj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de cuenta ", e);
			return lcj;
		}

	}
}
