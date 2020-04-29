package cl.certificadoradelsur.byecontabilidad.restdao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.conciliacion.ConciliacionBancaria;
import cl.certificadoradelsur.byecontabilidad.dao.CartolaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ClienteDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ComprobanteContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ConciliacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.MovimientoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Cartola;
import cl.certificadoradelsur.byecontabilidad.entities.ComprobanteContable;
import cl.certificadoradelsur.byecontabilidad.entities.Movimiento;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.MovimientoJson;
import cl.certificadoradelsur.byecontabilidad.json.TransaccionJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class MovimientoRD {
	private static Logger log = Logger.getLogger(MovimientoRD.class);
	@Inject
	private MovimientoDAO mdao;
	@Inject
	private UsuarioDAO udao;
	@Inject
	private CuentaDAO cuentadao;
	@Inject
	private CartolaDAO cdao;
	@Inject
	private ConciliacionBancaria cb;
	@Inject
	private CuentaContableDAO cuentaCondao;
	@Inject
	private ConciliacionDAO condao;
	@Inject
	private NoConciliadoDAO ncdao;
	@Inject
	private ComprobanteContableDAO comdao;
	@Inject
	private ClienteDAO clidao;

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front private Long id;
	 * 
	 */

	public String save(MovimientoJson mj) {
		try {

			Movimiento movimiento = new Movimiento();
			if (Utilidades.containsScripting(mj.getGlosa()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				movimiento.setGlosa(mj.getGlosa());
				movimiento.setMonto(mj.getMonto());
				movimiento.setNumComprobante(mj.getNumComprobante());
				movimiento.setTipoMovimiento(mj.getTipoMovimiento());
				movimiento.setTipoDocumento(mj.getTipoDocumento());
				movimiento.setNumDocumento(mj.getNumDocumento());
				movimiento.setEstado(mj.isEstado());
				movimiento.setFecha(Utilidades.convertidorFecha(mj.getFecha()));
//				movimiento.setEmpresa(udao.getById(mj.getIdUsuario()).getOficinaContable());
				movimiento.setUsuario(udao.getById(mj.getIdUsuario()));
				movimiento.setCuenta(cuentadao.getById(mj.getIdCuenta()));
				movimiento.setEliminado(false);
				mdao.guardar(movimiento);
			}

			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudieron guardar los movimientos ", e);
			return e.getMessage();
		}
	}

	/**
	 * Funcion elimina movimiento
	 * 
	 * @param pj json de movimiento
	 * @return mensaje de exito o error
	 */
	public String eliminar(MovimientoJson mj) {
		try {

			if (condao.getByMov(mj.getId()) == null || ncdao.getByIdMovimiento(mj.getId()) == null) {
				Movimiento movimiento = mdao.getById(mj.getId());
				mdao.eliminar(movimiento);
				return Constantes.MENSAJE_REST_OK;
			} else {
				return "No se puede eliminar el movimiento, ya que esta en uso por el proceso de conciliacíon";
			}
		} catch (Exception e) {
			log.error("No se pudo eliminar el movimiento");
			return e.getMessage();
		}

	}

	/**
	 * metodo obtener un movimiento
	 * 
	 * @param id movimiento
	 * @return json
	 */
	public MovimientoJson getById(MovimientoJson mj) {
		Movimiento m = mdao.getById(mj.getId());
		MovimientoJson ccJson = new MovimientoJson();
		ccJson.setId(m.getId());
		ccJson.setFecha(m.getFecha().toString().substring(0, 10));
		ccJson.setNumComprobante(m.getNumComprobante());
		ccJson.setGlosa(m.getGlosa());
		ccJson.setTipoMovimiento(m.getTipoMovimiento());
		ccJson.setTipoDocumento(m.getTipoDocumento());
		ccJson.setEstado(m.getEstado());
		ccJson.setMonto(m.getMonto());
		ccJson.setEliminado(m.isEliminado());
		ccJson.setAnalisis(m.getCuentaContable().isAnalisis().toString());
		ccJson.setConciliacion(m.getCuentaContable().isConciliacion().toString());
		if (m.getCuentaContable().isConciliacion().equals(true)) {
			ccJson.setIdBanco(m.getCuenta().getBanco().getId());
			ccJson.setIdCuenta(m.getCuenta().getId());
			ccJson.setNumDocumento(m.getNumDocumento());
		}
		if (m.getCuentaContable().isAnalisis().equals(true)) {
			ccJson.setIdCliente(m.getCliente().getId());
		}
		ccJson.setIdComprobanteContable(m.getComprobanteContable().getId());
		ccJson.setIdCuentaContable(m.getCuentaContable().getId());
		ccJson.setIdEmpresa(m.getEmpresa().getId());
		ccJson.setIdUsuario(m.getUsuario().getId());
		return ccJson;
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll() {
		try {
			return mdao.countAll();
		} catch (Exception e) {
			log.error("No se puede contar el total de movimientos ", e);
			return 0L;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAllM(Long id) {
		try {
			if (id == null) {
				return 0L;
			}
			return mdao.countAllM(id);
		} catch (Exception e) {
			log.error("No se puede contar el total de movimientos ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de movimientos en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de movimientos
	 */

	public List<MovimientoJson> getAll(Integer page, Integer limit) {
		List<MovimientoJson> lmj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}

			List<Movimiento> lm = mdao.getAll(inicio, limit);
			for (int i = 0; i < lm.size(); i++) {
				MovimientoJson mj = new MovimientoJson();
				mj.setId(lm.get(i).getId());
				mj.setGlosa(lm.get(i).getGlosa());
				mj.setEstado(lm.get(i).getEstado());
				mj.setMonto(lm.get(i).getMonto());
				mj.setNumDocumento(lm.get(i).getNumDocumento());
				mj.setNumComprobante(lm.get(i).getNumComprobante());
				mj.setEliminado(lm.get(i).isEliminado());
				mj.setFecha(Utilidades.strToTsDDMMYYYYHHmmssConGuion((lm.get(i).getFecha())).substring(0, 10));
				mj.setTipoDocumento(lm.get(i).getTipoDocumento());
				mj.setTipoMovimiento(lm.get(i).getTipoMovimiento());
				mj.setNumCuenta(lm.get(i).getCuenta().getNumCuenta());
				mj.setNombreBanco(lm.get(i).getCuenta().getBanco().getNombre());
				lmj.add(mj);
			}
		} catch (Exception e) {
			log.error(Constantes.MENSAJE_FAIL_LISTA, e);
		}
		return lmj;
	}

	/**
	 * Funcion que retorna el total de movimientos en json por transaccion
	 * 
	 * @return json con total de movimientos por transaccion
	 */

	public List<MovimientoJson> getAllMovimiento(TransaccionJson tj) {
		List<MovimientoJson> lmj = new ArrayList<>();
		try {
			List<Movimiento> lm = mdao.getAllMovimiento(tj.getId());
			for (int i = 0; i < lm.size(); i++) {
				MovimientoJson mj = new MovimientoJson();
				mj.setId(lm.get(i).getId());
				mj.setGlosa(lm.get(i).getGlosa());
				mj.setEstado(lm.get(i).getEstado());
				mj.setMonto(lm.get(i).getMonto());
				mj.setNumDocumento(lm.get(i).getNumDocumento());
				mj.setNumComprobante(lm.get(i).getNumComprobante());
				mj.setEliminado(lm.get(i).isEliminado());
				mj.setFecha(Utilidades.strToTsDDMMYYYYHHmmssConGuion((lm.get(i).getFecha())).substring(0, 10));
				mj.setTipoDocumento(lm.get(i).getTipoDocumento());
				mj.setTipoMovimiento(lm.get(i).getTipoMovimiento());
				mj.setNumCuenta(lm.get(i).getCuenta().getNumCuenta());
				mj.setNombreBanco(lm.get(i).getCuenta().getBanco().getNombre());
				lmj.add(mj);
			}
		} catch (Exception e) {
			log.error("No se puede obtener la lista de movimientos ", e);
		}
		return lmj;
	}

	/**
	 * Funcion que retorna el total de movimientos por id (modal)
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de movimientos
	 */

	public List<MovimientoJson> getAllM(Integer page, Integer limit, Long id) {
		List<MovimientoJson> lmj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}

			if (id == null) {
				return lmj;
			}
			List<Movimiento> lm = mdao.getAllMov(inicio, limit, id);
			for (int i = 0; i < lm.size(); i++) {
				MovimientoJson mj = new MovimientoJson();
				mj.setId(lm.get(i).getId());
				mj.setGlosa(lm.get(i).getGlosa());
				mj.setEstado(lm.get(i).getEstado());
				mj.setMonto(lm.get(i).getMonto());
				mj.setNumDocumento(lm.get(i).getNumDocumento());
				mj.setNumComprobante(lm.get(i).getNumComprobante());
				mj.setEliminado(lm.get(i).isEliminado());
				mj.setFecha(Utilidades.strToTsDDMMYYYYHHmmssConGuion((lm.get(i).getFecha())).substring(0, 10));
				mj.setTipoDocumento(lm.get(i).getTipoDocumento());

				if (lm.get(i).getTipoMovimiento().equals("INGRESO") || lm.get(i).getTipoMovimiento().equals("DEBE")) {
					mj.setTipoMovimiento("DEBE");
				} else if (lm.get(i).getTipoMovimiento().equals("EGRESO")
						|| lm.get(i).getTipoMovimiento().equals("HABER")) {
					mj.setTipoMovimiento("HABER");
				} else if (lm.get(i).getTipoMovimiento().equals("TRASPASO")) {
					if (lm.get(i).getTipoDocumento().equals("AJUSTE INGRESO")) {
						mj.setTipoMovimiento("DEBE");
					} else if (lm.get(i).getTipoDocumento().equals("AJUSTE EGRESO")) {
						mj.setTipoMovimiento("HABER");
					}
				}
				if (lm.get(i).getCuentaContable().isConciliacion().equals(true)) {
					mj.setNumCuenta(lm.get(i).getCuenta().getNumCuenta());
					mj.setNombreBanco(lm.get(i).getCuenta().getBanco().getNombre());
				} else {
					mj.setNumCuenta("");
					mj.setNombreBanco("");
				}
				lmj.add(mj);
			}
		} catch (Exception e) {
			log.error("No se puede obtener la lista de movimientos ", e);
		}
		return lmj;
	}

	/**
	 * Funcion que trae los movimientos por id comprobante contable
	 * 
	 * @param page
	 * @param limit
	 * @param id
	 * @return
	 */

	public List<MovimientoJson> getMovById(MovimientoJson mjj) {
		List<MovimientoJson> lmj = new ArrayList<>();
		try {

			List<Movimiento> lm = mdao.getAllM(mjj.getId());
			for (int i = 0; i < lm.size(); i++) {
				MovimientoJson mj = new MovimientoJson();
				mj.setId(lm.get(i).getId());
				mj.setCodigo(cuentaCondao.getById(lm.get(i).getCuentaContable().getId()).getCodigo());
				mj.setDescripcion(cuentaCondao.getById(lm.get(i).getCuentaContable().getId()).getGlosaGeneral());
				mj.setGlosa(lm.get(i).getGlosa());
				mj.setEstado(lm.get(i).getEstado());
				mj.setMonto(lm.get(i).getMonto());
				mj.setNumDocumento(lm.get(i).getNumDocumento());
				mj.setNumComprobante(lm.get(i).getNumComprobante());
				mj.setEliminado(lm.get(i).isEliminado());
				mj.setFecha(Utilidades.strToTsDDMMYYYYHHmmssConGuion((lm.get(i).getFecha())).substring(0, 10));
				mj.setTipoDocumento(lm.get(i).getTipoDocumento());
				mj.setTipoMovimiento(lm.get(i).getTipoMovimiento());
				mj.setIdCuentaContable(lm.get(i).getCuentaContable().getId());
				mj.setIdUsuario(lm.get(i).getUsuario().getId());
				if (lm.get(i).getTipoMovimiento().equals("INGRESO") || lm.get(i).getTipoMovimiento().equals("DEBE")) {
					mj.setDebe(lm.get(i).getMonto());
				} else if (lm.get(i).getTipoMovimiento().equals("EGRESO")
						|| lm.get(i).getTipoMovimiento().equals("HABER")) {
					mj.setHaber(lm.get(i).getMonto());
				} else if (lm.get(i).getTipoMovimiento().equals("TRASPASO")) {
					if (lm.get(i).getTipoDocumento().equals("AJUSTE INGRESO")) {
						mj.setDebe(lm.get(i).getMonto());
					} else if (lm.get(i).getTipoDocumento().equals("AJUSTE EGRESO")) {
						mj.setHaber(lm.get(i).getMonto());
					}
				}
				if (lm.get(i).getCuentaContable().isConciliacion().equals(true)) {
					mj.setNumCuenta(lm.get(i).getCuenta().getNumCuenta());
					mj.setNombreBanco(lm.get(i).getCuenta().getBanco().getNombre());
				} else if (lm.get(i).getCuentaContable().isAnalisis().equals(true)) {
					mj.setIdCliente(lm.get(i).getCliente().getId());
					mj.setNumCuenta("");
					mj.setNombreBanco("");
				}
				lmj.add(mj);
			}
		} catch (Exception e) {
			log.error("No se puede obtener la lista de movimientos ", e);
		}
		return lmj;
	}

	/**
	 * Funcion que trae todos los movimientos y cartolas en el rango de fecha
	 * 
	 * @return json con total de movimientos
	 * @throws ParseException
	 */
	public String getAllLista(MovimientoJson mj) {
		try {
			List<Movimiento> lm = mdao.getAllLista(Utilidades.convertidorFecha(mj.getFechaI()),
					Utilidades.convertidorFecha(mj.getFechaF()), mj.getIdBanco(), mj.getIdCuenta(), mj.getIdEmpresa());
			List<Cartola> lc = cdao.getAllLista(Utilidades.convertidorFecha(mj.getFechaI()),
					Utilidades.convertidorFecha(mj.getFechaF()), mj.getIdBanco(), mj.getIdCuenta(), mj.getIdEmpresa());

			if (lm.isEmpty() && lc.isEmpty()) {
				return Constantes.MENSAJE_REST_FAIL;
			}
			cb.conciliar(lm, lc);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error(Constantes.MENSAJE_REST_FAIL, e);
			return e.getMessage();
		}
	}

	/**
	 * metodo que modifica movimineto
	 * 
	 * @param pj json de movimientoJson
	 * @return mensaje de exito o error
	 */
	public String update(MovimientoJson mj) {
		try {
			Movimiento m = mdao.getById(mj.getId());
			if (condao.getByIdComprobante(m.getComprobanteContable().getId()) == null
					&& ncdao.getByIdComprobante(m.getComprobanteContable().getId()) == null) {
				if (Utilidades.containsScripting(mj.getGlosa()).compareTo(true) == 0) {
					throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
				} else {
					m.setCuentaContable(cuentaCondao.getById(mj.getIdCuentaContable()));
					m.setTipoMovimiento(mj.getTipoMovimiento());
					m.setTipoDocumento(mj.getTipoDocumento());
					m.setMonto(mj.getMonto());
					m.setGlosa(mj.getGlosa());
					if (cuentaCondao.getById(mj.getIdCuentaContable()).isConciliacion().equals(false)) {
						m.setCuenta(null);
					} else if (cuentaCondao.getById(mj.getIdCuentaContable()).isConciliacion().equals(true)) {
						m.setCuenta(cuentadao.getById(mj.getIdCuenta()));
					}
					if (cuentaCondao.getById(mj.getIdCuentaContable()).isAnalisis().equals(false)) {
						m.setCliente(null);
					} else if (cuentaCondao.getById(mj.getIdCuentaContable()).isAnalisis().equals(true)) {
						m.setCliente(clidao.getById(mj.getIdCliente()));
					}
					m.setNumDocumento(mj.getNumDocumento());
					m.setEstado(mj.isEstado());
				}
				mdao.update(m);
				return Constantes.MENSAJE_REST_OK;
			} else {
				return "No se puede modificar el movimiento, ya que esta en uso por el proceso de conciliacíon";
			}
		} catch (Exception e) {
			log.error("No se pudo modificar el movimiento");
			return e.getMessage();
		}
	}

	/**
	 * Funcion que elimina transaccion y movimientos relacionados a dicha
	 * transaccion
	 * 
	 * @param mj
	 * @return mensaje hacia el front
	 */
	public String delete(MovimientoJson mj) {

		if (condao.getByIdComprobante(mj.getIdComprobanteContable()) == null
				&& ncdao.getByIdComprobante(mj.getIdComprobanteContable()) == null) {
			List<Movimiento> lm = mdao.getByIdComprobante(mj.getIdComprobanteContable());
			for (int i = 0; i < lm.size(); i++) {
				mdao.eliminar(lm.get(i));
			}
			ComprobanteContable c = comdao.getById(mj.getId());
			comdao.eliminar(c);
			return Constantes.MENSAJE_REST_OK;
		} else {
			return "No se puede eliminar el comprobante, ya que esta en uso por el proceso de conciliacíon";
		}

	}

}
