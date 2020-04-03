package cl.certificadoradelsur.byecontabilidad.rest;

import java.io.InputStream;
import java.text.ParseException;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import cl.certificadoradelsur.byecontabilidad.restdao.ReporteRD;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que implementa la interfaz ReporteSvc
 * 
 * @author juan
 *
 */

public class ReporteSvcBean implements ReporteSvc   {
	@Inject
	private ReporteRD rrd;
	@Override
	public Response getByIdReporteBancoCuenta(String fechaDesde, String fechaHasta, Long idBanco, Long idCuenta) throws ParseException {
		InputStream file = rrd.getByIdReporteBancoCuenta(fechaDesde, fechaHasta, idBanco,idCuenta);
		ResponseBuilder response = Response.ok((Object) file);
		
		response.header("Content-Disposition", "attachment; filename=Reporte"+Utilidades.strToTsDDMMYYYYHHmmssConGuion(Utilidades.convertidorFechaSinHora(fechaDesde)).substring(0, 10)+"a"+Utilidades.strToTsDDMMYYYYHHmmssConGuion(Utilidades.convertidorFechaSinHora(fechaHasta)).substring(0, 10)+".xlsx");		

		return response.build();
	}
	
	@Override
	public Response getLibroDiario(String fechaDesde, String fechaHasta, String idUsuario) throws ParseException {
		InputStream file = rrd.getLibroDiario(fechaDesde, fechaHasta, idUsuario);
		ResponseBuilder response = Response.ok((Object) file);
		
		response.header("Content-Disposition", "attachment; filename=LibroDiario"+Utilidades.strToTsDDMMYYYYHHmmssConGuion(Utilidades.convertidorFechaSinHora(fechaDesde)).substring(0, 10)+"a"+Utilidades.strToTsDDMMYYYYHHmmssConGuion(Utilidades.convertidorFechaSinHora(fechaHasta)).substring(0, 10)+".xlsx");		

		return response.build();
	}

	@Override
	public Response getLibroMayor(String fechaDesde, String fechaHasta,Long inicialMayor,Long finalMayor, String idUsuario) throws ParseException {
		InputStream file = rrd.getLibroMayor(fechaDesde, fechaHasta,inicialMayor,finalMayor, idUsuario);
		ResponseBuilder response = Response.ok((Object) file);
		
		response.header("Content-Disposition", "attachment; filename=LibroMayor"+Utilidades.strToTsDDMMYYYYHHmmssConGuion(Utilidades.convertidorFechaSinHora(fechaDesde)).substring(0, 10)+"a"+Utilidades.strToTsDDMMYYYYHHmmssConGuion(Utilidades.convertidorFechaSinHora(fechaHasta)).substring(0, 10)+".xlsx");		

		return response.build();
	}
}
