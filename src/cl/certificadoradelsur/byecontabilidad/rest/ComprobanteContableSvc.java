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
 * Interfaz que declara los servicios rest de comprobante Contable
 * 
 * @author juan
 *
 */
@Local
@Path("/private/comprobanteContable")
public interface ComprobanteContableSvc {
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
	 * Funcion para traer todas los comprobantes Contables
	 * 
	 * @param
	 * @return lista de Cuentas
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin,@QueryParam("glosaGeneral") String glosaGeneral, @QueryParam("idUsuario") String idUsuario);
	
	/**
	 * Funcion para modificar un comprobante Contable
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);
	
	/**
	 * Funcion para modificar un comprobante Contable
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/modificar")
	@Produces("application/json")
	Response modificar(String datos);

	/**
	 * Funcion para buscar comprobante Contable por id
	 * 
	 * @param id comprobante Contable
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion que elimina un comprobante Contable
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);
		
	/**
	 * Funcion para buscar el codigo más grande de cuenta contable
	 * @return max codigo
	 */
	@POST
	@Path("/getMaxNumero")
	@Produces("application/json")
	Response getMaxNumero();
	
	
}