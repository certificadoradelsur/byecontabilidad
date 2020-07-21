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
import javax.persistence.UniqueConstraint;

/**
 * Clase que crea la tabla comprobante contable
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "comprobante_contable", uniqueConstraints = {@UniqueConstraint(columnNames = {"numero"}) })
@SequenceGenerator(name = "seq_comprobante_contable", sequenceName = "seq_comprobante_contable")
@NamedQueries({
		@NamedQuery(name = "ComprobanteContable.getAll", query = "SELECT c FROM ComprobanteContable c where c.fecha between :fechaInicial and :fechaFinal and c.empresa.id =:idEmpresa and c.empresa.oficinaContable.id =:idOficinaContable and (true = :ignoreGlosaGeneral or upper(c.glosaGeneral) like :glosaGeneral)"),
		@NamedQuery(name = "ComprobanteContable.countAll", query = "SELECT count(c.id) FROM ComprobanteContable c where c.fecha between :fechaInicial and :fechaFinal and c.empresa.id =:idEmpresa and c.empresa.oficinaContable.id =:idOficinaContable and (true = :ignoreGlosaGeneral or upper(c.glosaGeneral) like :glosaGeneral)"),
		@NamedQuery(name = "ComprobanteContable.getByNumero", query = "SELECT c FROM ComprobanteContable c where c.numero= :numero and c.empresa.id =:idEmpresa"),
		@NamedQuery(name = "ComprobanteContable.getMaxNumero", query = "SELECT MAX(c.numero) FROM ComprobanteContable c"),
		@NamedQuery(name = "ComprobanteContable.getLibroDiario", query = "SELECT c FROM ComprobanteContable c where c.empresa.oficinaContable.id =:idOficinaContable and c.empresa.id =:idEmpresa and c.borrador = false and c.fecha between :fechaInicial and :fechaFinal") })

public class ComprobanteContable implements Serializable {

	private static final long serialVersionUID = 1338781439392145452L;
	private Long id;
	private Long numero;
	private String glosaGeneral;
	private Timestamp fecha;
	private List<Movimiento> movimientos;
	private Empresa empresa;
	private Boolean borrador;
	private Timestamp fechaCreacion;

	@Id
	@GeneratedValue(generator = "seq_comprobante_contable", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "numero", nullable = false)
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	@Column(name = "glosa_general", nullable = false)
	public String getGlosaGeneral() {
		return glosaGeneral;
	}

	public void setGlosaGeneral(String glosaGeneral) {
		this.glosaGeneral = glosaGeneral;
	}

	@Column(name = "fecha", nullable = true)
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = true)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@OneToMany(mappedBy = "comprobanteContable", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	@Column(name = "borrador", nullable = true)
	public Boolean isBorrador() {
		return borrador;
	}

	public void setBorrador(Boolean borrador) {
		this.borrador = borrador;
	}

	@Column(name = "fecha_creacion", nullable = true)
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
