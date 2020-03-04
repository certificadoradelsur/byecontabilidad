package cl.certificadoradelsur.byecontabilidad.rest;

import javax.ejb.Local;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Interfaz que declara los servicios rest de grupoCuenta
 * 
 * @author juan
 *
 */
@Local
@Path("/private/grupoCuenta")
public interface GrupoCuentaSvc {

    /**
     * funcion que obtiene los grupoCuenta
     * @param datos
     * @return grupoCuenta que seran mostrados en el select
     */
	@POST
	@Path("/getLista")
	@Produces("application/json")
	Response getLista();

	/**
     * funcion que obtiene los grupoCuenta por id claseCuenta
     * @param datos
     * @return cuentas que seran mostrados en el select
     */
	@POST
	@Path("/getByIdClaseCuenta")
	@Produces("application/json")
	Response getByIdClaseCuenta(String datos);



}