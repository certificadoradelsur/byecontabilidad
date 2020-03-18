package cl.certificadoradelsur.byecontabilidad.restdao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cl.certificadoradelsur.byecontabilidad.dao.BancoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ConciliacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoCartolaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Conciliacion;
import cl.certificadoradelsur.byecontabilidad.entities.NoConciliado;
import cl.certificadoradelsur.byecontabilidad.entities.NoConciliadoCartola;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;


/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
public class ReporteRD {
	private static Logger log = Logger.getLogger(ReporteRD.class);
	/*
	@Inject
	private MovimientoDAO mdao;
	@Inject
	private CartolaDAO cartdao;
	*/
	@Inject
	private ConciliacionDAO cdao;
	@Inject
	private NoConciliadoDAO ncdao;
	@Inject
	private NoConciliadoCartolaDAO nccdao;
	@Inject
	private EmpresaDAO edao;
	@Inject
	private BancoDAO bdao;
	@Inject
	private CuentaDAO cuentadao;

	public InputStream getByIdReporteBancoCuenta(String fechaDesde, String fechaHasta, Long idBanco, Long idCuenta) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook();

			Sheet sheetInforme = workbook.createSheet("Informe");

			// Sheet sheetResumen = workbook.createSheet("Resumen");
			Sheet sheetConciliacion = workbook.createSheet("Conciliacion");
			Sheet sheetNoConciliado = workbook.createSheet("No conciliado empresa");
			Sheet sheetNoConciliadoCartola = workbook.createSheet("No conciliado banco");

			/*
			 * String[] titulosResumen = { "N° MOVIMIENTOS", "N° CARTOLAS",
			 * "N° MOVIMIENTO NO CONCILIADOS", "MONTO MOVIMIENTOS NO CONCILIADOS",
			 * "N° CARTOLAS NO CONCILIADAS","MONTO CARTOLAS NO CONCILIADAS",
			 * "N° CONCILIADOS", "FECHA INICIAL", "FECHA FINAL" };
			 */

			String[] titulosConciliacion = { "N° MOVIMIENTO", "MONTO MOVIMIENTO", "FECHA MOVIMIENTO", "N° CARTOLA",
					"N° DOCUMENTO", "MONTO CARTOLA", "FECHA CARTOLA", "DETALLE CARTOLA", "FECHA CONCILIACIÓN" };
			String[] titulosNoConciliado = { "N° MOVIMIENTO", "GLOSA", "MONTO", "TIPO DOCUMENTO", "TIPO MOVIMIENTO",
					"FECHA MOVIMIENTO" };
			String[] titulosNoConciliadoCartola = { "N° CARTOLA", "DESCRIPCIÓN", "MONTO", "TIPO MOVIMIENTO",
					"N° DOCUMENTO", "FECHA CARTOLA" };
			/*
			 * Row headerRowResumen = sheetResumen.createRow(0);
			 * 
			 * for (int i = 0; i < titulosResumen.length; i++) { Cell cell =
			 * headerRowResumen.createCell(i); cell.setCellValue(titulosResumen[i]); }
			 */

			Row headerRowConciliacion = sheetConciliacion.createRow(0);

			for (int i = 0; i < titulosConciliacion.length; i++) {
				Cell cell = headerRowConciliacion.createCell(i);
				cell.setCellValue(titulosConciliacion[i]);
			}

			Row headerRowNoConciliado = sheetNoConciliado.createRow(0);

			for (int i = 0; i < titulosNoConciliado.length; i++) {
				Cell cell = headerRowNoConciliado.createCell(i);
				cell.setCellValue(titulosNoConciliado[i]);
			}

			Row headerRowNoConciliadoCartola = sheetNoConciliadoCartola.createRow(0);

			for (int i = 0; i < titulosNoConciliadoCartola.length; i++) {
				Cell cell = headerRowNoConciliadoCartola.createCell(i);
				cell.setCellValue(titulosNoConciliadoCartola[i]);
			}

			int rowNum = 1;

			Timestamp fechaInicial = Utilidades.fechaDesde(fechaDesde);
			Timestamp fechaFinal = Utilidades.fechaHasta(fechaHasta);

			List<Conciliacion> listaConciliacion = cdao.getByIdReporteBancoCuenta(fechaInicial, fechaFinal, idBanco,
					idCuenta);
			List<NoConciliado> listaNoConciliado = ncdao.getByIdReporteBancoCuenta(fechaInicial, fechaFinal, idBanco,
					idCuenta);
			List<NoConciliadoCartola> listaNoConciliadoCartola = nccdao.getByIdReporteBancoCuenta(fechaInicial,
					fechaFinal, idBanco, idCuenta);

			rowNum = 1;
			for (int j = 0; j < listaConciliacion.size(); j++) {
				Row rowConciliacion = sheetConciliacion.createRow(rowNum++);
				rowConciliacion.createCell(0)
						.setCellValue(listaConciliacion.get(j).getMovimiento().getNumDocumento().toString());
				rowConciliacion.createCell(1)
						.setCellValue(listaConciliacion.get(j).getMovimiento().getMonto().toString());
				rowConciliacion.createCell(2)
						.setCellValue(Utilidades
								.strToTsDDMMYYYYHHmmssConGuion(listaConciliacion.get(j).getMovimiento().getFecha())
								.substring(0, 10));
				rowConciliacion.createCell(3).setCellValue(listaConciliacion.get(j).getCartola().getNumCartola());
				rowConciliacion.createCell(4).setCellValue(listaConciliacion.get(j).getCartola().getNumDocumento());
				rowConciliacion.createCell(5).setCellValue(listaConciliacion.get(j).getCartola().getMonto().toString());
				rowConciliacion.createCell(6)
						.setCellValue(Utilidades
								.strToTsDDMMYYYYHHmmssConGuion(listaConciliacion.get(j).getCartola().getFecha())
								.substring(0, 10));
				rowConciliacion.createCell(7).setCellValue(listaConciliacion.get(j).getCartola().getDescripcion());
				rowConciliacion.createCell(8).setCellValue(
						Utilidades.strToTsDDMMYYYYHHmmssConGuion(listaConciliacion.get(j).getFecha()).substring(0, 10));
			}

			rowNum = 1;
			Long montoNoConciliado = 0L;
			Long montoNoConciliadoSuma = 0L;
			Long montoNoConciliadoResta = 0L;
			for (int j = 0; j < listaNoConciliado.size(); j++) {
				Row rowNoConciliado = sheetNoConciliado.createRow(rowNum++);
				rowNoConciliado.createCell(0)
						.setCellValue(listaNoConciliado.get(j).getMovimiento().getNumDocumento().toString());
				rowNoConciliado.createCell(1).setCellValue(listaNoConciliado.get(j).getMovimiento().getGlosa());
				rowNoConciliado.createCell(2)
						.setCellValue(listaNoConciliado.get(j).getMovimiento().getMonto().toString());
				rowNoConciliado.createCell(3).setCellValue(listaNoConciliado.get(j).getMovimiento().getTipoDocumento());
				rowNoConciliado.createCell(4)
						.setCellValue(listaNoConciliado.get(j).getMovimiento().getTipoMovimiento());
				rowNoConciliado.createCell(5)
						.setCellValue(Utilidades
								.strToTsDDMMYYYYHHmmssConGuion(listaNoConciliado.get(j).getMovimiento().getFecha())
								.substring(0, 10));
				montoNoConciliado = montoNoConciliado + listaNoConciliado.get(j).getMovimiento().getMonto();

				if (listaNoConciliado.get(j).getMovimiento().getTipoDocumento().equals("CARGO")
						|| listaNoConciliado.get(j).getMovimiento().getTipoDocumento().equals("DEPOSITO")
						|| listaNoConciliado.get(j).getMovimiento().getTipoDocumento().equals("AJUSTE INGRESO")) {
					montoNoConciliadoSuma = montoNoConciliadoSuma + listaNoConciliado.get(j).getMovimiento().getMonto();
				}
				if (listaNoConciliado.get(j).getMovimiento().getTipoDocumento().equals("ABONO")
						|| listaNoConciliado.get(j).getMovimiento().getTipoDocumento().equals("CHEQUE")
						|| listaNoConciliado.get(j).getMovimiento().getTipoDocumento().equals("AJUSTE EGRESO")) {
					montoNoConciliadoResta = montoNoConciliadoResta
							+ listaNoConciliado.get(j).getMovimiento().getMonto();

				}
			}

			rowNum = 1;
			Long montoNoConciliadoCartola = 0L;
			Long montoNoConciliadoCartolaSuma = 0L;
			Long montoNoConciliadoCartolaResta = 0L;

			for (int j = 0; j < listaNoConciliadoCartola.size(); j++) {
				Row rowNoConciliadoCartola = sheetNoConciliadoCartola.createRow(rowNum++);
				rowNoConciliadoCartola.createCell(0)
						.setCellValue(listaNoConciliadoCartola.get(j).getCartola().getNumCartola());
				rowNoConciliadoCartola.createCell(1)
						.setCellValue(listaNoConciliadoCartola.get(j).getCartola().getDescripcion());
				rowNoConciliadoCartola.createCell(2)
						.setCellValue(listaNoConciliadoCartola.get(j).getCartola().getMonto().toString());
				rowNoConciliadoCartola.createCell(3)
						.setCellValue(listaNoConciliadoCartola.get(j).getCartola().getTipoMovimiento());
				rowNoConciliadoCartola.createCell(4)
						.setCellValue(listaNoConciliadoCartola.get(j).getCartola().getNumDocumento());
				rowNoConciliadoCartola.createCell(5)
						.setCellValue(Utilidades
								.strToTsDDMMYYYYHHmmssConGuion(listaNoConciliadoCartola.get(j).getCartola().getFecha())
								.substring(0, 10));
				montoNoConciliadoCartola = montoNoConciliadoCartola
						+ listaNoConciliadoCartola.get(j).getCartola().getMonto();

				if (listaNoConciliadoCartola.get(j).getCartola().getTipoMovimiento().equals("ABONO")) {
					montoNoConciliadoCartolaSuma = montoNoConciliadoCartolaSuma
							+ listaNoConciliadoCartola.get(j).getCartola().getMonto();
				}
				if (listaNoConciliadoCartola.get(j).getCartola().getTipoMovimiento().equals("CARGO")) {
					montoNoConciliadoCartolaResta = montoNoConciliadoCartolaResta
							+ listaNoConciliadoCartola.get(j).getCartola().getMonto();

				}

			}

			/*
			 * Row rowResumen =sheetResumen.createRow(1);
			 * rowResumen.createCell(0).setCellValue(mdao.countAllResumen(fechaInicial,
			 * fechaFinal,idCuenta,idBanco));
			 * rowResumen.createCell(1).setCellValue(cartdao.countAll(fechaInicial,
			 * fechaFinal, idCuenta, idBanco));
			 * rowResumen.createCell(2).setCellValue(ncdao.countAll(fechaInicial,
			 * fechaFinal, idCuenta, idBanco));
			 * rowResumen.createCell(3).setCellValue(montoNoConciliado);
			 * rowResumen.createCell(4).setCellValue(nccdao.countAll(fechaInicial,
			 * fechaFinal, idCuenta, idBanco));
			 * rowResumen.createCell(5).setCellValue(montoNoConciliadoCartola);
			 * rowResumen.createCell(6).setCellValue(cdao.countAllResumen(fechaInicial,
			 * fechaFinal)); rowResumen.createCell(7).setCellValue(Utilidades.
			 * strToTsDDMMYYYYHHmmssConGuion(fechaInicial).substring(0,10));
			 * rowResumen.createCell(8).setCellValue(Utilidades.
			 * strToTsDDMMYYYYHHmmssConGuion(fechaFinal).substring(0,10));
			 */

			Long saldoContable = (long) cuentadao.getById(idCuenta).getSaldoInicial();

			Long saldoBanco = saldoContable + montoNoConciliadoSuma + montoNoConciliadoCartolaSuma
					- (montoNoConciliadoCartolaResta + montoNoConciliadoResta);

			Long numEmp = (long) 1;
			Row rowInforme = sheetInforme.createRow(0);
			rowInforme.createCell(0).setCellValue("EMPRESA:");
			rowInforme.createCell(1).setCellValue(edao.getById(numEmp).getRazonSocial());

			Row rowInforme1 = sheetInforme.createRow(1);
			rowInforme1.createCell(0).setCellValue("RUT:");
			rowInforme1.createCell(1).setCellValue(edao.getById(numEmp).getRut());

			Row rowInforme2 = sheetInforme.createRow(2);
			rowInforme2.createCell(0).setCellValue("GIRO:");
			rowInforme2.createCell(1).setCellValue(edao.getById(numEmp).getGiro());

	//		Row rowInforme3 = sheetInforme.createRow(3);
	//		rowInforme3.createCell(0).setCellValue("DIRECCION:");
	//		rowInforme3.createCell(1).setCellValue(edao.getById(numEmp).getDireccion());

			Row rowInforme5 = sheetInforme.createRow(5);
			rowInforme5.createCell(0).setCellValue("BANCO:");
			rowInforme5.createCell(1).setCellValue(bdao.getById(idBanco).getNombre());
			
			Row rowInforme6 = sheetInforme.createRow(6);
			rowInforme6.createCell(0).setCellValue("CUENTA:");
			rowInforme6.createCell(1).setCellValue(cuentadao.getById(idCuenta).getNumCuenta());


			/*
			 * Row rowInforme6 = sheetInforme.createRow(6);
			 * rowInforme6.createCell(0).setCellValue("N° MOVIMIENTOS:");
			 * rowInforme6.createCell(1).setCellValue(mdao.countAllResumen(fechaInicial,
			 * fechaFinal, idCuenta, idBanco));
			 * 
			 * Row rowInforme7 = sheetInforme.createRow(7);
			 * rowInforme7.createCell(0).setCellValue("N° CARTOLAS:");
			 * rowInforme7.createCell(1).setCellValue(cartdao.countAll(fechaInicial,
			 * fechaFinal, idCuenta, idBanco));
			 * 
			 * 
			 * Row rowInforme6 = sheetInforme.createRow(6);
			 * rowInforme6.createCell(0).setCellValue("N° MOVIMIENTO NO CONCILIADOS:");
			 * rowInforme6.createCell(1).setCellValue(ncdao.countAll(fechaInicial,
			 * fechaFinal, idCuenta, idBanco));
			 */
			/*
			 * Row rowInforme8 = sheetInforme.createRow(8);
			 * rowInforme8.createCell(0).setCellValue("N° CARTOLAS NO CONCILIADAS:");
			 * rowInforme8.createCell(1).setCellValue(nccdao.countAll(fechaInicial,
			 * fechaFinal, idCuenta, idBanco));
			 */
			/*
			 * Row rowInforme11 = sheetInforme.createRow(11);
			 * rowInforme11.createCell(0).setCellValue("N° CONCILIADOS: ");
			 * rowInforme11.createCell(1).setCellValue(cdao.countAllResumen(fechaInicial,
			 * fechaFinal));
			 */

			Row rowInforme8 = sheetInforme.createRow(8);
			rowInforme8.createCell(0).setCellValue("SALDO CONTABLE:");
			rowInforme8.createCell(1).setCellValue(saldoContable);

			Row rowInforme9 = sheetInforme.createRow(9);
			rowInforme9.createCell(0).setCellValue("MONTO MOVIMIENTOS NO CONCILIADOS:");
			rowInforme9.createCell(1).setCellValue(montoNoConciliado);

			Row rowInforme10 = sheetInforme.createRow(10);
			rowInforme10.createCell(0).setCellValue("MONTO CARTOLAS NO CONCILIADAS:");
			rowInforme10.createCell(1).setCellValue(montoNoConciliadoCartola);

			Row rowInforme11 = sheetInforme.createRow(11);
			rowInforme11.createCell(0).setCellValue("SALDO BANCO:");
			rowInforme11.createCell(1).setCellValue(saldoBanco);

			Row rowInforme12 = sheetInforme.createRow(12);
			rowInforme12.createCell(0).setCellValue("FECHA INICIAL:");
			rowInforme12.createCell(1)
					.setCellValue(Utilidades.strToTsDDMMYYYYHHmmssConGuion(fechaInicial).substring(0, 10));

			Row rowInforme13 = sheetInforme.createRow(13);
			rowInforme13.createCell(0).setCellValue("FECHA FINAL:");
			rowInforme13.createCell(1)
					.setCellValue(Utilidades.strToTsDDMMYYYYHHmmssConGuion(fechaFinal).substring(0, 10));
			
			Row rowInforme15 = sheetInforme.createRow(15);
			rowInforme15.createCell(0).setCellValue("FECHA ELABORACIÓN INFORME:");
			rowInforme15.createCell(1)
					.setCellValue(Utilidades.strToTsDDMMYYYYHHmmssConGuion(new Timestamp(System.currentTimeMillis())));

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			return new ByteArrayInputStream(bos.toByteArray());

		} catch (Exception e) {
			log.error("Problemas al generar el reporte ", e);
			return null;
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					log.error("No se pudo cerrar el workbook", e);
				}
			}
		}
	}
}
