package com.ar.hibernate.o7planning;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ar.hibernate.dto.ShortEmpInfo;
import com.ar.hibernate.model.Employee;
import com.ar.hibernate.model.User;
import com.ar.hibernate.session.CustomSessionFactory;

/**
 * Hello world!
 *
 */
public class O7Planner {

	static User userObj;

	public static void main(String[] args) {
		// listEmployees();

		// listEmployeesFilterByDept();

		// fetchSomeColumnFromEmployees();

		// fetchShortInfoFromEmployees();

		// getMaxEmpId();

		getMaxEmpIdDetails();

		CustomSessionFactory.getSessionFactory().close();
	}

	private static void listEmployees() {
		// TODO Auto-generated method stub

		Session session = null;
		try {
			session = CustomSessionFactory.getSessionFactory().openSession();
			session.beginTransaction();

			// Create an HQL statement, query the object.
			// Equivalent to the SQL statement:
			// Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
			String sql = "Select e from " + Employee.class.getName() + " e  order by e.empName, e.empNo ";

			// Create Query object.
			Query<Employee> query = session.createQuery(sql);

			// Execute query.
			List<Employee> employees = query.getResultList();

			for (Employee emp : employees) {
				System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
			}
			// System.err.println(employees);

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				System.err.println("Session Closed ...");
			}

			CustomSessionFactory.getSessionFactory().close();
		}
	}

	private static void listEmployeesFilterByDept() {
		// TODO Auto-generated method stub

		Session session = null;
		try {
			session = CustomSessionFactory.getSessionFactory().openSession();
			session.beginTransaction();

			// Create an HQL statement, query the object.
			// Equivalent to the SQL statement:
			// Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
			String sql = "Select e from " + Employee.class.getName()
					+ " e where e.department.deptNo=:deptNo order by e.empName, e.empNo ";

			// Create Query object.
			Query<Employee> query = session.createQuery(sql);

			query.setParameter("deptNo", "D10");

			// Execute query.
			List<Employee> employees = query.getResultList();

			for (Employee emp : employees) {
				System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
			}
			// System.err.println(employees);

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				System.err.println("Session Closed ...");
			}
		}

	}

	private static void fetchSomeColumnFromEmployees() {
		// TODO Auto-generated method stub

		Session session = null;
		try {
			session = CustomSessionFactory.getSessionFactory().openSession();
			session.beginTransaction();

			// Create an HQL statement, query the object.
			// Equivalent to the SQL statement:
			// Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
			String sql = "Select e.empId, e.empNo, e.empName from " + Employee.class.getName()
					+ " e  order by e.empName, e.empNo ";

			// java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to
			// com.ar.hibernate.model.Employee
			// Query<Employee> query = session.createQuery(sql);

			Query<Object[]> query = session.createQuery(sql);

			// Execute query.
			List<Object[]> employees = query.getResultList();

			for (Object[] emp : employees) {
				System.out.println("Emp: " + emp[0] + " : " + emp[1] + " : " + emp[2]);
			}
			// System.err.println(employees);

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				System.err.println("Session Closed ...");
			}

		}
	}

	private static void fetchShortInfoFromEmployees() {
		// TODO Auto-generated method stub

		Session session = null;
		try {
			session = CustomSessionFactory.getSessionFactory().openSession();
			session.beginTransaction();

			// Create an HQL statement, query the object.
			// Equivalent to the SQL statement:
			// Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
			String sql = "Select new " + ShortEmpInfo.class.getName() + "(e.empId, e.empNo, e.empName) from "
					+ Employee.class.getName() + " e  order by e.empName, e.empNo ";

			// java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to
			// com.ar.hibernate.model.Employee
			// Query<Employee> query = session.createQuery(sql);

			Query<ShortEmpInfo> query = session.createQuery(sql);

			// Execute query.
			List<ShortEmpInfo> employees = query.getResultList();

			for (ShortEmpInfo emp : employees) {
				System.out.println("Emp: " + emp.getEmpId() + " : " + emp.getEmpNo() + " : " + emp.getEmpName());
			}
			// System.err.println(employees);

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				System.err.println("Session Closed ...");
			}

		}
	}

	private static void getMaxEmpId() {
		// TODO Auto-generated method stub

		Session session = null;
		try {
			session = CustomSessionFactory.getSessionFactory().openSession();
			session.beginTransaction();

			// Create an HQL statement, query the object.
			// Equivalent to the SQL statement:
			// Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
			String sql = "Select max(e.empId) from " + Employee.class.getName() + " e";

			// Create Query object.
			Query<Number> query = session.createQuery(sql);

			// Execute query.
			Number maxId = query.getSingleResult();

			System.err.println("maxId -> " + maxId);

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				System.err.println("Session Closed ...");
			}

			CustomSessionFactory.getSessionFactory().close();
		}
	}

	private static void getMaxEmpIdDetails() {
		// TODO Auto-generated method stub

		Session session = null;
		try {
			session = CustomSessionFactory.getSessionFactory().openSession();
			session.beginTransaction();

			// Create an HQL statement, query the object.
			// Equivalent to the SQL statement:
			// Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
			String sql_max = "Select max(ex.empId) from " + Employee.class.getName() + " ex";
			String sql = "select e from " + Employee.class.getName() + " where e.empId in (" + sql_max + ")";

			// Create Query object.
			Query<Employee> query = session.createQuery(sql);

			// Execute query.
			Employee e = query.getSingleResult();

			System.err.println("maxId -> " + e.getEmpName()+" Id -> " + e.getEmpId());

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				System.err.println("Session Closed ...");
			}

			CustomSessionFactory.getSessionFactory().close();
		}
	}

}
