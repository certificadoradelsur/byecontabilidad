package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;

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
 * Clase que crea la tabla otroImpuesto
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "otro_impuesto")
@SequenceGenerator(name = "seq_otro_impuesto", sequenceName = "seq_otro_impuesto")
@NamedQueries({
		@NamedQuery(name = "OtroImpuesto.getByIdCompra", query = "SELECT oi FROM OtroImpuesto oi  where oi.compra.id =:idCompra"),
		@NamedQuery(name = "OtroImpuesto.getByIdVenta", query = "SELECT oi FROM OtroImpuesto oi  where oi.venta.id =:idVenta")})

public class OtroImpuesto implements Serializable {

	private static final long serialVersionUID = 1338781439392145452L;
	private Long id;
	private CodigoImpuesto codigo;
	private Long monto;
	private Compra compra;
	private Venta venta;

	@Id
	@GeneratedValue(generator = "seq_otro_impuesto", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "id_compra", nullable = true)
	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	@ManyToOne
	@JoinColumn(name = "id_venta", nullable = true)
	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	@ManyToOne
	@JoinColumn(name = "id_codigo_impuesto", nullable = true)
	public CodigoImpuesto getCodigo() {
		return codigo;
	}

	public void setCodigo(CodigoImpuesto codigo) {
		this.codigo = codigo;
	}

	@Column(name = "monto", nullable = false)
	public void setMonto(Long monto) {
		this.monto = monto;
	}

	public Long getMonto() {
		return monto;
	}

}
