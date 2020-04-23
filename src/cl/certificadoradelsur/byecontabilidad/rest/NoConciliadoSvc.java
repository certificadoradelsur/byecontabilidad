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
 * Interfaz que declara los servicios rest de no conciliado
 * 
 * @author juan
 *
 */
@Local
@Path("/private/noConciliado")
public interface NoConciliadoSvc {
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
	 * Funcion para traer todas los no conciliados
	 * 
	 * @param
	 * @return lista de los no conciliados
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin,
			@QueryParam("fechaInicial") String fechaInicial, @QueryParam("fechaFinal") String fechaFinal,
			@QueryParam("idCuenta") Long isCuenta, @QueryParam("idBanco") Long idBanco,
			@QueryParam("idUsuario") String idUsuario, @QueryParam("idEmpresa") Long idEmpresa);

	/**
	 * Funcion para modificar un no conciliado
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);

	/**
	 * Funcion para buscar no conciliado por id
	 * 
	 * @param id no conciliado
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion que elimina un no conciliado
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);

	/**
	 * Funcion para traer todos los Movimientos no conciliados
	 * 
	 * @param inicio
	 * @param fin
	 * @param monto
	 * @param idNoConciliadoCartola
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getNoConciliadoMonto")
	Response listM(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin,
			@QueryParam("monto") Long monto, @QueryParam("idNoConciliadoCartola") Long idNoConciliadoCartola);

}