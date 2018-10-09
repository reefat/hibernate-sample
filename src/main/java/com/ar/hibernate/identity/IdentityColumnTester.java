package com.ar.hibernate.identity;

import org.hibernate.Session;

import com.ar.hibernate.session.CustomSessionFactory;

public class IdentityColumnTester {

	public static void main(String[] args) {
		Session sessionObj = null;
		try {

			System.out.println("buildSessionFactory sucs ...");
			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
			System.out.println("sessionObj sucs ...");

			sessionObj.beginTransaction();

			Test1 userObj = new Test1();
			userObj.setName("name -01");
			
			sessionObj.save(userObj);
			
			Test1 userObj2 = new Test1();
			userObj2.setName("name -02");
			
			sessionObj.save(userObj2);
			
			System.out.println("----- saved ------");
			sessionObj.getTransaction().commit();
			System.out.println("----- commited ------");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			if (sessionObj != null) {
				sessionObj.close();
				System.err.println("Session Closed ...");
			}

			CustomSessionFactory.getSessionFactory().close();
		}
	}
}
