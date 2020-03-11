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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que crea la tabla no conciliado
 * 
 * @author juan
 *
 */
@Entity
@Table(name = "no_conciliado")
@SequenceGenerator(name = "seq_no_conciliado", sequenceName = "seq_no_conciliado")
@NamedQueries({
		@NamedQuery(name = "NoConciliado.getAll", query = "SELECT c FROM NoConciliado c where c.eliminado = false and c.movimiento.fecha  between :fechaInicial and :fechaFinal and (true = :ignoreIdCuenta or c.movimiento.cuenta.id=:idCuenta) and (true = :ignoreIdBanco or c.movimiento.cuenta.banco.id=:idBanco)"),
		@NamedQuery(name = "NoConciliado.countAll", query = "SELECT count(c.id) FROM NoConciliado c where c.eliminado = false and c.movimiento.fecha  between :fechaInicial and :fechaFinal and (true = :ignoreIdCuenta or c.movimiento.cuenta.id=:idCuenta) and (true = :ignoreIdBanco or c.movimiento.cuenta.banco.id=:idBanco)"),
		@NamedQuery(name = "NoConciliado.getByIdMovimiento", query = "SELECT c FROM NoConciliado c where c.movimiento.id= :idMovimiento"),
		@NamedQuery(name = "NoConciliado.getNoConciliadoMonto", query = "SELECT  c FROM NoConciliado c where c.eliminado = false and c.movimiento.monto= :monto"),
		@NamedQuery(name = "NoConciliado.countAllMNC", query = "SELECT count(c.id) FROM NoConciliado c where c.eliminado = false and c.movimiento.monto= :monto"),
		@NamedQuery(name = "NoConciliado.getByIdTransaccion", query = "SELECT c FROM NoConciliado c where  c.movimiento.transaccion.id= :idTransaccion"),
		@NamedQuery(name = "NoConciliado.getByIdReporteBancoCuenta", query = "SELECT c FROM NoConciliado c where c.eliminado = false and c.movimiento.fecha between :fechaInicial and :fechaFinal and c.movimiento.cuenta.banco.id= :idBanco and c.movimiento.cuenta.id= :idCuenta") })

public class NoConciliado implements Serializable {

	private static final long serialVersionUID = 13546548143945452L;
	private Long id;
	private Movimiento movimiento;
	private Timestamp fecha;
	private Boolean eliminado;

	@Id
	@GeneratedValue(generator = "seq_no_conciliado", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "fecha", nullable = false)
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Column(name = "eliminado", nullable = false)
	public Boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	@ManyToOne
	@JoinColumn(name = "id_movimiento", nullable = true)
	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
}
