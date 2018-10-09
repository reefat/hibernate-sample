package com.ar.hibernate.seconddb;

import java.util.List;

import org.hibernate.Session;

import com.ar.hibernate.model.seconddb.Department;
import com.ar.hibernate.model.seconddb.Employee;
import com.ar.hibernate.session.SecondSessionFactory;

public class NamedQueryExample {
	public static void main(String[] args) {

		Session session = null;
		Employee emp = null;
		try {
			session = SecondSessionFactory.getSessionFactory().openSession();
			session.beginTransaction();

			List<Long> totalDept = session.createNamedQuery("get_total_dept", Long.class).getResultList();
			System.out.println("Total Department: " + totalDept.get(0));

			String deptName = session.createNamedQuery("get_dept_name_by_id", String.class).setParameter("id", 2)
					.getSingleResult();
			System.out.println(deptName);

			
			System.out.println("*********************************************");
			List<Department> departments = session.createNamedQuery("get_all_dept", Department.class).getResultList();
			for (Department department : departments) {
				System.out.println("ID : " + department.getId() + " \tNAME : " + department.getName());
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			SecondSessionFactory.getSessionFactory().close();
		}
	}
}
