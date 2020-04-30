package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * Clase que crea la tabla bitacora
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "bitacora")
@SequenceGenerator(name = "seq_bitacora", sequenceName = "seq_bitacora")
@NamedQueries({
	//	@NamedQuery(name = "Bitacora.getAll", query = "SELECT b FROM Banco b  where b.eliminado = false and (true = :ignoreNombre or  upper(b.nombre)  like :nombre )"),
 })

public class Bitacora implements Serializable {

	private static final long serialVersionUID = 1338781439392145452L;
	private Long id;
	private Usuario usuario;
	private Timestamp fecha;
	private String accion;
	private String tabla;


	@Id
	@GeneratedValue(generator = "seq_bitacora", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
