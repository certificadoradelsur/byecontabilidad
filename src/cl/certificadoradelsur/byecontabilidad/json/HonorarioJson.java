package cl.certificadoradelsur.byecontabilidad.json;


/**
 * Clase json de honorario
 * 
 * @author juan
 *
 */
public class HonorarioJson {

	private Long id;
	private String rut;
	private String nombre;
	private String numBoleta;
	private String fecha;
	private Long montoBruto;
	private Long retencion;
	private Long montoLiquido;
	private Long idEmpresa;
	private String idUsuario;

	
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

	public String getNumBoleta() {
		return numBoleta;
	}

	public void setNumBoleta(String numBoleta) {
		this.numBoleta = numBoleta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getMontoBruto() {
		return montoBruto;
	}

	public void setMontoBruto(Long montoBruto) {
		this.montoBruto = montoBruto;
	}

	public Long getRetencion() {
		return retencion;
	}

	public void setRetencion(Long retencion) {
		this.retencion = retencion;
	}

	public Long getMontoLiquido() {
		return montoLiquido;
	}

	public void setMontoLiquido(Long montoLiquido) {
		this.montoLiquido = montoLiquido;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	

}
