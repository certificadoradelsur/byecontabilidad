package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaContableDAO;
import cl.certificadoradelsur.byecontabilidad.entities.CuentaContable;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.CuentaContableJson;
import cl.certificadoradelsur.utils.Constantes;
import cl.certificadoradelsur.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class CuentaContableRD {
	private static Logger log = Logger.getLogger(CuentaContableRD.class);
	@Inject
	private CuentaContableDAO cuentadao;

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(CuentaContableJson ccj) {
		try {
			CuentaContable cuentaContable = cuentadao.getById(ccj.getId());
			if (Utilidades.containsScripting(ccj.getDescripcion()).compareTo(true) == 0
					|| Utilidades.containsScripting(ccj.getGlosaGeneral()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				cuentaContable.setGlosaGeneral(ccj.getGlosaGeneral());
				cuentaContable.setDescripcion(ccj.getDescripcion());
				cuentaContable.setAnalisis(ccj.isAnalisis());
				cuentaContable.setImputable(ccj.isImputable());

				cuentadao.guardar(cuentaContable);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo guardar la cuenta contable ", e);
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

			return cuentadao.countAll();
		} catch (Exception e) {
			log.error("No se puede contar el total de cuenta contable ", e);
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
	public List<CuentaContableJson> getAll(Integer page, Integer limit) {
		List<CuentaContableJson> lbj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}

			List<CuentaContable> lcc = cuentadao.getAll(inicio, limit);
			for (int i = 0; i < lcc.size(); i++) {
				CuentaContableJson ccj = new CuentaContableJson();
				ccj.setId(lcc.get(i).getId());
				ccj.setAnalisis(lcc.get(i).isAnalisis());
				ccj.setDescripcion(lcc.get(i).getDescripcion());
				ccj.setGlosaGeneral(lcc.get(i).getGlosaGeneral());
				ccj.setImputable(lcc.get(i).isImputable());

				lbj.add(ccj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de cuenta contable ", e);
		}
		return lbj;
	}

	/**
	 * metodo que modifica Banco
	 * 
	 * @param pj json de BancoJson
	 * @return mensaje de exito o error
	 */
	public String update(CuentaContableJson ccj) {
		try {
			CuentaContable cuentaContable = cuentadao.getById(ccj.getId());
			if (Utilidades.containsScripting(ccj.getDescripcion()).compareTo(true) == 0
					|| Utilidades.containsScripting(ccj.getGlosaGeneral()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				cuentaContable.setGlosaGeneral(ccj.getGlosaGeneral());
				cuentaContable.setDescripcion(ccj.getDescripcion());
				cuentadao.update(cuentaContable);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar el cuenta contable");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener una cuenta contable
	 * 
	 * @param id de cuenta contable
	 * @return mensaje de exito o error
	 */
	public CuentaContableJson getById(CuentaContableJson bj) {
		CuentaContable cuentaContable = cuentadao.getById(bj.getId());
		CuentaContableJson ccJson = new CuentaContableJson();
		ccJson.setId(cuentaContable.getId());
		ccJson.setGlosaGeneral(cuentaContable.getGlosaGeneral());
		ccJson.setDescripcion(cuentaContable.getDescripcion());
		ccJson.setAnalisis(cuentaContable.isAnalisis());
		ccJson.setImputable(cuentaContable.isImputable());
//		ccJson.setIdClaseCuenta(idClaseCuenta);
//		ccJson.setIdGrupoCuenta(idGrupoCuenta);
		return ccJson;
	}

	/**
	 * metodo elimina una cuenta contable
	 * 
	 * @param pj json de cuenta contable
	 * @return mensaje de exito o error
	 */
	public String eliminar(CuentaContableJson bj) {
		try {
			CuentaContable cuentaContable = cuentadao.getById(bj.getId());
			cuentadao.update(cuentaContable);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la  cuenta contable");
			return e.getMessage();
		}
	}

}
