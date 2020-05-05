package cl.certificadoradelsur.byecontabilidad.restdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BitacoraDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.HonorarioDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Bitacora;
import cl.certificadoradelsur.byecontabilidad.entities.Honorario;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.HonorarioJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class HonorarioRD {
	private static Logger log = Logger.getLogger(HonorarioRD.class);
	@Inject
	private HonorarioDAO hdao;
	@Inject
	private EmpresaDAO edao;
	@Inject
	private UsuarioDAO udao;
	@Inject
	private BitacoraDAO bidao;
	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(HonorarioJson hj) {
		try {
			Honorario h = new Honorario();
			if (Utilidades.containsScripting(hj.getNombre()).compareTo(true) == 0
					||Utilidades.containsScripting(hj.getRut()).compareTo(true) == 0
					||Utilidades.containsScripting(hj.getNumBoleta()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				h.setRut(hj.getRut());
				h.setNombre(hj.getNombre());
				h.setNumBoleta(hj.getNumBoleta());
				h.setRetencion(hj.getRetencion());
				h.setFecha(Utilidades.convertidorFechaSinHora(hj.getFecha()));
				h.setMontoBruto(hj.getMontoLiquido());
				h.setMontoLiquido(hj.getMontoLiquido());
				h.setEmpresa(edao.getById(hj.getIdEmpresa()));
				h.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				hdao.guardar(h);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (

		Exception e) {
			log.error("No se pudo guardar la Honorario ", e);
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
			return hdao.countAll();
		} catch (Exception e) {
			log.error("No se puede contar el total de Honorarios ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de Honorario en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de Honorarios
	 */
	public List<HonorarioJson> getAll(Integer page, Integer limit) {
		List<HonorarioJson> lhj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}

			List<Honorario> lh = hdao.getAll(inicio, limit);
			for (int i = 0; i < lh.size(); i++) {
				HonorarioJson hj = new HonorarioJson();
				hj.setId(lh.get(i).getId());
				hj.setNombre(lh.get(i).getNombre());
				hj.setNumBoleta(lh.get(i).getNumBoleta());
				hj.setRetencion(lh.get(i).getRetencion());
				hj.setFecha(Utilidades.timestamAStringSinHora(lh.get(i).getFecha()));
				hj.setMontoBruto(lh.get(i).getMontoLiquido());
				hj.setMontoLiquido(lh.get(i).getMontoLiquido());
				lhj.add(hj);
			}
		} catch (Exception e) {
			log.error("No se puede obtener la lista de Honorarios ", e);
		}
		return lhj;
	}

	/**
	 * metodo que modifica Honorario
	 * 
	 * @param pj json de HonorarioJson
	 * @return mensaje de exito o error
	 */
	public String update(HonorarioJson hj) {
		try {
			Honorario h = hdao.getById(hj.getId());
			if (Utilidades.containsScripting(hj.getNombre()).compareTo(true) == 0
					||Utilidades.containsScripting(hj.getRut()).compareTo(true) == 0
					||Utilidades.containsScripting(hj.getNumBoleta()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				h.setRut(hj.getRut());
				h.setNombre(hj.getNombre());
				h.setNumBoleta(hj.getNumBoleta());
				h.setRetencion(hj.getRetencion());
				h.setFecha(Utilidades.convertidorFechaSinHora(hj.getFecha()));
				h.setMontoBruto(hj.getMontoLiquido());
				h.setMontoLiquido(hj.getMontoLiquido());
				h.setEmpresa(edao.getById(hj.getIdEmpresa()));
				hdao.update(h);
				Bitacora b = new Bitacora();
				b.setUsuario(udao.getById(hj.getIdUsuario()));
				b.setFecha(new Timestamp(System.currentTimeMillis()));
				b.setTabla("Honorario");
				b.setAccion("Update");
				b.setDescripcion("Se modifico " + hdao.getById(hj.getId()).getRut());
				bidao.guardar(b);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar el Honorario");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener un Honorario
	 * 
	 * @param id de Honorario
	 * @return mensaje de exito o error
	 */
	public HonorarioJson getById(HonorarioJson cj) {
		Honorario h = hdao.getById(cj.getId());
		HonorarioJson hJson = new HonorarioJson();
		hJson.setId(h.getId());
		hJson.setRut(h.getRut());
		hJson.setNombre(h.getNombre());
		hJson.setNumBoleta(h.getNumBoleta());
		hJson.setMontoBruto(h.getMontoBruto());
		hJson.setMontoLiquido(h.getMontoLiquido());
		hJson.setRetencion(h.getRetencion());
		hJson.setFecha(h.getFecha().toString().substring(0, 10));
		hJson.setIdEmpresa(h.getEmpresa().getId());
		return hJson;
	}

	/**
	 * metodo elimina un Honorario
	 * 
	 * @param pj json de Honorario
	 * @return mensaje de exito o error
	 */
	public String eliminar(HonorarioJson hj) {
		try {
			Honorario h = hdao.getById(hj.getId());
			Bitacora b = new Bitacora();
			b.setUsuario(udao.getById(hj.getIdUsuario()));
			b.setFecha(new Timestamp(System.currentTimeMillis()));
			b.setTabla("Honorario");
			b.setAccion("Delete");
			b.setDescripcion("Se elimino " + hdao.getById(hj.getId()).getRut());
			bidao.guardar(b);
			hdao.eliminar(h);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la Honorario");
			return e.getMessage();
		}
	}

}
