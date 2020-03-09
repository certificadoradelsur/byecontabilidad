package cl.certificadoradelsur.byecontabilidad.json;

/**
 * Clase json de Empresa
 * 
 * @author juan
 *
 */
public class EmpresaJson {

	private Long id;
	private String rut;
	private String razonSocial;
	private String giro;

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
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getGiro() {
		return giro;
	}
	public void setGiro(String giro) {
		this.giro = giro;
	}


}
