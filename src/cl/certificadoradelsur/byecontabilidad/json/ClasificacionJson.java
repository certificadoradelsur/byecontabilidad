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
	private Long idEmpresa;
	private String razonSocialEmpresa;
	private String idUsuario;

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

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getRazonSocialEmpresa() {
		return razonSocialEmpresa;
	}

	public void setRazonSocialEmpresa(String razonSocialEmpresa) {
		this.razonSocialEmpresa = razonSocialEmpresa;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	

}
