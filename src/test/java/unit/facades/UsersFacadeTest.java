/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.facades;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;

import org.glassfish.embeddable.*; 
import org.glassfish.embeddable.archive.*;
import session.UsersFacade;

/**
 *
 * @author Squash
 */
public class UsersFacadeTest extends TestCase {
    private static final Logger LOG = Logger.getLogger(NewServletTest.class.getCanonicalName());
    private static GlassFish glassfish = null;
    private static final String WEB_APP_NAME = "RestTemp";
    private static final String BASE_URI = "http://localhost:" + 8080 + "/" + WEB_APP_NAME;
    private static final String REST_URI = BASE_URI + "/" + "resources" + "/" + "generic";


    protected void setUp() {
        try {
            GlassFishProperties gfProps = new GlassFishProperties();
            gfProps.setPort("http-listener", 8080); // NB: not sure where name comes from - a standard property?

            glassfish = GlassFishRuntime.bootstrap().newGlassFish(gfProps);
            glassfish.start();

            Deployer deployer = glassfish.getDeployer();
            ScatteredArchive archive = new ScatteredArchive(WEB_APP_NAME, ScatteredArchive.Type.WAR);
            File buildDir = new File("build", "classes");         // NB: location depends on IDE setup
            archive.addClassPath(buildDir);
            deployer.deploy(archive.toURI());
        } catch (GlassFishException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

    }
    
    public void testAdd() {
        UsersFacade usersFacade;
                
        assertTrue(2.0 == 2.0);
    }
}
