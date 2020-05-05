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
 * Clase que crea la tabla venta
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "venta")
@SequenceGenerator(name = "seq_venta", sequenceName = "seq_venta")
@NamedQueries({
		@NamedQuery(name = "Venta.getAll", query = "SELECT v FROM Venta v"),
		@NamedQuery(name = "Venta.countAll", query = "SELECT count(v.id) FROM Venta v") })

public class Venta implements Serializable {

	private static final long serialVersionUID = 1338781439378645452L;
	private Long id;
	private String rut;
	private String nombre;
	private String folio;
	private Timestamp fecha;
	private Long montoNeto;
	private Long iva;
	private Long otroImpuesto;
	private Long montoTotal;
	private Empresa empresa;
	private Timestamp fechaCreacion;

	
	@Id
	@GeneratedValue(generator = "seq_venta", strategy = GenerationType.AUTO)
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

	@Column(name = "rut", nullable = false)
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	@Column(name = "folio", nullable = false)
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Column(name = "fecha", nullable = false)
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Column(name = "monto_neto", nullable = false)
	public Long getMontoNeto() {
		return montoNeto;
	}

	public void setMontoNeto(Long montoNeto) {
		this.montoNeto = montoNeto;
	}

	@Column(name = "iva", nullable = false)
	public Long getIva() {
		return iva;
	}

	public void setIva(Long iva) {
		this.iva = iva;
	}

	@Column(name = "otro_impuesto", nullable = false)
	public Long getOtroImpuesto() {
		return otroImpuesto;
	}

	public void setOtroImpuesto(Long otroImpuesto) {
		this.otroImpuesto = otroImpuesto;
	}

	@Column(name = "monto_total", nullable = false)
	public Long getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Long montoTotal) {
		this.montoTotal = montoTotal;
	}

	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = true)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@Column(name = "fecha_creacion", nullable = true)
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
