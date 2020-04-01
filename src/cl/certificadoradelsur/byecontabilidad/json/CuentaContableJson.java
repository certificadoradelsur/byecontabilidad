package cl.certificadoradelsur.byecontabilidad.json;

/**
 * Clase json de CuentaContable
 * 
 * @author juan
 *
 */
public class CuentaContableJson {

	private Long id;
	private Long codigo;
	private String descripcion;
	private Boolean imputable;
	private Boolean analisis;
	private Boolean conciliacion;
	private String glosaGeneral;
	private Long idClaseCuenta;
	private String nombreClaseCuenta; 
	private Long idGrupoCuenta;
	private String nombreGrupoCuenta;
	private String nombreClasificacion;
	private Long idBanco;
	private Long idCuenta;
	private String idUsuario;
	private Long idEmpresa;
	private String razonSocialEmpresa;
	private Long idCuentaContable;
	private Long idSucursal;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean isImputable() {
		return imputable;
	}

	public void setImputable(Boolean imputable) {
		this.imputable = imputable;
	}

	public Boolean isAnalisis() {
		return analisis;
	}

	public void setAnalisis(Boolean analisis) {
		this.analisis = analisis;
	}

	public String getGlosaGeneral() {
		return glosaGeneral;
	}

	public void setGlosaGeneral(String glosaGeneral) {
		this.glosaGeneral = glosaGeneral;
	}

	public Long getIdClaseCuenta() {
		return idClaseCuenta;
	}

	public void setIdClaseCuenta(Long idClaseCuenta) {
		this.idClaseCuenta = idClaseCuenta;
	}

	public Long getIdGrupoCuenta() {
		return idGrupoCuenta;
	}

	public void setIdGrupoCuenta(Long idGrupoCuenta) {
		this.idGrupoCuenta = idGrupoCuenta;
	}

	public String getNombreClaseCuenta() {
		return nombreClaseCuenta;
	}

	public void setNombreClaseCuenta(String nombreClaseCuenta) {
		this.nombreClaseCuenta = nombreClaseCuenta;
	}

	public String getNombreGrupoCuenta() {
		return nombreGrupoCuenta;
	}

	public void setNombreGrupoCuenta(String nombreGrupoCuenta) {
		this.nombreGrupoCuenta = nombreGrupoCuenta;
	}

	public String getNombreClasificacion() {
		return nombreClasificacion;
	}

	public void setNombreClasificacion(String nombreClasificacion) {
		this.nombreClasificacion = nombreClasificacion;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Boolean isConciliacion() {
		return conciliacion;
	}

	public void setConciliacion(Boolean conciliacion) {
		this.conciliacion = conciliacion;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getRazonSocialEmpresa() {
		return razonSocialEmpresa;
	}

	public void setRazonSocialEmpresa(String razonSocialEmpresa) {
		this.razonSocialEmpresa = razonSocialEmpresa;
	}

	public Long getIdCuentaContable() {
		return idCuentaContable;
	}

	public void setIdCuentaContable(Long idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}

	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	
}
