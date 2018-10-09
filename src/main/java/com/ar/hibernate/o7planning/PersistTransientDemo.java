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

public class PersistTransientDemo {
	public static void main(String[] args) {

		Session session = null;
		Department department = null;
		Employee emp = null;
		try {
			session = CustomSessionFactory.getSessionFactory().openSession();
			session.beginTransaction();

			emp = findEmployee(session, "E7499");

			persist_Transient(session, emp);

			session.getTransaction().commit();

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

	private static Employee findEmployee(Session session, String employeeNo) {
		// TODO Auto-generated method stub
		String queryString = "Select e from " + Employee.class.getName() + " e  where e.empNo = :empNo";
		Employee e;
		Query<Employee> query = session.createQuery(queryString);
		query.setParameter("empNo", employeeNo);
		e = query.getSingleResult();
		return e;
	}

	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private static Timekeeper persist_Transient(Session session, Employee emp) {

		// Note:
		// Configuring of timekeeperId
		// @GeneratedValue(generator = "uuid")
		// @GenericGenerator(name = "uuid", strategy = "uuid2")
		Timekeeper tk1 = new Timekeeper();

		tk1.setEmployee(emp);
		tk1.setInOut(Timekeeper.IN);
		tk1.setDateTime(new Date());

		// Now, 'tk1' is transient object
		System.out.println("- tk1 Persistent? " + session.contains(tk1));

		System.out.println("====== CALL persist(tk).... ===========");

		// Hibernate assign value to Id of 'tk1'
		// No action to DB.
		session.persist(tk1);
		
		System.out.println("- tk1.getTimekeeperId() = " + tk1.getTimekeeperId());

		// Now 'tk1' is Persistent object.
		// But no action with DB.
		// ==> true
		System.out.println("- tk1 Persistent? " + session.contains(tk1));

		System.out.println("- Call flush..");

		// Flush data to DB.
		// Hibernate execute insert statement.
		session.flush();

		String timekeeperId = tk1.getTimekeeperId();
		System.out.println("- timekeeperId = " + timekeeperId);
		System.out.println("- inOut = " + tk1.getInOut());
		System.out.println("- dateTime = " + df.format(tk1.getDateTime()));
		System.out.println();
		return tk1;
	}

}
