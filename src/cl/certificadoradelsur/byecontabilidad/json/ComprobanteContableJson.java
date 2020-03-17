package cl.certificadoradelsur.byecontabilidad.json;

/**
 * Clase json de CuentaContable
 * 
 * @author juan
 *
 */
public class ComprobanteContableJson {

	private Long id;
	private Long numero;
	private String glosaGeneral;
	private String fecha;
	private String tipo;
	private Long idCuentaContable;
	private String descripcionCuentaContable;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getGlosaGeneral() {
		return glosaGeneral;
	}
	public void setGlosaGeneral(String glosaGeneral) {
		this.glosaGeneral = glosaGeneral;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getIdCuentaContable() {
		return idCuentaContable;
	}
	public void setIdCuentaContable(Long idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}
	public String getDescripcionCuentaContable() {
		return descripcionCuentaContable;
	}
	public void setDescripcionCuentaContable(String descripcionCuentaContable) {
		this.descripcionCuentaContable = descripcionCuentaContable;
	}


	
}
