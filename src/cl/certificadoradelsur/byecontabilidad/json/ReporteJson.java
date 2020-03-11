package cl.certificadoradelsur.byecontabilidad.json;


/**
 * Clase json de reporte
 * 
 * @author juan
 *
 */
public class ReporteJson {

	private String fecheDesde;
	private String fechaHasta;
	private Long idBanco;
	private Long idCuenta;
	public String getFecheDesde() {
		return fecheDesde;
	}
	public void setFecheDesde(String fecheDesde) {
		this.fecheDesde = fecheDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	public Long getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}
	
}
