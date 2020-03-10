package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Clase que crea la tabla oficinaContable
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "oficina_contable")
@SequenceGenerator(name = "seq_oficina_contable", sequenceName = "seq_oficina_contable")
@NamedQueries({
		@NamedQuery(name = "OficinaContable.getAll", query = "SELECT o FROM OficinaContable o"),
		@NamedQuery(name = "OficinaContable.countAll", query = "SELECT count(o.id) FROM OficinaContable o")})

public class OficinaContable implements Serializable {

	private static final long serialVersionUID = 1327287214392412312L;
	private Long id;
	private String rut;
	private String correo; 
	private String razonSocial;
	private Boolean eliminado;
	private List<Empresa> empresas;
	private Boolean activo;

	@Id
	@GeneratedValue(generator = "seq_oficina_contable", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "rut", nullable = false)
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	@Column(name = "correo", nullable = false)
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "razon_social", nullable = false)
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column(name = "eliminado", nullable = false)
	public Boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Column(name = "activo", nullable = false)
	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	@OneToMany(mappedBy = "oficinaContable", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	 

	
}
