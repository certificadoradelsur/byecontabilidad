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
 * Clase que crea la tabla grupoCuenta
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "grupo_cuenta")
@SequenceGenerator(name = "seq_grupo_cuenta", sequenceName = "seq_grupo_cuenta")
@NamedQueries({
		@NamedQuery(name = "GrupoCuenta.getAll", query = "SELECT g FROM GrupoCuenta g"),
		@NamedQuery(name = "GrupoCuenta.countAll", query = "SELECT count(g.id) FROM GrupoCuenta g") })

public class GrupoCuenta implements Serializable {

	private static final long serialVersionUID = 1324287814392145452L;
	private Long id;
	private String nombre;
	private ClaseCuenta claseCuenta;
	private Clasificacion clasificacion;

	@Id
	@GeneratedValue(generator = "seq_grupo_cuenta", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@ManyToOne
	@JoinColumn(name = "id_clase_cuenta", nullable = true)
	public ClaseCuenta getClaseCuenta() {
		return claseCuenta;
	}

	public void setClaseCuenta(ClaseCuenta claseCuenta) {
		this.claseCuenta = claseCuenta;
	}

	@ManyToOne
	@JoinColumn(name = "id_clasificacion", nullable = true)
	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

}
