package cl.certificadoradelsur.byecontabilidad.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;


/**
 * Clase utilitaria
 * 
 * @author juan
 *
 */
public class Utilidades {

	/**
	 * Se crea el constructor privado
	 */
	private Utilidades() {

	}

	/**
	 * Valida que string no contenga < ni > caracter
	 * 
	 * @param str string ingresado
	 * @return verdadero o falso segun corresponda
	 */
	public static Boolean containsScripting(String str) {
		boolean estado = false;

		if (str.length() == 0) {
			return estado;
		}

		if (str.contains(">") || str.contains("<")) {
			estado = true;
		}
		return estado;
	}

	/**
	 * Transforma a base64
	 * 
	 * @param texto en bytes
	 * @return el texto en b64
	 */
	public static String toB64(byte[] texto) {
		return Base64.getEncoder().encodeToString(texto);
	}

	/**
	 * transforma a sha256
	 * 
	 * @param valor texto a ecncriptar
	 * @return el texto encriptado
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] encriptarSHA256(String valor) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(valor.getBytes());
		return md.digest();
	}

	/**
	 * funcion de encriptacion
	 * 
	 * @param texto a encriptar
	 * @return el valor en base64 encriptado
	 * @throws NoSuchAlgorithmException
	 */
	public static String encriptar(String texto) throws NoSuchAlgorithmException {
		return toB64(encriptarSHA256(texto));
	}

	/**
	 * funcion que convierte un  String a timestamp 
	 * 
	 * @param fecha
	 * @return fecha en string
	 * @throws ParseException
	 */
	public static Timestamp convertidorFecha(String fecha) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = dateFormat.parse(fecha);
		return new java.sql.Timestamp(date.getTime());
	}

	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 	
	/**
	 * funcion que convierte un  String a timestamp 2
	 * 
	 * @param fecha
	 * @return fecha en string
	 * @throws ParseException
	 */
	public static Timestamp convertidorFecha(String fecha, String format) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = dateFormat.parse(fecha);
		return new java.sql.Timestamp(date.getTime());
	}
	
	
	/**
	 * funcion que ordena la fecha dd-mm-aaaa hh:mm
	 * 
	 * @param fecha
	 * @return fecha ordenada
	 */
	public static String strToTsDDMMYYYYHHmmssConGuion(Timestamp fecha) {
		Date fech = fecha;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		return formatter.format(fech);
	}
	
	/**
	 * Funcion para procesar archivo
	 * @param path
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static String procesaArchivoForm(String path, MultipartFormDataInput input) throws IOException {

		String fileName = "";

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("cartola");

		for (InputPart inputPart : inputParts) {

			MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = getFileName(header);
			// convert the uploaded file to inputstream
			InputStream inputStream = inputPart.getBody(InputStream.class, null);
			byte[] bytes = IOUtils.toByteArray(inputStream);
			// constructs upload file path
			fileName = path + fileName;
			writeFile(bytes, fileName);
		}
		return fileName;
	}

	/**
	 * 
	 * @param content
	 * @param filename
	 * @throws IOException
	 */
	public static void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}

	/**
	 * 
	 * @param header
	 * @return
	 */
	public static String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				return name[1].trim().replaceAll("\"", "");
			}
		}
		return "unknown";
	}
	
	
	


	

	/*
	 * Funcion que saca la fecha actual y la devuelve con hora 00:00 y primer dia del mes
	 */
	public static Timestamp fechaActualDesdeFiltro() throws ParseException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String fecha= timestamp.toString().substring(0, 7);
	fecha += "-01 00:00:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = dateFormat.parse(fecha);
		 return new java.sql.Timestamp(date.getTime());
	}
	
	/*
	 * Funcion que saca la fecha actual y la devuelve con hora 23:559:59 y día 31
	 */
	public static Timestamp fechaActualHastaFiltro() throws ParseException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String fecha= timestamp.toString().substring(0, 07);
	fecha += "-31 23:59:59";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = dateFormat.parse(fecha);
		 return new java.sql.Timestamp(date.getTime());
	}
	
	
	
	/*
	 * Funcion que saca la fecha actual y la devuelve con hora 00:00
	 */
	public static Timestamp fechaActualDesde() throws ParseException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String fecha= timestamp.toString().substring(0, 10);
	fecha += " 00:00:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = dateFormat.parse(fecha);
		 return new java.sql.Timestamp(date.getTime());
	}
	
	/*
	 * Funcion que saca la fecha actual y la devuelve con hora 23:559:59
	 */
	public static Timestamp fechaActualHasta() throws ParseException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String fecha= timestamp.toString().substring(0, 10);
	fecha += " 23:59:59";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = dateFormat.parse(fecha);
		 return new java.sql.Timestamp(date.getTime());
	}
	
	/*
	 * Funcion que recibe la fecha como string y la devuelve como timestamp con hora 00:00:00 
	 */
	public static Timestamp fechaDesde(String fecha) throws ParseException {	
	fecha =fecha.substring(0, 10)+" 00:00:00";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = dateFormat.parse(fecha);
		 return new java.sql.Timestamp(date.getTime());
	}
	
	
	/*
	 * Funcion que recibe la fecha como string y la devuelve como timestamp con hora 23:59:59 
	 */
	public static Timestamp fechaHasta(String fecha) throws ParseException {	
	fecha =fecha.substring(0, 10)+" 23:59:59";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = dateFormat.parse(fecha);
		 return new java.sql.Timestamp(date.getTime());
	}
}
