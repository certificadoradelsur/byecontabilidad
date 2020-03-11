package cl.certificadoradelsur.byecontabilidad.restdao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import cl.certificadoradelsur.byecontabilidad.dao.BancoDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CartolaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.CuentaDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Cartola;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.CartolaJson;
import cl.certificadoradelsur.byecontabilidad.json.ResultadoImportacionJson;
import cl.certificadoradelsur.utils.Constantes;
import cl.certificadoradelsur.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class CartolaRD {
	private static Logger log = Logger.getLogger(CartolaRD.class);
	@Inject
	private BancoDAO bdao;
	@Inject
	private CartolaDAO cdao;
	@Inject
	private CuentaDAO cuentadao;

	/**
	 * funcion que almacena cartola
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(CartolaJson cj) {
		try {
			Cartola cartola = new Cartola();
			if (Utilidades.containsScripting(cj.getDescripcion()).compareTo(true) == 0
					|| Utilidades.containsScripting(cj.getTipoMovimiento()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				cartola.setNumCartola(cj.getNumCartola());
				cartola.setBanco(bdao.getById(cj.getIdBanco()));
				cartola.setCuenta(cuentadao.getById(cj.getIdCuenta()));
				cartola.setNumDocumento(cj.getNumDocumento());
				cartola.setFecha(Utilidades.convertidorFecha(cj.getFecha()));
				cartola.setDescripcion(cj.getDescripcion());
				cartola.setTipoMovimiento(cj.getTipoMovimiento());
				cartola.setMonto(cj.getMonto());
				cartola.setEliminado(false);
				cdao.guardar(cartola);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (

		Exception e) {
			log.error("No se pudo guardar la cartola ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String fechaInicial, String fechaFinal, Long idCuenta, Long idBanco) {
		try {
			if (idCuenta == null || idBanco == null) {
				return 0L;
			}
			if (fechaInicial.equals("") || fechaFinal.equals("")) {
				fechaInicial = Utilidades.fechaActualDesdeFiltro().toString();
				fechaFinal = Utilidades.fechaActualHastaFiltro().toString();

			}
			return cdao.countAll(Utilidades.convertidorFecha(fechaInicial), Utilidades.fechaHasta(fechaFinal), idCuenta,
					idBanco);
		} catch (Exception e) {
			log.error("No se puede contar el total de cartolas ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de las cartolas en json
	 * 
	 * @param page  número de pagina
	 * @param limit largo de la pagina
	 * @return json con total de cartolas
	 */
	public List<CartolaJson> getAll(Integer page, Integer limit, String fechaInicial, String fechaFinal, Long idCuenta,
			Long idBanco) {
		List<CartolaJson> lcj = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}
			if (idCuenta == null || idBanco == null) {
				return lcj;
			}
			if (fechaInicial.equals("") || fechaFinal.equals("")) {
				fechaInicial = Utilidades.fechaActualDesdeFiltro().toString();
				fechaFinal = Utilidades.fechaActualHastaFiltro().toString();

			}
			List<Cartola> lc = cdao.getAll(inicio, limit, Utilidades.convertidorFecha(fechaInicial),
					Utilidades.fechaHasta(fechaFinal), idCuenta, idBanco);
			for (int i = 0; i < lc.size(); i++) {
				CartolaJson cj = new CartolaJson();
				cj.setId(lc.get(i).getId());
				cj.setNumCartola(lc.get(i).getNumCartola());
				cj.setNombreBanco(lc.get(i).getBanco().getNombre());
				cj.setNumCuenta(lc.get(i).getCuenta().getNumCuenta());
				cj.setIdBanco(lc.get(i).getBanco().getId());
				cj.setNumCuenta(lc.get(i).getCuenta().getNumCuenta());
				cj.setNumDocumento(lc.get(i).getNumDocumento());
				cj.setFecha(Utilidades.strToTsDDMMYYYYHHmmssConGuion((lc.get(i).getFecha())).substring(0, 10));
				cj.setDescripcion(lc.get(i).getDescripcion());
				cj.setTipoMovimiento(lc.get(i).getTipoMovimiento());
				cj.setMonto(lc.get(i).getMonto());
				lcj.add(cj);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de cartolas ", e);
		}
		return lcj;
	}

	/**
	 * metodo que modifica cartola
	 * 
	 * @param pj json de cartolaJson
	 * @return mensaje de exito o error
	 */
	public String update(CartolaJson cj) {
		try {
			Cartola cartola = cdao.getById(cj.getId());
			if (Utilidades.containsScripting(cj.getDescripcion()).compareTo(true) == 0
					|| Utilidades.containsScripting(cj.getTipoMovimiento()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {

				cartola.setCuenta(cuentadao.getById(cj.getIdCuenta()));
				cartola.setNumDocumento(cj.getNumDocumento());
				cartola.setDescripcion(cj.getDescripcion());
				cartola.setBanco(bdao.getById(cj.getIdBanco()));
				cartola.setFecha(Utilidades.convertidorFecha(cj.getFecha()));
				cartola.setTipoMovimiento(cj.getTipoMovimiento());
				cartola.setMonto(cj.getMonto());
				cartola.setEliminado(cj.isEliminado());
				cdao.update(cartola);
				return Constantes.MENSAJE_REST_OK;
			}
		} catch (Exception e) {
			log.error("No se pudo modificar la cartola");
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener una cartola
	 * 
	 * @param id de cartola
	 * @return mensaje de exito o error
	 */
	public CartolaJson getById(CartolaJson cj) {
		Cartola cartola = cdao.getById(cj.getId());
		CartolaJson cJson = new CartolaJson();
		cJson.setId(cartola.getId());
		cJson.setMonto(cartola.getMonto());
		cJson.setNumCuenta(cuentadao.getById(cj.getIdCuenta()).getNumCuenta());
		cJson.setNumDocumento(cartola.getNumDocumento());
		cJson.setDescripcion(cartola.getDescripcion());
		cJson.setEliminado(cartola.isEliminado());
		cJson.setFecha(cartola.getFecha().toString());
		cJson.setIdBanco(cartola.getBanco().getId());
		cJson.setTipoMovimiento(cartola.getTipoMovimiento());
		return cJson;
	}

	/**
	 * metodo elimina una cartola
	 * 
	 * @param pj json de cartola
	 * @return mensaje de exito o error
	 */
	public String eliminar(CartolaJson cj) {
		try {
			Cartola cartola = cdao.getById(cj.getId());
			cdao.eliminar(cartola);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la cartola");
			return e.getMessage();
		}
	}

	/*
	 * Funcion que lee un archivo excel con formato de cartola banco Estado y
	 * almacena en base de datos
	 */
	public List<ResultadoImportacionJson> leerEstado(MultipartFormDataInput input, Long banco, Long cuenta,
			String numCartola, String anio) {
		List<ResultadoImportacionJson> lrij = new ArrayList<>();
		try {
			String archivo = Utilidades.procesaArchivoForm(Constantes.FOLDER_IMPORTACION, input);
			FileInputStream file = new FileInputStream(new File(archivo));
			Workbook book = new XSSFWorkbook(file);
			Sheet datatypeSheet = book.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			log.info(Constantes.MENSAJE_PROCESO_IMPORTACION);
			int linea = 0;
			while (iterator.hasNext()) {
				linea++;
				Cartola c = new Cartola();
				Row currentRow = iterator.next();
				String cell1 = currentRow.getCell(5).getNumericCellValue() + "";
				long cell1M = Long.parseLong(cell1.replace(".", ""));
				String cell2 = currentRow.getCell(4).getNumericCellValue() + "";
				long cell2M = Long.parseLong(cell2.replace(".", ""));
				Timestamp fecha = Utilidades.convertidorFecha(currentRow.getCell(0).getStringCellValue(), "dd-MM-yyyy");

				Cartola car1 = cdao.getByFecha(fecha, cell1M, "ABONO");
				Cartola car2 = cdao.getByFecha(fecha, cell2M, "CARGO");

				Timestamp fechaInicio = Utilidades.convertidorFecha(anio + "-" + numCartola + "-" + 01);
				Timestamp fechaFinal = Utilidades.convertidorFecha(anio + "-" + numCartola + "-" + 31);
				if (((fecha.before(fechaFinal)) && (fecha.after(fechaInicio)))) {
					if (car1 != null) {
						if (!(car1.isEliminado())) {
							ResultadoImportacionJson rij = new ResultadoImportacionJson();
							rij.setDetalle(Constantes.MENSAJE_CARTOLA_EXISTENTE);
							rij.setEstado(Constantes.RESPUESTA_IMPORTACION_FAIL);
							rij.setLinea(linea);
							lrij.add(rij);
						}
					} else if (car2 != null) {
						if (!car2.isEliminado()) {
							ResultadoImportacionJson rij = new ResultadoImportacionJson();
							rij.setDetalle(Constantes.MENSAJE_CARTOLA_EXISTENTE);
							rij.setEstado(Constantes.RESPUESTA_IMPORTACION_FAIL);
							rij.setLinea(linea);
							lrij.add(rij);
						}
					} else {
						c.setFecha(fecha);
						String num = currentRow.getCell(2).getNumericCellValue() + "";
						c.setNumDocumento((num.replace(".", "")));
						c.setDescripcion(currentRow.getCell(3).getStringCellValue());
						if (currentRow.getCell(4).getNumericCellValue() == 0) {
							c.setMonto(cell1M);
							c.setTipoMovimiento("ABONO");
						} else {
							c.setMonto(cell2M);
							c.setTipoMovimiento("CARGO");
						}
						c.setCuenta(cuentadao.getById(cuenta));
						c.setBanco(bdao.getById(banco));
						c.setNumCartola(numCartola + anio);
						c.setEliminado(false);
						cdao.guardar(c);
						ResultadoImportacionJson rij = new ResultadoImportacionJson();
						rij.setDetalle(Constantes.MENSAJE_CARTOLA_OK);
						rij.setEstado(Constantes.RESPUESTA_IMPORTACION_OK);
						rij.setLinea(linea);
						lrij.add(rij);
					}
				} else {
					ResultadoImportacionJson rij = new ResultadoImportacionJson();
					rij.setDetalle(Constantes.MENSAJE_FECHA_FAIL);
					rij.setEstado(Constantes.RESPUESTA_IMPORTACION_FAIL);
					rij.setLinea(linea);
					lrij.add(rij);
				}
			}
			book.close();
			return lrij;
		} catch (Exception e) {
			log.error(e);
			return lrij;
		}
	}

	/*
	 * Funcion que lee un archivo excel con formato de cartola banco de Chile y
	 * almacena en base de datos
	 */
	public List<ResultadoImportacionJson> leerChile(MultipartFormDataInput input, Long banco, Long cuenta,
			String numCartola, String anio) {
		List<ResultadoImportacionJson> lrij = new ArrayList<>();
		try {
			String archivo = Utilidades.procesaArchivoForm(Constantes.FOLDER_IMPORTACION, input);
			FileInputStream file = new FileInputStream(new File(archivo));
			Workbook book = new XSSFWorkbook(file);
			Sheet datatypeSheet = book.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			log.info(Constantes.MENSAJE_PROCESO_IMPORTACION);
			int linea = 0;
			while (iterator.hasNext()) {
				Calendar fechaAct = Calendar.getInstance();
				int anioA = fechaAct.get(Calendar.YEAR);

				linea++;
				Cartola c = new Cartola();
				Row currentRow = iterator.next();
				String cell1 = currentRow.getCell(4).getNumericCellValue() + "";
				String cell11 = cell1.replace(".", "");
				String cell111 = cell11.substring(0, cell11.length() - 1);
				long cell1M = Long.parseLong(cell111);
				String cell2 = currentRow.getCell(3).getNumericCellValue() + "";
				String cell22 = cell2.replace(".", "");
				String cell222 = cell22.substring(0, cell22.length() - 1);
				long cell2M = Long.parseLong(cell222);

				String fechaS = currentRow.getCell(0).getStringCellValue() + "-" + anioA;
				String fechaS2 = fechaS.replace("/", "-");
				Timestamp fecha = Utilidades.convertidorFecha(fechaS2, "dd-MM-yyyy");

				Cartola car1 = cdao.getByFecha(fecha, cell1M, "ABONO");
				Cartola car2 = cdao.getByFecha(fecha, cell2M, "CARGO");

				Timestamp fechaInicio = Utilidades.convertidorFecha(anio + "-" + numCartola + "-" + 01);
				Timestamp fechaFinal = Utilidades.convertidorFecha(anio + "-" + numCartola + "-" + 31);
				if (((fecha.before(fechaFinal)) && (fecha.after(fechaInicio)))) {
					if (car1 != null) {
						if (!(car1.isEliminado())) {
							ResultadoImportacionJson rij = new ResultadoImportacionJson();
							rij.setDetalle(Constantes.MENSAJE_CARTOLA_EXISTENTE);
							rij.setEstado(Constantes.RESPUESTA_IMPORTACION_FAIL);
							rij.setLinea(linea);
							lrij.add(rij);
						}
					} else if (car2 != null) {
						if (!car2.isEliminado()) {
							ResultadoImportacionJson rij = new ResultadoImportacionJson();
							rij.setDetalle(Constantes.MENSAJE_CARTOLA_EXISTENTE);
							rij.setEstado(Constantes.RESPUESTA_IMPORTACION_FAIL);
							rij.setLinea(linea);
							lrij.add(rij);
						}
					} else {
						c.setFecha(fecha);
						c.setNumDocumento((""));
						c.setDescripcion(currentRow.getCell(1).getStringCellValue());
						if (currentRow.getCell(3).getNumericCellValue() == 0) {
							c.setMonto(cell1M);
							c.setTipoMovimiento("ABONO");
						} else {
							c.setMonto(cell2M);
							c.setTipoMovimiento("CARGO");
						}
						c.setCuenta(cuentadao.getById(cuenta));
						c.setBanco(bdao.getById(banco));
						c.setNumCartola(numCartola + anio);
						c.setEliminado(false);
						cdao.guardar(c);
						ResultadoImportacionJson rij = new ResultadoImportacionJson();
						rij.setDetalle(Constantes.MENSAJE_CARTOLA_OK);
						rij.setEstado(Constantes.RESPUESTA_IMPORTACION_OK);
						rij.setLinea(linea);
						lrij.add(rij);
					}
				}else {
				ResultadoImportacionJson rij = new ResultadoImportacionJson();
				rij.setDetalle(Constantes.MENSAJE_FECHA_FAIL);
				rij.setEstado(Constantes.RESPUESTA_IMPORTACION_FAIL);
				rij.setLinea(linea);
				lrij.add(rij);
			} 

				
			}
			book.close();
			return lrij;
		} catch (Exception e) {
			log.error(e);
			return lrij;
		}
	}

	/*
	 * Funcion que lee un archivo excel con formato de cartola banco Santander y
	 * almacena en base de datos
	 */
	public List<ResultadoImportacionJson> leerSantander(MultipartFormDataInput input, Long banco, Long cuenta,
			String numCartola, String anio) {
		List<ResultadoImportacionJson> lrij = new ArrayList<>();
		try {
			String archivo = Utilidades.procesaArchivoForm(Constantes.FOLDER_IMPORTACION, input);
			FileInputStream file = new FileInputStream(new File(archivo));
			Workbook book = new XSSFWorkbook(file);
			Sheet datatypeSheet = book.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			log.info(Constantes.MENSAJE_PROCESO_IMPORTACION);
			int linea = 0;
			while (iterator.hasNext()) {
				linea++;
				Cartola c = new Cartola();
				Row currentRow = iterator.next();
				String cell1 = null;
				String cell2 = null;

				try {
					cell1 = currentRow.getCell(1).getNumericCellValue() + "";

				} catch (Exception e) {
					cell1 = "0";
				}

				long cell1M = Long.parseLong(cell1.replace(".", ""));

				try {
					cell2 = currentRow.getCell(2).getNumericCellValue() + "";
				} catch (Exception e) {
					cell2 = "0";
				}

				long cell2M = Long.parseLong(cell2.replace(".", ""));
				String fechaS = currentRow.getCell(0).getStringCellValue().replace("/", "-");
				Timestamp fecha = Utilidades.convertidorFecha(fechaS, "dd-MM-yyyy");

				Cartola car1 = cdao.getByFecha(fecha, cell1M, "CARGO");
				Cartola car2 = cdao.getByFecha(fecha, cell2M, "ABONO");

				Timestamp fechaInicio = Utilidades.convertidorFecha(anio + "-" + numCartola + "-" + 01);
				Timestamp fechaFinal = Utilidades.convertidorFecha(anio + "-" + numCartola + "-" + 31);
				if (((fecha.before(fechaFinal)) && (fecha.after(fechaInicio)))) {
						if (car1 != null) {
						if (!(car1.isEliminado())) {
							ResultadoImportacionJson rij = new ResultadoImportacionJson();
							rij.setDetalle(Constantes.MENSAJE_CARTOLA_EXISTENTE);
							rij.setEstado(Constantes.RESPUESTA_IMPORTACION_FAIL);
							rij.setLinea(linea);
							lrij.add(rij);
						}
					} else if (car2 != null) {
						if (!car2.isEliminado()) {
							ResultadoImportacionJson rij = new ResultadoImportacionJson();
							rij.setDetalle(Constantes.MENSAJE_CARTOLA_EXISTENTE);
							rij.setEstado(Constantes.RESPUESTA_IMPORTACION_FAIL);
							rij.setLinea(linea);
							lrij.add(rij);
						}
					} else {
						c.setFecha(fecha);
						c.setNumDocumento((""));
						c.setDescripcion(currentRow.getCell(3).getStringCellValue());
						if (cell1M != 0) {
							c.setMonto(cell1M);
							c.setTipoMovimiento("CARGO");
						} else {
							c.setMonto(cell2M);
							c.setTipoMovimiento("ABONO");
						}
						c.setCuenta(cuentadao.getById(cuenta));
						c.setBanco(bdao.getById(banco));
						c.setNumCartola(numCartola + anio);
						c.setEliminado(false);
						cdao.guardar(c);
						ResultadoImportacionJson rij = new ResultadoImportacionJson();
						rij.setDetalle(Constantes.MENSAJE_CARTOLA_OK);
						rij.setEstado(Constantes.RESPUESTA_IMPORTACION_OK);
						rij.setLinea(linea);
						lrij.add(rij);
					}
				}else {
				ResultadoImportacionJson rij = new ResultadoImportacionJson();
				rij.setDetalle(Constantes.MENSAJE_FECHA_FAIL);
				rij.setEstado(Constantes.RESPUESTA_IMPORTACION_FAIL);
				rij.setLinea(linea);
				lrij.add(rij);
			} 
				
			}
			book.close();
			return lrij;
		} catch (Exception e) {
			log.error(e);

			return lrij;
		}
	}

	/**
	 * funcion que busca cartolas por numDocumento
	 * 
	 * @param cj
	 * @return cartolajson
	 */
	public Object getByNumDocumento(CartolaJson cj) {
		Cartola cartola = cdao.getByNumDocumento(cj.getNumDocumento());
		CartolaJson cJson = new CartolaJson();
		cJson.setId(cartola.getId());
		cJson.setMonto(cartola.getMonto());
		cJson.setNumCuenta(cuentadao.getById(cj.getIdCuenta()).getNumCuenta());
		cJson.setNumDocumento(cartola.getNumDocumento());
		cJson.setDescripcion(cartola.getDescripcion());
		cJson.setEliminado(cartola.isEliminado());
		cJson.setFecha(cartola.getFecha().toString());
		cJson.setIdBanco(cartola.getBanco().getId());
		cJson.setTipoMovimiento(cartola.getTipoMovimiento());
		return cJson;
	}

}
