package cl.certificadoradelsur.byecontabilidad.restdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BitacoraDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.dao.VentaDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Bitacora;
import cl.certificadoradelsur.byecontabilidad.entities.Venta;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.VentaJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class VentaRD {
	private static Logger log = Logger.getLogger(VentaRD.class);
	@Inject
	private VentaDAO vdao;
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
	public String save(VentaJson vj) {
		try {
			Venta v = new Venta();
			if (Utilidades.containsScripting(vj.getNombre()).compareTo(true) == 0
					||Utilidades.containsScripting(vj.getRut()).compareTo(true) == 0
					||Utilidades.containsScripting(vj.getFolio()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				v.setNombre(vj.getNombre());
				v.setRut(vj.getRut());
				v.setFecha(Utilidades.convertidorFechaSinHora(vj.getFecha()));
				v.setFolio(vj.getFolio());
				v.setIva(vj.getIva());
				v.setOtroImpuesto(vj.getOtroImpuesto());
				v.setMontoNeto(vj.getMontoNeto());
				v.setMontoTotal(vj.getMontoTotal());
				v.setEmpresa(edao.getById(vj.getIdEmpresa()));
				v.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				vdao.guardar(v);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (

		Exception e) {
			log.error("No se pudo guardar la venta ", e);
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
			return vdao.countAll();
		} catch (Exception e) {
			log.error("No se puede contar el total de ventas ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de venta en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de ventas
	 */
	public List<VentaJson> getAll(Integer page, Integer limit) {
		List<VentaJson> lvj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}

			List<Venta> lv = vdao.getAll(inicio, limit);
			for (int i = 0; i < lv.size(); i++) {
				VentaJson vj = new VentaJson();
				vj.setId(lv.get(i).getId());
				vj.setNombre(lv.get(i).getNombre());
				vj.setRut(lv.get(i).getRut());
				vj.setFolio(lv.get(i).getFolio());
				vj.setFecha(Utilidades.timestamAStringSinHora(lv.get(i).getFecha()));
				vj.setIva(lv.get(i).getIva());
				vj.setOtroImpuesto(lv.get(i).getOtroImpuesto());
				vj.setMontoTotal(lv.get(i).getMontoTotal());
				vj.setMontoNeto(lv.get(i).getMontoNeto());
				lvj.add(vj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de ventas ", e);
		}
		return lvj;
	}

	/**
	 * metodo que modifica venta
	 * 
	 * @param pj json de VentaJson
	 * @return mensaje de exito o error
	 */
	public String update(VentaJson vj) {
		try {
			Venta v = vdao.getById(vj.getId());
			if (Utilidades.containsScripting(vj.getNombre()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				v.setNombre(vj.getNombre());
				v.setRut(vj.getRut());
				v.setFecha(Utilidades.convertidorFechaSinHora(vj.getFecha()));
				v.setFolio(vj.getFolio());
				v.setIva(vj.getIva());
				v.setOtroImpuesto(vj.getOtroImpuesto());
				v.setMontoNeto(vj.getMontoNeto());
				v.setMontoTotal(vj.getMontoTotal());
				v.setEmpresa(edao.getById(vj.getIdEmpresa()));
				vdao.update(v);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar el venta");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener un venta
	 * 
	 * @param id de venta
	 * @return mensaje de exito o error
	 */
	public VentaJson getById(VentaJson vj) {
		Venta v = vdao.getById(vj.getId());
		VentaJson vjson = new VentaJson();
		vjson.setId(v.getId());
		vjson.setNombre(v.getNombre());
		vjson.setRut(v.getRut());
		vjson.setFolio(v.getFolio());
		vjson.setFecha(v.getFecha().toString().substring(0, 10));
		vjson.setIva(v.getIva());
		vjson.setOtroImpuesto(v.getOtroImpuesto());
		vjson.setMontoNeto(v.getMontoNeto());
		vjson.setMontoTotal(v.getMontoTotal());
		vjson.setIdEmpresa(v.getEmpresa().getId());
		return vjson;
	}

	/**
	 * metodo elimina un venta
	 * 
	 * @param pj json de venta
	 * @return mensaje de exito o error
	 */
	public String eliminar(VentaJson vj) {
		try {
			Venta v = vdao.getById(vj.getId());
			Bitacora b = new Bitacora();
			b.setUsuario(udao.getById(vj.getIdUsuario()));
			b.setFecha(new Timestamp(System.currentTimeMillis()));
			b.setTabla("Venta");
			b.setAccion("Delete");
			b.setDescripcion("Se elimino " + vdao.getById(vj.getId()).getRut());
			bidao.guardar(b);
			vdao.eliminar(v);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la venta");
			return e.getMessage();
		}
	}

}
