package cl.certificadoradelsur.byecontabilidad.json;

/**
 * Clase json de CuentaContable
 * 
 * @author juan
 *
 */
public class ClasificacionJson {

	private Long id;
	private String nombre;
	private Long idClaseCuenta;
	private String nombreClaseCuenta; 
	private Long idGrupoCuenta;
	private String nombreGrupoCuenta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdGrupoCuenta() {
		return idGrupoCuenta;
	}

	public void setIdGrupoCuenta(Long idGrupoCuenta) {
		this.idGrupoCuenta = idGrupoCuenta;
	}

	public String getNombreGrupoCuenta() {
		return nombreGrupoCuenta;
	}

	public void setNombreGrupoCuenta(String nombreGrupoCuenta) {
		this.nombreGrupoCuenta = nombreGrupoCuenta;
	}

	public Long getIdClaseCuenta() {
		return idClaseCuenta;
	}

	public void setIdClaseCuenta(Long idClaseCuenta) {
		this.idClaseCuenta = idClaseCuenta;
	}

	public String getNombreClaseCuenta() {
		return nombreClaseCuenta;
	}

	public void setNombreClaseCuenta(String nombreClaseCuenta) {
		this.nombreClaseCuenta = nombreClaseCuenta;
	}

}