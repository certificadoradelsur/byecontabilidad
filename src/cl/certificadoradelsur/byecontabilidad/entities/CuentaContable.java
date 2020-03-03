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
 * Clase que crea la tabla cuentaContable
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "cuenta_contable")
@SequenceGenerator(name = "seq_cuenta_contable", sequenceName = "seq_cuenta_contable")
@NamedQueries({ @NamedQuery(name = "CuentaContable.getAll", query = "SELECT c FROM CuentaContable c"),
		@NamedQuery(name = "CuentaContable.countAll", query = "SELECT count(c.id) FROM CuentaContable c") })

public class CuentaContable implements Serializable {

	private static final long serialVersionUID = 1323247814392112352L;
	private Long id;
	private String descripcion;
	private Boolean imputable;
	private Boolean analisis;
	private String glosaGeneral;
	private ClaseCuenta claseCuenta;
	private GrupoCuenta grupoCuenta;

	@Id
	@GeneratedValue(generator = "seq_cuenta_contable", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "imputable", nullable = false)
	public Boolean isImputable() {
		return imputable;
	}

	public void setImputable(Boolean imputable) {
		this.imputable = imputable;
	}

	@Column(name = "analisis", nullable = false)
	public Boolean isAnalisis() {
		return analisis;
	}

	public void setAnalisis(Boolean analisis) {
		this.analisis = analisis;
	}

	@Column(name = "glosa_general", nullable = false)
	public String getGlosaGeneral() {
		return glosaGeneral;
	}

	public void setGlosaGeneral(String glosaGeneral) {
		this.glosaGeneral = glosaGeneral;
	}

	@ManyToOne
	@JoinColumn(name = "id_grupo_cuenta", nullable = true)
	public GrupoCuenta getGrupoCuenta() {
		return grupoCuenta;
	}

	public void setGrupoCuenta(GrupoCuenta grupoCuenta) {
		this.grupoCuenta = grupoCuenta;
	}

	@ManyToOne
	@JoinColumn(name = "id_clase_cuenta", nullable = true)
	public ClaseCuenta getClaseCuenta() {
		return claseCuenta;
	}

	public void setClaseCuenta(ClaseCuenta claseCuenta) {
		this.claseCuenta = claseCuenta;
	}

}
