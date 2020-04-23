package cl.certificadoradelsur.byecontabilidad.rest;

import java.sql.Timestamp;
import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;


/**
 * Interfaz que declara los servicios rest de cartola
 * 
 * @author juan
 *
 */
@Local
@Path("/private/cartola")
public interface CartolaSvc {
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
	 * * Funcion para traer todas las cartolas
	 * 
	 * @param
	 * @param inicio
	 * @param fin
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param isCuenta
	 * @param idBanco
	 * @return lista de cartolas
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	Response list(@QueryParam("page") Integer inicio, @QueryParam("limit") Integer fin,
			@QueryParam("fechaInicial") String fechaInicial, @QueryParam("fechaFinal") String fechaFinal,
			@QueryParam("idCuenta") Long isCuenta, @QueryParam("idBanco") Long idBanco, 
			@QueryParam("idEmpresa") Long idEmpresa);

	/**
	 * Funcion para modificar una cartola
	 * 
	 * @param id
	 * @return Mensaje de exito o error
	 */
	@POST
	@Path("/update")
	@Produces("application/json")
	Response update(String datos);

	/**
	 * Funcion para buscar cartola por id
	 * 
	 * @param id cartola
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

	/**
	 * Funcion para buscar cartola por número de documento
	 * 
	 * @param id cartola
	 * @return json de salida
	 */
	@POST
	@Path("/getByNumDocumento")
	@Produces("application/json")
	Response getByNumDocumento(String datos);

	/**
	 * Funcion que elimina una cartola
	 * 
	 * @param datos el json de entrada
	 * @return json de salida
	 */
	@POST
	@Path("/delete")
	@Produces("application/json")
	Response eliminar(String datos);

	/**
	 * funcion para importar un archivo
	 * 
	 * @param input
	 * @return
	 * @throws ConciliacionBancariaException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/importar")
	@Consumes("multipart/form-data")
	public Response importar(MultipartFormDataInput input, @QueryParam("banco") Long banco,
			@QueryParam("cuenta") Long cuenta, @QueryParam("numCartola") String numCartola,
			@QueryParam("anio") String anio,
			@QueryParam("idEmpresa") Long idEmpresa) throws ByeContabilidadException;

	/**
	 * Funcion para traer todos las cartolas entre fechas
	 * 
	 * @param
	 * @return lista de cartolas
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllLista")
	Response listTotalCartolas(@QueryParam("fechaI") Timestamp fechaI, @QueryParam("fechaF") Timestamp fechaF);

}