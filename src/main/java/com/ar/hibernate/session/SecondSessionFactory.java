package com.ar.hibernate.session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SecondSessionFactory {

	public static SessionFactory getSessionFactory() {
		return SessionFactoryCreator.sessionFactoryObj;
	}

	private static class SessionFactoryCreator {

		private static final SessionFactory sessionFactoryObj = buildSessionFactory();

	}

	public static SessionFactory buildSessionFactory() {
		SessionFactory sessionFactory = null;
		try {

			System.err.println("------ scnd sessionFactory start -------------");
			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure("hibernate.cfg.second.xml") // configures settings from hibernate.cfg.xml
		        .build();

			System.err.println("------ scnd sessionFactory mid -------------");
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			System.err.println("------ scnd sessionFactory end -------------");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sessionFactory;
	}
}
