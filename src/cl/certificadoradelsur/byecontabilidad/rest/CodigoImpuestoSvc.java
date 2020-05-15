package cl.certificadoradelsur.byecontabilidad.rest;

import javax.ejb.Local;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Interfaz que declara los servicios rest de codigoImpuesto
 * 
 * @author juan
 *
 */
@Local
@Path("/private/codigoImpuesto")
public interface CodigoImpuestoSvc {

	/**
	 * Funcion para buscar banco por id
	 * 
	 * @param id banco
	 * @return json de salida
	 */
	@POST
	@Path("/getById")
	@Produces("application/json")
	Response getById(String datos);

    /**
     * funcion que obtiene los codigoImpuesto
     * @param datos
     * @return codigoImpuesto que seran mostrados en el select
     */
	@POST
	@Path("/getLista")
	@Produces("application/json")
	Response getLista(String datos);
		
}