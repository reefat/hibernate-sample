package com.ar.hibernate.quizgiri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.ar.hibernate.model.quizgiri.Category;
import com.ar.hibernate.model.quizgiri.Game;
import com.ar.hibernate.model.quizgiri.Option;
import com.ar.hibernate.model.quizgiri.Question;
import com.ar.hibernate.model.quizgiri.SpecialCategory;
import com.ar.hibernate.model.quizgiri.Topic;
import com.ar.hibernate.model.quizgiri.TopicFollow;
import com.ar.hibernate.model.seconddb.Department;
import com.ar.hibernate.session.CustomSessionFactory;

public class TestQuizgiriGame {

	public static void main(String[] args) {
		Session sessionObj = null;
		int ID = 0, topicID = 0;

		ArrayList<Integer> topicListToUpdate = new ArrayList<>();
		try {
			System.out.println("--------- started 1----------------");
			sessionObj = CustomSessionFactory.getSessionFactory().openSession();
			System.out.println("--------- started ----------------");
			sessionObj.beginTransaction();

			Category category = new Category();
			category.setName("cat - 01");

			Topic topic = new Topic();
			topic.setName("Topic - 01");
			topic.setUrl("/topics/topic1.jpg");

			Option option1a = new Option();
			option1a.setAnswer("Option A");
			Option option1b = new Option();
			option1b.setAnswer("Option B");
			Option option1c = new Option();
			option1c.setAnswer("Option C");
			Option option1d = new Option();
			option1d.setAnswer("Option D");

			Question question1 = new Question();
			question1.setBangla("Question in Bangla ..");
			question1.setEnglish("Question in English ..");

			option1a.setQuestion(question1);
			option1b.setQuestion(question1);
			option1c.setQuestion(question1);
			option1d.setQuestion(question1);

			Set<Option> optionSet1 = new HashSet<>(Arrays.asList(option1a, option1b, option1c, option1d));
			question1.setOptions(optionSet1);

			sessionObj.save(option1a);
			sessionObj.save(option1b);
			sessionObj.save(option1c);
			sessionObj.save(option1d);

			sessionObj.save(topic);

			question1.setTopicId(topic.getTopicId());
			question1.setCorrectAnswerId(option1c.getId());

			sessionObj.save(question1);
			long ID_ = question1.getQuestionId();

			Game game1 = new Game();
			game1.setPlayerId(1001L);
			game1.setStartTime(System.currentTimeMillis());
			game1.setExperienceGathered(99);
			sessionObj.save(game1);
			System.err.println("------------");
			game1.setExperienceGathered(200);

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}

			sessionObj.getTransaction().commit();
			getQuestion(sessionObj, ID_);
			
			getAllQuestionsOfATopic(sessionObj, topic.getTopicId());

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

	private static void getQuestion(Session sessionObj, long ID) {
		// TODO Auto-generated method stub

		sessionObj.close();
		sessionObj = CustomSessionFactory.getSessionFactory().openSession();
		sessionObj.beginTransaction();
		Question fetchedQ = sessionObj.get(Question.class, ID);
		System.out.println(fetchedQ.getBangla());
		System.out.println(fetchedQ.getEnglish());
		System.out.println(fetchedQ.getQuestionId());
		System.err.println(fetchedQ.getOptions().size());
		System.err.println(fetchedQ.getCorrectAnswerId());
		
		sessionObj.getTransaction().commit();
	}
	
	private static void getAllQuestionsOfATopic(Session sessionObj, int topicId) {
		sessionObj.close();
		sessionObj = CustomSessionFactory.getSessionFactory().openSession();
		sessionObj.beginTransaction();
				
		System.out.println("*********************************************");
		List<Question> questions = sessionObj.createNamedQuery("get_all_questions_under_a_topic", Question.class).setParameter("topicId", topicId).getResultList();
		for (Question question : questions) {
//			System.out.println(question);
		}
		System.out.println("done *************");
		sessionObj.getTransaction().commit();
	}
}
