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
 * Clase que crea la tabla clasificacion
 * 
 * @author juan
 *
 */

@Entity
@Table(name = "clasificacion")
@SequenceGenerator(name = "seq_clasificacion", sequenceName = "seq_clasificacion")
@NamedQueries({
		@NamedQuery(name = "Clasificacion.getAll", query = "SELECT c FROM Clasificacion c where (true =:ignoreIdClaseCuenta or c.claseCuenta.id=:idClaseCuenta) and (true =:ignoreIdGrupoCuenta or c.grupoCuenta.id =:idGrupoCuenta) and c.empresa.oficinaContable.id=:idOficinaContable and (true = :ignoreNombre or upper(c.nombre) like :nombre )"),
		@NamedQuery(name = "Clasificacion.countAll", query = "SELECT count(c.id) FROM Clasificacion c where (true =:ignoreIdClaseCuenta or c.claseCuenta.id=:idClaseCuenta) and (true =:ignoreIdGrupoCuenta or c.grupoCuenta.id =:idGrupoCuenta) and c.empresa.oficinaContable.id=:idOficinaContable and (true = :ignoreNombre or upper(c.nombre)  like :nombre )"),
		@NamedQuery(name = "Clasificacion.getByIdGrupoCuenta", query = "SELECT c FROM Clasificacion c  where c.empresa.oficinaContable.id=:idOficinaContable and c.grupoCuenta.id=:idGrupoCuenta")})

public class Clasificacion implements Serializable {

	private static final long serialVersionUID = 1323287814392112354L;
	private Long id;
	private String nombre;
	private ClaseCuenta claseCuenta;
	private GrupoCuenta grupoCuenta;
	private Empresa empresa;


	@Id
	@GeneratedValue(generator = "seq_clasificacion", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	@JoinColumn(name = "id_empresa", nullable = true)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	

	
	
}
