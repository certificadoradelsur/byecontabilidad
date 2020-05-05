package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que crea la tabla bitacora
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "bitacora")
@SequenceGenerator(name = "seq_bitacora", sequenceName = "seq_bitacora")
@NamedQueries({
	//	@NamedQuery(name = "Bitacora.getAll", query = "SELECT b FROM Bitacora b "),
 })

public class Bitacora implements Serializable {

	private static final long serialVersionUID = 1338781439392145452L;
	private Long id;
	private Usuario usuario;
	private Timestamp fecha;
	private String accion;
	private String tabla;
	private String descripcion; 

	@Id
	@GeneratedValue(generator = "seq_bitacora", strategy = GenerationType.AUTO)
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

	@Column(name = "fecha", nullable = true)
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Column(name = "accion", nullable = true)
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	@Column(name = "tabla", nullable = true)
	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	@Column(name = "descripcion", nullable = true)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
		
}
