package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * Clase que crea la tabla cuenta
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "cuenta")
@SequenceGenerator(name = "seq_cuenta", sequenceName = "seq_cuenta")
@NamedQueries({
		@NamedQuery(name = "Cuenta.getAll", query = "SELECT c FROM Cuenta c  where  c.empresa.id =:idEmpresa and c.empresa.oficinaContable.id =:idOficinaContable and c.eliminado = false and (true = :ignoreNumCuenta or  upper(c.numCuenta)  like :numCuenta )"),
		@NamedQuery(name = "Cuenta.countAll", query = "SELECT count(c.numCuenta) FROM Cuenta c  where c.empresa.id =:idEmpresa and c.empresa.oficinaContable.id =:idOficinaContable and c.eliminado = false and (true = :ignoreNumCuenta or  upper(c.numCuenta)  like :numCuenta )"),
		@NamedQuery(name = "Cuenta.getByIdBanco", query = "SELECT c FROM Cuenta c  where c.empresa.oficinaContable.id =:idOficinaContable and c.eliminado = false and  c.banco.id=:idBanco ") })

public class Cuenta implements Serializable {

	private static final long serialVersionUID = 1338781439392145452L;
	private Long id;
	private Boolean eliminado;
	private String numCuenta;
	private String nombreEjecutivo;
	private Integer saldoInicial;
	private Banco banco;
	private Empresa empresa;
    private Timestamp fechaCreacion;
	
	@Id
	@GeneratedValue(generator = "seq_cuenta", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "eliminado", nullable = false)
	public Boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Column(name = "numCuenta", nullable = false)
	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	@Column(name = "nombreEjecutivo", nullable = true)
	public String getNombreEjecutivo() {
		return nombreEjecutivo;
	}

	public void setNombreEjecutivo(String nombreEjecutivo) {
		this.nombreEjecutivo = nombreEjecutivo;
	}

	@Column(name = "saldoInicial", nullable = false)
	public Integer getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Integer saldoInicial) {
		this.saldoInicial = saldoInicial;
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
	
	@Column(name = "fecha_creacion", nullable = true)
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	

}
