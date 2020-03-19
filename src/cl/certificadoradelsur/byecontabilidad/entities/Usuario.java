package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase que crea la tabla usuario
 * 
 * @author juan
 *
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
		@NamedQuery(name = "Usuario.getAll", query = "SELECT u FROM Usuario  u  where u.oficinaContable.id =:idOficinaContable and u.eliminado = false and (true = :ignoreId or upper(u.id)  like :id)"),
		@NamedQuery(name = "Usuario.countAll", query = "SELECT count(u.id) FROM Usuario u where u.oficinaContable.id =:idOficinaContable and u.eliminado = false and (true = :ignoreId or upper(u.id)  like :id)") })

public class Usuario implements Serializable {

	private static final long serialVersionUID = -484233189837672260L;
	private String id;
	private Boolean activo;
	private String password;
	private String email;
	private Boolean eliminado;
	private Perfil perfil;
	private OficinaContable oficinaContable;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "activo", nullable = false)
	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Column(name = "clave", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "eliminado", nullable = false)
	public Boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	@ManyToOne
	@JoinColumn(name = "id_perfil", nullable = true)
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@ManyToOne
	@JoinColumn(name = "id_oficina_contable", nullable = true)
	public OficinaContable getOficinaContable() {
		return oficinaContable;
	}

	public void setOficinaContable(OficinaContable oficinaContable) {
		this.oficinaContable = oficinaContable;
	}

}
