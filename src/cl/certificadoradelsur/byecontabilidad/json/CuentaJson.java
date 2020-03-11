package cl.certificadoradelsur.byecontabilidad.json;

/**
 * Clase json de cuenta
 * 
 * @author juan
 *
 */
public class CuentaJson {


	private Long id;
	private String descripcion;
	private Boolean eliminado;
	private String numCuenta;
	private String nombreEjecutivo;
	private Integer saldoInicial;
	private Long idBanco;
	private String nombreBanco;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public Long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	public String getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	public String getNombreEjecutivo() {
		return nombreEjecutivo;
	}
	public void setNombreEjecutivo(String nombreEjecutivo) {
		this.nombreEjecutivo = nombreEjecutivo;
	}
	public Integer getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(Integer saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public String getNombreBanco() {
		return nombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

}
