package com.ar.hibernate.o7planning;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ar.hibernate.model.Department;
import com.ar.hibernate.model.Employee;
import com.ar.hibernate.model.Timekeeper;
import com.ar.hibernate.session.CustomSessionFactory;

public class HIbernateStateTester {
	public static void main(String[] args) {

		Session session = null;
		Department department = null;
		try {
			session = CustomSessionFactory.getSessionFactory().openSession();
			session.beginTransaction();
			String departmentNo = "D10";

			System.out.println("- Finding Department deptNo = D10...");

			// Persistent object.
			department = findDepartment(session, departmentNo);

			System.out.println("- First change Location");

			// Changing something on Persistent object.
			department.setLocation("Chicago " + System.currentTimeMillis());

			System.out.println("- Location = " + department.getLocation());
			Thread.sleep(2000);
			System.out.println("- Calling flush...");

			// Use session.flush () to actively push the changes to the DB.
			// It works for all changed Persistent objects.
			session.flush();

			System.out.println("- Flush OK");

			System.out.println("- Second change Location");

			// Change something on Persistent object
			department.setLocation("Chicago " + System.currentTimeMillis());

			// Print out location
			System.out.println("- Location = " + department.getLocation());
			Thread.sleep(2000);
			System.out.println("- Calling commit...");

			// Commit
			session.getTransaction().commit();

			System.out.println("- Commit OK");

			// Create the session after it had been closed earlier
			// (Cause by commit or update)
			session = CustomSessionFactory.getSessionFactory().getCurrentSession();
			try {
				session.getTransaction().begin();

				System.out.println("- Finding Department deptNo = D10...");

				// Query lại Department D10.

				department = findDepartment(session, "D10");

				// Print out location
				System.out.println("- D10 Location = " + department.getLocation());

				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			CustomSessionFactory.getSessionFactory().close();
		}
	}

	private static Department findDepartment(Session session, String departmentNo) {
		// TODO Auto-generated method stub
		String queryString = "Select d from " + Department.class.getName() + " d  where d.deptNo = :deptNo";
		Department d;
		Query<Department> query = session.createQuery(queryString);
		query.setParameter("deptNo", departmentNo);
		d = query.getSingleResult();
		return d;
	}

	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
}
