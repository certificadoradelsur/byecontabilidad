package cl.certificadoradelsur.byecontabilidad.json;

/**
 * Clase json de no conciliado cartola
 * 
 * @author juan
 *
 */
public class NoConciliadoCartolaJson {

	private Long id;
	private Long idBanco;
	private String numDocumento;
	private String fecha;
	private String fechaT;
	private String descripcion;
	private String tipoMovimiento;
	private Long monto;
	private boolean eliminado;
	private String nombreBanco;
	private String numCuenta;
	private Long idCuenta;
	private String numCartola;
	private Long idNoConciliado;
	private Long idEmpresa;
	private String idUsuario;

	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}



	public Long getIdNoConciliado() {
		return idNoConciliado;
	}

	public void setIdNoConciliado(Long idNoConciliado) {
		this.idNoConciliado = idNoConciliado;
	}

	public String getFechaT() {
		return fechaT;
	}

	public void setFechaT(String fechaT) {
		this.fechaT = fechaT;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
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

	public String getNumCartola() {
		return numCartola;
	}

	public void setNumCartola(String numCartola) {
		this.numCartola = numCartola;
	}

}
