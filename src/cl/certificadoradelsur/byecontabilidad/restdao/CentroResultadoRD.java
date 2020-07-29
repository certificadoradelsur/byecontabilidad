package cl.certificadoradelsur.byecontabilidad.restdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BitacoraDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CentroResultadoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.SucursalDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Bitacora;
import cl.certificadoradelsur.byecontabilidad.entities.CentroResultado;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.CentroResultadoJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * 
 * @author Ernesto
 *
 */

@Stateless
public class CentroResultadoRD {
	private static Logger log = Logger.getLogger(CentroResultadoRD.class);
	@Inject
	private CentroResultadoDAO crdao;
	@Inject
	private BitacoraDAO bidao;
	@Inject
	private UsuarioDAO udao;
	@Inject
	private SucursalDAO sudao;
	
	public String save(CentroResultadoJson cr) {
		try {
			CentroResultado centroresu = new CentroResultado();
			if(Utilidades.containsScripting(cr.getNombre()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			}else {
				centroresu.setNombre(cr.getNombre());
				centroresu.setSucursal(sudao.getById(cr.getIdSucursal()));
				crdao.guardar(centroresu);
				return Constantes.MENSAJE_REST_OK;
			}
			
			
		}catch(Exception e) {
			log.error("No se pudo guardar el Centro Resultado", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}
	
	
	public String update(CentroResultadoJson cr) {
		try {
			CentroResultado centroresu = crdao.getById(cr.getId());
			if(Utilidades.containsScripting(cr.getNombre()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			}else {
				centroresu.setNombre(cr.getNombre());
				centroresu.setSucursal(sudao.getById(cr.getIdSucursal()));
				Bitacora b = new Bitacora();
				b.setUsuario(udao.getById(cr.getIdUsuario()));
				b.setFecha(new Timestamp(System.currentTimeMillis()));
				b.setTabla("Centro Resultado");
				b.setAccion("Update");
				b.setDescripcion("Se modifico Centro Resultado " + crdao.getById(cr.getId()).getNombre());
				bidao.guardar(b);
				
				crdao.update(centroresu);
				return Constantes.MENSAJE_REST_OK;
			}
			
			
		}catch(Exception e) {
			log.error("No se pudo guardar el Centro Resultado", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}
	
	
	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(Long idSucursal) {
		try {
			
			return crdao.countAll(idSucursal);
		} catch (Exception e) {
			log.error("No se puede contar el total de Centro Resultados ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de Centro Resultados en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de Centro Resultados
	 */
	public List<CentroResultadoJson> getAll(Integer page, Integer limit,Long idSucursal) {
		List<CentroResultadoJson> lpj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}

			List<CentroResultado> cr = crdao.getAll(inicio, limit,idSucursal);
			for (int i = 0; i < cr.size(); i++) {
				CentroResultadoJson uj = new CentroResultadoJson();
				uj.setId(cr.get(i).getId());
				uj.setNombre(cr.get(i).getNombre());
				uj.setNombreSucursal(cr.get(i).getSucursal().getDireccion());
				lpj.add(uj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de Centro Resultado ", e);
		}
		return lpj;
	}
	
	/**
	 * metodo obtener un Centro Resultado
	 * 
	 * @param id de Centro Resultado
	 * @return json
	 */
	public CentroResultadoJson getById(CentroResultadoJson cj) {
		CentroResultado c = crdao.getById(cj.getId());
		CentroResultadoJson cJson = new CentroResultadoJson();
		cJson.setId(c.getId());
		cJson.setNombre(c.getNombre());
		cJson.setIdSucursal(c.getSucursal().getCodigo());
		return cJson;
	}
	
	/**
	 * metodo elimina un Cenntro resultado
	 * 
	 * @param pj json de centro resultado
	 * @return mensaje de exito o error
	 */
	public String eliminar(CentroResultadoJson cj) {
		try {
			CentroResultado centro = crdao.getById(cj.getId());
			Bitacora b = new Bitacora();
			b.setUsuario(udao.getById(cj.getIdUsuario()));
			b.setFecha(new Timestamp(System.currentTimeMillis()));
			b.setTabla("Centro Resultado");
			b.setAccion("Delete");
			b.setDescripcion("Se elimino Centro Resultado " + crdao.getById(cj.getId()).getNombre());
			bidao.guardar(b);
			
			crdao.eliminar(centro);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar el Centro Resultado ",e);
			return e.getMessage();
		}
	}



}
