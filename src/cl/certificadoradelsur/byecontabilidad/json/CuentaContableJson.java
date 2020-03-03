package cl.certificadoradelsur.byecontabilidad.json;

/**
 * Clase json de CuentaContable
 * 
 * @author juan
 *
 */
public class CuentaContableJson {

	private Long id;
	private String descripcion;
	private Boolean imputable;
	private Boolean analisis;
	private String glosaGeneral;
	private Long idClaseCuenta;
	private Long idGrupoCuenta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean isImputable() {
		return imputable;
	}

	public void setImputable(Boolean imputable) {
		this.imputable = imputable;
	}

	public Boolean isAnalisis() {
		return analisis;
	}

	public void setAnalisis(Boolean analisis) {
		this.analisis = analisis;
	}

	public String getGlosaGeneral() {
		return glosaGeneral;
	}

	public void setGlosaGeneral(String glosaGeneral) {
		this.glosaGeneral = glosaGeneral;
	}

	public Long getIdClaseCuenta() {
		return idClaseCuenta;
	}

	public void setIdClaseCuenta(Long idClaseCuenta) {
		this.idClaseCuenta = idClaseCuenta;
	}

	public Long getIdGrupoCuenta() {
		return idGrupoCuenta;
	}

	public void setIdGrupoCuenta(Long idGrupoCuenta) {
		this.idGrupoCuenta = idGrupoCuenta;
	}

}
