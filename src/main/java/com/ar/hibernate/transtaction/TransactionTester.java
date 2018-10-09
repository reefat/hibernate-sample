package com.ar.hibernate.transtaction;

import java.util.Date;

import org.hibernate.Session;

import com.ar.hibernate.identity.Test1;
import com.ar.hibernate.model.User;
import com.ar.hibernate.session.CustomSessionFactory;

public class TransactionTester {

	static User userObj;

	public static void main(String[] args) {
		Session sessionObj = null;
		try {
			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
			sessionObj.beginTransaction();

			userObj = new User();
			userObj.setUserid(1004);
			userObj.setUsername("Editor 1004");
			userObj.setCreatedBy("Administrator");
			userObj.setCreatedDate(new Date());

			sessionObj.save(userObj);
			System.out.println("\n.......<1> Records Saved Successfully To The Database.......\n");
			String a = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			Test1 test1 = new Test1();
			test1.setId(userObj.getUserid() * Integer.MAX_VALUE);
			test1.setName(a + a + a + a + a + a + a + a + a + a + a + a + a + a + a + a + a + a + a);
			sessionObj.save(test1);
			System.out.println("\n.......<2> Records Saved Successfully To The Database.......\n");
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
