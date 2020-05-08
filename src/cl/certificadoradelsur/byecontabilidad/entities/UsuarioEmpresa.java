package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que crea la tabla empresa
 * 
 * @author juan
 *
 */
@Entity
@Table(name = "usuario_empresa")
@SequenceGenerator(name = "seq_usuario_empresa", sequenceName = "seq_usuario_empresa")


public class UsuarioEmpresa implements Serializable {

	private static final long serialVersionUID = 710736179651008478L;
	private Long id;
	private Usuario usuario;
	private Empresa empresa;

	@Id
	@GeneratedValue(generator = "seq_usuario_empresa", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = true)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = true)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}