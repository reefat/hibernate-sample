package com.ar.hibernate.seconddb;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import com.ar.hibernate.model.seconddb.*;
import com.ar.hibernate.session.SecondSessionFactory;

public class NativeQueryExample {
	public static void main(String[] args) {

		Session session = null;
		Employee emp = null;
		try {
			session = SecondSessionFactory.getSessionFactory().openSession();
			session.beginTransaction();

			// Native query selecting all columns
			List<Object[]> departments = session.createNativeQuery("SELECT * FROM department").list();
			for (Object[] objects : departments) {
				Integer id = (Integer) objects[0];
				String name = (String) objects[1];
				System.out.println("Department[" + id + "," + name + "]");
			}

			// Native query with custom column selection (scaler query)
			System.out.println("--------------------------------------------------------------");
			List<Object[]> employees = session.createNativeQuery("SELECT * FROM employee")
					.addScalar("emp_id", IntegerType.INSTANCE).addScalar("name", StringType.INSTANCE)
					.addScalar("designation", StringType.INSTANCE).list();
			for (Object[] objects : employees) {
				Integer id = (Integer) objects[0];
				String name = (String) objects[1];
				String designation = (String) objects[2];
				System.out.println("Employee[" + id + "," + name + "," + designation + "]");
			}

			// Native query with JOIN
			System.out.println("--------------------------------------------------------------");
			List<Object[]> empDepts = session
					.createNativeQuery("" + "select e.name as emp_name, e.designation, d.name as dep_name "
							+ "from employee e inner join department d " + "on e.dpt_id=d.dpt_id")
					.list();
			for (Object[] objects : empDepts) {
				String employee = (String) objects[0];
				String designation = (String) objects[1];
				String department = (String) objects[2];
				System.out.println("Employee[" + employee + "," + designation + "," + department + "]");
			}

			// Mapping Native query to Entity
			List<Employee> employees_ = session.createNativeQuery("SELECT * FROM employee", Employee.class).list();
			for (Employee employee : employees_) {
				Integer id = employee.getId();
				String name = employee.getName();
				String designation = employee.getDesignation();
				System.out.println("Employee[" + id + "," + name + "," + designation + "]");
			}

			System.out.println("***********************************************");

			// Mapping Native JOIN query to Entities
			System.out.println("--------------------------------------------------------------");
			List<Object[]> departments_ = session
					.createNativeQuery("select e.*, d.* from department d inner join  employee e "
							+ "on d.dpt_id=e.dpt_id")
					.addEntity("d", Department.class).addJoin("e", "d.employees").list();

			for (Object[] object : departments_) {
				Department department = (Department) object[0];
				System.out.println("Department - " + department.getName());
				for (Employee employee : department.getEmployees()) {
					System.out.println(
							"\t Employee - " + employee.getName() + " \t Designation - " + employee.getDesignation());
				}
			}
			

			System.out.println("***********************************************");
	         //Binding parameter for native query
	         employees = session.createNativeQuery("SELECT * FROM employee where dpt_id=?")
	               .addScalar("emp_id", IntegerType.INSTANCE)
	               .addScalar( "name", StringType.INSTANCE )
	               .addScalar( "designation", StringType.INSTANCE )
	               .setParameter(1, 2) //positional parameter binding 
	               .list();
	         for (Object[] objects : employees) {
	            Integer id=(Integer)objects[0];
	            String name=(String)objects[1];
	            String designation=(String)objects[2];
	            System.out.println("Employee["+id+","+name+","+designation+"]");
	         }
	         

				System.out.println("***********************************************");

	         //Binding parameter for native query
	         employees = session.createNativeQuery("SELECT * FROM employee where dpt_id=?")
	               .addScalar("emp_id", IntegerType.INSTANCE)
	               .addScalar( "name", StringType.INSTANCE )
	               .addScalar( "designation", StringType.INSTANCE )
	               .setParameter(1, 2) //positional parameter binding 
	               .list();
	         for (Object[] objects : employees) {
	            Integer id=(Integer)objects[0];
	            String name=(String)objects[1];
	            String designation=(String)objects[2];
	            System.out.println("Employee["+id+","+name+","+designation+"]");
	         }


	         //Mapping Native query with Entity
	         System.out.println("--------------------------------------------------------------");
	         List<Department>  _departments = session.createNativeQuery("SELECT * FROM department where name like :deptName")
	               .addEntity(Department.class)
	               .setParameter("deptName", "H%") //named parameter binding 
	               .list();
	         for (Department department : _departments) {
	            System.out.println("Department["+department.getId()+","+department.getName()+"]");
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
