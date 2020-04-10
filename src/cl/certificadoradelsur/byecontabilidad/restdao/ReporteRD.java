package cl.certificadoradelsur.byecontabilidad.restdao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cl.certificadoradelsur.byecontabilidad.dao.BancoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ComprobanteContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ConciliacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.MovimientoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoCartolaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.ComprobanteContable;
import cl.certificadoradelsur.byecontabilidad.entities.Conciliacion;
import cl.certificadoradelsur.byecontabilidad.entities.Movimiento;
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
	 * @Inject private MovimientoDAO mdao;
	 * 
	 * @Inject private CartolaDAO cartdao;
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
	@Inject
	private CuentaContableDAO cuentaContabledao;
	@Inject
	private ComprobanteContableDAO comprobantedao;
	@Inject
	private UsuarioDAO udao;
	@Inject
	private MovimientoDAO movimientodao;

	/**
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idBanco
	 * @param idCuenta
	 * @return
	 */
	public InputStream getByIdReporteBancoCuenta(String fechaDesde, String fechaHasta, Long idBanco, Long idCuenta) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook();

			Sheet sheetInforme = workbook.createSheet("Informe");

			Sheet sheetConciliacion = workbook.createSheet("Conciliacion");
			Sheet sheetNoConciliado = workbook.createSheet("No conciliado empresa");
			Sheet sheetNoConciliadoCartola = workbook.createSheet("No conciliado banco");

			String[] titulosConciliacion = { "N° MOVIMIENTO", "MONTO MOVIMIENTO", "FECHA MOVIMIENTO", "N° CARTOLA",
					"N° DOCUMENTO", "MONTO CARTOLA", "FECHA CARTOLA", "DETALLE CARTOLA", "FECHA CONCILIACIÓN" };
			String[] titulosNoConciliado = { "N° MOVIMIENTO", "GLOSA", "MONTO", "TIPO DOCUMENTO", "TIPO MOVIMIENTO",
					"FECHA MOVIMIENTO" };
			String[] titulosNoConciliadoCartola = { "N° CARTOLA", "DESCRIPCIÓN", "MONTO", "TIPO MOVIMIENTO",
					"N° DOCUMENTO", "FECHA CARTOLA" };

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

			Long saldoContable = (long) cuentadao.getById(idCuenta).getSaldoInicial();

			Long saldoBanco = saldoContable + montoNoConciliadoSuma + montoNoConciliadoCartolaSuma
					- (montoNoConciliadoCartolaResta + montoNoConciliadoResta);

			Long numEmp = cuentadao.getById(idCuenta).getEmpresa().getId();
			Row rowInforme = sheetInforme.createRow(0);
			rowInforme.createCell(0).setCellValue("EMPRESA:");
			rowInforme.createCell(1).setCellValue(edao.getById(numEmp).getRazonSocial());

			Row rowInforme1 = sheetInforme.createRow(1);
			rowInforme1.createCell(0).setCellValue("RUT:");
			rowInforme1.createCell(1).setCellValue(edao.getById(numEmp).getRut());

			Row rowInforme2 = sheetInforme.createRow(2);
			rowInforme2.createCell(0).setCellValue("GIRO:");
			rowInforme2.createCell(1).setCellValue(edao.getById(numEmp).getGiro());

			Row rowInforme5 = sheetInforme.createRow(5);
			rowInforme5.createCell(0).setCellValue("BANCO:");
			rowInforme5.createCell(1).setCellValue(bdao.getById(idBanco).getNombre());

			Row rowInforme6 = sheetInforme.createRow(6);
			rowInforme6.createCell(0).setCellValue("CUENTA:");
			rowInforme6.createCell(1).setCellValue(cuentadao.getById(idCuenta).getNumCuenta());

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

	public InputStream getLibroDiario(String fechaDesde, String fechaHasta, String idUsuario) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook();

			Sheet sheetDiario = workbook.createSheet("Libro diario");

			String[] titulosDiario = { "Cuenta", "Descripción", "C.R", "T.D", "Número RUT", "Glosa", "Debe", "Haber" };
			Timestamp fechaInicial = Utilidades.fechaDesde(fechaDesde);
			Timestamp fechaFinal = Utilidades.fechaHasta(fechaHasta);
			List<ComprobanteContable> listaComprobante = comprobantedao.getLibroDiario(fechaInicial, fechaFinal,
					udao.getById(idUsuario).getOficinaContable().getId());

			int rowNum = 0;
			for (int i = 0; i < listaComprobante.size(); i++) {
				List<Movimiento> listaMovimiento = movimientodao.getByIdComprobanteReporte(listaComprobante.get(i).getId());
				Row rowComprobante = sheetDiario.createRow(rowNum++);
				rowComprobante.createCell(0).setCellValue("N° de Comprobante");
				rowComprobante.createCell(1).setCellValue(listaComprobante.get(i).getNumero().toString());
				rowComprobante.createCell(2).setCellValue("Fecha");
				rowComprobante.createCell(3).setCellValue(
						Utilidades.strToTsDDMMYYYYHHmmssConGuion(listaComprobante.get(i).getFecha()).substring(0, 10));
				Row headerRowDiario = sheetDiario.createRow(rowNum++);
				for (int k = 0; k < titulosDiario.length; k++) {
					Cell cell2 = headerRowDiario.createCell(k);
					cell2.setCellValue(titulosDiario[k]);
				}
				for (int l = 0; l < listaMovimiento.size(); l++) {
					Row rowMovimiento = sheetDiario.createRow(rowNum++);
					rowMovimiento.createCell(0).setCellValue(listaMovimiento.get(l).getCuentaContable().getCodigo());
					rowMovimiento.createCell(1)
							.setCellValue(listaMovimiento.get(l).getCuentaContable().getGlosaGeneral());
					rowMovimiento.createCell(2)
							.setCellValue(listaMovimiento.get(l).getCuentaContable().getSucursal().getDireccion());
					rowMovimiento.createCell(3).setCellValue(listaMovimiento.get(l).getTipoDocumento());
					if (listaMovimiento.get(l).getCuentaContable().isAnalisis().equals(true)) {
						rowMovimiento.createCell(4).setCellValue(listaMovimiento.get(l).getCliente().getRut());
					} else {
						rowMovimiento.createCell(4).setCellValue("");
					}
					rowMovimiento.createCell(5).setCellValue(listaMovimiento.get(l).getGlosa());

					if (listaMovimiento.get(l).getTipoMovimiento().equals("INGRESO")
							|| listaMovimiento.get(l).getTipoDocumento().equals("AJUSTE INGRESO")||
							listaMovimiento.get(l).getTipoMovimiento().equals("DEBE")) {
						rowMovimiento.createCell(6).setCellValue(listaMovimiento.get(l).getMonto());
					} else if (listaMovimiento.get(l).getTipoMovimiento().equals("EGRESO")
							|| listaMovimiento.get(l).getTipoDocumento().equals("AJUSTE EGRESO")
							|| listaMovimiento.get(l).getTipoMovimiento().equals("HABER")) {
						rowMovimiento.createCell(7).setCellValue(listaMovimiento.get(l).getMonto());
					}

				}

			}

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

	public InputStream getLibroMayor(String fechaDesde, String fechaHasta, Long inicialMayor, Long finalMayor,
			String idUsuario) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook();

			Sheet sheetMayor = workbook.createSheet("Libro mayor");

			String[] titulosMayor = { "Día", "Mes", "Número", "Tipo", "TD", "Documento", "Glosa", "Debito", "Credito",
					"Saldo" };
			Timestamp fechaInicial = Utilidades.fechaDesde(fechaDesde);
			Timestamp fechaFinal = Utilidades.fechaHasta(fechaHasta);
			List<Movimiento> listaMovimiento = movimientodao.getByMovEntreCuentas(fechaInicial, fechaFinal,
					inicialMayor, finalMayor, udao.getById(idUsuario).getOficinaContable().getId());

			int rowNum = 0;
			Map<Long, List<Movimiento>> lc = listaMovimiento.stream()
					.collect(Collectors.groupingBy(m -> m.getCuentaContable().getCodigo()));
			for (Map.Entry<Long, List<Movimiento>> entry : lc.entrySet()) {
				Long acumulador = 0L;
				Long debitoA = 0L;
				Long creditoA = 0L;

				Row rowMayor = sheetMayor.createRow(rowNum++);
				rowMayor.createCell(0).setCellValue(entry.getKey());
				rowMayor.createCell(1).setCellValue(entry.getValue().get(0).getCuentaContable().getGlosaGeneral());

				Row headerRowMayor = sheetMayor.createRow(rowNum++);
				for (int k = 0; k < titulosMayor.length; k++) {
					Cell cell2 = headerRowMayor.createCell(k);
					cell2.setCellValue(titulosMayor[k]);
				}

				for (Movimiento mov : entry.getValue()) {
					Row rowMovimiento = sheetMayor.createRow(rowNum++);
					rowMovimiento.createCell(0)
							.setCellValue(Utilidades.strToTsDDMMYYYYHHmmssConGuion(mov.getFecha()).substring(0, 2));
					rowMovimiento.createCell(1).setCellValue(
							Utilidades.mes((Utilidades.strToTsDDMMYYYYHHmmssConGuion(mov.getFecha()).substring(3, 5))));
					rowMovimiento.createCell(2).setCellValue(mov.getComprobanteContable().getNumero().toString());
					rowMovimiento.createCell(3).setCellValue(mov.getTipoMovimiento());
					rowMovimiento.createCell(4).setCellValue(mov.getTipoDocumento());
					rowMovimiento.createCell(5).setCellValue(mov.getNumDocumento());
					rowMovimiento.createCell(6).setCellValue(mov.getGlosa());
					if (mov.getTipoMovimiento().equals("INGRESO") || mov.getTipoDocumento().equals("AJUSTE INGRESO")|| mov.getTipoMovimiento().equals("DEBE")) {
						rowMovimiento.createCell(7).setCellValue(mov.getMonto());
						debitoA = debitoA + mov.getMonto();
					} else if (mov.getTipoMovimiento().equals("EGRESO")
							|| mov.getTipoDocumento().equals("AJUSTE EGRESO")
							|| mov.getTipoMovimiento().equals("HABER")) {
						rowMovimiento.createCell(8).setCellValue(mov.getMonto());
						creditoA = creditoA + mov.getMonto();
					}
					if (acumulador.equals(0L)) {
						if (mov.getTipoMovimiento().equals("INGRESO")
								|| mov.getTipoDocumento().equals("AJUSTE INGRESO")
								|| mov.getTipoMovimiento().equals("DEBE")) {
							acumulador = acumulador + (mov.getMonto());

						} else if (mov.getTipoMovimiento().equals("EGRESO")
								|| mov.getTipoDocumento().equals("AJUSTE EGRESO")
								|| mov.getTipoMovimiento().equals("HABER")) {
							acumulador = acumulador - (mov.getMonto());

						}
						rowMovimiento.createCell(9).setCellValue(mov.getMonto());

					} else {
						if (mov.getTipoMovimiento().equals("INGRESO")
								|| mov.getTipoDocumento().equals("AJUSTE INGRESO")
								|| mov.getTipoMovimiento().equals("DEBE")) {
							acumulador = acumulador + (mov.getMonto());

							rowMovimiento.createCell(9).setCellValue(acumulador);
						} else if (mov.getTipoMovimiento().equals("EGRESO")
								|| mov.getTipoDocumento().equals("AJUSTE EGRESO")
								|| mov.getTipoMovimiento().equals("HABER")) {
							acumulador = acumulador - (mov.getMonto());
							rowMovimiento.createCell(9).setCellValue(acumulador);
						}
					}
				}
				Row rowAcumulado = sheetMayor.createRow(rowNum++);
				rowAcumulado.createCell(7).setCellValue(debitoA);
				rowAcumulado.createCell(8).setCellValue(creditoA);
				rowAcumulado.createCell(9).setCellValue(acumulador);
			}

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

	public InputStream getBalanceGeneral(String fechaDesde, String fechaHasta, String anio, String idUsuario) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook();

			Sheet sheetGeneral = workbook.createSheet("Balance general");

			String[] titulosGeneral = { "CUENTA", "", "DEBITO", "CREDITO", "DEUDOR", "ACREEDOR", "ACTIVOS", "PASIVOS",
					"PERDIDA", "GANANCIA" };
			Timestamp fechaInicial = Utilidades.convertidorFecha(anio + "-" + fechaDesde + "-" + 01);
			Timestamp fechaFinal = Utilidades.convertidorFecha(anio + "-" + fechaHasta + "-" + 01);
			List<Movimiento> listaMovimientos = movimientodao.getBalanceGeneral(fechaInicial, fechaFinal,
					udao.getById(idUsuario).getOficinaContable().getId());
//
//			int rowNum = 0;
//			Map<Long, List<Movimiento>> lc = listaMovimiento.stream()
//					.collect(Collectors.groupingBy(m -> m.getCuentaContable().getCodigo()));
//			for (Map.Entry<Long, List<Movimiento>> entry : lc.entrySet()) {
//				Long acumulador = 0L;
//				Long debitoA = 0L;
//				Long creditoA = 0L;
//
//				Row rowMayor = sheetMayor.createRow(rowNum++);
//				rowMayor.createCell(0).setCellValue(entry.getKey());
//				rowMayor.createCell(1).setCellValue(entry.getValue().get(0).getCuentaContable().getGlosaGeneral());
//
//				Row headerRowMayor = sheetMayor.createRow(rowNum++);
//				for (int k = 0; k < titulosMayor.length; k++) {
//					Cell cell2 = headerRowMayor.createCell(k);
//					cell2.setCellValue(titulosMayor[k]);
//				}
//
//				for (Movimiento mov : entry.getValue()) {
//					Row rowMovimiento = sheetMayor.createRow(rowNum++);
//					rowMovimiento.createCell(0)
//							.setCellValue(Utilidades.strToTsDDMMYYYYHHmmssConGuion(mov.getFecha()).substring(0, 2));
//					rowMovimiento.createCell(1).setCellValue(
//							Utilidades.mes((Utilidades.strToTsDDMMYYYYHHmmssConGuion(mov.getFecha()).substring(3, 5))));
//					rowMovimiento.createCell(2).setCellValue(mov.getComprobanteContable().getNumero().toString());
//					rowMovimiento.createCell(3).setCellValue(mov.getTipoMovimiento());
//					rowMovimiento.createCell(4).setCellValue(mov.getTipoDocumento());
//					rowMovimiento.createCell(5).setCellValue(mov.getNumDocumento());
//					rowMovimiento.createCell(6).setCellValue(mov.getGlosa());
//					if (mov.getTipoMovimiento().equals("INGRESO") || mov.getTipoDocumento().equals("AJUSTE INGRESO")) {
//						rowMovimiento.createCell(7).setCellValue(mov.getMonto());
//						debitoA = debitoA + mov.getMonto();
//					} else if (mov.getTipoMovimiento().equals("EGRESO")
//							|| mov.getTipoDocumento().equals("AJUSTE EGRESO")) {
//						rowMovimiento.createCell(8).setCellValue(mov.getMonto());
//						creditoA = creditoA + mov.getMonto();
//					}
//					if (acumulador.equals(0L)) {
//						if (mov.getTipoMovimiento().equals("INGRESO")
//								|| mov.getTipoDocumento().equals("AJUSTE INGRESO")) {
//							acumulador = acumulador + (mov.getMonto());
//
//						} else if (mov.getTipoMovimiento().equals("EGRESO")
//								|| mov.getTipoDocumento().equals("AJUSTE EGRESO")) {
//							acumulador = acumulador - (mov.getMonto());
//
//						}
//						rowMovimiento.createCell(9).setCellValue(mov.getMonto());
//
//					} else {
//						if (mov.getTipoMovimiento().equals("INGRESO")
//								|| mov.getTipoDocumento().equals("AJUSTE INGRESO")) {
//							acumulador = acumulador + (mov.getMonto());
//
//							rowMovimiento.createCell(9).setCellValue(acumulador);
//						} else if (mov.getTipoMovimiento().equals("EGRESO")
//								|| mov.getTipoDocumento().equals("AJUSTE EGRESO")) {
//							acumulador = acumulador - (mov.getMonto());
//							rowMovimiento.createCell(9).setCellValue(acumulador);
//						}
//					}
//				}
//				Row rowAcumulado = sheetMayor.createRow(rowNum++);
//				rowAcumulado.createCell(7).setCellValue(debitoA);
//				rowAcumulado.createCell(8).setCellValue(creditoA);
//				rowAcumulado.createCell(9).setCellValue(acumulador);
//			}

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

	public InputStream getBalanceClasificado(String fechaDesde, String fechaHasta, String anio, String idUsuario) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook();

			Sheet sheetClasificado = workbook.createSheet("Balance clasificado");

			Timestamp fechaInicial = Utilidades.convertidorFecha(anio + "-" + fechaDesde + "-" + 01);
			Timestamp fechaFinal = Utilidades.convertidorFecha(anio + "-" + fechaHasta + "-" + 01);
//			List<Movimiento> listaMovimiento = movimientodao.getByMovEntreCuentas(fechaInicial, fechaFinal,
//					inicialMayor, finalMayor, udao.getById(idUsuario).getOficinaContable().getId());
//
//			int rowNum = 0;
//			Map<Long, List<Movimiento>> lc = listaMovimiento.stream()
//					.collect(Collectors.groupingBy(m -> m.getCuentaContable().getCodigo()));
//			for (Map.Entry<Long, List<Movimiento>> entry : lc.entrySet()) {
//				Long acumulador = 0L;
//				Long debitoA = 0L;
//				Long creditoA = 0L;
//
//				Row rowMayor = sheetMayor.createRow(rowNum++);
//				rowMayor.createCell(0).setCellValue(entry.getKey());
//				rowMayor.createCell(1).setCellValue(entry.getValue().get(0).getCuentaContable().getGlosaGeneral());
//
//				Row headerRowMayor = sheetMayor.createRow(rowNum++);
//				for (int k = 0; k < titulosMayor.length; k++) {
//					Cell cell2 = headerRowMayor.createCell(k);
//					cell2.setCellValue(titulosMayor[k]);
//				}
//
//				for (Movimiento mov : entry.getValue()) {
//					Row rowMovimiento = sheetMayor.createRow(rowNum++);
//					rowMovimiento.createCell(0)
//							.setCellValue(Utilidades.strToTsDDMMYYYYHHmmssConGuion(mov.getFecha()).substring(0, 2));
//					rowMovimiento.createCell(1).setCellValue(
//							Utilidades.mes((Utilidades.strToTsDDMMYYYYHHmmssConGuion(mov.getFecha()).substring(3, 5))));
//					rowMovimiento.createCell(2).setCellValue(mov.getComprobanteContable().getNumero().toString());
//					rowMovimiento.createCell(3).setCellValue(mov.getTipoMovimiento());
//					rowMovimiento.createCell(4).setCellValue(mov.getTipoDocumento());
//					rowMovimiento.createCell(5).setCellValue(mov.getNumDocumento());
//					rowMovimiento.createCell(6).setCellValue(mov.getGlosa());
//					if (mov.getTipoMovimiento().equals("INGRESO") || mov.getTipoDocumento().equals("AJUSTE INGRESO")) {
//						rowMovimiento.createCell(7).setCellValue(mov.getMonto());
//						debitoA = debitoA + mov.getMonto();
//					} else if (mov.getTipoMovimiento().equals("EGRESO")
//							|| mov.getTipoDocumento().equals("AJUSTE EGRESO")) {
//						rowMovimiento.createCell(8).setCellValue(mov.getMonto());
//						creditoA = creditoA + mov.getMonto();
//					}
//					if (acumulador.equals(0L)) {
//						if (mov.getTipoMovimiento().equals("INGRESO")
//								|| mov.getTipoDocumento().equals("AJUSTE INGRESO")) {
//							acumulador = acumulador + (mov.getMonto());
//
//						} else if (mov.getTipoMovimiento().equals("EGRESO")
//								|| mov.getTipoDocumento().equals("AJUSTE EGRESO")) {
//							acumulador = acumulador - (mov.getMonto());
//
//						}
//						rowMovimiento.createCell(9).setCellValue(mov.getMonto());
//
//					} else {
//						if (mov.getTipoMovimiento().equals("INGRESO")
//								|| mov.getTipoDocumento().equals("AJUSTE INGRESO")) {
//							acumulador = acumulador + (mov.getMonto());
//
//							rowMovimiento.createCell(9).setCellValue(acumulador);
//						} else if (mov.getTipoMovimiento().equals("EGRESO")
//								|| mov.getTipoDocumento().equals("AJUSTE EGRESO")) {
//							acumulador = acumulador - (mov.getMonto());
//							rowMovimiento.createCell(9).setCellValue(acumulador);
//						}
//					}
//				}
//				Row rowAcumulado = sheetMayor.createRow(rowNum++);
//				rowAcumulado.createCell(7).setCellValue(debitoA);
//				rowAcumulado.createCell(8).setCellValue(creditoA);
//				rowAcumulado.createCell(9).setCellValue(acumulador);
//			}

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
