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
 * Interfaz que declara los servicios rest de venta
 * 
 * @author juan
 *
 */
@Local
@Path("/private/venta")
public interface VentaSvc {
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
	 * Funcion para traer todas las ventas
	 * 
	 * @param
	 * @return lista de ventas
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin, @QueryParam("fechaDesde") String fechaDesde,
			@QueryParam("fechaHasta") String fechaHasta, @QueryParam("idUsuario") String idUsuario,
			@QueryParam("idEmpresa") Long idEmpresa);

	/**
	 * Funcion para modificar un venta
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);

	/**
	 * Funcion para buscar venta por id
	 * 
	 * @param id venta
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion que elimina una venta
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);

}