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
		
		response.header("Content-Disposition", "attachment; filename=Reporte"+Utilidades.strToTsDDMMYYYYHHmmssConGuion(Utilidades.convertidorFecha(fechaDesde)).substring(0, 10)+"a"+Utilidades.strToTsDDMMYYYYHHmmssConGuion(Utilidades.convertidorFecha(fechaHasta)).substring(0, 10)+".xlsx");		

		return response.build();
	}
	


}
