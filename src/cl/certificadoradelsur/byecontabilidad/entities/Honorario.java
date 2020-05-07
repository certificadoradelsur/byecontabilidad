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
 * Clase que crea la tabla honorario
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "honorario")
@SequenceGenerator(name = "seq_honorario", sequenceName = "seq_honorario")
@NamedQueries({
		@NamedQuery(name = "Honorario.getAll", query = "SELECT h FROM Honorario h where h.fecha between :fechaInicial and :fechaFinal and h.empresa.id =:idEmpresa and h.empresa.oficinaContable.id =:idOficinaContable"),
		@NamedQuery(name = "Honorario.countAll", query = "SELECT count(h.id) FROM Honorario h where h.fecha between :fechaInicial and :fechaFinal and h.empresa.id =:idEmpresa and h.empresa.oficinaContable.id =:idOficinaContable") })

public class Honorario implements Serializable {

	private static final long serialVersionUID = 1331181439392145452L;
	private Long id;
	private Cliente cliente;
	private String nombre;
	private String numBoleta;
	private Timestamp fecha;
	private Long montoBruto;
	private Long retencion;
	private Boolean retencionEstado;
	private Long montoLiquido;
	private Empresa empresa;
	private Timestamp fechaCreacion;

	
	@Id
	@GeneratedValue(generator = "seq_honorario", strategy = GenerationType.AUTO)
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

	@Column(name = "numero_boleta", nullable = false)
	public String getNumBoleta() {
		return numBoleta;
	}

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = true)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setNumBoleta(String numBoleta) {
		this.numBoleta = numBoleta;
	}

	@Column(name = "fecha", nullable = false)
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Column(name = "monto_bruto", nullable = false)
	public Long getMontoBruto() {
		return montoBruto;
	}

	public void setMontoBruto(Long montoBruto) {
		this.montoBruto = montoBruto;
	}

	@Column(name = "retencion", nullable = true)
	public Long getRetencion() {
		return retencion;
	}

	public void setRetencion(Long retencion) {
		this.retencion = retencion;
	}

	@Column(name = "monto_liquido", nullable = false)
	public Long getMontoLiquido() {
		return montoLiquido;
	}

	public void setMontoLiquido(Long montoLiquido) {
		this.montoLiquido = montoLiquido;
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

	@Column(name = "retencion_estado", nullable = true)
	public Boolean isRetencionEstado() {
		return retencionEstado;
	}

	public void setRetencionEstado(Boolean retencionEstado) {
		this.retencionEstado = retencionEstado;
	}
	
	
}
