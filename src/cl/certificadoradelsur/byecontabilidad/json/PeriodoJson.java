package cl.certificadoradelsur.byecontabilidad.json;

public class PeriodoJson {
	private Long id;
	private Long mes;
	private Long anio;
	private boolean estado;
	private String idUsuario;
	private Long idEmpresa;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMes() {
		return mes;
	}
	public void setMes(Long mes) {
		this.mes = mes;
	}
	public Long getAnio() {
		return anio;
	}
	public void setAnio(Long anio) {
		this.anio = anio;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
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
	
	

}
