package com.ar.hibernate.session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class CustomSessionFactory {

	public static SessionFactory getSessionFactory() {
		return SessionFactoryCreator.sessionFactoryObj;
	}

	private static class SessionFactoryCreator {

		private static final SessionFactory sessionFactoryObj = buildSessionFactory();

	}

	public static SessionFactory buildSessionFactory() {
		SessionFactory sessionFactory  = null;
		try {

			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure() // configures settings from hibernate.cfg.xml
		        .build();
			// Creating Configuration Instance & Passing Hibernate Configuration File
			/*Configuration configObj = new Configuration();
			configObj.configure("hibernate.cfg.xml");
			configObj.addAnnotatedClass(User.class);
			configObj.addAnnotatedClass(Test1.class);
			configObj.addAnnotatedClass(Troop.class);
			configObj.addAnnotatedClass(Soldier.class);

			// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
			ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
					.applySettings(configObj.getProperties()).build();

			// Creating Hibernate SessionFactory Instance
			SessionFactory sessionFactory = configObj.buildSessionFactory(serviceRegistryObj);
			
			*/
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			System.err.println("------ sessionFactory end -------------");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sessionFactory;
	}

	// public static SessionFactory buildSessionFactory() {
	// System.err.println("---------- SessionFactory Built -------------");
	// // Creating Configuration Instance & Passing Hibernate Configuration File
	// Configuration configObj = new Configuration();
	// configObj.configure("hibernate.cfg.xml");
	// configObj.addAnnotatedClass(User.class);
	// configObj.addAnnotatedClass(Test1.class);
	// configObj.addAnnotatedClass(Troop.class);
	// configObj.addAnnotatedClass(Soldier.class);
	//
	// // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
	// ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
	// .applySettings(configObj.getProperties()).build();
	//
	// // Creating Hibernate SessionFactory Instance
	// SessionFactory sessionFactory =
	// configObj.buildSessionFactory(serviceRegistryObj);
	// System.err.println("------ sessionFactory end -------------");
	// return sessionFactory;
	// }
}
