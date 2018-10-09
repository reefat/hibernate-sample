package com.ar.hibernate.configfile;

import org.hibernate.Session;

import com.ar.hibernate.model.TestConfigEmployee;
import com.ar.hibernate.session.CustomSessionFactory;

public class ConfigFileTester {
	public static void main(String[] args) {
		TestConfigEmployee emp = null;
		Session sessionObj = null;
		try {
			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
			sessionObj.beginTransaction();

			for (int i = 11; i <= 20; i++) {
				emp = new TestConfigEmployee("First-Name : ", "last-Name : ", 5000 * i);
				sessionObj.save(emp);
			}
			System.out.println("\n.......Records Saved Successfully To The Database.......\n");
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			sessionObj.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
				System.err.println("Session Closed ...");
			}

			CustomSessionFactory.getSessionFactory().close();
		}

	}

	// public static void main(String[] args) {
	// try {
	// List employees = list();
	// System.err.println(employees.size());
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// HibernateUtil.getSessionFactory().close();
	// }
	//
	// private static List list() {
	// SessionFactory sf = HibernateUtil.getSessionFactory();
	// Session session = sf.openSession();
	//
	// List employees = session.createQuery("from Employee").list();
	// session.close();
	// return employees;
	// }
}
