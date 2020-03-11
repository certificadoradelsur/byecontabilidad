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
 * Clase que crea la tabla banco
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "banco")
@SequenceGenerator(name = "seq_banco", sequenceName = "seq_banco")
@NamedQueries({
		@NamedQuery(name = "Banco.getAll", query = "SELECT b FROM Banco b  where b.eliminado = false and (true = :ignoreNombre or  upper(b.nombre)  like :nombre )"),
		@NamedQuery(name = "Banco.countAll", query = "SELECT count(b.nombre) FROM Banco b  where b.eliminado = false and (true = :ignoreNombre or  upper(b.nombre)  like :nombre )"),
		@NamedQuery(name = "Banco.getAllLista", query = "SELECT b FROM Banco b  where b.eliminado = false ") })

public class Banco implements Serializable {

	private static final long serialVersionUID = 1338781439392145452L;
	private Long id;
	private String nombre;
	private Boolean eliminado;

	@Id
	@GeneratedValue(generator = "seq_banco", strategy = GenerationType.AUTO)
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

	@Column(name = "eliminado", nullable = false)
	public Boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

}
