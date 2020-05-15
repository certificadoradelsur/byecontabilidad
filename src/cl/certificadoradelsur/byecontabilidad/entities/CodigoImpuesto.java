package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que crea la tabla codigo_impuesto
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "codigo_impuesto")
@SequenceGenerator(name = "seq_codigo_impuesto", sequenceName = "seq_codigo_impuesto")
@NamedQueries({
		@NamedQuery(name = "CodigoImpuesto.getAllLista", query = "SELECT c FROM CodigoImpuesto c") })

public class CodigoImpuesto implements Serializable {

	private static final long serialVersionUID = 1323287814392412352L;
	private Long id;
	private Long codigo;
	private String descripcion;
	
	@Id
	@GeneratedValue(generator = "seq_codigo_impuesto", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "codigo", nullable = false)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
