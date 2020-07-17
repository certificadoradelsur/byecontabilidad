package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author ernesto
 *
 */


@Entity
@Table(name = "centroResultado")
@SequenceGenerator(name = "seq_cenResul", sequenceName = "seq_cenResul")
public class CentroResultado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	
	
	@Id
	@GeneratedValue(generator = "seq_cenResul", strategy = GenerationType.AUTO)
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
