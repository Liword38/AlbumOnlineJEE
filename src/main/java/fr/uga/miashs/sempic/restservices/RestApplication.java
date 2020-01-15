/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.restservices;

import fr.uga.miashs.sempic.ApplicationConfig;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

/*import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;*/

/**
 *
 * @author Jerome David <jerome.david@univ-grenoble-alpes.fr>
 */
@ApplicationPath(ApplicationConfig.WEB_API)
public class RestApplication extends Application /*ResourceConfig *//* use javax.ws.rs.core.Application if not jersey specific*/ {

    public RestApplication() {

        // Now you can expect validation errors to be sent to the client.
        //property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        //property(ServerProperties.MOXY_JSON_FEATURE_DISABLE,false);
        //packages("fr.uga.miashs.sempic.restservices");
        //register(MoxyJsonFeature.class);
        //register(JacksonFeature.class);
        //packages("fr.uga.miashs.sempic.restservices");
        
    }

    /**
     * Web services classes and related have to be regitered here
     * @return 
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set res =  new HashSet();
        
        String[] features = {
            "org.glassfish.jersey.moxy.json.MoxyJsonFeature",
            "org.apache.cxf.interceptor.security.SecureAnnotationsInterceptor"
        };
        for (String fName : features) {
            try {
                Class cls = Class.forName(fName);
                res.add(cls);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RestApplication.class.getName()).log(Level.WARNING, fName+" not available");
            }
        }
        
        res.add(MOXyJsonProvider.class);  //Uses moxy a JAXB provider that produce Json from xml annotations
        res.add(PhotoStore.class);
        //res.add(SempicExceptionMapper.class);
        
        return res;
    }

}
