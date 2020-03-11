package cl.certificadoradelsur.byecontabilidad.json;
/**
 * Clase json para responder por cada linea de importacion
 * @author juan
 *
 */
public class ResultadoImportacionJson {
	
	private Integer linea;
	private String detalle;
	private String estado;
	
	public Integer getLinea() {
		return linea;
	}
	public void setLinea(Integer linea) {
		this.linea = linea;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
