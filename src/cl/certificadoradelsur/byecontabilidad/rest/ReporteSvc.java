package cl.certificadoradelsur.byecontabilidad.rest;

import java.text.ParseException;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Local
@Path("/private/reporte")
public interface ReporteSvc {

	/**
	 * Funcion que alimenta con datos necesarios para realizar reporte conciliación
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idBanco
	 * @param idCuenta
	 * @return
	 * @throws ParseException
	 */
	@GET
	@Path("/getByIdReporteBancoCuenta")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	Response getByIdReporteBancoCuenta(@QueryParam("fechaDesde") String fechaDesde,
			@QueryParam("fechaHasta") String fechaHasta, @QueryParam("idBanco") Long idBanco,
			@QueryParam("idCuenta") Long idCuenta) throws ParseException;

	/**
	 * Funcion que provee con datos necesarios para realizar reporte libro diario
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 * @throws ParseException
	 */
	@GET
	@Path("/getLibroDiario")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	Response getLibroDiario(@QueryParam("fechaDesde") String fechaDesde, @QueryParam("fechaHasta") String fechaHasta,
			@QueryParam("idUsuario") String idUsuario) throws ParseException;

	/**
	 * Funcion que provee con datos necesarios para realizar reporte libro mayor
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param inicialMayor
	 * @param finalMayor
	 * @param idUsuario
	 * @return
	 * @throws ParseException
	 */
	@GET
	@Path("/getLibroMayor")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	Response getLibroMayor(@QueryParam("fechaDesde") String fechaDesde, @QueryParam("fechaHasta") String fechaHasta,
			@QueryParam("inicialMayor") Long inicialMayor, @QueryParam("finalMayor") Long finalMayor,
			@QueryParam("idUsuario") String idUsuario) throws ParseException;

}
