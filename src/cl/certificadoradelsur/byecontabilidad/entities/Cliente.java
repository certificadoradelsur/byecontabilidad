package cl.certificadoradelsur.byecontabilidad.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Clase que crea la tabla cliente
 * 
 * @author juan
 *
 */
@Entity
@Table(name = "cliente")
@SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente")
@NamedQueries({ 
	@NamedQuery(name = "Cliente.getAll", query = "SELECT c FROM Cliente  c  "),
	@NamedQuery(name = "Cliente.countAll", query = "SELECT count(C.id) FROM Cliente c"),
	@NamedQuery(name = "Cliente.getAllLista", query = "SELECT c FROM Cliente c  where c.activo = true ")
})

public class Cliente implements Serializable {

	private static final long serialVersionUID = 884233189837672260L;
	private Long id;
	private String rut;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String giro;
	private String email;
	private String telefono;
	private Boolean activo;

	@Id
	@GeneratedValue(generator = "seq_cliente", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "activo", nullable = false)
	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Column(name = "rut", nullable = false)
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "direccion", nullable = false)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "ciudad", nullable = false)
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Column(name = "giro", nullable = false)
	public String getGiro() {
		return giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "telefono", nullable = false)
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
