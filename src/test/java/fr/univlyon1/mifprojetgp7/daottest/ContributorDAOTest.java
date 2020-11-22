package fr.univlyon1.mifprojetgp7.daottest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.dao.AccountDAO;
import fr.univlyon1.mifprojetgp7.dao.CategoryDAO;
import fr.univlyon1.mifprojetgp7.dao.ContributorDAO;
import fr.univlyon1.mifprojetgp7.dao.EventDAO;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Contributor;
import fr.univlyon1.mifprojetgp7.model.Event;

public class ContributorDAOTest {
    
    private EntityManager em;
    private ContributorDAO dao;
    private Account userTest;
    private Event eventTest;
    private EventDAO eventDAO;
    
    public ContributorDAOTest() {
	em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	dao = new ContributorDAO(em);
	userTest = new AccountDAO(em).getAccount("user@test.com");
	eventDAO = new EventDAO(em);
	em.getTransaction().begin();
	eventTest = eventDAO.createEvent("test-event", "test", userTest, new CategoryDAO(em).getCategory("Sports"));
	em.getTransaction().commit();
    }

    @Test
    public void getContributor() {
	Contributor contributor = dao.getContributor(eventTest, userTest);
	assertEquals(null, contributor);
    }

    @Test
    public void getContributorsByUser() {
	List<Contributor> contributors = dao.getContributors(userTest);
	assertEquals(0,contributors.size());
    }

    @Test
    public void getContributorsByEvent() {
	List<Contributor> contributors = dao.getContributors(eventTest);
	assertEquals(0,contributors.size());
    }

    @Test
    public void addContributor() {
	em.getTransaction().begin();
	Contributor contributor = dao.addContributor(eventTest, userTest);
	em.getTransaction().commit();
	assertNotEquals(null, contributor);
    }

    @Test
    public void deleteContributors() {
	em.getTransaction().begin();
	Contributor contributor = dao.getContributor(eventTest, userTest);
	if(contributor == null) {
	    contributor = dao.addContributor(eventTest, userTest);
	}
	dao.deleteContributor(contributor);
	em.getTransaction().commit();
	assertEquals(null, dao.getContributor(eventTest, userTest));
	em.getTransaction().begin();
	eventDAO.deleteEvent(eventTest);
	em.getTransaction().commit();
    }
}
