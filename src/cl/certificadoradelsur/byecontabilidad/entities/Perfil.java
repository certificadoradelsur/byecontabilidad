package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * tabla perfil
 * 
 * @author juan
 *
 */
@Entity
@Table(name = "perfil")
@NamedQueries({ @NamedQuery(name = "Perfil.getAll", query = "SELECT p FROM Perfil p  ") })

public class Perfil implements Serializable {

	private static final long serialVersionUID = 1338781439392145452L;
	private String id;
	private String descripcion;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "descripcion", nullable = true)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
