package cl.certificadoradelsur.byecontabilidad.json;


/**
 * Clase json de conciliacion
 * 
 * @author juan
 *
 */
public class ConciliacionJson {

	private Long id;
	private String fecha;
	private String fechaCartola;
	private String fechaMovimiento;
	private String fechaConciliacion;
	private Long montoCartola;
	private Long montoMovimiento;
	private String numDocumento;
	private String descripcionCartola;
	private Long idCartola;
	private Long idMovimiento;
	private String tipoDocumento;
	private Long idNoConciliadoCartola;
	private Long idNoConciliado;
	private String numCartola;

	
	public Long getIdNoConciliadoCartola() {
		return idNoConciliadoCartola;
	}
	public void setIdNoConciliadoCartola(Long idNoConciliadoCartola) {
		this.idNoConciliadoCartola = idNoConciliadoCartola;
	}
	public Long getIdNoConciliado() {
		return idNoConciliado;
	}
	public void setIdNoConciliado(Long idNoConciliado) {
		this.idNoConciliado = idNoConciliado;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getFechaConciliacion() {
		return fechaConciliacion;
	}
	public void setFechaConciliacion(String fechaConciliacion) {
		this.fechaConciliacion = fechaConciliacion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFechaCartola() {
		return fechaCartola;
	}
	public void setFechaCartola(String fechaCartola) {
		this.fechaCartola = fechaCartola;
	}
	public String getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimiento(String fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}
	public Long getMontoCartola() {
		return montoCartola;
	}
	public void setMontoCartola(Long montoCartola) {
		this.montoCartola = montoCartola;
	}
	public Long getMontoMovimiento() {
		return montoMovimiento;
	}
	public void setMontoMovimiento(Long montoMovimiento) {
		this.montoMovimiento = montoMovimiento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getDescripcionCartola() {
		return descripcionCartola;
	}
	public void setDescripcionCartola(String descripcionCartola) {
		this.descripcionCartola = descripcionCartola;
	}
	public Long getIdCartola() {
		return idCartola;
	}
	public void setIdCartola(Long idCartola) {
		this.idCartola = idCartola;
	}
	public Long getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(Long idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public String getNumCartola() {
		return numCartola;
	}
	public void setNumCartola(String numCartola) {
		this.numCartola = numCartola;
	}
	
	
	
}
