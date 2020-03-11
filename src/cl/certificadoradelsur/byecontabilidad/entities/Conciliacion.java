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
 * Clase que crea la tabla conciliacion
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "conciliacion")
@SequenceGenerator(name = "seq_conciliacion", sequenceName = "seq_conciliacion")
@NamedQueries({
		@NamedQuery(name = "Conciliacion.getAll", query = "SELECT c FROM  Conciliacion c where c.eliminado = false and c.movimiento.fecha between :fechaInicial and :fechaFinal and (true = :ignoreIdCuenta or c.cartola.cuenta.id=:idCuenta) and (true = :ignoreIdBanco or c.cartola.banco.id=:idBanco)"),
		@NamedQuery(name = "Conciliacion.countAll", query = "SELECT count(c.id) FROM Conciliacion c where c.eliminado = false and c.movimiento.fecha  between :fechaInicial and :fechaFinal and (true = :ignoreIdCuenta or c.cartola.cuenta.id=:idCuenta) and (true = :ignoreIdBanco or c.cartola.banco.id=:idBanco)"),
		@NamedQuery(name = "Conciliacion.getByMovCar", query = "SELECT c FROM Conciliacion c where  c.cartola.id =  :idCartola or c.movimiento.id= :idMovimiento"),
		@NamedQuery(name = "Conciliacion.getByMov", query = "SELECT c FROM Conciliacion c where  c.movimiento.id= :idMovimiento"),
		@NamedQuery(name = "Conciliacion.getByCart", query = "SELECT c FROM Conciliacion c where  c.cartola.id= :idCartola"),
		@NamedQuery(name = "Conciliacion.getByIdTransaccion", query = "SELECT c FROM Conciliacion c where  c.movimiento.transaccion.id= :idTransaccion"),
		@NamedQuery(name = "Conciliacion.countAllResumen", query = "SELECT count(c.id) FROM Conciliacion c where c.eliminado = false and c.movimiento.fecha between :fechaI and :fechaF"),
		@NamedQuery(name = "Conciliacion.getByIdReporteBancoCuenta", query = "SELECT c FROM Conciliacion c where c.eliminado = false and c.movimiento.fecha between :fechaInicial and :fechaFinal and c.cartola.banco.id= :idBanco and c.cartola.cuenta.id= :idCuenta") })



public class Conciliacion implements Serializable {

	private static final long serialVersionUID = 1338781439392145452L;
	private Long id;
	private Cartola cartola;
	private Movimiento movimiento;
	private Timestamp fecha;
	private Boolean eliminado;

	@Id
	@GeneratedValue(generator = "seq_conciliacion", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "id_cartola", nullable = true)
	public Cartola getCartola() {
		return cartola;
	}

	public void setCartola(Cartola cartola) {
		this.cartola = cartola;
	}

	@ManyToOne
	@JoinColumn(name = "id_movimiento", nullable = true)
	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	@Column(name = "fecha", nullable = true)
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
}
