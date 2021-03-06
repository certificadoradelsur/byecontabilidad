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
 * @author Ernesto
 *
 */

@Local
@Path("/private/CentroResultado")
public interface CentroResultadoSvc {
	
	/**
	 * Funcion que almacena los datos
	 * 
	 * @param datos json que viene desde el front
	 * @return una respuesta en json
	 */
	@POST
	@Path("/add")
	@Produces("application/json")
	Response add(String datos);
	
	/**
	 * Funcion para modificar un centro de resultado
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);
	
	

	/**
	 * Funcion para traer todos los centro Resultados
	 * @param inicio
	 * @param fin
	 * @return lista de CentroResultados
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, 
			@QueryParam("limit") Integer fin,
			@QueryParam("idSucursal") Long idSucursal);

	/**
	 * Funcion para buscar Centro resultado por id
	 * 
	 * @param id centro Resultado
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);
	
	
	/**
	 * Funcion que elimina un Centro Resultado
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);
}
