package cl.certificadoradelsur.byecontabilidad.restdao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.OficinaContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Empresa;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.EmpresaJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class EmpresaRD {
	private static Logger log = Logger.getLogger(EmpresaRD.class);
	@Inject
	private EmpresaDAO edao;
	@Inject
	private OficinaContableDAO ofidao;
	@Inject
	private UsuarioDAO udao;


	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(EmpresaJson ej) {
		try {
			Empresa e = new Empresa();
			if (Utilidades.containsScripting(ej.getRut()).compareTo(true) == 0
					||Utilidades.containsScripting(ej.getGiro()).compareTo(true) == 0
					||Utilidades.containsScripting(ej.getRazonSocial()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				e.setGiro(ej.getGiro());
				e.setRazonSocial(ej.getRazonSocial());
				e.setRut(ej.getRut());
				e.setActivo(true);
				e.setOficinaContable(udao.getById(ej.getIdUsuario()).getOficinaContable());
				edao.guardar(e);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo guardar la empresa ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String razonSocial, String idUsuario) {
		try {
			if (razonSocial == null) {
				razonSocial = "";
			}
			return edao.countAll(razonSocial, udao.getById(idUsuario).getOficinaContable().getId());
		} catch (Exception e) {
			log.error("No se puede contar el total de empresas ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de empresa en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de empresas
	 */
	public List<EmpresaJson> getAll(Integer page, Integer limit, String razonSocial, String idUsuario) {
		List<EmpresaJson> lej = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			
			if (razonSocial == null) {
				razonSocial = "";
			}
			List<Empresa> le = edao.getAll(inicio, limit, razonSocial, udao.getById(idUsuario).getOficinaContable().getId());
			for (int i = 0; i < le.size(); i++) {
				EmpresaJson ej = new EmpresaJson();
				ej.setId(le.get(i).getId());
				ej.setGiro(le.get(i).getGiro());
				ej.setRut(le.get(i).getRut());
				ej.setRazonSocial(le.get(i).getRazonSocial());
				ej.setRazonSocialOficina(ofidao.getById(le.get(i).getOficinaContable().getId()).getRazonSocial());
				lej.add(ej);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de empresas ", e);
		}
		return lej;
	}

	/**
	 * metodo que modifica empresa
	 * 
	 * @param pj json de BancoJson
	 * @return mensaje de exito o error
	 */
	public String update(EmpresaJson ej) {
		try {
			Empresa e = edao.getById(ej.getId());
			if (Utilidades.containsScripting(ej.getRut()).compareTo(true) == 0
					||Utilidades.containsScripting(ej.getGiro()).compareTo(true) == 0
					||Utilidades.containsScripting(ej.getRazonSocial()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				e.setGiro(ej.getGiro());
				e.setRut(ej.getRut());
				e.setRazonSocial(ej.getRazonSocial());
				e.setActivo(ej.isActivo());
				edao.update(e);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar la empresa");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener una empresa
	 * 
	 * @param id de empresa
	 * @return mensaje de exito o error
	 */
	public EmpresaJson getById(EmpresaJson bj) {
		Empresa e = edao.getById(bj.getId());
		EmpresaJson eJson = new EmpresaJson();
		eJson.setId(e.getId());
		eJson.setGiro(e.getGiro());
		eJson.setRut(e.getRut());
		eJson.setRazonSocial(e.getRazonSocial());
		eJson.setIdOficianaContable(e.getOficinaContable().getId());
		eJson.setActivo(e.getActivo());
		return eJson;
	}

	/**
	 * metodo elimina una empresa
	 * 
	 * @param pj json de empresa
	 * @return mensaje de exito o error
	 */
	public String eliminar(EmpresaJson bj) {
		try {
			Empresa e = edao.getById(bj.getId());
			e.setActivo(false);
			edao.update(e);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la empresa");
			return e.getMessage();
		}
	}
	
	/*
	 * funcion que trae todas las empresas para llenar select
	 * 
	 */
	public List<EmpresaJson> getAllLista(EmpresaJson ej) {

		List<EmpresaJson> lgj = new ArrayList<>();
		try {
			List<Empresa> g = edao.getLista(udao.getById(ej.getIdUsuario()).getOficinaContable().getId());
			for (int i = 0; i < g.size(); i++) {
				EmpresaJson gj = new EmpresaJson();
				gj.setId(g.get(i).getId());
				gj.setRazonSocial(g.get(i).getRazonSocial());
				lgj.add(gj);
			}
			return lgj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de empresas", e);
			return lgj;
		}

	}

	
}
