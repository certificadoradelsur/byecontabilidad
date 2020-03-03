package cl.certificadoradelsur.byecontabilidad.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Clase usada para activar los servicios rest con jboss wildfly
 * 
 * @author juan
 *
 */
@ApplicationPath("/rest-services")
public class RestActivator extends Application {
}
