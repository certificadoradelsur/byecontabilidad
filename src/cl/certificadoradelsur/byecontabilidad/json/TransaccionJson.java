package cl.certificadoradelsur.byecontabilidad.json;

import java.util.List;
/**
 * Clase Json que contiene la transaccion
 * @author juan
 *
 */
public class TransaccionJson {
	
	private Long id;
	private Long numTransaccion;
	private String glosaTransaccion;
	List<MovimientoJson> movimientos;
	private Boolean eliminado;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumTransaccion() {
		return numTransaccion;
	}

	public void setNumTransaccion(Long numTransaccion) {
		this.numTransaccion = numTransaccion;
	}

	public String getGlosaTransaccion() {
		return glosaTransaccion;
	}

	public void setGlosaTransaccion(String glosaTransaccion) {
		this.glosaTransaccion = glosaTransaccion;
	}

	public List<MovimientoJson> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientoJson> movimientos) {
		this.movimientos = movimientos;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	

}
