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
 * Interfaz que declara los servicios rest de no conciliado cartola
 * 
 * @author juan
 *
 */
@Local
@Path("/private/noConciliadoCartola")
public interface NoConciliadoCartolaSvc {
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
	 * Funcion para traer todas las cartolas no conciliadas
	 * 

	 * @param inicio
	 * @param fin
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param isCuenta
	 * @param idBanco
	 * @return lista de las cartolas no conciliadas
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin,
			@QueryParam("fechaInicial") String fechaInicial, @QueryParam("fechaFinal") String fechaFinal,
			@QueryParam("idCuenta") Long isCuenta, @QueryParam("idBanco") Long idBanco);

	/**
	 * Funcion para modificar una cartola no conciliada
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);

	/**
	 * Funcion para buscar una cartola no conciliada por id
	 * 
	 * @param id cartola no conciliada
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion que elimina una cartola no conciliada
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);

	/**
	 * Funcion para traer todas las cartolas no conciliadas
	 * 
	 * @param inicio
	 * @param fin
	 * @param monto
	 * @param idNoConciliado
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getNoConciliadoCartolaMonto")
	Response listM(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin,
			@QueryParam("monto") Long monto, @QueryParam("idNoConciliado") Long idNoConciliado);
	
	/**
	 * Funcion para traer todos las cartolas no conciliadas
	 * 
	 * @param inicio
	 * @param fin
	 * @param monto
	 * @param idNoConciliadoCartola
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getNoConciliadoCartolaDoc")
	Response listD(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin,
			@QueryParam("numDocumento") Long numDocumento, @QueryParam("idNoConciliado") Long idNoConciliado);

}