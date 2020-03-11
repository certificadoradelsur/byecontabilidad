package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que crea la tabla Transaccion
 * 
 * @author juan
 *
 */
@Entity
@Table(name = "transaccion")
@SequenceGenerator(name = "seq_transaccion", sequenceName = "seq_transaccion")
@NamedQueries({
		@NamedQuery(name = "Transaccion.getAll", query = "SELECT t FROM Transaccion t where t.eliminado = false and (true = :ignoreGlosaTransaccion or  upper(t.glosaTransaccion)  like :glosaTransaccion )"),
		@NamedQuery(name = "Transaccion.countAll", query = "SELECT count(t.id) FROM Transaccion t where t.eliminado = false and (true = :ignoreGlosaTransaccion or upper(t.glosaTransaccion)  like :glosaTransaccion )"),
		@NamedQuery(name = "Transaccion.maxNum", query = "SELECT MAX(t.numTransaccion) FROM Transaccion T")

})

public class Transaccion implements Serializable {

	private static final long serialVersionUID = 1338781439392145452L;
	private Long id;
	private Long numTransaccion;
	private String glosaTransaccion;
	private List<Movimiento> movimientos;
	private Boolean eliminado;

	@Id
	@GeneratedValue(generator = "seq_transaccion", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "numTransaccion", nullable = false)
	public Long getNumTransaccion() {
		return numTransaccion;
	}

	public void setNumTransaccion(Long numTransaccion) {
		this.numTransaccion = numTransaccion;
	}

	@Column(name = "glosaTransaccion", nullable = false)
	public String getGlosaTransaccion() {
		return glosaTransaccion;
	}

	public void setGlosaTransaccion(String glosaTransaccion) {
		this.glosaTransaccion = glosaTransaccion;
	}

	@OneToMany(mappedBy = "transaccion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	@Column(name = "eliminado", nullable = true)
	public Boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

}
