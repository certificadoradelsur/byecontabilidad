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
 * Interfaz que declara los servicios rest de cuenta
 * 
 * @author juan
 *
 */
@Local
@Path("/private/cuenta")
public interface CuentaSvc {
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
	 * Funcion para traer todas las cuentas
	 * 
	 * @param
	 * @return lista de cuentas
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin, @QueryParam("numCuenta") String numCuenta);
	
	/**
	 * Funcion para modificar una cuenta
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);

	/**
	 * Funcion para buscar cuenta por id
	 * 
	 * @param id cuenta
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion que elimina una cuenta
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);
	
    /**
     * funcion que obtiene las cuentas
     * @param datos
     * @return cuentas que seran mostrados en el select
     */
	@POST
	@Path("/getByIdBanco")
	@Produces("application/json")
	Response getByIdBanco(String datos);
	
	
		
}