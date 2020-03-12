package cl.certificadoradelsur.byecontabilidad.json;


/**
 * Clase json de banco
 * 
 * @author juan
 *
 */
public class BancoJson {

	private Long id;
	private String nombre;
	private String cuenta;
	private String nombreEjecutivo;
	private Integer saldoInicial;
	private Boolean eliminado;
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
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
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
	public Boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
}
