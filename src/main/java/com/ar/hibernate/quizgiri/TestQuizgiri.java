package com.ar.hibernate.quizgiri;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.ar.hibernate.model.quizgiri.Option;
import com.ar.hibernate.model.quizgiri.Question;
import com.ar.hibernate.model.quizgiri.SpecialCategory;
import com.ar.hibernate.model.quizgiri.Topic;
import com.ar.hibernate.model.quizgiri.TopicFollow;
import com.ar.hibernate.session.CustomSessionFactory;

public class TestQuizgiri {

	public static void main(String[] args) {
		Session sessionObj = null;
		int ID = 0, topicID = 0;

		ArrayList<Integer> topicListToUpdate = new ArrayList<>();
		try {
			System.out.println("--------- started 1----------------");
			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
			System.out.println("--------- started ----------------");
			sessionObj.beginTransaction();
			SpecialCategory sc1 = new SpecialCategory();
			sc1.setName("Tournament");

			SpecialCategory sc2 = new SpecialCategory();
			sc2.setName("New & Updated");

			SpecialCategory sc3 = new SpecialCategory();
			sc3.setName("Editor's choice");

			Topic t1 = new Topic();
			t1.setName("Bangla Movie");
			t1.setUrl("topic/1.jpg");
			// t1.getSpecialCategories().add(sc2);

			Topic t2 = new Topic();
			t2.setName("Hindi Movie");
			t2.setUrl("topic/2.jpg");
			// t2.getSpecialCategories().add(sc2);

			Topic t3 = new Topic();
			t3.setName("English Movie");
			t3.setUrl("topic/3.jpg");
			// t3.getSpecialCategories().add(sc2);

			Topic t4 = new Topic();
			t4.setName("Tamil Movie");
			t4.setUrl("topic/4.jpg");

			Topic t5 = new Topic();
			t5.setName("Arabian Movie");
			t5.setUrl("topic/5.jpg");

			sc2.getTopics().add(t1);
			sc2.getTopics().add(t2);
			sc2.getTopics().add(t3);

			sessionObj.save(t1);
			sessionObj.save(t2);
			sessionObj.save(t3);
			sessionObj.save(t4);
			sessionObj.save(t5);
			topicID = t2.getTopicId();
			topicListToUpdate.add(t2.getTopicId());
			topicListToUpdate.add(t5.getTopicId());

			sessionObj.save(sc1);
			sessionObj.save(sc2);
			ID = sc2.getId();

			System.out.println("\n.......Records Saved Successfully To The Database.......\n");

			sessionObj.getTransaction().commit();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}

			/*
			 * System.out.println("ID -> " + ID); SpecialCategory loaded = (SpecialCategory)
			 * sessionObj.get(SpecialCategory.class, ID);
			 * System.out.println("loaded has been loaded ..."); try { Thread.sleep(1000); }
			 * catch (Exception e) { // TODO: handle exception } if (loaded != null) {
			 * System.out.println(loaded.getTopics().size());
			 * loaded.getTopics().stream().forEach(topic ->
			 * System.err.println("## NAME : "+topic.getName())); } else {
			 * System.out.println("loaded is null ...."); }
			 * 
			 * // Topic loadedTopic = (Topic) sessionObj.get(Topic.class, topicID); //
			 * loadedTopic System.err.println("3---------------------------");
			 * 
			 * 
			 * // List<Topic> filteredList = loaded.getTopics().stream() // .filter(i ->
			 * topicListToUpdate.contains(i.getTopicId())).collect(Collectors.toList());
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * String sql = "Select t from " + Topic.class.getName() + " t";
			 * 
			 * // Create Query object. Query<Topic> query = sessionObj.createQuery(sql);
			 * 
			 * // Execute query. List<Topic> allTopics = query.getResultList();
			 * 
			 * 
			 * List<Topic> filteredList = allTopics.stream() .filter(i ->
			 * topicListToUpdate.contains(i.getTopicId())).collect(Collectors.toList());
			 * 
			 * 
			 * System.err.println("2---------------------------"); loaded.setTopics(new
			 * HashSet<>(filteredList)); loaded.getTopics().stream().forEach(topic ->
			 * System.err.println("## NAME : "+topic.getName())); sessionObj.merge(loaded);
			 * 
			 * 
			 * Topic t6 = new Topic(); t6.setName("Ghanaian Movie");
			 * t6.setUrl("topic/6.jpg"); // sessionObj.save(t) loaded.getTopics().add(t6);
			 * 
			 * 
			 */
			sessionObj.close();
			
			sessionObj = addQuestionAndoption(sessionObj);

			sessionObj.close();
			
			fetchQuestionAndOption(sessionObj);

//			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
//
//			sessionObj.beginTransaction();

			System.err.println("1---------------------------");
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

	private static Session fetchQuestionAndOption(Session sessionObj) {
		// TODO Auto-generated method stub

		sessionObj = CustomSessionFactory.getSessionFactory().openSession();

		
		sessionObj.beginTransaction();
		

		Question q = sessionObj.get(Question.class, 8L);
		System.err.println("q fetched ...............");
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		q.getOptions();
		System.err.println("options fetched ..............."+q.getOptions().size());
		
		TopicFollow topicFollow = new TopicFollow();
		topicFollow.setFollowerId(1);
		topicFollow.setTopicId(122);
		sessionObj.save(topicFollow);
		
		sessionObj.getTransaction().commit();
		return sessionObj;
	}

	private static Session addQuestionAndoption(Session sessionObj) {
		// TODO Auto-generated method stub

		sessionObj = CustomSessionFactory.getSessionFactory().openSession();

		sessionObj.beginTransaction();
		
		Question q1 = new Question();
		q1.setBangla("Q1 in Bangla");
		q1.setEnglish("Q1 in English");

		Question q2 = new Question();
		q2.setBangla("Q2 in Bangla");
		q2.setEnglish("Q2 in English");

		Option o1 = new Option("Ans 1").setQuestion(q1);
		Option o2 = new Option("Ans 2").setQuestion(q1);
		Option o3 = new Option("Ans 3").setQuestion(q1);
		Option o4 = new Option("Ans 4").setQuestion(q1);
		Option o5 = new Option("Ans 5").setQuestion(q2);
		Option o6 = new Option("Ans 6").setQuestion(q2);
		Option o7 = new Option("Ans 7").setQuestion(q2);
		Option o8 = new Option("Ans 8").setQuestion(q2);
		Set<Option> set1 = new HashSet<>();
		set1.add(o1);
		set1.add(o2);
		set1.add(o3);
		set1.add(o4);

		q1.setOptions(set1);

		Set<Option> set2 = new HashSet<>();
		set2.add(o5);
		set2.add(o6);
		set2.add(o7);
		set2.add(o8);

		q2.setOptions(set2);

		sessionObj.save(q1);
		sessionObj.save(q2);

		sessionObj.getTransaction().commit();
		return sessionObj;
	}
}
