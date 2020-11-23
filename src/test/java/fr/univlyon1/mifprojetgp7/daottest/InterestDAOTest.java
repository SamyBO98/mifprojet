package fr.univlyon1.mifprojetgp7.daottest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.dao.InterestDAO;
import fr.univlyon1.mifprojetgp7.metier.AccountM;
import fr.univlyon1.mifprojetgp7.metier.CategoryM;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Interest;

public class InterestDAOTest {

    private EntityManager em;
    private InterestDAO dao;
    private Account userTest;
    private Category category;
    
    public InterestDAOTest() {
	em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	dao = new InterestDAO(em);
	userTest = new AccountM(em).getAccount("user@test.com");
	category = new CategoryM(em).getCategory("Sports");
    }

    @Test
    public void getInterest() {
	Interest interest = dao.getInterest(category, userTest);
	if(interest != null) {
	    em.getTransaction().begin();
	    dao.deleteInterest(interest);
	    em.getTransaction().commit();
	}
	assertEquals(null,interest);
    }

    @Test
    public void getInterestsByUser() {
	List<Interest> interests = dao.getInterests(userTest);
	if(interests.size() > 0) {
	    em.getTransaction().begin();
	    for(Interest i : interests) {
		dao.deleteInterest(i);
	    }
	    em.getTransaction().commit();
	}
	interests = dao.getInterests(userTest);
	assertEquals(0,interests.size());
    }

    @Test
    public void addInterest() {
	em.getTransaction().begin();
	Interest interest = dao.getInterest(category, userTest);
	if(interest == null) {
	    interest = dao.addInterest(category, userTest);
	}
	em.getTransaction().commit();
	assertNotEquals(null, interest);
    }

    @Test
    public void getInterestsByCategory() {
	List<Interest> interests = dao.getInterests(category);
	assertEquals(true,interests.contains(dao.getInterest(category, userTest)));
    }

    @Test
    public void deleteInterest() {
	Interest interest = dao.getInterest(category, userTest);
	if(interest == null) {
	    em.getTransaction().begin();
	    interest = dao.addInterest(category, userTest);
	    em.getTransaction().commit();
	}
	em.getTransaction().begin();
	dao.deleteInterest(interest);
	em.getTransaction().commit();
	assertEquals(null, dao.getInterest(category, userTest));
    }
}
