package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.CuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.TransaccionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Movimiento;
import cl.certificadoradelsur.byecontabilidad.entities.Transaccion;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.TransaccionJson;
import cl.certificadoradelsur.utils.Constantes;
import cl.certificadoradelsur.utils.Utilidades;
/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
public class TransaccionRD {
	private static Logger log = Logger.getLogger(TransaccionRD.class);
	@Inject
	private UsuarioDAO udao;
	@Inject
	private CuentaDAO cuentadao;
	@Inject
	private TransaccionDAO tdao;


	public String save(TransaccionJson tj) {
		try {
			Transaccion transaccion = new Transaccion();
			if (Utilidades.containsScripting(tj.getGlosaTransaccion()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {

				transaccion.setGlosaTransaccion(tj.getGlosaTransaccion());
				transaccion.setNumTransaccion(tj.getNumTransaccion());
				transaccion.setEliminado(false);
				List<Movimiento> movimientos = new ArrayList<>();
				for (int i = 0; i < tj.getMovimientos().size(); i++) {
					Movimiento movimiento = new Movimiento();
					if (Utilidades.containsScripting(tj.getMovimientos().get(i).getGlosa()).compareTo(true) == 0) {
						throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
					} else {
						movimiento.setGlosa(tj.getMovimientos().get(i).getGlosa());
						movimiento.setMonto(tj.getMovimientos().get(i).getMonto());
						movimiento.setTipoMovimiento(tj.getMovimientos().get(i).getTipoMovimiento());
						movimiento.setTipoDocumento(tj.getMovimientos().get(i).getTipoDocumento());
						movimiento.setNumComprobante(tj.getMovimientos().get(i).getNumComprobante());
						movimiento.setNumDocumento(tj.getMovimientos().get(i).getNumDocumento());
						movimiento.setEstado(tj.getMovimientos().get(i).isEstado());
						movimiento.setFecha(Utilidades.convertidorFecha(tj.getMovimientos().get(i).getFecha()));
//						movimiento.setEmpresa(udao.getById(tj.getMovimientos().get(i).getIdUsuario()).getOficinaContable());
						movimiento.setUsuario(udao.getById(tj.getMovimientos().get(i).getIdUsuario()));
						movimiento.setCuenta(cuentadao.getById(tj.getMovimientos().get(i).getIdCuenta()));
						movimiento.setEliminado(false);
						movimiento.setTransaccion(transaccion);
						movimientos.add(movimiento);
					}
				}
				transaccion.setMovimientos(movimientos);
				tdao.guardar(transaccion);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo guardar la transaccion ", e);
			return e.getMessage();
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String glosaTransaccion) {
		try {
			if (glosaTransaccion == null) {
				glosaTransaccion = "";
			}
			return tdao.countAll(glosaTransaccion);
		} catch (Exception e) {
			log.error("No se puede contar el total de transacciones ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de transacciones en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de transacciones
	 */

	public List<TransaccionJson> getAll(Integer page, Integer limit, String glosaTransaccion) {
		List<TransaccionJson> ltj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if (glosaTransaccion == null) {
				glosaTransaccion = "";
			}
			List<Transaccion> lt = tdao.getAll(inicio, limit, glosaTransaccion);
			for (int i = 0; i < lt.size(); i++) {
				TransaccionJson tj = new TransaccionJson();
				tj.setId(lt.get(i).getId());
				tj.setGlosaTransaccion(lt.get(i).getGlosaTransaccion());
				tj.setNumTransaccion(lt.get(i).getNumTransaccion());
				tj.setEliminado(lt.get(i).isEliminado());
				
				ltj.add(tj);
			}
		} catch (Exception e) {
			log.error("No se puede obtener la lista de transacciones ", e);
		}
		return ltj;
	}

	/**
	 * metodo obtener un Transaccion
	 * 
	 * @param id de Transaccion
	 * @return mensaje de exito o error
	 */
	public TransaccionJson getById(TransaccionJson tj) {
		Transaccion transaccion = tdao.getById(tj.getId());
		TransaccionJson tJson = new TransaccionJson();
		tJson.setId(transaccion.getId());
		tJson.setNumTransaccion(transaccion.getNumTransaccion());
		return tJson;
	}

	/**
	 * metodo elimina un transaccion
	 * 
	 * @param pj json de transaccion
	 * @return mensaje de exito o error
	 */
	public String eliminar(TransaccionJson tj) {
		try {
			
			Transaccion transaccion = tdao.getById(tj.getId());
			transaccion.setEliminado(true);
			tdao.update(transaccion);
			
			
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la transaccion, ya que posee movimientos en Proceso conciliación");
			return e.getMessage();
		}
	}
	
	public Long getByT() {
		return tdao.getByT();	
	}
}
