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
 * Interfaz que declara los servicios rest de clasificacion
 * 
 * @author juan
 *
 */
@Local
@Path("/private/clasificacion")
public interface ClasificacionSvc {
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
	 * Funcion para traer todas las clasificaciones
	 * 
	 * @param
	 * @return lista de clasificaciones
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin,
			@QueryParam("nombre") String nombre,@QueryParam("idClaseCuenta") Long idClaseCuenta,
			@QueryParam("idGrupoCuenta") Long idGrupoCuenta, @QueryParam("idUsuario") String idUsuario);

	/**
	 * Funcion para modificar una clasificacion
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);

	/**
	 * Funcion para buscar clasificacion por id
	 * 
	 * @param id clasificacion
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion que elimina una clasificacion
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);

	/**
	 * funcion que obtiene las clasificaciones por id gupo cuenta
	 * 
	 * @param datos
	 * @return cuentas que seran mostrados en el select
	 */
	@POST
	@Path("/getByIdGrupoCuenta")
	@Produces("application/json")
	Response getByIdGrupoCuenta(String datos);

}