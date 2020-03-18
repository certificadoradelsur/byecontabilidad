package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.ComprobanteContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaContableDAO;
import cl.certificadoradelsur.byecontabilidad.entities.ComprobanteContable;
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
					c.setFecha(Utilidades.convertidorFecha(ccj.getFecha()));
					c.setTipo(ccj.getTipo());
					c.setCuentaContable(cuentadao.getById(ccj.getIdCuentaContable()));
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
	public Long countAll(
			//String glosaGeneral, Long idClaseCuenta, Long idGrupoCuenta
			) {
		try {
		//	if (glosaGeneral == null) {
		//		glosaGeneral = "";
		//	}
			return comdao.countAll(
					//glosaGeneral, idClaseCuenta, idGrupoCuenta
					);
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
	public List<ComprobanteContableJson> getAll(Integer page, Integer limit
			//, String glosaGeneral, Long idClaseCuenta, Long idGrupoCuenta
			) {
		List<ComprobanteContableJson> lcj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
		//	if (glosaGeneral == null) {
		//		glosaGeneral = "";
		//	}
			List<ComprobanteContable> lcc = comdao.getAll(inicio, limit
					//, glosaGeneral, idClaseCuenta, idGrupoCuenta
					);
			for (int i = 0; i < lcc.size(); i++) {
				ComprobanteContableJson ccj = new ComprobanteContableJson();
				ccj.setId(lcc.get(i).getId());
				ccj.setGlosaGeneral(lcc.get(i).getGlosaGeneral());
				ccj.setFecha(Utilidades.strToTsDDMMYYYYHHmmssConGuion(lcc.get(i).getFecha()));
				ccj.setGlosaGeneralCuentaContable(cuentadao.getById(ccj.getIdCuentaContable()).getDescripcion());
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
			if (comdao.getByNumero(ccj.getNumero()) == null
					|| comdao.getByNumero(ccj.getNumero()).getNumero() == c.getNumero()) {
				if ( Utilidades.containsScripting(ccj.getGlosaGeneral()).compareTo(true) == 0) {
					throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
				} else {
					/*
					cuentaContable.setGlosaGeneral(ccj.getGlosaGeneral());
					cuentaContable.setCodigo(ccj.getCodigo());
					cuentaContable.setDescripcion(ccj.getDescripcion());
					cuentaContable.setAnalisis(ccj.isAnalisis());
					// cuentaContable.setImputable(ccj.isImputable());
					cuentaContable.setImputable(true);
					cuentaContable.setConciliacion(ccj.isConciliacion());
					cuentaContable.setClaseCuenta(clasedao.getById(ccj.getIdClaseCuenta()));
					cuentaContable.setGrupoCuenta(grupodao.getById(ccj.getIdGrupoCuenta()));
					if (ccj.isAnalisis().equals(true)) {
						cuentaContable.setAnalizable(ccj.getAnalizable());
						cuentaContable.setBanco(null);
						cuentaContable.setCuenta(null);
					}
					if (ccj.isConciliacion().equals(true)) {
						cuentaContable.setBanco(bdao.getById(ccj.getIdBanco()));
						cuentaContable.setCuenta(cdao.getById(ccj.getIdCuenta()));
						cuentaContable.setAnalizable("");
					}
					comdao.update(cuentaContable);
					*/
					return Constantes.MENSAJE_REST_OK;
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
		/*
		ccJson.setId(cuentaContable.getId());
		ccJson.setCodigo(cuentaContable.getCodigo());
		ccJson.setGlosaGeneral(cuentaContable.getGlosaGeneral());
		ccJson.setDescripcion(cuentaContable.getDescripcion());
		ccJson.setAnalisis(cuentaContable.isAnalisis());
		ccJson.setConciliacion(cuentaContable.isConciliacion());
		if(cuentaContable.isAnalisis().equals(true)) {
			ccJson.setAnalizable(cuentaContable.getAnalizable());	
		}
		if(cuentaContable.isConciliacion().equals(true)) {
			ccJson.setIdBanco(cuentaContable.getBanco().getId());
			ccJson.setIdCuenta(cuentaContable.getCuenta().getId());
		}
		ccJson.setConciliacion(cuentaContable.isConciliacion());
		ccJson.setImputable(cuentaContable.isImputable());
		ccJson.setIdClaseCuenta(cuentaContable.getClaseCuenta().getId());
		ccJson.setIdGrupoCuenta(cuentaContable.getGrupoCuenta().getId());
*/
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
