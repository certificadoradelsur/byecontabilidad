package cl.certificadoradelsur.byecontabilidad.restdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BitacoraDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.PeriodoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Bitacora;
import cl.certificadoradelsur.byecontabilidad.entities.Periodo;
import cl.certificadoradelsur.byecontabilidad.json.PeriodoJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;

/**
 * 
 * @author ernesto
 *
 */

@Stateless
public class PeriodoRD {
	private static Logger log = Logger.getLogger(PeriodoRD.class);
	@Inject
	private PeriodoDAO peridao;
	@Inject
	private BitacoraDAO bidao;
	@Inject
	private UsuarioDAO udao;
	@Inject
	private EmpresaDAO edao;

	
	/**
	 * Funcion que almacena
	 * @param uj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(PeriodoJson uj) {
		try {
			
			Periodo per = peridao.getBymesPeriodo(uj.getAnio(),uj.getMes(),uj.getIdEmpresa());
			if(per == null) {
			
			Periodo periodo = new Periodo();
			
				periodo.setAnio(uj.getAnio());
				periodo.setMes(uj.getMes());
				periodo.setEstado(uj.isEstado());
				periodo.setEmpresa(edao.getById(uj.getIdEmpresa()));
				peridao.guardar(periodo);
				return Constantes.MENSAJE_REST_OK;
			}else {
				return "YA SE CREO UN PERIODO PARA ESE MES Y AÑO";
			}
		} catch (Exception e) {
			log.error("No se pudo guardar el periodo ", e);
			return e.getMessage();
		}
	}
	
	/**
	 * Funcion para almacenar todo los periodos del año al instante
	 * @param pj
	 * @return mensaje hacia el front
	 */
	public String periodoAnio(PeriodoJson pj) {
		
		List<Periodo> peri = peridao.getByAnio(pj.getAnio(),pj.getIdEmpresa());
		if(peri.isEmpty()) {
		try {
		for (int i = 1; i < 13; i++) {
			Long mess = (long) i;
			Periodo per = peridao.getBymesPeriodo(mess,pj.getAnio(),pj.getIdEmpresa());
			if(per == null) {
			Periodo periodo = new Periodo();
			periodo.setAnio(pj.getAnio());
			periodo.setMes(mess);
			periodo.setEstado(true);
			periodo.setEmpresa(edao.getById(pj.getIdEmpresa()));
			peridao.guardar(periodo);
			}
		}
		return Constantes.MENSAJE_REST_OK;
		}catch(Exception e) {
			log.error("No se pudo guardar el periodo",e);
			return e.getMessage();
		}
		}else {
			return "Periodo para este año ya ingresado";
		}
		
	}
	
	/**
	 * metodo modifica periodo
	 * @param uj json de periodo
	 * @return mensaje de exito o error
	 */
	public String update(PeriodoJson uj) {
		try {
			Periodo periodo = peridao.getById(uj.getId());
			periodo.setAnio(uj.getAnio());
			periodo.setMes(uj.getMes());
			periodo.setEstado(uj.isEstado());
			
				peridao.update(periodo);
				
				Bitacora b = new Bitacora();
				b.setUsuario(udao.getById(uj.getIdUsuario()));
				b.setFecha(new Timestamp(System.currentTimeMillis()));
				b.setTabla("Periodo");
				b.setAccion("Update");
				b.setDescripcion("Se modifico el periodo de " + peridao.getById(uj.getId()).getId());
				bidao.guardar(b);
				return Constantes.MENSAJE_REST_OK;
			
		} catch (Exception e) {
			log.error("No se pudo modificar el periodo");
			return e.getMessage();
		}
	}
	
	public String updateEstado(PeriodoJson uj) {
		try {
			Periodo periodo = peridao.getById(uj.getId());
			if(uj.isEstado() == true) {
				periodo.setEstado(false);
			}else {
				periodo.setEstado(true);

			}
				peridao.update(periodo);
				
				Bitacora b = new Bitacora();
				b.setUsuario(udao.getById(uj.getIdUsuario()));
				b.setFecha(new Timestamp(System.currentTimeMillis()));
				b.setTabla("Periodo");
				b.setAccion("Update");
				b.setDescripcion("Se modifico el estado del periodo de " + peridao.getById(uj.getId()).getMes() + peridao.getById(uj.getId()).getAnio());
				bidao.guardar(b);
				return Constantes.MENSAJE_REST_OK;
			
		} catch (Exception e) {
			log.error("No se pudo modificar el periodo");
			return e.getMessage();
		}
	}
	
	/**
	 * metodo elimina un periodo
	 * 
	 * @param pj json de periodo
	 * @return mensaje de exito o error
	 */
	public String eliminar(PeriodoJson psj) {
		try {
			Periodo p = peridao.getById(psj.getId());
			Bitacora b = new Bitacora();
			b.setUsuario(udao.getById(psj.getIdUsuario()));
			b.setFecha(new Timestamp(System.currentTimeMillis()));
			b.setTabla("ProfesionalSalud");
			b.setAccion("Delete");
			b.setDescripcion("Se elimino el periodo de  " + peridao.getById(psj.getId()).getMes());
			bidao.guardar(b);
			peridao.eliminar(p);
			return Constantes.MENSAJE_REST_OK;
			
		} catch (Exception e) {
			log.error("No se pudo eliminar el periodo");
			return e.getMessage();
		}
	}
	
	
	/**
	 * Funcion que retorna el total de usuarios en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de usuarios
	 */
	public List<PeriodoJson> getAll(Integer page, Integer limit,Long anio,Long idEmpresa) {
		List<PeriodoJson> lpj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			List<Periodo> per = peridao.getAll(inicio, limit,anio,idEmpresa);
			for (int i = 0; i < per.size(); i++) {
				PeriodoJson pe = new PeriodoJson();
				pe.setId(per.get(i).getId());
				pe.setMes(per.get(i).getMes());
				pe.setAnio(per.get(i).getAnio());
				pe.setEstado(per.get(i).isEstado());
				pe.setIdEmpresa(per.get(i).getEmpresa().getId());
				lpj.add(pe);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de usuarios ", e);
		}
		return lpj;
	}
	
	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(Long anio,Long idEmpresa) {
		try {
			
			return peridao.countAll(anio,idEmpresa);
		} catch (Exception e) {
			log.error("No se puede contar el total de periodo ", e);
			return 0L;
		}
	}
	
	/**
	 * metodo obtener un Periodo
	 * 
	 * @param id de periodo
	 * @return lista de periodo por id
	 */
	public PeriodoJson getById(PeriodoJson pj) {
		Periodo periodo = peridao.getById(pj.getId());
		PeriodoJson pJson = new PeriodoJson();
		pJson.setId(periodo.getId());
		pJson.setMes(periodo.getMes());
		pJson.setAnio(periodo.getAnio());
		pJson.setEstado(periodo.isEstado());
		pJson.setIdEmpresa(periodo.getEmpresa().getId());
		return pJson;
	}
	
	/**
	 * Metodo que obtiene el periodo del anio
	 * @param anio
	 * @return lista de periodo
	 */
	public List<PeriodoJson> getByAnio(PeriodoJson dato){
		
		List<PeriodoJson> lpj = new ArrayList<>();
			List<Periodo> per = peridao.getByAnio(dato.getAnio(),dato.getIdEmpresa());
			for (int i = 0; i < per.size(); i++) {
			PeriodoJson pe = new PeriodoJson();
			pe.setId(per.get(i).getId());
			pe.setMes(per.get(i).getMes());
			pe.setAnio(per.get(i).getAnio());
			pe.setEstado(per.get(i).isEstado());
			pe.setIdEmpresa(per.get(i).getEmpresa().getId());
			lpj.add(pe);
		}
			return lpj;
	}
	


}
