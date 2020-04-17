package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que crea la tabla cuentaContable
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "cuenta_contable")
@SequenceGenerator(name = "seq_cuenta_contable", sequenceName = "seq_cuenta_contable")
@NamedQueries({
		@NamedQuery(name = "CuentaContable.getAll", query = "SELECT c FROM CuentaContable c where c.empresa.oficinaContable.id =:idOficinaContable and (true = :ignoreGlosaGeneral or upper(c.glosaGeneral)  like :glosaGeneral) and (true =:ignoreIdClaseCuenta or c.claseCuenta.id=:idClaseCuenta) and (true =:ignoreIdGrupoCuenta or c.grupoCuenta.id =:idGrupoCuenta) ORDER BY c.claseCuenta.id"),
		@NamedQuery(name = "CuentaContable.countAll", query = "SELECT count(c.id) FROM CuentaContable c where c.empresa.oficinaContable.id =:idOficinaContable and (true = :ignoreGlosaGeneral or upper(c.glosaGeneral)  like :glosaGeneral) and (true =:ignoreIdClaseCuenta or c.claseCuenta.id=:idClaseCuenta) and (true =:ignoreIdGrupoCuenta or c.grupoCuenta.id =:idGrupoCuenta)"),
		@NamedQuery(name = "CuentaContable.getByCodigo", query = "SELECT c FROM CuentaContable c where  c.codigo= :codigo and c.empresa.oficinaContable.id =:idOficinaContable"),
		@NamedQuery(name = "CuentaContable.getAllLista", query = "SELECT c FROM CuentaContable c where c.empresa.oficinaContable.id =:idOficinaContable"),
		@NamedQuery(name = "CuentaContable.maxCodigo", query = "SELECT MAX(c.codigo) FROM CuentaContable c "),
		@NamedQuery(name = "CuentaContable.getByIdEmpresa", query = "SELECT c FROM CuentaContable c  where c.empresa.oficinaContable.id =:idOficinaContable  and  c.empresa.id=:idEmpresa"),
		@NamedQuery(name = "CuentaContable.getBalance", query = "SELECT c FROM CuentaContable c where c.empresa.oficinaContable.id =:idOficinaContable and c.claseCuenta.id <=2 and c.descripcion <> 'Sin descripción'") })

public class CuentaContable implements Serializable {

	private static final long serialVersionUID = 1323247814392112352L;
	private Long id;
	private Long codigo;
	private String descripcion;
	private Boolean imputable;
	private Boolean analisis;
	private Boolean conciliacion;
	private String glosaGeneral;
	private ClaseCuenta claseCuenta;
	private GrupoCuenta grupoCuenta;
	private Cuenta cuenta;
	private Banco banco;
	private Empresa empresa;
	private Sucursal sucursal;
	private ComprobanteContable comprobanteContable;

	@Id
	@GeneratedValue(generator = "seq_cuenta_contable", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "codigo", nullable = false)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "imputable", nullable = false)
	public Boolean isImputable() {
		return imputable;
	}

	public void setImputable(Boolean imputable) {
		this.imputable = imputable;
	}

	@Column(name = "analisis", nullable = false)
	public Boolean isAnalisis() {
		return analisis;
	}

	public void setAnalisis(Boolean analisis) {
		this.analisis = analisis;
	}

	@Column(name = "conciliacion", nullable = true)
	public Boolean isConciliacion() {
		return conciliacion;
	}

	public void setConciliacion(Boolean conciliacion) {
		this.conciliacion = conciliacion;
	}

	@Column(name = "glosa_general", nullable = false)
	public String getGlosaGeneral() {
		return glosaGeneral;
	}

	public void setGlosaGeneral(String glosaGeneral) {
		this.glosaGeneral = glosaGeneral;
	}

	@ManyToOne
	@JoinColumn(name = "id_grupo_cuenta", nullable = true)
	public GrupoCuenta getGrupoCuenta() {
		return grupoCuenta;
	}

	public void setGrupoCuenta(GrupoCuenta grupoCuenta) {
		this.grupoCuenta = grupoCuenta;
	}

	@ManyToOne
	@JoinColumn(name = "id_clase_cuenta", nullable = true)
	public ClaseCuenta getClaseCuenta() {
		return claseCuenta;
	}

	public void setClaseCuenta(ClaseCuenta claseCuenta) {
		this.claseCuenta = claseCuenta;
	}

	@ManyToOne
	@JoinColumn(name = "id_cuenta", nullable = true)
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@ManyToOne
	@JoinColumn(name = "id_banco", nullable = true)
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = true)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne
	@JoinColumn(name = "id_comprobante_contable", nullable = true)
	public ComprobanteContable getComprobanteContable() {
		return comprobanteContable;
	}

	public void setComprobanteContable(ComprobanteContable comprobanteContable) {
		this.comprobanteContable = comprobanteContable;
	}

	@ManyToOne
	@JoinColumn(name = "id_sucursal", nullable = true)
	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
