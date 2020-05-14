package cl.certificadoradelsur.byecontabilidad.restdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BitacoraDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ClienteDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.OtroImpuestoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.dao.VentaDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Bitacora;
import cl.certificadoradelsur.byecontabilidad.entities.OtroImpuesto;
import cl.certificadoradelsur.byecontabilidad.entities.Venta;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.OtroImpuestoJson;
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
	@Inject
	private ClienteDAO clidao;
	@Inject
	private OtroImpuestoDAO oidao;

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
					|| Utilidades.containsScripting(vj.getRut()).compareTo(true) == 0
					|| Utilidades.containsScripting(vj.getFolio()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				v.setNombre(vj.getNombre());
				v.setCliente(clidao.getById(vj.getIdCliente()));
				v.setFecha(Utilidades.convertidorFechaSinHora(vj.getFecha()));
				v.setFolio(vj.getFolio());
				v.setIva(vj.getIva());
				v.setMontoNeto(vj.getMontoNeto());
				v.setMontoTotal(vj.getMontoTotal());
				v.setEmpresa(edao.getById(vj.getIdEmpresa()));
				v.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				v.setOtrosEstado(vj.isOtrosEstado());
				v.setIvaEstado(vj.isIvaEstado());
				if (vj.isOtrosEstado().equals(true)) {
					List<OtroImpuesto> otroi = new ArrayList<>();
					for (int i = 0; i < vj.getOtrosImpuestos().size(); i++) {
						OtroImpuesto oi = new OtroImpuesto();
						oi.setVenta(v);
						oi.setCodigo(vj.getOtrosImpuestos().get(i).getCodigo());
						oi.setMonto(vj.getOtrosImpuestos().get(i).getMonto());
						otroi.add(oi);
					}
					v.setOtrosImpuestos(otroi);
				}
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
	public Long countAll(String fechaDesde, String fechaHasta, String idUsuario,
			Long idEmpresa) {
		try {
			Timestamp fechaInicial = Utilidades.fechaDesde(Utilidades.fechaActualDesde().toString());
			Timestamp fechaFinal = Utilidades.fechaHasta(Utilidades.fechaActualHasta().toString());
			
			if (fechaDesde!=null || fechaHasta!=null) {
				fechaInicial = Utilidades.fechaDesde(fechaDesde);
				fechaFinal = Utilidades.fechaHasta(fechaHasta);
			}
			return vdao.countAll(fechaInicial,fechaFinal,
					udao.getById(idUsuario).getOficinaContable().getId(),idEmpresa);
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
	public List<VentaJson> getAll(Integer page, Integer limit, String fechaDesde,
			String fechaHasta, String idUsuario, Long idEmpresa) {
		List<VentaJson> lvj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}

			Timestamp fechaInicial = Utilidades.fechaDesde(Utilidades.fechaActualDesde().toString());
			Timestamp fechaFinal = Utilidades.fechaHasta(Utilidades.fechaActualHasta().toString());
			
			List<Venta> lv = vdao.getAll(inicio, limit,fechaInicial,fechaFinal,
					udao.getById(idUsuario).getOficinaContable().getId(), idEmpresa);
			for (int i = 0; i < lv.size(); i++) {
				VentaJson vj = new VentaJson();
				vj.setId(lv.get(i).getId());
				vj.setNombre(lv.get(i).getNombre());
				vj.setIdCliente(clidao.getById(lv.get(i).getCliente().getId()).getId());
				vj.setRut(clidao.getById(lv.get(i).getCliente().getId()).getRut());
				vj.setFolio(lv.get(i).getFolio());
				vj.setFecha(Utilidades.timestamAStringSinHora(lv.get(i).getFecha()));
				vj.setIva(lv.get(i).getIva());
				vj.setMontoTotal(lv.get(i).getMontoTotal());
				vj.setMontoNeto(lv.get(i).getMontoNeto());
				Long sumaOtros = 0L;
				for (int j = 0; j < lv.get(i).getOtrosImpuestos().size(); j++) {
					sumaOtros = sumaOtros + (lv.get(i).getOtrosImpuestos().get(j).getMonto());
				}
				vj.setOtroImpuesto(sumaOtros);
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
			List<OtroImpuesto> otroI = oidao.getByIdVenta(vj.getId());
			for (int i = 0; i < otroI.size(); i++) {
				oidao.eliminar(oidao.getById(otroI.get(i).getId()));
			}
			if (Utilidades.containsScripting(vj.getNombre()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				v.setCliente(clidao.getById(vj.getIdCliente()));
				v.setNombre(vj.getNombre());
				v.setFecha(Utilidades.convertidorFechaSinHora(vj.getFecha()));
				v.setFolio(vj.getFolio());
				v.setIva(vj.getIva());
				v.setMontoNeto(vj.getMontoNeto());
				v.setMontoTotal(vj.getMontoTotal());
				v.setEmpresa(edao.getById(vj.getIdEmpresa()));
				v.setOtrosEstado(vj.isOtrosEstado());
				v.setIvaEstado(vj.isIvaEstado());
				if (vj.isOtrosEstado().equals(true)) {
					List<OtroImpuesto> otroi = new ArrayList<>();
					for (int i = 0; i < vj.getOtrosImpuestos().size(); i++) {
						OtroImpuesto oi = new OtroImpuesto();
						oi.setVenta(v);
						oi.setCodigo(vj.getOtrosImpuestos().get(i).getCodigo());
						oi.setMonto(vj.getOtrosImpuestos().get(i).getMonto());
						otroi.add(oi);
					}
					v.setOtrosImpuestos(otroi);
				}
				vdao.update(v);

				Bitacora b = new Bitacora();
				b.setUsuario(udao.getById(vj.getIdUsuario()));
				b.setFecha(new Timestamp(System.currentTimeMillis()));
				b.setTabla("Compra");
				b.setAccion("Update");
				b.setDescripcion("Se modifico la compra " + vj.getFolio());
				bidao.guardar(b);

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
		vjson.setFolio(v.getFolio());
		vjson.setFecha(v.getFecha().toString().substring(0, 10));
		vjson.setIva(v.getIva());
		vjson.setIvaEstado(v.isIvaEstado());
		vjson.setOtrosEstado(v.isOtrosEstado());
		List<OtroImpuestoJson> oij = new ArrayList<>();
		if (v.isOtrosEstado().equals(true)) {
			for (int i = 0; i < v.getOtrosImpuestos().size(); i++) {
				OtroImpuestoJson oi = new OtroImpuestoJson();
				oi.setCodigo(v.getOtrosImpuestos().get(i).getCodigo());
				oi.setMonto(v.getOtrosImpuestos().get(i).getMonto());
				oij.add(oi);
			}
		}
		vjson.setOtrosImpuestos(oij);
		vjson.setMontoNeto(v.getMontoNeto());
		vjson.setMontoTotal(v.getMontoTotal());
		vjson.setIdCliente(v.getCliente().getId());
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
			b.setDescripcion("Se elimino " + vj.getFolio());
			bidao.guardar(b);
			vdao.eliminar(v);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la venta");
			return e.getMessage();
		}
	}

}
