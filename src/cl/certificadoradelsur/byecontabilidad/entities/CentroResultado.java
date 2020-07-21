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
 * 
 * @author ernesto
 *
 */


@Entity
@Table(name = "centroResultado")
@SequenceGenerator(name = "seq_cenResul", sequenceName = "seq_cenResul")
@NamedQueries({
@NamedQuery(name = "CentroResultado.getAll", query = "SELECT cr FROM CentroResultado cr where cr.sucursal.codigo =:idSucursal"),
@NamedQuery(name = "CentroResultado.countAll", query = "SELECT count(cr.id) FROM CentroResultado cr where cr.sucursal.codigo =:idSucursal")
})
public class CentroResultado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private Sucursal sucursal;
	
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
	
	@ManyToOne
	@JoinColumn(name = "id_Sucursal", nullable = true)
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	
	
	
	
}
