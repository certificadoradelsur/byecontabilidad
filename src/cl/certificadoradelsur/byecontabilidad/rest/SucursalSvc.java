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
 * Interfaz que declara los servicios rest de sucursal
 * 
 * @author juan
 *
 */
@Local
@Path("/private/sucursal")
public interface SucursalSvc {
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
	 * Funcion para traer todas las sucursales
	 * 
	 * @param
	 * @return lista de sucursales
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin, @QueryParam("idEmpresa") Long idEmpresa, @QueryParam("idUsuario") String idUsuario );
	
	/**
	 * Funcion para modificar una sucursal
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);

	/**
	 * Funcion para buscar sucursal por id
	 * 
	 * @param id sucursal
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion que elimina una sucursal
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);
		
    /**
     * funcion que obtiene sucursales por id empresa
     * @param datos
     * @return sucursal que seran mostrados en el select
     */
	@POST
	@Path("/getByIdEmpresa")
	@Produces("application/json")
	Response getByIdEmpresa(String datos);


	
}