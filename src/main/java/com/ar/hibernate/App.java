package com.ar.hibernate;

import java.util.Date;

import org.hibernate.Session;

import com.ar.hibernate.model.User;
import com.ar.hibernate.session.CustomSessionFactory;

/**
 * Hello world!
 *
 */
public class App {

	static User userObj;

	public static void main(String[] args) {
		Session sessionObj = null;
		try {
			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
			sessionObj.beginTransaction();

			for (int i = 401; i <= 500; i++) {
				userObj = new User();
				userObj.setUserid(i);
				userObj.setUsername("Editor " + i);
				userObj.setCreatedBy("Administrator");
				userObj.setCreatedDate(new Date());

				sessionObj.save(userObj);
				if (i % 30 == 0) {
					sessionObj.flush();
					sessionObj.clear();
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
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
}
