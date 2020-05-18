package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que crea la tabla compra
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "compra")
@SequenceGenerator(name = "seq_compra", sequenceName = "seq_compra")
@NamedQueries({
		@NamedQuery(name = "Compra.getAll", query = "SELECT c FROM Compra c where c.fecha between :fechaInicial and :fechaFinal and c.empresa.id =:idEmpresa and c.empresa.oficinaContable.id =:idOficinaContable"),
		@NamedQuery(name = "Compra.countAll", query = "SELECT count(c.id) FROM Compra c where c.fecha between :fechaInicial and :fechaFinal and c.empresa.id =:idEmpresa and c.empresa.oficinaContable.id =:idOficinaContable") })

public class Compra implements Serializable {

	private static final long serialVersionUID = 1338781439378645452L;
	private Long id;
	private Cliente cliente;
	private String nombre;
	private String folio;
	private Timestamp fecha;
	private Long montoNeto;
	private Long iva;
	private List<OtroImpuesto> otrosImpuestos;
	private Boolean ivaEstado;
	private Boolean otrosEstado;
	private Long montoTotal;
	private Empresa empresa;
	private Timestamp fechaCreacion;

	
	@Id
	@GeneratedValue(generator = "seq_compra", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = true)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = true)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Column(name = "folio", nullable = true)
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Column(name = "fecha", nullable = true)
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Column(name = "monto_neto", nullable = true)
	public Long getMontoNeto() {
		return montoNeto;
	}

	public void setMontoNeto(Long montoNeto) {
		this.montoNeto = montoNeto;
	}

	@Column(name = "iva", nullable = true)
	public Long getIva() {
		return iva;
	}

	public void setIva(Long iva) {
		this.iva = iva;
	}

	@Column(name = "monto_total", nullable = true)
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

	@OneToMany(mappedBy = "compra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<OtroImpuesto> getOtrosImpuestos() {
		return otrosImpuestos;
	}

	public void setOtrosImpuestos(List<OtroImpuesto> otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
	}

	@Column(name = "iva_estado", nullable = true)
	public Boolean isIvaEstado() {
		return ivaEstado;
	}

	public void setIvaEstado(Boolean ivaEstado) {
		this.ivaEstado = ivaEstado;
	}

	@Column(name = "otro_estado", nullable = true)
	public Boolean isOtrosEstado() {
		return otrosEstado;
	}

	public void setOtrosEstado(Boolean otrosEstado) {
		this.otrosEstado = otrosEstado;
	}	

}
