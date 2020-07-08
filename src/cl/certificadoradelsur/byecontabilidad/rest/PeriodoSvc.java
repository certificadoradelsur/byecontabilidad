package cl.certificadoradelsur.byecontabilidad.rest;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author ernesto
 *
 */

@Local
@Path("/private/periodo")
public interface PeriodoSvc {
	/**
	 * Funcion para almacenar los datos
	 * @param datos json que vienen del front
	 * @return una respuesta del json
	 */
	
	@POST
	@Path("/add")
	@Produces("application/json")
	Response add(String datos);
	
	/**
	 * Funcion para traer todos los periodos
	 * @param inicio
	 * @param fin
	 * @param id
	 * @return lista de periodos
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, 
				  @QueryParam("limit") Integer fin,
				  @QueryParam("anio") Long anio,
				  @QueryParam("idEmpresa")Long idEmpresa);

	/**
	 * Funcion para modificar un periodo
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);
	
	/**
	 * Funcion modifica periodo
	 * @param datos
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/updateEstado")
	@Produces("application/json")
	Response updateEstado(String datos);

	
	/**
	 * Funcion para buscar periodo por id
	 * 
	 * @param id periodo
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion que elimina un periodo
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);
	
	/**
	 * Funcion que crea periodos del año actual
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/periodoAnio")
	@Produces("application/json")
	Response periodoAnio(String datos);
	
	/**
	 * Traer periodo por anio
	 * @param datos
	 * @return json de salida
	 */
	@POST
	@Path("/getByPeriodoAnio")
	@Produces("application/json")
	Response getByPeriodoAnio(String datos);
	

}
