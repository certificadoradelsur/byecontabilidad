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
 * Clase que crea la tabla cartola
 * 
 * @author juan
 *
 */
@Entity
@Table(name = "cartola")
@SequenceGenerator(name = "seq_cartola", sequenceName = "seq_cartola")
@NamedQueries({
		@NamedQuery(name = "Cartola.getAll", query = "SELECT c FROM Cartola c where c.eliminado = false and c.empresa.id =:idEmpresa and c.fecha  between :fechaInicial and :fechaFinal and (true = :ignoreIdCuenta or c.cuenta.id=:idCuenta) and (true = :ignoreIdBanco or c.banco.id=:idBanco)"),
		@NamedQuery(name = "Cartola.countAll", query = "SELECT count(c.id) FROM Cartola c where c.eliminado = false and c.empresa.id =:idEmpresa and c.fecha  between :fechaInicial and :fechaFinal and (true = :ignoreIdCuenta or c.cuenta.id=:idCuenta) and (true = :ignoreIdBanco or c.banco.id=:idBanco)"),
		@NamedQuery(name = "Cartola.getByFecha", query = "SELECT c FROM Cartola c where c.fecha= :fecha and c.monto= :monto and c.tipoMovimiento=:tipoMovimiento"),
		@NamedQuery(name = "Cartola.countAllResumen", query = "SELECT count(c.id) FROM Cartola c where c.eliminado = false and c.fecha between :fechaI and :fechaF"),
		@NamedQuery(name = "Cartola.getByIdCuenta", query = "SELECT c FROM Cartola c where  c.cuenta.id= :idCuenta"),
		@NamedQuery(name = "Cartola.getByNumDocumento", query = "SELECT c FROM Cartola c where c.numDocumento= :numDocumento"),
		@NamedQuery(name = "Cartola.getAllFecha", query = "SELECT c  from Cartola c where c.empresa.id = :idEmpresa and c.cuenta.banco.id = :idBanco and c.fecha between :fechaI and :fechaF  ") })

public class Cartola implements Serializable {

	private static final long serialVersionUID = 1365781439757654452L;
	private Long id;
	private Banco banco;
	private Cuenta cuenta;
	private String numCartola;
	private String numDocumento;
	private Timestamp fecha;
	private String descripcion;
	private String tipoMovimiento;
	private Long monto;
	private boolean eliminado;
	private Empresa empresa;

	@Id
	@GeneratedValue(generator = "seq_cartola", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "idBanco", nullable = true)
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@ManyToOne
	@JoinColumn(name = "id_Cuenta", nullable = true)
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Column(name = "fecha", nullable = true)
	public Timestamp getFecha() {
		return fecha;
	}

	@Column(name = "numDocumento", nullable = true)
	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	@Column(name = "numCartola", nullable = true)
	public String getNumCartola() {
		return numCartola;
	}

	public void setNumCartola(String numCartola) {
		this.numCartola = numCartola;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Column(name = "descripcion", nullable = true)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "tipoMovimiento", nullable = true)
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	@Column(name = "monto", nullable = true)
	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

	@Column(name = "eliminado", nullable = true)
	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = true)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
