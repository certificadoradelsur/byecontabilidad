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
 * Clase que crea la tabla comprobante contable
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "comprobante_contable")
@SequenceGenerator(name = "seq_comprobante_contable", sequenceName = "seq_comprobante_contable")
@NamedQueries({
		@NamedQuery(name = "ComprobanteContable.getAll", query = "SELECT c FROM ComprobanteContable c "),
		@NamedQuery(name = "ComprobanteContable.countAll", query = "SELECT count(c.id) FROM ComprobanteContable c "),
		@NamedQuery(name = "ComprobanteContable.getByNumero", query = "SELECT c FROM ComprobanteContable c where  c.numero= :numero"),
		@NamedQuery(name = "ComprobanteContable.getMaxNumero", query = "SELECT MAX(c.numero) FROM ComprobanteContable c")
})

public class ComprobanteContable implements Serializable {

	private static final long serialVersionUID = 1338781439392145452L;
	private Long id;
	private Long numero;
	private String glosaGeneral;
	private Timestamp fecha;
	private String tipo;
	private CuentaContable cuentaContable;
	private Long debe;
	private Long haber;

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

	@Column(name = "fecha", nullable = false)
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Column(name = "tipo", nullable = false)
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@ManyToOne
	@JoinColumn(name = "id_cuenta_contable", nullable = true)
	public CuentaContable getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(CuentaContable cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	@Column(name = "debe", nullable = true)
	public Long getDebe() {
		return debe;
	}

	public void setDebe(Long debe) {
		this.debe = debe;
	}
	@Column(name = "haber", nullable = true)
	public Long getHaber() {
		return haber;
	}

	public void setHaber(Long haber) {
		this.haber = haber;
	}
	
	
	

}
