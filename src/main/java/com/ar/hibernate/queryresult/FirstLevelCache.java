package com.ar.hibernate.queryresult;

import java.util.Date;

import org.hibernate.Session;

import com.ar.hibernate.model.User;
import com.ar.hibernate.session.CustomSessionFactory;

/**
 * Hello world!
 *
 */
public class FirstLevelCache {

	static User userObj;

	public static void main(String[] args) {
		Session sessionObj = null;
		try {
			int ID = 500;
			System.out.println("buildSessionFactory sucs ...");
			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
			System.out.println("sessionObj sucs ...");

			sessionObj.beginTransaction();

				userObj = new User();
				userObj.setUserid(500);
				userObj.setUsername("Editor 500");
				userObj.setCreatedBy("Administrator");
				userObj.setCreatedDate(new Date());

				sessionObj.save(userObj);
			System.out.println("\n.......Records Saved Successfully To The Database.......\n");			
			sessionObj.getTransaction().commit();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			User loaded = (User) sessionObj.get(User.class, ID);
			System.err.println("loaded user name : " + loaded.getUsername());
			
			sessionObj.close();
			

			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
			loaded = (User) sessionObj.get(User.class, ID);
			System.err.println("## 2 loaded user name : " + loaded.getUsername());try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			loaded = (User) sessionObj.get(User.class, ID);
			System.err.println("## 3 loaded user name : " + loaded.getUsername());try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
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
