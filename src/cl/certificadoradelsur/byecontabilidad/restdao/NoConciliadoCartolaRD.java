package cl.certificadoradelsur.byecontabilidad.restdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BitacoraDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CartolaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoCartolaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Bitacora;
import cl.certificadoradelsur.byecontabilidad.entities.NoConciliadoCartola;
import cl.certificadoradelsur.byecontabilidad.json.NoConciliadoCartolaJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class NoConciliadoCartolaRD {
	private static Logger log = Logger.getLogger(NoConciliadoCartolaRD.class);
	@Inject
	private NoConciliadoCartolaDAO nccdao;
	@Inject
	private CartolaDAO cdao;
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
	public String save(NoConciliadoCartolaJson ncj) {
		try {
			NoConciliadoCartola noConciliadoCartola = new NoConciliadoCartola();
			noConciliadoCartola.setCartola(cdao.getById(ncj.getId()));
			noConciliadoCartola.setFecha(Utilidades.convertidorFechaSinHora(ncj.getFecha()));
			noConciliadoCartola.setEmpresa(edao.getById(ncj.getIdNoConciliado()));
			nccdao.guardar(noConciliadoCartola);
			return Constantes.MENSAJE_REST_OK;
		} catch (

		Exception e) {
			log.error("No se pudo guardar la cartola no conciliada ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String fechaInicial, String fechaFinal, Long idCuenta, Long idBanco, String idUsuario, Long idEmpresa) {
		try {
			if (fechaInicial == null || fechaFinal == null || idCuenta == null || idBanco == null) {
				fechaInicial = Utilidades.fechaActualDesdeFiltro().toString();
				fechaFinal = Utilidades.fechaActualHastaFiltro().toString();
			}
			return nccdao.countAll(Utilidades.convertidorFechaSinHora(fechaInicial), Utilidades.fechaHasta(fechaFinal),
					idCuenta, idBanco, udao.getById(idUsuario).getOficinaContable().getId(), idEmpresa);
		} catch (Exception e) {
			log.error("No se puede contar el total de cartolas no conciliadas ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total cartolas no conciliadas en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de cartolas no conciliadas
	 */
	public List<NoConciliadoCartolaJson> getAll(Integer page, Integer limit, String fechaInicial, String fechaFinal,
			Long idCuenta, Long idBanco, String idUsuario, Long idEmpresa) {
		List<NoConciliadoCartolaJson> lncj = new ArrayList<>();
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
			List<NoConciliadoCartola> lc = nccdao.getAll(inicio, limit, Utilidades.convertidorFechaSinHora(fechaInicial),
					Utilidades.fechaHasta(fechaFinal), idCuenta, idBanco, udao.getById(idUsuario).getOficinaContable().getId(), idEmpresa);
			for (int i = 0; i < lc.size(); i++) {
				NoConciliadoCartolaJson cj = new NoConciliadoCartolaJson();
				cj.setId(lc.get(i).getId());
				cj.setFechaT(lc.get(i).getCartola().getFecha().toString());
				cj.setNumCartola(lc.get(i).getCartola().getNumCartola());
				cj.setNombreBanco(lc.get(i).getCartola().getBanco().getNombre());
				cj.setNumCuenta(lc.get(i).getCartola().getCuenta().getNumCuenta());
				cj.setIdBanco(lc.get(i).getCartola().getBanco().getId());
				cj.setNumCuenta(lc.get(i).getCartola().getCuenta().getNumCuenta());
				cj.setNumDocumento(lc.get(i).getCartola().getNumDocumento());
				cj.setFecha(
						Utilidades.strToTsDDMMYYYYHHmmssConGuion((lc.get(i).getCartola().getFecha())).substring(0, 10));
				cj.setDescripcion(lc.get(i).getCartola().getDescripcion());
				cj.setTipoMovimiento(lc.get(i).getCartola().getTipoMovimiento());
				cj.setMonto(lc.get(i).getCartola().getMonto());
				lncj.add(cj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de cartolas no conciliadas ", e);
		}
		return lncj;
	}

	/**
	 * metodo que modifica No Conciliado
	 * 
	 * @param pj json de ConciliacionJson
	 * @return mensaje de exito o error
	 */
	public String update(NoConciliadoCartolaJson ncj) {
		try {
			NoConciliadoCartola noConciliadoCartola = nccdao.getById(ncj.getId());
			noConciliadoCartola.setFecha(Utilidades.convertidorFechaSinHora(ncj.getFecha()));
			nccdao.update(noConciliadoCartola);
			return Constantes.MENSAJE_REST_OK;

		} catch (Exception e) {
			log.error("No se pudo modificar la cartola no conciliada");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener una cartola No Conciliada
	 * 
	 * @param id de No Conciliado
	 * @return mensaje de exito o error
	 */
	public NoConciliadoCartolaJson getById(NoConciliadoCartolaJson cj) {
		NoConciliadoCartola noConciliadoCartola = nccdao.getById(cj.getId());
		NoConciliadoCartolaJson cJson = new NoConciliadoCartolaJson();
		cJson.setId(noConciliadoCartola.getId());
		return cJson;
	}

	/**
	 * metodo elimina una cartola no conciliada
	 * 
	 * @param pj json de cartola no conciliada
	 * @return mensaje de exito o error
	 */
	public String eliminar(NoConciliadoCartolaJson cj) {
		try {
			NoConciliadoCartola noConciliadoCartola = nccdao.getById(cj.getId());
			noConciliadoCartola.setEliminado(true);
			nccdao.update(noConciliadoCartola);
			Bitacora b = new Bitacora();
			b.setUsuario(udao.getById(cj.getIdUsuario()));
			b.setFecha(new Timestamp(System.currentTimeMillis()));
			b.setTabla("NoConciliadoCartola");
			b.setAccion("Delete");
			b.setDescripcion("Se elimino no conciliado cartola " + nccdao.getById(cj.getId()).getCartola().getDescripcion());
			bidao.guardar(b);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la cartola no conciliada");
			return e.getMessage();
		}
	}

	/**
	 * funcion que busca un movimiento no conciliado
	 * 
	 * @param noConciliadoCartola
	 * @return movimiento no conciliado
	 */
	public Object getByIdCartola(Long idCartola) {
		return nccdao.getByIdCartola(idCartola);
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAllNCC(Long monto) {
		try {
			if (monto == null) {
				monto =  0L;
			}
			return nccdao.countAllNCC(monto);
		} catch (Exception e) {
			log.error("No se puede contar el total de cartolas no conciliadas ", e);
			return 0L;
		}
	}
	
	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAllNCCDoc(Long numDocumento) {
		try {
			if (numDocumento == null) {
				numDocumento =  0L;
			}
			return nccdao.countAllNCC(numDocumento);
		} catch (Exception e) {
			log.error("No se puede contar el total de cartolas no conciliadas ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna cartolas no conciliadas segun una monto
	 * 
	 * @param page
	 * @param limit
	 * @param monto
	 * @return
	 */
	public List<NoConciliadoCartolaJson> getNoConciliadoCartolaMonto(Integer page, Integer limit, Long monto,
			Long idNoConciliado) {
		List<NoConciliadoCartolaJson> lncj = new ArrayList<>();
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
			List<NoConciliadoCartola> lc = nccdao.getNoConciliadoCartolaMonto(inicio, limit,
					monto);
			for (int i = 0; i < lc.size(); i++) {
				NoConciliadoCartolaJson cj = new NoConciliadoCartolaJson();
				cj.setId(lc.get(i).getId());
				cj.setNumCartola(lc.get(i).getCartola().getNumCartola());
				cj.setNombreBanco(lc.get(i).getCartola().getBanco().getNombre());
				cj.setNumCuenta(lc.get(i).getCartola().getCuenta().getNumCuenta());
				cj.setIdBanco(lc.get(i).getCartola().getBanco().getId());
				cj.setNumCuenta(lc.get(i).getCartola().getCuenta().getNumCuenta());
				cj.setNumDocumento(lc.get(i).getCartola().getNumDocumento());
				cj.setFecha(
						Utilidades.strToTsDDMMYYYYHHmmssConGuion((lc.get(i).getCartola().getFecha())).substring(0, 10));
				cj.setDescripcion(lc.get(i).getCartola().getDescripcion());
				cj.setTipoMovimiento(lc.get(i).getCartola().getTipoMovimiento());
				cj.setMonto(lc.get(i).getCartola().getMonto());
				cj.setIdNoConciliado(idNoConciliado);
				lncj.add(cj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de cartolas no conciliadas ", e);
		}
		return lncj;
	}

	
	/**
	 * Funcion que retorna cartolas no conciliadas segun una numDocumento
	 * 
	 * @param page
	 * @param limit
	 * @param numDocumento
	 * @return
	 */
	public List<NoConciliadoCartolaJson> getNoConciliadoCartolaDoc(Integer page, Integer limit, Long numDocumento,
			Long idNoConciliado) {
		List<NoConciliadoCartolaJson> lncj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if (numDocumento == null) {
				return lncj;
			}
			List<NoConciliadoCartola> lc = nccdao.getNoConciliadoCartolaDoc(inicio, limit,
					numDocumento.toString());
			for (int i = 0; i < lc.size(); i++) {
				NoConciliadoCartolaJson cj = new NoConciliadoCartolaJson();
				cj.setId(lc.get(i).getId());
				cj.setNumCartola(lc.get(i).getCartola().getNumCartola());
				cj.setNombreBanco(lc.get(i).getCartola().getBanco().getNombre());
				cj.setNumCuenta(lc.get(i).getCartola().getCuenta().getNumCuenta());
				cj.setIdBanco(lc.get(i).getCartola().getBanco().getId());
				cj.setNumCuenta(lc.get(i).getCartola().getCuenta().getNumCuenta());
				cj.setNumDocumento(lc.get(i).getCartola().getNumDocumento());
				cj.setFecha(
						Utilidades.strToTsDDMMYYYYHHmmssConGuion((lc.get(i).getCartola().getFecha())).substring(0, 10));
				cj.setDescripcion(lc.get(i).getCartola().getDescripcion());
				cj.setTipoMovimiento(lc.get(i).getCartola().getTipoMovimiento());
				cj.setMonto(lc.get(i).getCartola().getMonto());
				cj.setIdNoConciliado(idNoConciliado);
				lncj.add(cj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de cartolas no conciliadas ", e);
		}
		return lncj;
	}
}
