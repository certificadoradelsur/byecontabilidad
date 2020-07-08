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



@Entity
@Table(name = "periodo")
@SequenceGenerator(name = "seq_periodo", sequenceName = "seq_periodo")
@NamedQueries({
	@NamedQuery(name = "Periodo.getAll", query = "SELECT p FROM Periodo p where p.anio =:anio and p.empresa.id =:idEmpresa order by p.mes"),
	@NamedQuery(name = "Periodo.countAll", query = "SELECT count(p.id) FROM Periodo p where p.anio =:anio and p.empresa.id =:idEmpresa"),
	@NamedQuery(name ="Periodo.getbyPeriodo",query = "SELECT p FROM Periodo p where p.mes =:mes and p.anio =:anio and p.empresa.id =:idEmpresa"),
	@NamedQuery(name ="Periodo.getByAnio", query ="SELECT p FROM Periodo p where p.anio =:anio and p.empresa.id =:idEmpresa")})



public class Periodo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5671666343658506381L;
	private Long id;
	private Long mes;
	private Long anio;
	private boolean estado;
	private Empresa empresa;
	
	@Id
	@GeneratedValue(generator = "seq_periodo", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "mes", nullable = true)
	public Long getMes() {
		return mes;
	}
	public void setMes(Long mes) {
		this.mes = mes;
	}
	@Column(name = "anio", nullable = true)
	public Long getAnio() {
		return anio;
	}
	public void setAnio(Long anio) {
		this.anio = anio;
	}
	
	@Column(name = "estado", nullable = true)
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = true)
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}