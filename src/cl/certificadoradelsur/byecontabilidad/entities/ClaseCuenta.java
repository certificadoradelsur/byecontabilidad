package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que crea la tabla claseCuenta
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "clase_cuenta")
@SequenceGenerator(name = "seq_clase_cuenta", sequenceName = "seq_clase_cuenta")
@NamedQueries({ @NamedQuery(name = "ClaseCuenta.getAll", query = "SELECT c FROM ClaseCuenta c"),
		@NamedQuery(name = "ClaseCuenta.countAll", query = "SELECT count(c.id) FROM ClaseCuenta c"),
		@NamedQuery(name = "ClaseCuenta.getAllLista", query = "SELECT c FROM ClaseCuenta c") 
})

public class ClaseCuenta implements Serializable {

	private static final long serialVersionUID = 1323287814392412352L;
	private Long id;
	private String nombre;

	@Id
	@GeneratedValue(generator = "seq_clase_cuenta", strategy = GenerationType.AUTO)
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

}
