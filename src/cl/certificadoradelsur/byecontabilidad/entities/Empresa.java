package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que crea la tabla empresa
 * 
 * @author juan
 *
 */
@Entity
@Table(name = "empresa")
@SequenceGenerator(name = "seq_empresa", sequenceName = "seq_empresa")
@NamedQueries({ @NamedQuery(name = "Empresa.getAll", query = "SELECT e FROM Empresa e  "),
				@NamedQuery(name = "Empresa.countAll", query = "SELECT count(e.razonSocial) FROM Empresa e"),
				@NamedQuery(name = "Empresa.get", query = "select e from Empresa e")})

public class Empresa implements Serializable {

	private static final long serialVersionUID = 710736179651008478L;
	private Long id;
	private String rut;
	private String razonSocial;
	private String giro;
	private OficinaContable oficinaContable;
	

	@Id
	@GeneratedValue(generator = "seq_empresa", strategy = GenerationType.AUTO)
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

	@Column(name = "razon_social", nullable = false)
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column(name = "giro", nullable = true)
	public String getGiro() {
		return giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
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
