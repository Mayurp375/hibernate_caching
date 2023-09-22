package org.example.connection;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateConf {
	private static SessionFactory factory;
	
	private HibernateConf() {}
	
	public static SessionFactory getFactory() {
		if(factory == null) {
			Configuration configuration = new Configuration();
			
			Properties settings = new Properties();
			settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			settings.put(Environment.URL, "jdbc:mysql://localhost:3306/employee");
			settings.put(Environment.USER, "root");
			settings.put(Environment.PASS, "root");
			
			settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
			settings.put(Environment.SHOW_SQL, "false");
			settings.put(Environment.HBM2DDL_AUTO, "update");
			
			configuration.setProperties(settings);
			configuration.addAnnotatedClass(Student.class);
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.build();
			
			factory = configuration.buildSessionFactory(serviceRegistry);
		}
		
		return factory;
	}
}