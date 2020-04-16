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
 * Clase que crea la tabla Movimiento
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "movimiento")
@SequenceGenerator(name = "seq_movimiento", sequenceName = "seq_movimiento")
@NamedQueries({
		@NamedQuery(name = "Movimiento.getAll", query = "SELECT m FROM Movimiento m where m.eliminado = false "),
		@NamedQuery(name = "Movimiento.countAll", query = "SELECT count(m.id) FROM Movimiento m where m.eliminado = false"),
		@NamedQuery(name = "Movimiento.countAllM", query = "SELECT count(m.id) FROM Movimiento m where m.eliminado = false and  (true = :ignoreId or m.comprobanteContable.id=:id)"),
		@NamedQuery(name = "Movimiento.getAllM", query = "SELECT  m FROM Movimiento m where m.eliminado = false and m.comprobanteContable.id=:id"),
		@NamedQuery(name = "Movimiento.countAllResumen", query = "SELECT count(m.id) FROM Movimiento m where m.eliminado = false and m.fecha  between :fechaInicial and :fechaFinal and (true = :ignoreIdCuenta or m.cuenta.id=:idCuenta) and (true = :ignoreIdBanco or m.cuenta.banco.id=:idBanco)"),
		@NamedQuery(name = "Movimiento.getByIdCuenta", query = "SELECT m FROM Movimiento m where  m.cuenta.id= :idCuenta"),
		@NamedQuery(name = "Movimiento.getByIdTransaccion", query = "SELECT m FROM Movimiento m where  m.transaccion.id= :idTransaccion"),
		@NamedQuery(name = "Movimiento.getByIdComprobante", query = "SELECT m FROM Movimiento m where  m.comprobanteContable.id= :idComprobante"),
		@NamedQuery(name = "Movimiento.getByIdComprobanteReporte", query = "SELECT m FROM Movimiento m where  m.comprobanteContable.id= :idComprobante and m.comprobanteContable.borrador=false"),
		@NamedQuery(name = "Movimiento.getAllFecha", query = "SELECT m  from Movimiento m where m.cuenta.banco.id = :idBanco and m.fecha between :fechaI and :fechaF  "),
		@NamedQuery(name = "Movimiento.getByMovEntreCuentas", query = "SELECT M FROM Movimiento m where m.cuentaContable.codigo between :inicialMayor and :finalMayor and m.fecha between :fechaInicial and :fechaFinal and m.empresa.oficinaContable.id= :idOficinaContable and m.comprobanteContable.borrador=false ORDER BY m.cuentaContable.id"),
		@NamedQuery(name = "Movimiento.getBalanceGeneral", query = "SELECT M FROM Movimiento m where m.fecha between :fechaInicial and :fechaFinal and m.empresa.oficinaContable.id= :idOficinaContable and m.cuentaContable.id= :idCuentaContable and m.comprobanteContable.borrador=false ORDER BY m.cuentaContable.codigo")
})

public class Movimiento implements Serializable {

	private static final long serialVersionUID = 167858714424457L;
	private Long id;
	private Timestamp fecha;
	private Long numDocumento;
	private Long numComprobante;
	private String glosa;
	private String tipoMovimiento;
	private String tipoDocumento;
	private boolean estado;
	private Long monto;
	private boolean eliminado;
	private Cuenta cuenta;
	private Transaccion transaccion;
	private ComprobanteContable comprobanteContable;
	private CuentaContable cuentaContable;
	private Empresa empresa;
	private Cliente cliente;
	private Usuario usuario;

	@Id
	@GeneratedValue(generator = "seq_movimiento", strategy = GenerationType.AUTO)
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

	@Column(name = "glosa", nullable = false)
	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	@Column(name = "tipoMovimiento", nullable = false)
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	@Column(name = "tipoDocumento", nullable = false)
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	@Column(name = "monto", nullable = false)
	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Column(name = "estado", nullable = false)
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Column(name = "eliminado", nullable = false)
	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Column(name = "numComprobante", nullable = true)
	public Long getNumComprobante() {
		return numComprobante;
	}

	public void setNumComprobante(Long numComprobante) {
		this.numComprobante = numComprobante;
	}

	@Column(name = "numDocumento", nullable = true)
	public Long getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(Long numDocumento) {
		this.numDocumento = numDocumento;
	}

	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = true)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = true)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne
	@JoinColumn(name = "id_transaccion", nullable = true)
	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	@ManyToOne
	@JoinColumn(name = "id_comprobante_contable", nullable = true)
	public ComprobanteContable getComprobanteContable() {
		return comprobanteContable;
	}

	public void setComprobanteContable(ComprobanteContable comprobanteContable) {
		this.comprobanteContable = comprobanteContable;
	}

	@ManyToOne
	@JoinColumn(name = "id_cuenta_contable", nullable = true)
	public CuentaContable getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(CuentaContable cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	@ManyToOne
	@JoinColumn(name = "id_cuenta", nullable = true)
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = true)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}