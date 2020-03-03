package cl.certificadoradelsur.byecontabilidad.exception;
/**
 * Clase de exception del programa
 * @author juan
 *
 */
public class ByeContabilidadException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3797879634183056917L;
	/**
	 * Constructor de la clase exception
	 * @param msg para que se mostrado con la excepcion
	 */
	public ByeContabilidadException(String msg) {
		super(msg);
	}

}
