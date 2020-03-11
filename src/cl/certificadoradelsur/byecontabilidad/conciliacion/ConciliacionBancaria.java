package cl.certificadoradelsur.byecontabilidad.conciliacion;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import cl.certificadoradelsur.byecontabilidad.dao.ConciliacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoCartolaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.NoConciliadoDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Cartola;
import cl.certificadoradelsur.byecontabilidad.entities.Conciliacion;
import cl.certificadoradelsur.byecontabilidad.entities.Movimiento;
import cl.certificadoradelsur.byecontabilidad.entities.NoConciliado;
import cl.certificadoradelsur.byecontabilidad.entities.NoConciliadoCartola;


/**
 * Clase del negocio que implementa funciones de conciliacion
 * 
 * @author juan
 *
 */
@Stateless
public class ConciliacionBancaria {
	@Inject
	private ConciliacionDAO cdao;
	@Inject
	private NoConciliadoDAO ncdao;
	@Inject
	private NoConciliadoCartolaDAO nccdao;
	
	List<Cartola> cartolasConciliadas = new ArrayList<>();

	/**
	 * Funcion que recibe una movimiento y una cartola en el rango de fecha y
	 * recorre la lista movimientos
	 * 
	 * @param lista movimientos
	 * @param lista cartolas
	 */
	public void conciliar(List<Movimiento> movimientos, List<Cartola> cartolas) {
		for (int i = 0; i < movimientos.size(); i++) {
			conciliarMovimiento(movimientos.get(i), cartolas);
		}
		guardaCartolaNoConciliada(cartolas, cartolasConciliadas);
	}

	/**
	 * Funcion que recorre la lista cartola y la compara con el movimiento, si los
	 * parametros comparados son iguales, guarda una conciliacion, de lo contrario
	 * guarda un noconciliado

	 * @param mov
	 * @param lc
	 */
	public void conciliarMovimiento(Movimiento mov, List<Cartola> lc) {
		Boolean movimientoConciliado = false;
		for (int i = 0; i < lc.size(); i++) {
			if (// mov.getNumMovimiento().compareTo(lc.get(i).getNumDocumento()) == 0 &&
				// && mov.getFecha().compareTo(lc.get(i).getFecha()) == 0
					  mov.getCuenta().equals(lc.get(i).getCuenta()) && mov.getMonto().compareTo(lc.get(i).getMonto()) == 0
						//&& !mov.isEliminado() && !lc.get(i).isEliminado()
						) {
					if (mov.getFecha().compareTo(lc.get(i).getFecha()) == 0 || mov.getFecha().before(lc.get(i).getFecha())) {
						if (cdao.getByMov(mov.getId())== null && cdao.getByCart(lc.get(i).getId()) == null) {
							Conciliacion conciliacion = new Conciliacion();
							conciliacion.setMovimiento(mov);
							conciliacion.setCartola(lc.get(i));
							conciliacion.setFecha(new Timestamp(System.currentTimeMillis()));
							conciliacion.setEliminado(false);
							cdao.guardar(conciliacion);
							cartolasConciliadas.add(lc.get(i));
							movimientoConciliado = true;
							break;
						}
					}
				}
			}
		if (movimientoConciliado.compareTo(false) == 0 && ncdao.getByIdMovimiento(mov.getId()) == null
				&& !mov.isEliminado() && cdao.getByMov(mov.getId())==null) {
			NoConciliado noConciliado = new NoConciliado();
			noConciliado.setMovimiento(mov);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			noConciliado.setFecha(timestamp);
			noConciliado.setEliminado(false);
			ncdao.guardar(noConciliado);
		}
	}

	/**
	 * Funcion que almacena cartolas no conciliadas
	 * 
	 * @param lc
	 * @param cartolasConciliadas
	 */
	public void guardaCartolaNoConciliada(List<Cartola> lc, List<Cartola> cartolasConciliadas) {
		int num = 0;
		if (cartolasConciliadas.isEmpty()) {
			for (int i = 0; i < lc.size(); i++) {
				if (nccdao.getByIdCartola(lc.get(i).getId()) == null && !lc.get(i).isEliminado()) {
					NoConciliadoCartola noconciliadocartola = new NoConciliadoCartola();
					noconciliadocartola.setCartola(lc.get(i));
					noconciliadocartola.setFecha(new Timestamp(System.currentTimeMillis()));
					noconciliadocartola.setEliminado(false);
					nccdao.guardar(noconciliadocartola);
					break;
				}
			}
		} else {
			for (int j = 0; j < lc.size(); j++) {
				for (int k = 0; k < cartolasConciliadas.size(); k++) {
					if (lc.get(j).getId().equals(cartolasConciliadas.get(k).getId()) 
							&& !lc.get(j).isEliminado() && !cartolasConciliadas.get(k).isEliminado()
					) {
						num++;
					}
				}
				if (num == 0 && nccdao.getByIdCartola(lc.get(j).getId()) == null && !lc.get(j).isEliminado()) {
					NoConciliadoCartola noconciliadocartola = new NoConciliadoCartola();
					noconciliadocartola.setCartola(lc.get(j));
					noconciliadocartola.setFecha(new Timestamp(System.currentTimeMillis()));
					noconciliadocartola.setEliminado(false);
					nccdao.guardar(noconciliadocartola);
				}
				num = 0;
			}
		}
	}

}
