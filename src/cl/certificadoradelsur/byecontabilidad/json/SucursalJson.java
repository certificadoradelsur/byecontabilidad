package cl.certificadoradelsur.byecontabilidad.json;

/**
 * Clase json de sucursal
 * 
 * @author juan
 *
 */
public class SucursalJson {

	private Long codigo;
	private String direccion;
	private Long idEmpresa;
	private String nombreEmpresa;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	


}
