package com.ar.hibernate.queryresult;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ar.hibernate.model.User;
import com.ar.hibernate.session.CustomSessionFactory;

public class SetLimit {

	public static void main(String[] args) {
		Session sessionObj = null;
		try {
			System.out.println("buildSessionFactory sucs ...");
			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
			System.out.println("sessionObj sucs ...");

			fetchRecordUsingLimit(sessionObj);
			
			checkTotalCount(sessionObj);

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

	private static void fetchRecordUsingLimit(Session sessionObj) {
		// TODO Auto-generated method stub
		try {
//			sessionObj.beginTransaction();
			Query<User> query = sessionObj.createQuery("from User");
			query.setFirstResult(10);
			query.setMaxResults(10);
			List<User> employees = (List<User>) query.list();
			if (employees != null) {
				System.out.println("Total Results:" + employees.size());
				for (User employee : employees) {
					System.out.println(employee.getUserid() + " - " + employee.getUsername());
				}
			} else {
				System.err.println("employees NULL");
			}

//			sessionObj.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	private static void checkTotalCount(Session sessionObj) {
		// TODO Auto-generated method stub
		try {
//			sessionObj.beginTransaction();
			Query<User> query = sessionObj.createQuery("select count (u.userid) from User u");
			System.out.println("Count  => "+query.uniqueResult());
			
			Query<User> query2 = sessionObj.createQuery("select count (u.userid) from User u");
			System.out.println("## Count  => "+query2.uniqueResult());
			
//			sessionObj.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
