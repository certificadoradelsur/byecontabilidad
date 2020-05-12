package cl.certificadoradelsur.byecontabilidad.json;

import cl.certificadoradelsur.byecontabilidad.entities.Compra;

/**
 * Clase json de otroImpuesto
 * 
 * @author juan
 *
 */
public class OtroImpuestoJson {

	private Long id;
	private Long codigo;
	private Long monto;
	private Compra compra;
	private String idUsuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Long getMonto() {
		return monto;
	}
	public void setMonto(Long monto) {
		this.monto = monto;
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
}
