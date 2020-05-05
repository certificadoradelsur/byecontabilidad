package cl.certificadoradelsur.byecontabilidad.json;

/**
 * Clase json de no conciliado
 * 
 * @author juan
 *
 */
public class NoConciliadoJson {

	private Long id;
	private String fecha;
	private String fechaT;
	private Boolean eliminado;
	private Long numDocumento;
	private Long numComprobante;
	private Long monto;
	private String fechaMod;
	private String banco;
	private String glosa;
	private String tipoDocumento;
	private String tipoMovimiento;
	private Long idNoConciliadoCartola;
	private boolean estado;
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

	public Long getIdNoConciliadoCartola() {
		return idNoConciliadoCartola;
	}

	public void setIdNoConciliadoCartola(Long idNoConciliadoCartola) {
		this.idNoConciliadoCartola = idNoConciliadoCartola;
	}

	public String getFechaT() {
		return fechaT;
	}

	public void setFechaT(String fechaT) {
		this.fechaT = fechaT;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getFechaMod() {
		return fechaMod;
	}

	public void setFechaMod(String fechaMod) {
		this.fechaMod = fechaMod;
	}

	public Long getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(Long numDocumento) {
		this.numDocumento = numDocumento;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
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

	public Boolean isEliminado() {
		return eliminado;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	public Long getNumComprobante() {
		return numComprobante;
	}

	public void setNumComprobante(Long numComprobante) {
		this.numComprobante = numComprobante;
	}

}
