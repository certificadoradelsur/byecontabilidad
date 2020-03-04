package cl.certificadoradelsur.byecontabilidad.json;

/**
 * Clase json de CuentaContable
 * 
 * @author juan
 *
 */
public class GrupoCuentaJson {

	private Long id;
	private String nombre;
	private Long idClaseCuenta;
	private String nombreClaseCuenta;
	
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
