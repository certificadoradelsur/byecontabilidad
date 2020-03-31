package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BancoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ClaseCuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ClasificacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.GrupoCuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.CuentaContable;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.CuentaContableJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

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
	@Inject
	private ClaseCuentaDAO clasedao;
	@Inject
	private GrupoCuentaDAO grupodao;
	@Inject
	private ClasificacionDAO clasificaciondao;
	@Inject
	private BancoDAO bdao;
	@Inject
	private CuentaDAO cdao;
	@Inject
	private UsuarioDAO udao;
	@Inject
	private EmpresaDAO edao;
	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(CuentaContableJson ccj) {
		try {
			CuentaContable cuentaContable = new CuentaContable();

			if (cuentadao.getByCodigo(ccj.getCodigo()) == null) {
				if (Utilidades.containsScripting(ccj.getDescripcion()).compareTo(true) == 0
						|| Utilidades.containsScripting(ccj.getGlosaGeneral()).compareTo(true) == 0) {
					throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
				} else {
					cuentaContable.setGlosaGeneral(ccj.getGlosaGeneral());
					cuentaContable.setCodigo(ccj.getCodigo());
					cuentaContable.setDescripcion(ccj.getDescripcion());
					cuentaContable.setAnalisis(ccj.isAnalisis());
					cuentaContable.setImputable(true);
					cuentaContable.setConciliacion(ccj.isConciliacion());
					cuentaContable.setClaseCuenta(clasedao.getById(ccj.getIdClaseCuenta()));
					cuentaContable.setGrupoCuenta(grupodao.getById(ccj.getIdGrupoCuenta()));
					cuentaContable.setEmpresa(edao.getById(ccj.getIdEmpresa()));
					if (ccj.isConciliacion().equals(true)) {
						cuentaContable.setBanco(bdao.getById(ccj.getIdBanco()));
						cuentaContable.setCuenta(cdao.getById(ccj.getIdCuenta()));
					}
					cuentadao.guardar(cuentaContable);
					return Constantes.MENSAJE_REST_OK;
				}
			} else {
				return "El codigo ingresado, ya se encuentra registrado";
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
	public Long countAll(String glosaGeneral, Long idClaseCuenta, Long idGrupoCuenta,String idUsuario) {
		try {
			if (glosaGeneral == null) {
				glosaGeneral = "";
			}
			return cuentadao.countAll(glosaGeneral, idClaseCuenta, idGrupoCuenta, udao.getById(idUsuario).getOficinaContable().getId());
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
	public List<CuentaContableJson> getAll(Integer page, Integer limit, String glosaGeneral, Long idClaseCuenta,
			Long idGrupoCuenta, String idUsuario) {
		List<CuentaContableJson> lcj = new ArrayList<>();
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
			List<CuentaContable> lcc = cuentadao.getAll(inicio, limit, glosaGeneral, idClaseCuenta, idGrupoCuenta, udao.getById(idUsuario).getOficinaContable().getId());
			for (int i = 0; i < lcc.size(); i++) {
				CuentaContableJson ccj = new CuentaContableJson();
				ccj.setId(lcc.get(i).getId());
				ccj.setCodigo(lcc.get(i).getCodigo());
				ccj.setAnalisis(lcc.get(i).isAnalisis());
				if (lcc.get(i).getDescripcion().equals("Sin descripción")) {
					ccj.setDescripcion(lcc.get(i).getDescripcion());
				} else {
					ccj.setDescripcion(
							clasificaciondao.getById(Long.parseLong(lcc.get(i).getDescripcion())).getNombre());
				}
				ccj.setGlosaGeneral(lcc.get(i).getGlosaGeneral());
				ccj.setImputable(lcc.get(i).isImputable());
				ccj.setNombreClaseCuenta(clasedao.getById(lcc.get(i).getClaseCuenta().getId()).getNombre());
				ccj.setNombreGrupoCuenta(grupodao.getById(lcc.get(i).getGrupoCuenta().getId()).getNombre());
				ccj.setRazonSocialEmpresa(edao.getById(lcc.get(i).getEmpresa().getId()).getRazonSocial());
				lcj.add(ccj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de cuenta contable ", e);
		}
		return lcj;
	}

	/**
	 * metodo que modifica la cuenta contable
	 * 
	 * @param pj json de cuentaContableJson
	 * @return mensaje de exito o error
	 */
	public String update(CuentaContableJson ccj) {
		try {
			CuentaContable cuentaContable = cuentadao.getById(ccj.getId());
			if (cuentadao.getByCodigo(ccj.getCodigo()) == null
					|| cuentadao.getByCodigo(ccj.getCodigo()).getCodigo().equals(cuentaContable.getCodigo())) {
				if (Utilidades.containsScripting(ccj.getDescripcion()).compareTo(true) == 0
						|| Utilidades.containsScripting(ccj.getGlosaGeneral()).compareTo(true) == 0) {
					throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
				} else {
					cuentaContable.setGlosaGeneral(ccj.getGlosaGeneral());
					cuentaContable.setCodigo(ccj.getCodigo());
					cuentaContable.setDescripcion(ccj.getDescripcion());
					cuentaContable.setAnalisis(ccj.isAnalisis());
					cuentaContable.setImputable(true);
					cuentaContable.setConciliacion(ccj.isConciliacion());
					cuentaContable.setClaseCuenta(clasedao.getById(ccj.getIdClaseCuenta()));
					cuentaContable.setGrupoCuenta(grupodao.getById(ccj.getIdGrupoCuenta()));
					cuentaContable.setEmpresa(edao.getById(ccj.getIdEmpresa()));
					if (ccj.isAnalisis().equals(true)) {
						cuentaContable.setBanco(null);
						cuentaContable.setCuenta(null);
					}
					if (ccj.isConciliacion().equals(true)) {
						cuentaContable.setBanco(bdao.getById(ccj.getIdBanco()));
						cuentaContable.setCuenta(cdao.getById(ccj.getIdCuenta()));
					}
					cuentadao.update(cuentaContable);
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
	public CuentaContableJson getById(CuentaContableJson bj) {
		CuentaContable cuentaContable = cuentadao.getById(bj.getId());
		CuentaContableJson ccJson = new CuentaContableJson();
		ccJson.setId(cuentaContable.getId());
		ccJson.setCodigo(cuentaContable.getCodigo());
		ccJson.setGlosaGeneral(cuentaContable.getGlosaGeneral());
		ccJson.setDescripcion(cuentaContable.getDescripcion());
		ccJson.setAnalisis(cuentaContable.isAnalisis());
		ccJson.setConciliacion(cuentaContable.isConciliacion());
		ccJson.setIdEmpresa(cuentaContable.getEmpresa().getId());
		if(cuentaContable.isConciliacion().equals(true)) {
			ccJson.setIdBanco(cuentaContable.getBanco().getId());
			ccJson.setIdCuenta(cuentaContable.getCuenta().getId());
		}
		ccJson.setConciliacion(cuentaContable.isConciliacion());
		ccJson.setImputable(cuentaContable.isImputable());
		ccJson.setIdClaseCuenta(cuentaContable.getClaseCuenta().getId());
		ccJson.setIdGrupoCuenta(cuentaContable.getGrupoCuenta().getId());

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
			cuentadao.eliminar(cuentaContable);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la cuenta contable");
			return e.getMessage();
		}
	}

	/*
	 * funcion que trae todas las cuenta contable para llenar select
	 * 
	 */
	public List<CuentaContableJson> getAllLista(CuentaContableJson bj) {

		List<CuentaContableJson> lcj = new ArrayList<>();
		try {
			List<CuentaContable> c = cuentadao.getLista(udao.getById(bj.getIdUsuario()).getOficinaContable().getId());
			for (int i = 0; i < c.size(); i++) {
				CuentaContableJson cj = new CuentaContableJson();
				cj.setId(c.get(i).getId());
				cj.setGlosaGeneral(c.get(i).getGlosaGeneral());
				cj.setDescripcion(c.get(i).getDescripcion());
				cj.setIdEmpresa(c.get(i).getEmpresa().getId());
				cj.setConciliacion(c.get(i).isConciliacion());
				cj.setCodigo(c.get(i).getCodigo());
				if(c.get(i).isConciliacion().equals(true)) {
				cj.setIdBanco(c.get(i).getBanco().getId());
				cj.setIdCuenta(c.get(i).getCuenta().getId());
				}
				cj.setAnalisis(c.get(i).isAnalisis());
				lcj.add(cj);
			}
			return lcj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de cuenta contable ", e);
			return lcj;
		}

	}
	
	public Long getMaxCodigo() {
		return cuentadao.getMaxCodigo();	
	}
	
	/**
	 * funcion que busca cuenta por idEmpresa
	 * @param idBanco
	 * @return
	 */
	public List<CuentaContableJson> getByIdEmpresa(CuentaContableJson ccj) {

		List<CuentaContableJson> lcj = new ArrayList<>();
		try {

			List<CuentaContable> c = cuentadao.getByIdEmpresa(ccj.getIdEmpresa(), udao.getById(ccj.getIdUsuario()).getOficinaContable().getId());
			for (int i = 0; i < c.size(); i++) {	
			CuentaContableJson cj = new CuentaContableJson();
				cj.setId(c.get(i).getId());
				cj.setGlosaGeneral(c.get(i).getGlosaGeneral());
				cj.setDescripcion(c.get(i).getDescripcion());
				cj.setIdEmpresa(c.get(i).getEmpresa().getId());
				cj.setConciliacion(c.get(i).isConciliacion());
				cj.setCodigo(c.get(i).getCodigo());
				if(c.get(i).isConciliacion().equals(true)) {
				cj.setIdBanco(c.get(i).getBanco().getId());
				cj.setIdCuenta(c.get(i).getCuenta().getId());
				}
				cj.setAnalisis(c.get(i).isAnalisis());
				lcj.add(cj);
			}
			return lcj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de cuentas contables ", e);
			return lcj;
		}

	}
}
