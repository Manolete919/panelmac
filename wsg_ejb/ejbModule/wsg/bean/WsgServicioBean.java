package wsg.bean;


import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import wsg.beanAbstracto.AbstractBean;
import wsg.interfaz.WsgServicioBeanRemote;
import wsg.logger.WebServiceJPALogger;
import wsg.modelo.WsgServicio;

/**
 * Session Bean implementation class WsgServicioBean
 */
@Stateless
public class WsgServicioBean extends AbstractBean<WsgServicio> implements WsgServicioBeanRemote , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(WsgServicio.class);
	private Properties propiedades = new Properties();

	private EntityManager em;
	
	private EntityManagerFactory entityManagerFactory = null;
	
	/**
     * Default constructor. 
     */
    public WsgServicioBean() {
        super(WsgServicio.class);
        WebServiceJPALogger.getInstance();
    }
       


	@Override
	protected EntityManager getEntityManager() {
		InputStream iostream = Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/wsg-ejb-ear.properties");
		try {
			propiedades.load(iostream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		   
	    em = getEntityManagerFactory().createEntityManager();	
	    
		return em;
		
	}



	public EntityManagerFactory getEntityManagerFactory() {
		
		entityManagerFactory = Persistence.createEntityManagerFactory(propiedades.getProperty("wsgejb.pu"));
		
		return entityManagerFactory;
		
	}
	

}
