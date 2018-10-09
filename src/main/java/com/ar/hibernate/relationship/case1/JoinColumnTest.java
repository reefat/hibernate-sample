package com.ar.hibernate.relationship.case1;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ar.hibernate.session.CustomSessionFactory;


public class JoinColumnTest {

	public static void main(String[] args) {
		Session sessionObj = null;
		try {
			System.out.println("--------- started 0----------------");
//			SessionFactory sessionFactory = CustomSessionFactory.buildSessionFactory();
			System.out.println("--------- started 1----------------");
			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
			System.out.println("--------- started ----------------");
			sessionObj.beginTransaction();
			Troop troop1 = new Troop();
			troop1.setName("Troop - 1");

			Soldier jahid1 = new Soldier();
			jahid1.setName("jahid1");

			Soldier nahid1 = new Soldier();
			nahid1.setName("nahid1");

			Soldier kahid1 = new Soldier();
			kahid1.setName("kahid1");

			Set<Soldier> soldierGroup1 = new HashSet<Soldier>();
			jahid1.setTroop(troop1);
			soldierGroup1.add(jahid1);
			soldierGroup1.add(nahid1);
			soldierGroup1.add(kahid1);

			troop1.setSoldiers(soldierGroup1);

			sessionObj.save(troop1);
			
			System.out.println("\n.......Records Saved Successfully To The Database.......\n");
			
			sessionObj.getTransaction().commit();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			System.err.println("--------------------- ERROR -----------------");
			System.err.println(e);
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
