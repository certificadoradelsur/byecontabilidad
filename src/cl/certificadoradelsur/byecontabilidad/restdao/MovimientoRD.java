package cl.certificadoradelsur.byecontabilidad.restdao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.conciliacion.ConciliacionBancaria;
import cl.certificadoradelsur.byecontabilidad.dao.CartolaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ConciliacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.MovimientoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.TransaccionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Cartola;
import cl.certificadoradelsur.byecontabilidad.entities.Movimiento;
import cl.certificadoradelsur.byecontabilidad.entities.Transaccion;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.MovimientoJson;
import cl.certificadoradelsur.byecontabilidad.json.TransaccionJson;
import cl.certificadoradelsur.utils.Constantes;
import cl.certificadoradelsur.utils.Utilidades;

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
	private TransaccionDAO tdao;
	@Inject
	private ConciliacionDAO condao;
	@Inject
	private NoConciliadoDAO ncdao;

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
	 * Funcion modifica movimiento
	 * 
	 * @param pj json de movimiento
	 * @return mensaje de exito o error
	 */
	public String eliminar(MovimientoJson mj) {
		try {

			if (condao.getByMov(mj.getId()) == null && ncdao.getByIdMovimiento(mj.getId()) == null) {

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
	 * Funcion que retorna el total de movimientos en json
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
			List<Movimiento> lm = mdao.getAllM(inicio, limit, id);
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
	 * Funcion que trae todos los movimientos y cartolas en el rango de fecha
	 * 
	 * @return json con total de movimientos
	 * @throws ParseException
	 */
	public String getAllLista(MovimientoJson mj) {
		try {
			List<Movimiento> lm = mdao.getAllLista(Utilidades.convertidorFecha(mj.getFechaI()),
					Utilidades.convertidorFecha(mj.getFechaF()), mj.getIdBanco());
			List<Cartola> lc = cdao.getAllLista(Utilidades.convertidorFecha(mj.getFechaI()),
					Utilidades.convertidorFecha(mj.getFechaF()), mj.getIdBanco());
					 
			if(lm.isEmpty() && lc.isEmpty()) {
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
	 * Funcion que elimina transaccion y movimientos relacionados a dicha
	 * transaccion
	 * 
	 * @param mj
	 * @return mensaje hacia el front
	 */
	public String delete(MovimientoJson mj) {

		if (condao.getByIdTransaccion(mj.getIdTransaccion()) == null
				&& ncdao.getByIdTransaccion(mj.getIdTransaccion()) == null) {
			List<Movimiento> lm = mdao.getByIdTransaccion(mj.getIdTransaccion());
			for (int i = 0; i < lm.size(); i++) {
				mdao.eliminar(lm.get(i));
			}
			Transaccion transaccion = tdao.getById(mj.getId());
			tdao.eliminar(transaccion);
			return Constantes.MENSAJE_REST_OK;
		} else {
			return "No se puede eliminar la transaccion, ya que esta en uso por el proceso de conciliacíon";
		}

	}

}
