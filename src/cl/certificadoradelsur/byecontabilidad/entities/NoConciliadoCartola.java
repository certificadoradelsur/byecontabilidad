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
 * Clase que crea la tabla no Conciliado Cartola
 * 
 * @author juan
 *
 */
@Entity
@Table(name = "no_conciliado_cartola")
@SequenceGenerator(name = "seq_no_conciliado_cartola", sequenceName = "seq_no_conciliado_cartola")
@NamedQueries({
		@NamedQuery(name = "NoConciliadoCartola.getAll", query = "SELECT c FROM NoConciliadoCartola c where c.eliminado = false and c.empresa.id =:idEmpresa and c.empresa.oficinaContable.id =:idOficinaContable and c.cartola.fecha  between :fechaInicial and :fechaFinal  and (true = :ignoreIdCuenta or c.cartola.cuenta.id=:idCuenta) and (true = :ignoreIdBanco or c.cartola.banco.id=:idBanco)"),
		@NamedQuery(name = "NoConciliadoCartola.countAll", query = "SELECT count(c.id) FROM NoConciliadoCartola c where c.eliminado = false and c.empresa.id =:idEmpresa and c.empresa.oficinaContable.id =:idOficinaContable and c.cartola.fecha  between :fechaInicial and :fechaFinal  and (true = :ignoreIdCuenta or c.cartola.cuenta.id=:idCuenta) and (true = :ignoreIdBanco or c.cartola.banco.id=:idBanco)"),
		@NamedQuery(name = "NoConciliadoCartola.getByIdCartola", query = "SELECT c FROM NoConciliadoCartola c where c.cartola.id= :idCartola"),
		@NamedQuery(name = "NoConciliadoCartola.getNoConciliadoCartolaMonto", query = "SELECT  c FROM NoConciliadoCartola c where c.eliminado = false and c.cartola.monto = :monto"),
		@NamedQuery(name = "NoConciliadoCartola.getNoConciliadoCartolaDoc", query = "SELECT  c FROM NoConciliadoCartola c where c.eliminado = false and c.cartola.numDocumento = :numDocumento"),
		@NamedQuery(name = "NoConciliadoCartola.countAllNCC", query = "SELECT  count(c.id) FROM NoConciliadoCartola c where c.eliminado = false and c.cartola.monto = :monto"),
		@NamedQuery(name = "NoConciliadoCartola.countAllNCCDoc", query = "SELECT  count(c.id) FROM NoConciliadoCartola c where c.eliminado = false and c.cartola.numDocumento = :numDocumento"),
		@NamedQuery(name = "NoConciliadoCartola.getByIdReporteBancoCuenta", query = "SELECT c FROM NoConciliadoCartola c where c.eliminado = false and c.cartola.fecha between :fechaInicial and :fechaFinal and c.cartola.cuenta.banco.id= :idBanco and c.cartola.cuenta.id= :idCuenta")

})

public class NoConciliadoCartola implements Serializable {

	private static final long serialVersionUID = 13546548143945452L;
	private Long id;
	private Cartola cartola;
	private Timestamp fecha;
	private Boolean eliminado;
	private Empresa empresa;

	@Id
	@GeneratedValue(generator = "seq_no_conciliado_cartola", strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "id_cartola", nullable = true)
	public Cartola getCartola() {
		return cartola;
	}

	public void setCartola(Cartola cartola) {
		this.cartola = cartola;
	}

	@Column(name = "fecha", nullable = true)
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Column(name = "eliminado", nullable = true)
	public Boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
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
