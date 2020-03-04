package cl.certificadoradelsur.byecontabilidad.rest;

import javax.ejb.Local;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Interfaz que declara los servicios rest de claseCuenta
 * 
 * @author juan
 *
 */
@Local
@Path("/private/claseCuenta")
public interface ClaseCuentaSvc {

    /**
     * funcion que obtiene los claseCuenta
     * @param datos
     * @return grupoCuenta que seran mostrados en el select
     */
	@POST
	@Path("/getLista")
	@Produces("application/json")
	Response getLista();

}