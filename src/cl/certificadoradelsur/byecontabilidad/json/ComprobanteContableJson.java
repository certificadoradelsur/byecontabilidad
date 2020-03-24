package cl.certificadoradelsur.byecontabilidad.json;

import java.util.List;

/**
 * Clase json de CuentaContable
 * 
 * @author juan
 *
 */
public class ComprobanteContableJson {

	private Long id;
	private Long numero;
	private String glosaGeneral;
	private String fecha;
	private String tipo;
	private Long idCuentaContable;
	private String glosaGeneralCuentaContable;
	private Long monto;
	private Long idCliente;
	private String glosa;
	private Long numDocumento;
	private String tipoDocumento;
	private Long idEmpresa;
	private String idUsuario;
	private String nombreEmpresa;
	List<MovimientoJson> movimientos;
	List<CuentaContableJson> cuentasContables;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getGlosaGeneral() {
		return glosaGeneral;
	}
	public void setGlosaGeneral(String glosaGeneral) {
		this.glosaGeneral = glosaGeneral;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getIdCuentaContable() {
		return idCuentaContable;
	}
	public void setIdCuentaContable(Long idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}
	public String getGlosaGeneralCuentaContable() {
		return glosaGeneralCuentaContable;
	}
	public void setGlosaGeneralCuentaContable(String glosaGeneralCuentaContable) {
		this.glosaGeneralCuentaContable = glosaGeneralCuentaContable;
	}
	public Long getMonto() {
		return monto;
	}
	public void setMonto(Long monto) {
		this.monto = monto;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getGlosa() {
		return glosa;
	}
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
	public Long getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(Long numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public List<MovimientoJson> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<MovimientoJson> movimientos) {
		this.movimientos = movimientos;
	}
	public List<CuentaContableJson> getCuentasContables() {
		return cuentasContables;
	}
	public void setCuentasContables(List<CuentaContableJson> cuentasContables) {
		this.cuentasContables = cuentasContables;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	
}
