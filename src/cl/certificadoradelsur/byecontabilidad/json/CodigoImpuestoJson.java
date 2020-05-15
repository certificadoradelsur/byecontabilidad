package cl.certificadoradelsur.byecontabilidad.json;


/**
 * Clase json de CodigoImpuesto
 * 
 * @author juan
 *
 */
public class CodigoImpuestoJson {

	private Long id;
	private Long codigo;
	private String descripcion;
	private String idUsuario;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
