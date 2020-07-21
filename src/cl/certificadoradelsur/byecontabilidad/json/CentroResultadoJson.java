package cl.certificadoradelsur.byecontabilidad.json;

public class CentroResultadoJson {
	
	
	private Long id;
	private String nombre;
	private String idUsuario;
	private Long idSucursal;
	private String nombreSucursal;
	
	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdSucursal() {
		return idSucursal;
	}
	public String getNombreSucursal() {
		return nombreSucursal;
	}
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	
	
	
	

}
