package cl.certificadoradelsur.byecontabilidad.restdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BitacoraDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ClienteDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CodigoImpuestoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CompraDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.OtroImpuestoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.PeriodoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Bitacora;
import cl.certificadoradelsur.byecontabilidad.entities.Compra;
import cl.certificadoradelsur.byecontabilidad.entities.OtroImpuesto;
import cl.certificadoradelsur.byecontabilidad.entities.Periodo;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.CompraJson;
import cl.certificadoradelsur.byecontabilidad.json.OtroImpuestoJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class CompraRD {
	private static Logger log = Logger.getLogger(CompraRD.class);
	@Inject
	private CompraDAO cdao;
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
	@Inject
	private CodigoImpuestoDAO cidao;
	@Inject
	private PeriodoDAO peridao;
	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(CompraJson cj) {
		try {
			
			Timestamp fech = Utilidades.convertidorFechaSinHora(cj.getFecha());
			Long mes = getMes(fech);
			Long anio = getAnio(fech);
			Periodo per = peridao.getBymesPeriodo(mes, anio,cj.getIdEmpresa());
			
			if(per.isEstado() == true) {
			
			Compra c = new Compra();
			if (Utilidades.containsScripting(cj.getNombre()).compareTo(true) == 0
					|| Utilidades.containsScripting(cj.getFolio()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				c.setNombre(cj.getNombre());
				c.setCliente(clidao.getById(cj.getIdCliente()));
				c.setFecha(Utilidades.convertidorFechaSinHora(cj.getFecha()));
				c.setFolio(cj.getFolio());
				c.setIva(cj.getIva());
				c.setMontoNeto(cj.getMontoNeto());
				c.setMontoTotal(cj.getMontoTotal());
				c.setEmpresa(edao.getById(cj.getIdEmpresa()));
				c.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				c.setOtrosEstado(cj.isOtrosEstado());
				c.setIvaEstado(cj.isIvaEstado());
				if (cj.isOtrosEstado().equals(true)) {
					List<OtroImpuesto> otroi = new ArrayList<>();
					for (int i = 0; i < cj.getOtrosImpuestos().size(); i++) {
						OtroImpuesto oi = new OtroImpuesto();
						oi.setCompra(c);
						oi.setCodigo(cidao.getById(cj.getOtrosImpuestos().get(i).getCodigo()));
						oi.setMonto(cj.getOtrosImpuestos().get(i).getMonto());
						otroi.add(oi);
					}
					c.setOtrosImpuestos(otroi);
				}
				cdao.guardar(c);
				return Constantes.MENSAJE_REST_OK;
			}
			}else {
				return "El periodo esta cerrado";
			}
		} catch (

		Exception e) {
			log.error("No se pudo guardar la compra ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}
		
		public Long getMes(Timestamp fechaInicioReposo) {
			Long mes = 0L;
			Calendar fecha = Calendar.getInstance();
			fecha.setTime(fechaInicioReposo);
			
			mes = (long)fecha.get(Calendar.MONTH);
			return mes+1;
		}
		
		public Long getAnio(Timestamp fechaInicioReposo) {
			Long anio = 0L;
			Calendar fecha = Calendar.getInstance();
			fecha.setTime(fechaInicioReposo);
			
			anio = (long)fecha.get(Calendar.YEAR);
			return anio;
		}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String fechaDesde, String fechaHasta, String idUsuario, Long idEmpresa) {
		try {
			Timestamp fechaInicial = Utilidades.fechaDesde(Utilidades.fechaActualDesde().toString());
			Timestamp fechaFinal = Utilidades.fechaHasta(Utilidades.fechaActualHasta().toString());

			if (fechaDesde != null && fechaHasta != null) {
				fechaInicial = Utilidades.fechaDesde(fechaDesde);
				fechaFinal = Utilidades.fechaHasta(fechaHasta);
			}
			return cdao.countAll(fechaInicial, fechaFinal, udao.getById(idUsuario).getOficinaContable().getId(),
					idEmpresa);
		} catch (Exception e) {
			log.error("No se puede contar el total de compras ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de compra en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de compras
	 */
	public List<CompraJson> getAll(Integer page, Integer limit, String fechaDesde, String fechaHasta, String idUsuario,
			Long idEmpresa) {
		List<CompraJson> lcj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			Timestamp fechaInicial = Utilidades.fechaDesde(Utilidades.fechaActualDesde().toString());
			Timestamp fechaFinal = Utilidades.fechaHasta(Utilidades.fechaActualHasta().toString());

			if (fechaDesde != null && fechaHasta != null) {
				fechaInicial = Utilidades.fechaDesde(fechaDesde);
				fechaFinal = Utilidades.fechaHasta(fechaHasta);
			}
			List<Compra> lc = cdao.getAll(inicio, limit, fechaInicial, fechaFinal,
					udao.getById(idUsuario).getOficinaContable().getId(), idEmpresa);
			for (int i = 0; i < lc.size(); i++) {
				CompraJson cj = new CompraJson();
				cj.setId(lc.get(i).getId());
				cj.setNombre(lc.get(i).getNombre());
				cj.setIdCliente(clidao.getById(lc.get(i).getCliente().getId()).getId());
				cj.setRut(clidao.getById(lc.get(i).getCliente().getId()).getRut());
				cj.setFolio(lc.get(i).getFolio());
				cj.setFecha(Utilidades.timestamAStringSinHora(lc.get(i).getFecha()));
				cj.setIva(lc.get(i).getIva());
				cj.setMontoTotal(lc.get(i).getMontoTotal());
				cj.setMontoNeto(lc.get(i).getMontoNeto());
				Long sumaOtros = 0L;
				for (int j = 0; j < lc.get(i).getOtrosImpuestos().size(); j++) {
					sumaOtros = sumaOtros + (lc.get(i).getOtrosImpuestos().get(j).getMonto());
				}
				cj.setOtroImpuesto(sumaOtros);
				lcj.add(cj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de compras ", e);
		}
		return lcj;
	}

	/**
	 * metodo que modifica compra
	 * 
	 * @param pj json de CompraJson
	 * @return mensaje de exito o error
	 */
	public String update(CompraJson cj) {
		try {
			Timestamp fech = Utilidades.convertidorFechaSinHora(cj.getFecha());
			Long mes = getMes(fech);
			Long anio = getAnio(fech);
			Periodo per = peridao.getBymesPeriodo(mes, anio,cj.getIdEmpresa());
			
			if(per.isEstado() == true) {
			Compra c = cdao.getById(cj.getId());
			List<OtroImpuesto> otroI = oidao.getByIdCompra(cj.getId());
			for (int i = 0; i < otroI.size(); i++) {
				oidao.eliminar(oidao.getById(otroI.get(i).getId()));
			}
			if (Utilidades.containsScripting(cj.getNombre()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				c.setCliente(clidao.getById(cj.getIdCliente()));
				c.setNombre(cj.getNombre());
				c.setFecha(Utilidades.convertidorFechaSinHora(cj.getFecha()));
				c.setFolio(cj.getFolio());
				c.setIva(cj.getIva());
				c.setMontoNeto(cj.getMontoNeto());
				c.setMontoTotal(cj.getMontoTotal());
				c.setEmpresa(edao.getById(cj.getIdEmpresa()));
				c.setOtrosEstado(cj.isOtrosEstado());
				c.setIvaEstado(cj.isIvaEstado());
				if (cj.isOtrosEstado().equals(true)) {
					List<OtroImpuesto> otroi = new ArrayList<>();
					for (int i = 0; i < cj.getOtrosImpuestos().size(); i++) {
						OtroImpuesto oi = new OtroImpuesto();
						oi.setCompra(c);
						oi.setCodigo(cidao.getById(cj.getOtrosImpuestos().get(i).getCodigo()));
						oi.setMonto(cj.getOtrosImpuestos().get(i).getMonto());
						otroi.add(oi);
					}
					c.setOtrosImpuestos(otroi);
				}
				cdao.update(c);

				Bitacora b = new Bitacora();
				b.setUsuario(udao.getById(cj.getIdUsuario()));
				b.setFecha(new Timestamp(System.currentTimeMillis()));
				b.setTabla("Compra");
				b.setAccion("Update");
				b.setDescripcion("Se modifico la compra " + cj.getFolio());
				bidao.guardar(b);

				return Constantes.MENSAJE_REST_OK;
			}
			}else {
				return "Periodo esta cerrado";
			}
		} catch (Exception e) {
			log.error("No se pudo modificar el compra");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener un compra
	 * 
	 * @param id de compra
	 * @return mensaje de exito o error
	 */
	public CompraJson getById(CompraJson cj) {
		Compra c = cdao.getById(cj.getId());
		CompraJson cJson = new CompraJson();
		cJson.setId(c.getId());
		cJson.setNombre(c.getNombre());
		cJson.setFolio(c.getFolio());
		cJson.setFecha(c.getFecha().toString().substring(0, 10));
		cJson.setIva(c.getIva());
		cJson.setIvaEstado(c.isIvaEstado());
		cJson.setOtrosEstado(c.isOtrosEstado());
		List<OtroImpuestoJson> oij = new ArrayList<>();
		if (c.isOtrosEstado().equals(true)) {
			for (int i = 0; i < c.getOtrosImpuestos().size(); i++) {
				OtroImpuestoJson oi = new OtroImpuestoJson();
				oi.setCodigo(c.getOtrosImpuestos().get(i).getCodigo().getId());
				oi.setMonto(c.getOtrosImpuestos().get(i).getMonto());
				oij.add(oi);
			}
		}
		cJson.setOtrosImpuestos(oij);
		cJson.setMontoNeto(c.getMontoNeto());
		cJson.setMontoTotal(c.getMontoTotal());
		cJson.setIdCliente(c.getCliente().getId());
		cJson.setIdEmpresa(c.getEmpresa().getId());
		Long sumaOtros = 0L;
		for (int j = 0; j < c.getOtrosImpuestos().size(); j++) {
			sumaOtros = sumaOtros + (c.getOtrosImpuestos().get(j).getMonto());
		}
		cJson.setOtroImpuesto(sumaOtros);
		return cJson;
	}

	/**
	 * metodo elimina un compra
	 * 
	 * @param pj json de compra
	 * @return mensaje de exito o error
	 */
	public String eliminar(CompraJson cj) {
		try {
			Compra c = cdao.getById(cj.getId());
			Bitacora b = new Bitacora();
			b.setUsuario(udao.getById(cj.getIdUsuario()));
			b.setFecha(new Timestamp(System.currentTimeMillis()));
			b.setTabla("Compra");
			b.setAccion("Delete");
			b.setDescripcion("Se elimino " + cj.getFolio());
			bidao.guardar(b);
			cdao.eliminar(c);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la compra");
			return e.getMessage();
		}
	}

}
