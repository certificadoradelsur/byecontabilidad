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
 * Interfaz que declara los servicios rest de Movimiento
 * 
 * @author juan
 *
 */
@Local
@Path("/private/movimiento")
public interface MovimientoSvc {
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
	 * Funcion para traer todos los Movimientos
	 * 
	 * @param
	 * @return lista de Movimientos
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin);

	/**
	 * Funcion para modificar un Movimiento
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);

	/**
	 * Funcion para buscar Movimiento por id
	 * 
	 * @param id Movimiento
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion que elimina un Movimiento
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);

	/**
	 * Funcion que lista Movimientos por transaccion
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/listaMovimiento")
	@Produces("application/json")
	Response listarMovimiento(String datos);

	/**
	 * Funcion que busca movimientos por id transaccion para eliminarlos
	 * 
	 * @param datos
	 * @return
	 */
	@POST
	@Path("/eliminarMovimientosComprobante")
	@Produces("application/json")
	Response eliminarMovimientosComprobante(String datos);

	/**
	 * Funcion para traer todos los Movimientos
	 * 
	 * @param
	 * @return lista de Movimientos
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllM")
	Response listM(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin, @QueryParam("id") Long id);

	/**
	 * funcion que trae todos los movimientos relacionados al id del comprobante
	 * 
	 * @param inicio
	 * @param fin
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getMovById")
	Response getMovById(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin,
			@QueryParam("id") Long id);

	/**
	 * Funcion para traer todos los Movimientos entre fechas
	 * 
	 * @param
	 * @return lista de Movimientos
	 * 
	 */

	@POST
	@Path("/getAllLista")
	@Produces("application/json")
	Response listTotalMovimientos(String datos);
}