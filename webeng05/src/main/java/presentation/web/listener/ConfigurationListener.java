package presentation.web.listener;

import configuration.Configuration;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


/**
 * populates COnfiguration object from ServletContext-InitParams
 */
@WebListener
public class ConfigurationListener implements ServletContextListener { 
    public void contextInitialized(ServletContextEvent sce) { 
    	Configuration.setDataSourceType(sce.getServletContext().getInitParameter("dataSourceType"));
    	Configuration.setUsername(sce.getServletContext().getInitParameter("db.user"));
    	Configuration.setPassword(sce.getServletContext().getInitParameter("db.password"));
    	Configuration.setUrl(sce.getServletContext().getInitParameter("db.url"));
    	Configuration.setDriver(sce.getServletContext().getInitParameter("db.driver"));
    	
    } 	

    public void contextDestroyed(ServletContextEvent sce) { 
        
    } 

}
