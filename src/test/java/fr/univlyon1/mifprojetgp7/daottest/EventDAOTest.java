package fr.univlyon1.mifprojetgp7.daottest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Test;

import fr.univlyon1.mifprojetgp7.dao.AccountDAO;
import fr.univlyon1.mifprojetgp7.dao.CategoryDAO;
import fr.univlyon1.mifprojetgp7.dao.EventDAO;
import fr.univlyon1.mifprojetgp7.metier.ContributorM;
import fr.univlyon1.mifprojetgp7.metier.EventM;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Event;

public class EventDAOTest {
    
    private EntityManager em;
    private EventDAO dao;
    private CategoryDAO categoryDAO;
    private AccountDAO accountDAO;
    private Account userTest;
    
    public EventDAOTest() {
	em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	dao = new EventDAO(em);
	categoryDAO = new CategoryDAO(em);
	accountDAO = new AccountDAO(em);
	userTest = accountDAO.getAccount("user@test.com");
    }
    
    @Test
    public void createEvent() {
	em.getTransaction().begin();
	Category c = categoryDAO.getCategory("Sports");
	Event e = dao.createEvent("test-event", "test", userTest, c);
	em.getTransaction().commit();
	assertEquals(e.getAccount(), userTest);
	assertEquals(e.getCategory(), c);
	assertEquals("test-event", e.getTitle());
    }
    
    @Test
    public void getEventByTitle() {
	List<Event> eventList = dao.getEvent("test-event");
	for(Event e : eventList) {
	    assertEquals("test-event", e.getTitle());
	}
    }
    
    @Test
    public void getEventByCategory() {
	Category c = categoryDAO.getCategory("Sports");
	List<Event> eventList = dao.getEvent(c);
	for(Event e : eventList) {
	    assertEquals(c, e.getCategory());
	}
    }

    @Test
    public void getEventByUser() {
	List<Event> eventList = dao.getEvent(userTest);
	for(Event e : eventList) {
	    assertEquals(userTest, e.getAccount());
	}
    }

    @Test
    public void deleteEvent() {
	List<Event> eventList = dao.getEvent(userTest);
	ContributorM c = new ContributorM(em);
	for(Event e : eventList) {
	    if(c.getContributors(e).size() != 0) {
		c.deleteContributors(c.getContributors(e));
	    }
	    em.getTransaction().begin();
	    dao.deleteEvent(e);
	    em.getTransaction().commit();
	    assertEquals(null, dao.getEvent(e.getId()));
	}
    }
    
    @After
    public void cleanUp() {
	EventM eventM = new EventM(em);
	List<Event> eventList = eventM.getEvent(userTest);
	ContributorM c = new ContributorM(em);
	for(Event e : eventList) {
	    if(c.getContributors(e).size() != 0) {
		c.deleteContributors(c.getContributors(e));
	    }
	    eventM.deleteEvent(e);
	}
    }
    
}
