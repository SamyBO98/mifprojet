package fr.univlyon1.mifprojetgp7.mtest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.metier.AccountM;
import fr.univlyon1.mifprojetgp7.metier.CategoryM;
import fr.univlyon1.mifprojetgp7.metier.ContributorM;
import fr.univlyon1.mifprojetgp7.metier.EventM;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Event;

public class EventMTest {
    
    EntityManager em;
    EventM eventM;
    CategoryM categoryM;
    AccountM accountM;
    Account userTest;
    
    public EventMTest() {
	this.em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	this.eventM = new EventM(em);
	this.categoryM = new CategoryM(em);
	this.accountM = new AccountM(em);
	this.userTest = accountM.getAccount("user@test.com");
    }
    
    @Test
    public void createEvent() {
	
	Category c = categoryM.getCategory("Sports");
	Event e = eventM.createEvent("test-event", "test", userTest, c);
	assertEquals(e.getAccount(), userTest);
	assertEquals(e.getCategory(), c);
	assertEquals("test-event", e.getTitle());
    }
    
    @Test
    public void getEventByTitle() {
	List<Event> eventList = eventM.getEvent("test-event");
	for(Event e : eventList) {
	    assertEquals("test-event", e.getTitle());
	}
    }
    
    @Test
    public void getEventByCategory() {
	Category c = categoryM.getCategory("Sports");
	List<Event> eventList = eventM.getEvent(c);
	for(Event e : eventList) {
	    assertEquals(c, e.getCategory());
	}
    }
    
    @Test
    public void getEventByUser() {
	List<Event> eventList = eventM.getEvent(userTest);
	for(Event e : eventList) {
	    assertEquals(userTest, e.getAccount());
	}
    }
    
    @Test
    public void deleteEvent() {
	EventM eventM = new EventM(em);
	List<Event> eventList = eventM.getEvent(userTest);
	ContributorM c = new ContributorM(em);
	for(Event e : eventList) {
	    if(c.getContributors(e).size() != 0) {
		c.deleteContributors(c.getContributors(e));
	    }
	    eventM.deleteEvent(e);
	    assertEquals(null, eventM.getEvent(e.getId()));
	}
	assertEquals(0,eventM.getEvent(userTest).size());
    }
    
    
}
