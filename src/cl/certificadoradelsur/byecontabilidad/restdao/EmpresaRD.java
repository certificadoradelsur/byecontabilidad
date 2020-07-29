package cl.certificadoradelsur.byecontabilidad.restdao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import cl.certificadoradelsur.byecontabilidad.dao.BitacoraDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ClaseCuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.ClasificacionDAO;
import cl.certificadoradelsur.byecontabilidad.dao.EmpresaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.GrupoCuentaDAO;
import cl.certificadoradelsur.byecontabilidad.dao.OficinaContableDAO;
import cl.certificadoradelsur.byecontabilidad.dao.SucursalDAO;
import cl.certificadoradelsur.byecontabilidad.dao.UsuarioDAO;
import cl.certificadoradelsur.byecontabilidad.entities.Bitacora;
import cl.certificadoradelsur.byecontabilidad.entities.Clasificacion;
import cl.certificadoradelsur.byecontabilidad.entities.Empresa;
import cl.certificadoradelsur.byecontabilidad.entities.Sucursal;
import cl.certificadoradelsur.byecontabilidad.entities.Usuario;
import cl.certificadoradelsur.byecontabilidad.exception.ByeContabilidadException;
import cl.certificadoradelsur.byecontabilidad.json.EmpresaJson;
import cl.certificadoradelsur.byecontabilidad.utils.Constantes;
import cl.certificadoradelsur.byecontabilidad.utils.Utilidades;

/**
 * Clase que hace el nexo entre los servicios rest y el patron dao
 * 
 * @author juan
 *
 */
@Stateless
public class EmpresaRD {
	private static Logger log = Logger.getLogger(EmpresaRD.class);
	@Inject
	private EmpresaDAO edao;
	@Inject
	private OficinaContableDAO ofidao;
	@Inject
	private UsuarioDAO udao;
	@Inject
	private ClasificacionDAO cdao;
	@Inject
	private GrupoCuentaDAO grupodao;
	@Inject
	private ClaseCuentaDAO clasedao;
	@Inject
	private SucursalDAO sudao;
	@Inject
	private BitacoraDAO bidao;

	/**
	 * funcion que almacena
	 * 
	 * @param pj objeto json
	 * @return mensaje hacia el front
	 */
	public String save(EmpresaJson ej) {
		try {
			Empresa e = new Empresa();
			if (Utilidades.containsScripting(ej.getRut()).compareTo(true) == 0
					|| Utilidades.containsScripting(ej.getGiro()).compareTo(true) == 0
					|| Utilidades.containsScripting(ej.getRazonSocial()).compareTo(true) == 0
					|| Utilidades.containsScripting(ej.getDireccion()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				if (Utilidades.validarRut(ej.getRut()).equals(true)) {
					e.setGiro(ej.getGiro());
					e.setRazonSocial(ej.getRazonSocial());
					e.setRut(ej.getRut());
					e.setActivo(true);
					e.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					e.setOficinaContable(udao.getById(ej.getIdUsuario()).getOficinaContable());
					edao.guardar(e);
					saveClasificacion(e.getId());
					Sucursal s = new Sucursal();
					s.setDireccion(ej.getDireccion());
					s.setEmpresa(edao.getById(e.getId()));
					s.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					sudao.guardar(s);
					return Constantes.MENSAJE_REST_OK;
				} else {
					return "El rut ingresado no es valido";
				}
			}
		} catch (Exception e) {
			log.error("No se pudo guardar la empresa ", e);
			return Constantes.MENSAJE_REST_FAIL;
		}
	}

//	/**
//	 * Funcion que crea sucursal al agregar empresa
//	 * 
//	 * @param direccion
//	 * @param idEmpresa
//	 */
//	private void saveSucursal(String direccion, Long idEmpresa) {
//		try {
//			Sucursal s = new Sucursal();
//			if (Utilidades.containsScripting(direccion).compareTo(true) == 0) {
//				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
//			} else {
//				s.setDireccion(direccion);
//				s.setEmpresa(edao.getById(idEmpresa));
//				sudao.guardar(s);
//				log.error("Sucursal gurdada exitosamente");
//
//			}
//		} catch (Exception e) {
//			log.error("No se pudo guardar la sucursal ", e);
//
//		}
//	}

	/**
	 * Funcion que crea clasificaciones al agregar una empresa nueva
	 * 
	 * @param idEmpresa
	 */
	private void saveClasificacion(Long idEmpresa) {
		Clasificacion c1 = new Clasificacion();
		c1.setNombre("Disponible en caja");
		c1.setClaseCuenta(clasedao.getById(1L));
		c1.setGrupoCuenta(grupodao.getById(1L));
		c1.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c1);

		Clasificacion c2 = new Clasificacion();
		c2.setNombre("Depsitos a plazo");
		c2.setClaseCuenta(clasedao.getById(1L));
		c2.setGrupoCuenta(grupodao.getById(1L));
		c2.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c2);

		Clasificacion c3 = new Clasificacion();
		c3.setNombre("Valores Negociables");
		c3.setClaseCuenta(clasedao.getById(1L));
		c3.setGrupoCuenta(grupodao.getById(1L));
		c3.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c3);

		Clasificacion c4 = new Clasificacion();
		c4.setNombre("Deudores por venta");
		c4.setClaseCuenta(clasedao.getById(1L));
		c4.setGrupoCuenta(grupodao.getById(1L));
		c4.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c4);

		Clasificacion c5 = new Clasificacion();
		c5.setNombre("Documentos por cobrar");
		c5.setClaseCuenta(clasedao.getById(1L));
		c5.setGrupoCuenta(grupodao.getById(1L));
		c5.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c5);

		Clasificacion c6 = new Clasificacion();
		c6.setNombre("Deudores varios");
		c6.setClaseCuenta(clasedao.getById(1L));
		c6.setGrupoCuenta(grupodao.getById(1L));
		c6.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c6);

		Clasificacion c7 = new Clasificacion();
		c7.setNombre("Provisión de cuentas incobrables");
		c7.setClaseCuenta(clasedao.getById(1L));
		c7.setGrupoCuenta(grupodao.getById(1L));
		c7.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c7);

		Clasificacion c8 = new Clasificacion();
		c8.setNombre("Impuestos por recuperar");
		c8.setClaseCuenta(clasedao.getById(1L));
		c8.setGrupoCuenta(grupodao.getById(1L));
		c8.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c8);

		Clasificacion c9 = new Clasificacion();
		c9.setNombre("Existencia");
		c9.setClaseCuenta(clasedao.getById(1L));
		c9.setGrupoCuenta(grupodao.getById(1L));
		c9.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c9);

		Clasificacion c10 = new Clasificacion();
		c10.setNombre("Gastos pagados por anticipo");
		c10.setClaseCuenta(clasedao.getById(1L));
		c10.setGrupoCuenta(grupodao.getById(1L));
		c10.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c10);

		Clasificacion c11 = new Clasificacion();
		c11.setNombre("Otros activos circulantes");
		c11.setClaseCuenta(clasedao.getById(1L));
		c11.setGrupoCuenta(grupodao.getById(1L));
		c11.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c11);

		Clasificacion c12 = new Clasificacion();
		c12.setNombre("Bienes del activo fijo físico");
		c12.setClaseCuenta(clasedao.getById(1L));
		c12.setGrupoCuenta(grupodao.getById(2L));
		c12.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c12);

		Clasificacion c13 = new Clasificacion();
		c13.setNombre("Depreciaciones acumuladas");
		c13.setClaseCuenta(clasedao.getById(1L));
		c13.setGrupoCuenta(grupodao.getById(2L));
		c13.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c13);

		Clasificacion c14 = new Clasificacion();
		c14.setNombre("Retazacion técnica del activo fijo");
		c14.setClaseCuenta(clasedao.getById(1L));
		c14.setGrupoCuenta(grupodao.getById(2L));
		c14.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c14);

		Clasificacion c15 = new Clasificacion();
		c15.setNombre("Inversiones en subsidiarias");
		c15.setClaseCuenta(clasedao.getById(1L));
		c15.setGrupoCuenta(grupodao.getById(3L));
		c15.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c15);

		Clasificacion c16 = new Clasificacion();
		c16.setNombre("Derechos en sociedades de persona");
		c16.setClaseCuenta(clasedao.getById(1L));
		c16.setGrupoCuenta(grupodao.getById(3L));
		c16.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c16);

		Clasificacion c17 = new Clasificacion();
		c17.setNombre("Deudores a largo plazo");
		c17.setClaseCuenta(clasedao.getById(1L));
		c17.setGrupoCuenta(grupodao.getById(3L));
		c17.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c17);

		Clasificacion c18 = new Clasificacion();
		c18.setNombre("Impuesto a la renta diferido");
		c18.setClaseCuenta(clasedao.getById(1L));
		c18.setGrupoCuenta(grupodao.getById(3L));
		c18.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c18);

		Clasificacion c19 = new Clasificacion();
		c19.setNombre("Otros activos (AMORTIZACIONES)");
		c19.setClaseCuenta(clasedao.getById(1L));
		c19.setGrupoCuenta(grupodao.getById(3L));
		c19.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c19);

		Clasificacion c20 = new Clasificacion();
		c20.setNombre("Obligaciones con bancos a corto plazo");
		c20.setClaseCuenta(clasedao.getById(2L));
		c20.setGrupoCuenta(grupodao.getById(4L));
		c20.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c20);

		Clasificacion c21 = new Clasificacion();
		c21.setNombre("Obligaciones con el publico (Efectos de comercio)");
		c21.setClaseCuenta(clasedao.getById(2L));
		c21.setGrupoCuenta(grupodao.getById(4L));
		c21.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c21);

		Clasificacion c22 = new Clasificacion();
		c22.setNombre("Obligaciones con el publico a largo plazo con vencimiento dentro de un año");
		c22.setClaseCuenta(clasedao.getById(2L));
		c22.setGrupoCuenta(grupodao.getById(4L));
		c22.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c22);

		Clasificacion c23 = new Clasificacion();
		c23.setNombre("Dividendos por pagar");
		c23.setClaseCuenta(clasedao.getById(2L));
		c23.setGrupoCuenta(grupodao.getById(4L));
		c23.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c23);

		Clasificacion c24 = new Clasificacion();
		c24.setNombre("Cuentas por pagar");
		c24.setClaseCuenta(clasedao.getById(2L));
		c24.setGrupoCuenta(grupodao.getById(4L));
		c24.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c24);

		Clasificacion c25 = new Clasificacion();
		c25.setNombre("Documentos por pagar");
		c25.setClaseCuenta(clasedao.getById(2L));
		c25.setGrupoCuenta(grupodao.getById(4L));
		c25.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c25);

		Clasificacion c26 = new Clasificacion();
		c26.setNombre("Acreedores varios");
		c26.setClaseCuenta(clasedao.getById(2L));
		c26.setGrupoCuenta(grupodao.getById(4L));
		c26.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c26);

		Clasificacion c27 = new Clasificacion();
		c27.setNombre("Documentos y cuentas por pagar a empresas relacionadas");
		c27.setClaseCuenta(clasedao.getById(2L));
		c27.setGrupoCuenta(grupodao.getById(4L));
		c27.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c27);

		Clasificacion c28 = new Clasificacion();
		c28.setNombre("Provisiones y retenciones");
		c28.setClaseCuenta(clasedao.getById(2L));
		c28.setGrupoCuenta(grupodao.getById(4L));
		c28.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c28);

		Clasificacion c29 = new Clasificacion();
		c29.setNombre("Impuesto a la renta");
		c29.setClaseCuenta(clasedao.getById(2L));
		c29.setGrupoCuenta(grupodao.getById(4L));
		c29.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c29);

		Clasificacion c30 = new Clasificacion();
		c30.setNombre("Otros pasivos circulantes");
		c30.setClaseCuenta(clasedao.getById(2L));
		c30.setGrupoCuenta(grupodao.getById(4L));
		c30.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c30);

		Clasificacion c31 = new Clasificacion();
		c31.setNombre("Obligaciones a largo plazo");
		c31.setClaseCuenta(clasedao.getById(2L));
		c31.setGrupoCuenta(grupodao.getById(5L));
		c31.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c31);

		Clasificacion c32 = new Clasificacion();
		c32.setNombre("Cuentas por pagar a largo plazo");
		c32.setClaseCuenta(clasedao.getById(2L));
		c32.setGrupoCuenta(grupodao.getById(5L));
		c32.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c32);

		Clasificacion c33 = new Clasificacion();
		c33.setNombre("Cuentas por pagar a largo plazo");
		c33.setClaseCuenta(clasedao.getById(2L));
		c33.setGrupoCuenta(grupodao.getById(5L));
		c33.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c33);

		Clasificacion c34 = new Clasificacion();
		c34.setNombre("Capital pagado");
		c34.setClaseCuenta(clasedao.getById(2L));
		c34.setGrupoCuenta(grupodao.getById(6L));
		c34.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c34);

		Clasificacion c35 = new Clasificacion();
		c35.setNombre("Otras particiones en el patrimonio");
		c35.setClaseCuenta(clasedao.getById(2L));
		c35.setGrupoCuenta(grupodao.getById(6L));
		c35.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c35);

		Clasificacion c36 = new Clasificacion();
		c36.setNombre("Otras reservas");
		c36.setClaseCuenta(clasedao.getById(2L));
		c36.setGrupoCuenta(grupodao.getById(6L));
		c36.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c36);

		Clasificacion c37 = new Clasificacion();
		c37.setNombre("Ganancias (pérdidas) acumuladas");
		c37.setClaseCuenta(clasedao.getById(2L));
		c37.setGrupoCuenta(grupodao.getById(6L));
		c37.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c37);

		Clasificacion c38 = new Clasificacion();
		c38.setNombre("Patrimonio atribuible a los propietarios de la controladora");
		c38.setClaseCuenta(clasedao.getById(2L));
		c38.setGrupoCuenta(grupodao.getById(6L));
		c38.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c38);

		Clasificacion c39 = new Clasificacion();
		c39.setNombre("Participaciones no controladoras");
		c39.setClaseCuenta(clasedao.getById(2L));
		c39.setGrupoCuenta(grupodao.getById(6L));
		c39.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c39);

		Clasificacion c40 = new Clasificacion();
		c40.setNombre("Prima de emisión");
		c40.setClaseCuenta(clasedao.getById(2L));
		c40.setGrupoCuenta(grupodao.getById(6L));
		c40.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c40);

		Clasificacion c41 = new Clasificacion();
		c41.setNombre("Acciones propias en cartera");
		c41.setClaseCuenta(clasedao.getById(2L));
		c41.setGrupoCuenta(grupodao.getById(6L));
		c41.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c41);

		Clasificacion c42 = new Clasificacion();
		c42.setNombre("Impuesto a la renta");
		c42.setClaseCuenta(clasedao.getById(3L));
		c42.setGrupoCuenta(grupodao.getById(7L));
		c42.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c42);

		Clasificacion c43 = new Clasificacion();
		c43.setNombre("Otros egresos fuera de explotación");
		c43.setClaseCuenta(clasedao.getById(3L));
		c43.setGrupoCuenta(grupodao.getById(7L));
		c43.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c43);

		Clasificacion c44 = new Clasificacion();
		c44.setNombre("Correccción monetaria");
		c44.setClaseCuenta(clasedao.getById(3L));
		c44.setGrupoCuenta(grupodao.getById(7L));
		c44.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c44);

		Clasificacion c48 = new Clasificacion();
		c48.setNombre("Impuesto a la renta");
		c48.setClaseCuenta(clasedao.getById(3L));
		c48.setGrupoCuenta(grupodao.getById(8L));
		c48.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c48);

		Clasificacion c49 = new Clasificacion();
		c49.setNombre("Otros egresos fuera de explotación");
		c49.setClaseCuenta(clasedao.getById(3L));
		c49.setGrupoCuenta(grupodao.getById(8L));
		c49.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c49);

		Clasificacion c50 = new Clasificacion();
		c50.setNombre("Correccción monetaria");
		c50.setClaseCuenta(clasedao.getById(3L));
		c50.setGrupoCuenta(grupodao.getById(8L));
		c50.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c50);

		Clasificacion c51 = new Clasificacion();
		c51.setNombre("Impuesto a la renta");
		c51.setClaseCuenta(clasedao.getById(3L));
		c51.setGrupoCuenta(grupodao.getById(9L));
		c51.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c51);

		Clasificacion c52 = new Clasificacion();
		c52.setNombre("Otros egresos fuera de explotación");
		c52.setClaseCuenta(clasedao.getById(3L));
		c52.setGrupoCuenta(grupodao.getById(9L));
		c52.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c52);

		Clasificacion c53 = new Clasificacion();
		c53.setNombre("Correccción monetaria");
		c53.setClaseCuenta(clasedao.getById(3L));
		c53.setGrupoCuenta(grupodao.getById(9L));
		c53.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c53);

		Clasificacion c54 = new Clasificacion();
		c54.setNombre("Impuesto a la renta");
		c54.setClaseCuenta(clasedao.getById(3L));
		c54.setGrupoCuenta(grupodao.getById(10L));
		c54.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c54);

		Clasificacion c55 = new Clasificacion();
		c55.setNombre("Otros egresos fuera de explotación");
		c55.setClaseCuenta(clasedao.getById(3L));
		c55.setGrupoCuenta(grupodao.getById(10L));
		c55.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c55);

		Clasificacion c56 = new Clasificacion();
		c56.setNombre("Correccción monetaria");
		c56.setClaseCuenta(clasedao.getById(3L));
		c56.setGrupoCuenta(grupodao.getById(10L));
		c56.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c56);

		Clasificacion c57 = new Clasificacion();
		c57.setNombre("Impuesto a la renta");
		c57.setClaseCuenta(clasedao.getById(4L));
		c57.setGrupoCuenta(grupodao.getById(11L));
		c57.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c57);

		Clasificacion c58 = new Clasificacion();
		c58.setNombre("Otros egresos fuera de explotación");
		c58.setClaseCuenta(clasedao.getById(4L));
		c58.setGrupoCuenta(grupodao.getById(11L));
		c58.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c58);

		Clasificacion c59 = new Clasificacion();
		c59.setNombre("Correccción monetaria");
		c59.setClaseCuenta(clasedao.getById(4L));
		c59.setGrupoCuenta(grupodao.getById(11L));
		c59.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c59);

		Clasificacion c60 = new Clasificacion();
		c60.setNombre("Impuesto a la renta");
		c60.setClaseCuenta(clasedao.getById(4L));
		c60.setGrupoCuenta(grupodao.getById(12L));
		c60.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c60);

		Clasificacion c61 = new Clasificacion();
		c61.setNombre("Otros egresos fuera de explotación");
		c61.setClaseCuenta(clasedao.getById(4L));
		c61.setGrupoCuenta(grupodao.getById(12L));
		c61.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c61);

		Clasificacion c62 = new Clasificacion();
		c62.setNombre("Correccción monetaria");
		c62.setClaseCuenta(clasedao.getById(4L));
		c62.setGrupoCuenta(grupodao.getById(12L));
		c62.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c62);

		Clasificacion c63 = new Clasificacion();
		c63.setNombre("Impuesto a la renta");
		c63.setClaseCuenta(clasedao.getById(4L));
		c63.setGrupoCuenta(grupodao.getById(13L));
		c63.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c63);

		Clasificacion c64 = new Clasificacion();
		c64.setNombre("Otros egresos fuera de explotación");
		c64.setClaseCuenta(clasedao.getById(4L));
		c64.setGrupoCuenta(grupodao.getById(13L));
		c64.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c64);

		Clasificacion c65 = new Clasificacion();
		c65.setNombre("Correccción monetaria");
		c65.setClaseCuenta(clasedao.getById(4L));
		c65.setGrupoCuenta(grupodao.getById(13L));
		c65.setEmpresa(edao.getById(idEmpresa));
		cdao.guardar(c65);
	}

	/**
	 * Cuenta el total de las filas
	 * 
	 * @return el total
	 */
	public Long countAll(String razonSocial, String idUsuario) {
		try {
			if (razonSocial == null) {
				razonSocial = "";
			}
			return edao.countAll(razonSocial, udao.getById(idUsuario).getOficinaContable().getId());
		} catch (Exception e) {
			log.error("No se puede contar el total de empresas ", e);
			return 0L;
		}
	}

	/**
	 * Funcion que retorna el total de empresa en json
	 * 
	 * @param page  numero de pagina
	 * @param limit largo de la pagina
	 * @return json con total de empresas
	 */
	public List<EmpresaJson> getAll(Integer page, Integer limit, String razonSocial, String idUsuario) {
		List<EmpresaJson> lej = new ArrayList<>();
		try {
			Integer inicio = 0;
			if (page.compareTo(1) == 0) {
				inicio = 0;
			} else {
				inicio = (page * limit) - limit;
			}

			if (razonSocial == null) {
				razonSocial = "";
			}
			List<Empresa> le = edao.getAll(inicio, limit, razonSocial,
					udao.getById(idUsuario).getOficinaContable().getId());
			for (int i = 0; i < le.size(); i++) {
				EmpresaJson ej = new EmpresaJson();
				ej.setId(le.get(i).getId());
				ej.setGiro(le.get(i).getGiro());
				ej.setRut(le.get(i).getRut());
				ej.setRazonSocial(le.get(i).getRazonSocial());
				ej.setActivo(le.get(i).getActivo());
				ej.setRazonSocialOficina(ofidao.getById(le.get(i).getOficinaContable().getId()).getRazonSocial());
				lej.add(ej);
			}

		} catch (Exception e) {
			log.error("No se puede obtener la lista de empresas ", e);
		}
		return lej;
	}

	/**
	 * metodo que modifica empresa
	 * 
	 * @param pj json de BancoJson
	 * @return mensaje de exito o error
	 */
	public String update(EmpresaJson ej) {
		try {
			Empresa e = edao.getById(ej.getId());
			if (Utilidades.containsScripting(ej.getRut()).compareTo(true) == 0
					|| Utilidades.containsScripting(ej.getGiro()).compareTo(true) == 0
					|| Utilidades.containsScripting(ej.getRazonSocial()).compareTo(true) == 0) {
				throw new ByeContabilidadException(Constantes.MENSAJE_CARACATERES_INVALIDOS);
			} else {
				if (Utilidades.validarRut(ej.getRut()).equals(true)) {
					e.setGiro(ej.getGiro());
					e.setRut(ej.getRut());
					e.setRazonSocial(ej.getRazonSocial());
					e.setActivo(ej.isActivo());
					edao.update(e);
					Bitacora b = new Bitacora();
					b.setUsuario(udao.getById(ej.getIdUsuario()));
					b.setFecha(new Timestamp(System.currentTimeMillis()));
					b.setTabla("Empresa");
					b.setAccion("Update");
					b.setDescripcion("Se modifico " + edao.getById(ej.getId()).getRazonSocial());
					bidao.guardar(b);
					return Constantes.MENSAJE_REST_OK;
				} else {
					return "El rut ingresado no es valido";
				}
			}
		} catch (Exception e) {
			log.error("No se pudo modificar la empresa ",e);
			return e.getMessage();
		}
	}

	/**
	 * metodo obtener una empresa
	 * 
	 * @param id de empresa
	 * @return mensaje de exito o error
	 */
	public EmpresaJson getById(EmpresaJson bj) {
		Empresa e = edao.getById(bj.getId());
		EmpresaJson eJson = new EmpresaJson();
		eJson.setId(e.getId());
		eJson.setGiro(e.getGiro());
		eJson.setRut(e.getRut());
		eJson.setRazonSocial(e.getRazonSocial());
		eJson.setIdOficianaContable(e.getOficinaContable().getId());
		eJson.setActivo(e.getActivo());
		return eJson;
	}

	/**
	 * metodo elimina una empresa
	 * 
	 * @param pj json de empresa
	 * @return mensaje de exito o error
	 */
	public String eliminar(EmpresaJson ej) {
		try {
			Empresa e = edao.getById(ej.getId());
			if (e.getActivo().equals(true)) {
				e.setActivo(false);
			} else {
				e.setActivo(true);
			}
			Bitacora b = new Bitacora();
			b.setUsuario(udao.getById(ej.getIdUsuario()));
			b.setFecha(new Timestamp(System.currentTimeMillis()));
			b.setTabla("Empresa");
			b.setAccion("Delete");
			b.setDescripcion("Se cambio estado " + edao.getById(ej.getId()).getRazonSocial() + " "
					+ edao.getById(ej.getId()).getActivo());
			bidao.guardar(b);
			edao.update(e);
			return Constantes.MENSAJE_REST_OK;
		} catch (Exception e) {
			log.error("No se pudo eliminar la empresa ",e);
			return e.getMessage();
		}
	}

	/*
	 * funcion que trae todas las empresas para llenar select
	 * 
	 */
	public List<EmpresaJson> getAllLista(EmpresaJson ej) {

		List<EmpresaJson> lgj = new ArrayList<>();
		try {
			List<Empresa> g = edao.getLista(udao.getById(ej.getIdUsuario()).getOficinaContable().getId());
			for (int i = 0; i < g.size(); i++) {
				EmpresaJson gj = new EmpresaJson();
				gj.setId(g.get(i).getId());
				gj.setRazonSocial(g.get(i).getRazonSocial());
				lgj.add(gj);
			}
			return lgj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de empresas", e);
			return lgj;
		}

	}

	/**
	 * funcion que trae todas las empresas asociadas al supervisor para llenar
	 * select
	 * 
	 * @param ej
	 * @return
	 */
	public List<EmpresaJson> getSupervisor(EmpresaJson ej) {
		List<EmpresaJson> lgj = new ArrayList<>();
		try {
			Usuario usuario = udao.getById(ej.getIdUsuario());
			for (int i = 0; i < usuario.getUsuarioEmpresa().size(); i++) {

				EmpresaJson gj = new EmpresaJson();
				gj.setId(usuario.getUsuarioEmpresa().get(i).getEmpresa().getId());
				gj.setRazonSocial(usuario.getUsuarioEmpresa().get(i).getEmpresa().getRazonSocial());
				lgj.add(gj);
			}
			return lgj;
		} catch (Exception e) {
			log.error("No se pudo obtener la lista de empresas", e);
			return lgj;
		}

	}

}
