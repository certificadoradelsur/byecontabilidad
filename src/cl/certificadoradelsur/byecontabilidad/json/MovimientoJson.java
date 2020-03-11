package cl.certificadoradelsur.byecontabilidad.json;


/**
 * Clase json de movimiento
 * 
 * @author juan
 *
 */
public class MovimientoJson {

	private Long id;
	private String  fecha;
	private String glosa;
	private String tipoMovimiento;
	private String tipoDocumento;
	private boolean estado;
	private boolean eliminado;
	private Long monto;
	private String nombreEmpresa;
	private String idUsuario;
	private Long idBanco;
	private Long idTransaccion;
	private String nombreBanco;
	private Long numDocumento;
	private Long numTransaccion;
	private Long idCuenta;
	private String numCuenta;
	private Long numComprobante;
	
	private String fechaI;
	private String fechaF;

	
	
	public String getFechaI() {
		return fechaI;
	}
	public void setFechaI(String fechaI) {
		this.fechaI = fechaI;
	}
	public String getFechaF() {
		return fechaF;
	}
	public void setFechaF(String fechaF) {
		this.fechaF = fechaF;
	}
	public String getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	public Long getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
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
	public String getGlosa() {
		return glosa;
	}
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public Long getMonto() {
		return monto;
	}
	public void setMonto(Long monto) {
		this.monto = monto;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdBanco() {
		return idBanco;
	}
	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}
	public Long getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getNombreBanco() {
		return nombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	public Long getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(Long numDocumento) {
		this.numDocumento = numDocumento;
	}
	public Long getNumTransaccion() {
		return numTransaccion;
	}
	public void setNumTransaccion(Long numTransaccion) {
		this.numTransaccion = numTransaccion;
	}
	public Long getNumComprobante() {
		return numComprobante;
	}
	public void setNumComprobante(Long numComprobante) {
		this.numComprobante = numComprobante;
	}

}