package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Actor;
import model.Movie;

public class MainTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("many-to-many");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();

		try {
			txn.begin();

			Actor a = new Actor();
			a.setName("Marty Bird");
			Actor a1 = new Actor();
			a1.setName("John Snow");
			Actor a2 = new Actor();
			a2.setName("Pablo Escobar");

			Movie m = new Movie();
			m.setName("OZARK");
			Movie m1 = new Movie();
			m1.setName("Game Of Thrones");
			Movie m2 = new Movie();
			m2.setName("Narcos");

			m1.getActors().add(a);
			m1.getActors().add(a1);
			m2.getActors().add(a2);

			em.persist(m1);
			em.persist(m2);

			txn.commit();

		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}
}
