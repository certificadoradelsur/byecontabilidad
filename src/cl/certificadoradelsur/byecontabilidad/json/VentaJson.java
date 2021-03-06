package cl.certificadoradelsur.byecontabilidad.json;

import java.util.List;

/**
 * Clase json de venta
 * 
 * @author juan
 *
 */
public class VentaJson {

	private Long id;
	private Long idCliente;
	private String nombre;
	private String folio;
	private String fecha;
	private Long montoNeto;
	private Long iva;
	private Long otroImpuesto;
	private Long montoTotal;
	private Long idEmpresa;
	private String idUsuario;
	private Boolean ivaEstado;
	private Boolean otrosEstado;
	private String rut;
	List<OtroImpuestoJson> otrosImpuestos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Long getMontoNeto() {
		return montoNeto;
	}
	public void setMontoNeto(Long montoNeto) {
		this.montoNeto = montoNeto;
	}
	public Long getIva() {
		return iva;
	}
	public void setIva(Long iva) {
		this.iva = iva;
	}
	public Long getOtroImpuesto() {
		return otroImpuesto;
	}
	public void setOtroImpuesto(Long otroImpuesto) {
		this.otroImpuesto = otroImpuesto;
	}
	public Long getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Long montoTotal) {
		this.montoTotal = montoTotal;
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
	public Boolean isIvaEstado() {
		return ivaEstado;
	}
	public void setIvaEstado(Boolean ivaEstado) {
		this.ivaEstado = ivaEstado;
	}
	public Boolean isOtrosEstado() {
		return otrosEstado;
	}
	public void setOtrosEstado(Boolean otrosEstado) {
		this.otrosEstado = otrosEstado;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public List<OtroImpuestoJson> getOtrosImpuestos() {
		return otrosImpuestos;
	}
	public void setOtrosImpuestos(List<OtroImpuestoJson> otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
	}
	
	
}
