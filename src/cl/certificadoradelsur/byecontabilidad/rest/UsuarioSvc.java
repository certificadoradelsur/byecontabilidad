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
 * Interfaz que declara los servicios rest de usuario
 * 
 * @author juan
 *
 */
@Local
@Path("/private/usuario")
public interface UsuarioSvc {
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
	 * Funcion para traer todas las usuarios
	 * @param inicio
	 * @param fin
	 * @param id
	 * @return lista de usuarios
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin, @QueryParam("id") String id, @QueryParam("idUsuario") String idUsuario);

	/**
	 * Funcion para modificar un usuario
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);

	/**
	 * Funcion para modificar las credenciales de seguridad del usuario
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/updatePass")
	@Produces("application/json")
	Response updatePass(String datos);

	/**
	 * Funcion para buscar usuario por id
	 * 
	 * @param id usuario
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion que elimina un usuario
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);
	
}