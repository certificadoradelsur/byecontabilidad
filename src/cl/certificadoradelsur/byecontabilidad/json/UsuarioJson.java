package cl.certificadoradelsur.byecontabilidad.json;

import java.util.List;

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
	private Boolean activo;
	private String razonSocial;
	private String idUsuario;
	List<EmpresaJson> empresas;
		
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
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public List<EmpresaJson> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<EmpresaJson> empresas) {
		this.empresas = empresas;
	}

	
}
