package com.ar.hibernate.criteriaapi;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ar.hibernate.model.User;
import com.ar.hibernate.session.CustomSessionFactory;

public class Pagination {

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
			// sessionObj.beginTransaction();
			CriteriaBuilder cb = sessionObj.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);

			Root<User> from = cq.from(User.class);
			CriteriaQuery<User> select = cq.select(from);

			TypedQuery<User> typedQuery = sessionObj.createQuery(select);
			typedQuery.setFirstResult(0);
			typedQuery.setMaxResults(20);

			List<User> employees = typedQuery.getResultList();

			if (employees != null) {
				System.out.println("Total Results:" + employees.size());
				for (User employee : employees) {
					System.out.println(employee.getUserid() + " - " + employee.getUsername());
				}
			} else {
				System.err.println("employees NULL");
			}

			// sessionObj.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	private static void checkTotalCount(Session sessionObj) {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder cb = sessionObj.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);

			cq.select(cb.count(cq.from(User.class)));
			long count = sessionObj.createQuery(cq).getSingleResult();
			System.err.println("Total Count -> " + count);
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
