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
 * Clase que crea la tabla sucursal
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "Sucursal")
@SequenceGenerator(name = "seq_sucursal", sequenceName = "seq_sucursal")
@NamedQueries({
		@NamedQuery(name = "Sucursal.getAll", query = "SELECT s FROM Sucursal s  where s.empresa.oficinaContable.id =:idOficinaContable and s.empresa.id =:idEmpresa"),
		@NamedQuery(name = "Sucursal.countAll", query = "SELECT count(s.codigo) FROM Sucursal s where s.empresa.oficinaContable.id =:idOficinaContable and s.empresa.id =:idEmpresa"),
		@NamedQuery(name = "Sucursal.getAllLista", query = "SELECT s FROM Sucursal s where s.empresa.oficinaContable.id =:idOficinaContable"),
		@NamedQuery(name = "Sucursal.getByIdEmpresa", query = "SELECT s FROM Sucursal s  where s.empresa.oficinaContable.id =:idOficinaContable  and  s.empresa.id=:idEmpresa "),
		@NamedQuery(name = "Sucursal.maxId", query = "SELECT MAX(s.codigo) FROM Sucursal s") })

public class Sucursal implements Serializable {

	private static final long serialVersionUID = 1323287814392412352L;
	private Long codigo;
	private String direccion;
	private Empresa empresa;
	private Timestamp fechaCreacion;

	@Id
	@GeneratedValue(generator = "seq_sucursal", strategy = GenerationType.AUTO)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "direccion", nullable = false)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
