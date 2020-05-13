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
 * Interfaz que declara los servicios rest de transaccion 
 * 
 * @author juan
 *
 */
@Local
@Path("/private/transaccion")
public interface TransaccionSvc {
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
	 * Funcion para traer todos las transacciones
	 * 

	 * @param inicio
	 * @param fin
	 * @param glosaTransaccion
	 * @return lista de transacciones	 
	 * */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin, @QueryParam("glosaTransaccion") String glosaTransaccion);

	/**
	 * Funcion para buscar transaccion por id
	 * 
	 * @param id transaccion
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);


	/**
	 * Funcion que elimina un transaccion
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);
	
	
	/**
	 * Funcion para buscar transaccion por id
	 * 
	 * @param id transaccion
	 * @return json de salida
	 */
	@POST
	@Path("/getByT")
	@Produces("application/json")
	Response getByT();
	
}