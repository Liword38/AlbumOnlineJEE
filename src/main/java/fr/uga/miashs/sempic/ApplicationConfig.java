/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic;

import fr.uga.miashs.sempic.dao.AlbumFacade;
import fr.uga.miashs.sempic.entities.SempicGroup;
import fr.uga.miashs.sempic.entities.SempicUser;
import fr.uga.miashs.sempic.entities.SempicUserType;
import fr.uga.miashs.sempic.dao.GroupFacade;
import fr.uga.miashs.sempic.dao.PhotoFacade;
import fr.uga.miashs.sempic.dao.SempicUserFacade;
import fr.uga.miashs.sempic.entities.SempicAlbum;
import fr.uga.miashs.sempic.entities.SempicPhoto;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.ws.rs.core.Application;

/**
 * In this class, some generic application config are given
 *
 * @author Jerome David <jerome.david@univ-grenoble-alpes.fr>
 */
@DataSourceDefinition(
        name = ApplicationConfig.DATA_SOURCE,
        // HSQL
        className = "org.hsqldb.jdbcDriver",
        //url="jdbc:hsqldb:mem:hsqldb", // memory database that is deleted when the server is stopped
        url = "jdbc:hsqldb:file:sempicdb",
        databaseName = "SempicDB",
        user = "sempic",
        password = "sempic"
)

/*
Works only for glassfish (ie JavaEE8 fully compliant server), for tomm use web.xml login-config + declaration in context.xml
 */
 /*@DatabaseIdentityStoreDefinition(
        dataSourceLookup = ApplicationConfig.DATA_SOURCE,
        callerQuery = "select passwordhash from sempic.sempicuser where email=?", // prefix sempicuser with sempic. for Derby
        groupsQuery = "select userType from sempic.sempicuser where email = ?", // prefix sempicuser with sempic. for Derby
        hashAlgorithm = Pbkdf2PasswordHash.class,
        hashAlgorithmParameters = { "Pbkdf2PasswordHash.Iterations=3072", 
                                    "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", 
                                    "Pbkdf2PasswordHash.SaltSizeBytes=64"},

        priority = 30)



@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/faces/login.xhtml",
                errorPage = "/login-error.html",
                useForwardToLogin=false))
 */
@FacesConfig(version = FacesConfig.Version.JSF_2_3)

@Singleton
@Named
@Startup
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application{

    public final static String DATA_SOURCE = "java:app/sempicdb";
    public final static String WEB_API = "";

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Inject
    private SempicUserFacade userFacade;
    @Inject
    private GroupFacade groupFacade;
    @Inject
    private AlbumFacade albumFacade;
    @Inject
    private PhotoFacade photoFacade;

    @PostConstruct
    public void init() {
        //Create Admin
        SempicUser admin = new SempicUser();
        admin.setFirstname("Jack");
        admin.setLastname("Rabbit");
        admin.setEmail("admin@miashs.fr");
        admin.setUserType(SempicUserType.ADMIN);
        admin.setPasswordHash(passwordHash.generate("admin".toCharArray()));

        //Create UserTest
        SempicUser userTest = new SempicUser();
        userTest.setFirstname("User");
        userTest.setLastname("Test");
        userTest.setEmail("user@user");
        userTest.setUserType(SempicUserType.USER);
        userTest.setPasswordHash(passwordHash.generate("user".toCharArray()));

        //Create Admin Group
        SempicGroup g = new SempicGroup();
        g.setName("admins");
        g.setOwner(admin);

        //Create Admin's Album
        SempicAlbum a = new SempicAlbum();
        a.setName("L'album de l'admin");
        a.setDescription("Cet album sert à tester l'app");
        a.setAlbumOwner(admin);

        //Create Photo for Admin's Album
//        SempicPhoto p = new SempicPhoto();
//        p.setName("Une magnifique photo (pour le moment une photo est une string)");
//        p.setInAlbum(a);

        try {
            userFacade.create(admin);

            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.WARNING, "Admin created");
        } catch (SempicModelException e) {
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.WARNING, "Admin already exists");
        }
        
        try {
            userFacade.create(userTest);
            groupFacade.create(g);
            //Ajoute UserTest au groupe de l'admin
            groupFacade.addMember(g.getId(), userTest.getId());
            albumFacade.create(a);
            System.out.println("On va ajouter un album dans un groupe");
            groupFacade.addAlbum(g.getId(), a.getId());
          //  photoFacade.create(p);
            
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.WARNING, "Static data created");
         } catch (SempicModelException e) {
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.WARNING, "Static data failed to create");
        }

    }
    
    
     @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(fr.uga.miashs.sempic.restservices.PhotoStore.class);
    }

}
