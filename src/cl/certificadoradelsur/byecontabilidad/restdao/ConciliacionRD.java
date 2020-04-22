package cl.certificadoradelsur.byecontabilidad.restdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.ConciliacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoCartolaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Conciliacion;
import cl.certificadoradelsur.byecontabilidad.entities.NoConciliado;
import cl.certificadoradelsur.byecontabilidad.entities.NoConciliadoCartola;
import cl.certificadoradelsur.byecontabilidad.json.ConciliacionJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class ConciliacionRD {
	private static Logger log = Logger.getLogger(ConciliacionRD.class);
	@Inject
	private ConciliacionDAO cdao;
	@Inject
	private NoConciliadoDAO ncdao;
	@Inject
	private NoConciliadoCartolaDAO nccdao;
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
	public String save(ConciliacionJson cj) {
		try {

			// if
			// (cdao.getByMovCar(ncdao.getById(cj.getIdNoConciliado()).getMovimiento().getId(),
			// nccdao.getById(cj.getIdNoConciliadoCartola()).getCartola().getId()) == null)
			// {

			if (cdao.getByMov(ncdao.getById(cj.getIdNoConciliado()).getMovimiento().getId()) == null
					&& cdao.getByCart(nccdao.getById(cj.getIdNoConciliadoCartola()).getCartola().getId()) == null) {

				// if (nccdao.getById(cj.getIdNoConciliadoCartola()).getCartola().getMonto()
				// .compareTo(ncdao.getById(cj.getIdNoConciliado()).getMovimiento().getMonto())
				// == 0) {
				Conciliacion conciliacion = new Conciliacion();
				conciliacion.setCartola(nccdao.getById(cj.getIdNoConciliadoCartola()).getCartola());
				conciliacion.setMovimiento(ncdao.getById(cj.getIdNoConciliado()).getMovimiento());
				conciliacion.setFecha(new Timestamp(System.currentTimeMillis()));
				conciliacion.setEliminado(false);
				conciliacion.setEmpresa(edao.getById(cj.getIdEmpresa()));
				cdao.guardar(conciliacion);

				NoConciliado nc = ncdao.getById(cj.getIdNoConciliado());
				nc.setEliminado(true);
				ncdao.update(nc);
				NoConciliadoCartola ncc = nccdao.getById(cj.getIdNoConciliadoCartola());
				ncc.setEliminado(true);
				nccdao.update(ncc);

				return Constantes.MENSAJE_REST_OK;
				// } else {
				// return "No se puede realizar conciliaciones entre movimientos con montos
				// distintos";
				// }
			} else {
				return "No es posible conciliar estos movimientos";
			}
		} catch (

		Exception e) {
			log.error("No se pudo guardar la conciliación ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String fechaInicial, String fechaFinal, Long idCuenta, Long idBanco, String idUsuario) {
		try {
			if (fechaInicial == null || fechaFinal == null || idCuenta == null || idBanco == null) {
				fechaInicial = Utilidades.fechaActualDesdeFiltro().toString();
				fechaFinal = Utilidades.fechaActualHastaFiltro().toString();
			}
			return cdao.countAll(Utilidades.convertidorFecha(fechaInicial), Utilidades.fechaHasta(fechaFinal), idCuenta,
					idBanco, udao.getById(idUsuario).getOficinaContable().getId());
		} catch (Exception e) {
			log.error("No se puede contar el total de Conciliaciones ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de conciliaciones en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de conciliaciones
	 */
	public List<ConciliacionJson> getAll(Integer page, Integer limit, String fechaInicial, String fechaFinal,
			Long idCuenta, Long idBanco, String idUsuario) {
		List<ConciliacionJson> lcj = new ArrayList<>();
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
			List<Conciliacion> lc = cdao.getAll(inicio, limit, Utilidades.convertidorFecha(fechaInicial),
					Utilidades.fechaHasta(fechaFinal), idCuenta, idBanco,
					udao.getById(idUsuario).getOficinaContable().getId());
			for (int i = 0; i < lc.size(); i++) {
				ConciliacionJson cj = new ConciliacionJson();
				cj.setId(lc.get(i).getId());
				cj.setIdMovimiento(lc.get(i).getMovimiento().getNumDocumento());
				cj.setIdCartola(lc.get(i).getCartola().getId());
				cj.setFechaMovimiento(Utilidades.strToTsDDMMYYYYHHmmssConGuion((lc.get(i).getMovimiento().getFecha()))
						.substring(0, 10));
				cj.setFechaCartola(
						Utilidades.strToTsDDMMYYYYHHmmssConGuion((lc.get(i).getCartola().getFecha())).substring(0, 10));
				cj.setMontoMovimiento(lc.get(i).getMovimiento().getMonto());
				cj.setNumCartola(lc.get(i).getCartola().getNumCartola());
				cj.setMontoCartola(lc.get(i).getCartola().getMonto());
				cj.setNumDocumento(lc.get(i).getCartola().getNumDocumento());
				cj.setTipoDocumento(lc.get(i).getMovimiento().getTipoDocumento());
				cj.setDescripcionCartola(lc.get(i).getCartola().getDescripcion());
				cj.setFechaConciliacion(
						Utilidades.strToTsDDMMYYYYHHmmssConGuion((lc.get(i).getFecha())).substring(0, 10));
				lcj.add(cj);
			}

		} catch (

		Exception e) {
			log.error("No se puede obtener la lista de conciliaciones ", e);
		}
		return lcj;
	}

	/**
	 * metodo que modifica Conciliaciones
	 * 
	 * @param pj json de ConciliacionJson
	 * @return mensaje de exito o error
	 */
	public String update(ConciliacionJson cj) {
		try {
			Conciliacion conciliacion = cdao.getById(cj.getId());
			conciliacion.setFecha(Utilidades.convertidorFecha(cj.getFecha()));
			cdao.update(conciliacion);
			return Constantes.MENSAJE_REST_OK;

		} catch (Exception e) {
			log.error("No se pudo modificar la conciliación ");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener una conciliacion
	 * 
	 * @param id de conciliacion
	 * @return mensaje de exito o error
	 */
	public ConciliacionJson getById(ConciliacionJson cj) {
		Conciliacion conciliacion = cdao.getById(cj.getId());
		ConciliacionJson cJson = new ConciliacionJson();
		cJson.setId(conciliacion.getId());
		return cJson;
	}

	/**
	 * metodo elimina una conciliacion
	 * 
	 * @param pj json de conciliacion
	 * @return mensaje de exito o error
	 */
	public String eliminar(ConciliacionJson cj) {
		try {
			Conciliacion conciliacion = cdao.getById(cj.getId());
			conciliacion.setEliminado(true);
			cdao.eliminar(conciliacion);

			if (ncdao.getByIdMovimiento(conciliacion.getMovimiento().getId()) != null
					&& nccdao.getByIdCartola(conciliacion.getCartola().getId()) != null) {

				NoConciliado nc = ncdao.getByIdMovimiento(conciliacion.getMovimiento().getId());
				nc.setEliminado(false);
				ncdao.update(nc);
				NoConciliadoCartola ncc = nccdao.getByIdCartola(conciliacion.getCartola().getId());
				ncc.setEliminado(false);
				nccdao.update(ncc);
			}
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la conciliacion");
			return e.getMessage();
		}
	}

	/**
	 * funcion que busca una cartola no conciliada por el idCartola
	 * 
	 * @param noConciliadoCartola
	 * @return cartola no conciliada
	 */
	public Object getByMovCar(Long idMovimiento, Long idCartola) {
		return cdao.getByMovCar(idMovimiento, idCartola);
	}

}
