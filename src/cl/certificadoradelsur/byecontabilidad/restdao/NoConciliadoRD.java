package cl.certificadoradelsur.byecontabilidad.restdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BitacoraDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.MovimientoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Bitacora;
import cl.certificadoradelsur.byecontabilidad.entities.NoConciliado;
import cl.certificadoradelsur.byecontabilidad.json.NoConciliadoJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class NoConciliadoRD {
	private static Logger log = Logger.getLogger(NoConciliadoRD.class);
	@Inject
	private NoConciliadoDAO ncdao;
	@Inject
	private MovimientoDAO mdao;
	@Inject
	private UsuarioDAO udao;
	@Inject
	private EmpresaDAO edao;
	@Inject
	private BitacoraDAO bidao;

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(NoConciliadoJson ncj) {
		try {
			NoConciliado noConciliado = new NoConciliado();
			noConciliado.setMovimiento(mdao.getById(ncj.getId()));
			noConciliado.setFecha(Utilidades.convertidorFechaSinHora(ncj.getFecha()));
			noConciliado.setEliminado(false);
			noConciliado.setEmpresa(edao.getById(ncj.getIdEmpresa()));
			ncdao.guardar(noConciliado);
			return Constantes.MENSAJE_REST_OK;
		} catch (

		Exception e) {
			log.error("No se pudo guardar el movimiento no conciliado ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param idCuenta
	 * @param idBanco
	 * @return
	 */
	public Long countAll(String fechaInicial, String fechaFinal, Long idCuenta, Long idBanco, String idUsuario,
			Long idEmpresa) {
		try {
			if (fechaInicial == null || fechaFinal == null || idCuenta == null || idBanco == null) {
				fechaInicial = Utilidades.fechaActualDesdeFiltro().toString();
				fechaFinal = Utilidades.fechaActualHastaFiltro().toString();
			}
			return ncdao.countAll(Utilidades.convertidorFechaSinHora(fechaInicial), Utilidades.fechaHasta(fechaFinal),
					idCuenta, idBanco, udao.getById(idUsuario).getOficinaContable().getId(), idEmpresa);
		} catch (Exception e) {
			log.error("No se puede contar el total de movimientos no conciliados ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de no Conciliados en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total no Conciliados
	 */
	public List<NoConciliadoJson> getAll(Integer page, Integer limit, String fechaInicial, String fechaFinal,
			Long idCuenta, Long idBanco, String idUsuario, Long idEmpresa) {
		List<NoConciliadoJson> lncj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if (fechaInicial == null || fechaFinal == null || idCuenta == null || idBanco == null) {
				fechaInicial = Utilidades.fechaActualDesdeFiltro().toString();
				fechaFinal = Utilidades.fechaActualHastaFiltro().toString();
			}
			List<NoConciliado> lc = ncdao.getAll(inicio, limit, Utilidades.convertidorFechaSinHora(fechaInicial),
					Utilidades.fechaHasta(fechaFinal), idCuenta, idBanco,
					udao.getById(idUsuario).getOficinaContable().getId(), idEmpresa);
			for (int i = 0; i < lc.size(); i++) {
				NoConciliadoJson cj = new NoConciliadoJson();
				cj.setId(lc.get(i).getId());
				cj.setFecha(Utilidades.strToTsDDMMYYYYHHmmssConGuion((lc.get(i).getMovimiento().getFecha()))
						.substring(0, 10));
				cj.setFechaT(lc.get(i).getMovimiento().getFecha().toString());

				cj.setEliminado(lc.get(i).isEliminado());
				cj.setNumDocumento(lc.get(i).getMovimiento().getNumDocumento());
				cj.setNumComprobante(lc.get(i).getMovimiento().getNumComprobante());
				cj.setMonto(lc.get(i).getMovimiento().getMonto());
				cj.setGlosa(lc.get(i).getMovimiento().getGlosa());
				cj.setEstado(lc.get(i).getMovimiento().getEstado());
				cj.setTipoDocumento(lc.get(i).getMovimiento().getTipoDocumento());
				cj.setTipoMovimiento(lc.get(i).getMovimiento().getTipoMovimiento());
				lncj.add(cj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de movimientos no conciliados ", e);
		}
		return lncj;
	}

	/**
	 * metodo que modifica No Conciliado
	 * 
	 * @param pj json de ConciliacionJson
	 * @return mensaje de exito o error
	 */
	public String update(NoConciliadoJson ncj) {
		try {
			NoConciliado noConciliado = ncdao.getById(ncj.getId());
			noConciliado.setFecha(Utilidades.convertidorFechaSinHora(ncj.getFecha()));
			ncdao.update(noConciliado);
			return Constantes.MENSAJE_REST_OK;

		} catch (Exception e) {
			log.error("No se pudo modificar el movimiento no conciliado");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener un NoConciliado
	 * 
	 * @param id de No Conciliado
	 * @return mensaje de exito o error
	 */
	public NoConciliadoJson getById(NoConciliadoJson cj) {
		NoConciliado noConciliado = ncdao.getById(cj.getId());
		NoConciliadoJson cJson = new NoConciliadoJson();
		cJson.setId(noConciliado.getId());
		return cJson;
	}

	/**
	 * metodo elimina un no conciliado
	 * 
	 * @param pj json de no conciliado
	 * @return mensaje de exito o error
	 */
	public String eliminar(NoConciliadoJson cj) {
		try {
			NoConciliado noConciliado = ncdao.getById(cj.getId());
			noConciliado.setEliminado(true);
			ncdao.update(noConciliado);
			Bitacora b = new Bitacora();
			b.setUsuario(udao.getById(cj.getIdUsuario()));
			b.setFecha(new Timestamp(System.currentTimeMillis()));
			b.setTabla("NoConciliado");
			b.setAccion("Delete");
			b.setDescripcion("Se elimino movimiento no conciliado con fecha " + ncdao.getById(cj.getId()).getFecha());
			bidao.guardar(b);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar el movimiento no conciliado");
			return e.getMessage();
		}
	}

	/**
	 * funcion que busca una cartola no conciliada por el idCartola
	 * 
	 * @param noConciliadoCartola
	 * @return cartola no conciliada
	 */
	public Object getByIdMovimiento(Long idMovimiento) {
		return ncdao.getByIdMovimiento(idMovimiento);
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAllMNC(Long monto) {
		try {
			if (monto == null) {
				monto = 0L;
			}

			return ncdao.countAllMNC(monto);
		} catch (Exception e) {
			log.error("No se puede contar el total de movimientos no conciliados ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna movimientos no conciliados segun una fecha
	 * 
	 * @param page
	 * @param limit
	 * @param fecha
	 * @return
	 */
	public List<NoConciliadoJson> getNoConciliadoMonto(Integer page, Integer limit, Long monto,
			Long idNoConciliadoCartola) {
		List<NoConciliadoJson> lncj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if (monto == null) {
				return lncj;
			}

			List<NoConciliado> lc = ncdao.getNoConciliadoMonto(inicio, limit, monto);
			for (int i = 0; i < lc.size(); i++) {
				NoConciliadoJson cj = new NoConciliadoJson();
				cj.setId(lc.get(i).getId());
				cj.setFecha(Utilidades.strToTsDDMMYYYYHHmmssConGuion((lc.get(i).getMovimiento().getFecha()))
						.substring(0, 10));
				cj.setEliminado(lc.get(i).isEliminado());
				cj.setNumDocumento(lc.get(i).getMovimiento().getNumDocumento());
				cj.setMonto(lc.get(i).getMovimiento().getMonto());
				cj.setGlosa(lc.get(i).getMovimiento().getGlosa());
				cj.setEstado(lc.get(i).getMovimiento().getEstado());
				cj.setTipoDocumento(lc.get(i).getMovimiento().getTipoDocumento());
				cj.setTipoMovimiento(lc.get(i).getMovimiento().getTipoMovimiento());
				cj.setIdNoConciliadoCartola(idNoConciliadoCartola);
				lncj.add(cj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de movimientos no conciliados ", e);
		}
		return lncj;
	}

}
