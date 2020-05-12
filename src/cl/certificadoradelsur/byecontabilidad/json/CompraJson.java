package cl.certificadoradelsur.byecontabilidad.json;

import java.util.List;

/**
 * Clase json de compra
 * 
 * @author juan
 *
 */
public class CompraJson {

	private Long id;
	private String rut;
	private String nombre;
	private String folio;
	private String fecha;
	private Long montoNeto;
	private Long iva;
	private Long otroImpuesto;
	private Long montoTotal;
	private Long idEmpresa;
	private String idUsuario;
	List<OtroImpuestoJson> otrosImpuestos;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
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

	public List<OtroImpuestoJson> getOtrosImpuestos() {
		return otrosImpuestos;
	}

	public void setOtrosImpuestos(List<OtroImpuestoJson> otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
	}
	
	

}
