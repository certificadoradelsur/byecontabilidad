package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.ClienteDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ComprobanteContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ConciliacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.ComprobanteContable;
import cl.certificadoradelsur.byecontabilidad.entities.Movimiento;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.ComprobanteContableJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class ComprobanteContableRD {
	private static Logger log = Logger.getLogger(ComprobanteContableRD.class);
	@Inject
	private ComprobanteContableDAO comdao;
	@Inject
	private CuentaContableDAO cuentadao;
	@Inject
	private EmpresaDAO edao;
	@Inject
	private CuentaDAO cdao;
	@Inject
	private UsuarioDAO udao;
	@Inject
	private ConciliacionDAO condao;
	@Inject
	private NoConciliadoDAO ncdao;
	@Inject
	private ClienteDAO clientedao;

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(ComprobanteContableJson ccj) {
		try {
			ComprobanteContable c = new ComprobanteContable();
			if (comdao.getByNumero(ccj.getNumero()) == null) {
				if (Utilidades.containsScripting(ccj.getGlosaGeneral()).compareTo(true) == 0) {
					throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
				} else {
					c.setGlosaGeneral(ccj.getGlosaGeneral());
					c.setNumero(ccj.getNumero());
					c.setFecha(Utilidades.convertidorFechaSinHora(ccj.getFecha()));
					c.setEmpresa(edao.getById(ccj.getIdEmpresa()));
					c.setBorrador(ccj.isBorrador());
					List<Movimiento> movimientos = new ArrayList<>();
					for (int i = 0; i < ccj.getMovimientos().size(); i++) {
						Movimiento movimiento = new Movimiento();
						if (Utilidades.containsScripting(ccj.getMovimientos().get(i).getGlosa()).compareTo(true) == 0) {
							throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
						} else {
							movimiento.setGlosa(ccj.getMovimientos().get(i).getGlosa());
							movimiento.setMonto(ccj.getMovimientos().get(i).getMonto());
							movimiento.setTipoMovimiento(ccj.getMovimientos().get(i).getTipoMovimiento());
							movimiento.setTipoDocumento(ccj.getMovimientos().get(i).getTipoDocumento());
							movimiento.setNumComprobante(ccj.getMovimientos().get(i).getNumComprobante());
							movimiento.setNumDocumento(ccj.getMovimientos().get(i).getNumDocumento());
							movimiento.setEstado(ccj.getMovimientos().get(i).isEstado());
							movimiento.setFecha(
									Utilidades.convertidorFechaSinHora(ccj.getMovimientos().get(i).getFecha()));
							movimiento.setEmpresa(edao.getById(cuentadao
									.getById(ccj.getMovimientos().get(i).getIdCuentaContable()).getEmpresa().getId()));
							movimiento.setUsuario(udao.getById(ccj.getMovimientos().get(i).getIdUsuario()));
							if (cuentadao.getById(ccj.getMovimientos().get(i).getIdCuentaContable()).isConciliacion()
									.equals(true)) {
								movimiento.setCuenta(cdao.getById(ccj.getMovimientos().get(i).getIdCuenta()));
							}
							if(cuentadao.getById(ccj.getMovimientos().get(i).getIdCuentaContable()).isAnalisis()
									.equals(true)) {
								movimiento.setCliente(clientedao.getById(ccj.getMovimientos().get(i).getIdCliente()));
							}
							movimiento.setCuentaContable(
									cuentadao.getById(ccj.getMovimientos().get(i).getIdCuentaContable()));
							movimiento.setEliminado(false);
							movimiento.setComprobanteContable(c);
							movimientos.add(movimiento);
						}
					}
//					c.setCuentasContables(cuentasContables);
					c.setMovimientos(movimientos);
					comdao.guardar(c);
					return Constantes.MENSAJE_REST_OK;
				}
			} else {
				return "El número ingresado, ya se encuentra registrado";
			}

		} catch (Exception e) {
			log.error("No se pudo guardar el comprobante contable ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String glosaGeneral, String idUsuario) {
		try {
			if (glosaGeneral == null) {
				glosaGeneral = "";
			}
			return comdao.countAll(glosaGeneral, udao.getById(idUsuario).getOficinaContable().getId());
		} catch (Exception e) {
			log.error("No se puede contar el total de comprobantes contables ", e);
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
	public List<ComprobanteContableJson> getAll(Integer page, Integer limit, String glosaGeneral, String idUsuario) {
		List<ComprobanteContableJson> lcj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if (glosaGeneral == null) {
				glosaGeneral = "";
			}
			List<ComprobanteContable> lcc = comdao.getAll(inicio, limit, glosaGeneral,
					udao.getById(idUsuario).getOficinaContable().getId());
			for (int i = 0; i < lcc.size(); i++) {
				ComprobanteContableJson ccj = new ComprobanteContableJson();
				ccj.setId(lcc.get(i).getId());
				ccj.setGlosaGeneral(lcc.get(i).getGlosaGeneral());
				ccj.setNumero(lcc.get(i).getNumero());
				ccj.setFecha(Utilidades.strToTsDDMMYYYYHHmmssConGuion(lcc.get(i).getFecha()).substring(0, 10));
				ccj.setNombreEmpresa(lcc.get(i).getEmpresa().getRazonSocial());
				ccj.setBorrador(lcc.get(i).isBorrador());
				lcj.add(ccj);
			}
		} catch (Exception e) {
			log.error("No se puede obtener la lista de comprobantes contables ", e);
		}
		return lcj;
	}

	/**
	 * metodo que modifica la comprobante contable
	 * 
	 * @param pj json de cuentaContableJson
	 * @return mensaje de exito o error
	 */
	public String update(ComprobanteContableJson ccj) {
		try {
			ComprobanteContable c = comdao.getById(ccj.getId());
			if (condao.getByIdComprobante(c.getId()) == null && ncdao.getByIdComprobante(c.getId()) == null) {
				if (comdao.getByNumero(ccj.getNumero()) == null
						|| comdao.getByNumero(ccj.getNumero()).getNumero().equals(c.getNumero())) {
					if (Utilidades.containsScripting(ccj.getGlosaGeneral()).compareTo(true) == 0) {
						throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
					} else {
						c.setNumero(ccj.getNumero());
						c.setGlosaGeneral(ccj.getGlosaGeneral());
						c.setFecha(Utilidades.convertidorFechaSinHora((ccj.getFecha())));
						c.setBorrador(ccj.isBorrador());

						for (int i = 0; i < c.getMovimientos().size(); i++) {
							Movimiento m = c.getMovimientos().get(i);
							m.setNumComprobante(ccj.getNumero());
							m.setFecha(Utilidades.convertidorFechaSinHora((ccj.getFecha())));
						}
						comdao.update(c);
						return Constantes.MENSAJE_REST_OK;
					}
				} else {
					return "No se puede modificar el comprobante contable, ya que esta en uso por el proceso de conciliacíon";
				}
			} else {
				return "El codigo ingresado, ya se encuentra registrado";
			}

		} catch (Exception e) {
			log.error("No se pudo modificar la cuenta contable");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener una cuenta contable
	 * 
	 * @param id de cuenta contable
	 * @return mensaje de exito o error
	 */
	public ComprobanteContableJson getById(ComprobanteContableJson bj) {
		ComprobanteContable c = comdao.getById(bj.getId());
		ComprobanteContableJson ccJson = new ComprobanteContableJson();
		ccJson.setId(c.getId());
		ccJson.setNumero(c.getNumero());
		ccJson.setGlosaGeneral(c.getGlosaGeneral());
		ccJson.setFecha(c.getFecha().toString().substring(0, 10));
		ccJson.setIdEmpresa(c.getEmpresa().getId());
		ccJson.setNombreEmpresa(edao.getById(c.getEmpresa().getId()).getRazonSocial());
		ccJson.setBorrador(c.isBorrador());
		return ccJson;
	}

	/**
	 * metodo elimina una comprobante contable
	 * 
	 * @param pj json de comprobante contable
	 * @return mensaje de exito o error
	 */
	public String eliminar(ComprobanteContableJson bj) {
		try {
			ComprobanteContable c = comdao.getById(bj.getId());
			comdao.eliminar(c);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar el comprobante contable");
			return e.getMessage();
		}
	}

	public Long getMaxNumero() {
		return comdao.getMaxNumero();
	}
}
