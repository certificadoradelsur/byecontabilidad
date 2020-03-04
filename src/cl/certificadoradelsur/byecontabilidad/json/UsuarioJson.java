package cl.certificadoradelsur.byecontabilidad.json;


/**
 * Clase json de usuario
 * 
 * @author juan
 *
 */
public class UsuarioJson {

	private String id;
	private String password;
	private String email;
	private Boolean eliminado;
	private String perfil;
	private Long empresa;
	private Boolean activo;
	private String nombreEmpresa;
	private String idUsuario;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean isActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean isEliminado() {
		return eliminado;
	}
	public void isEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public Long getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

}
