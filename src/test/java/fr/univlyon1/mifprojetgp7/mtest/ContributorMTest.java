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
import fr.univlyon1.mifprojetgp7.model.Contributor;
import fr.univlyon1.mifprojetgp7.model.Event;

public class ContributorMTest {
    
    EntityManager em;
    ContributorM contributorM;
    Account userTest;
    EventM eventM;
    Event eventTest;
    
    public ContributorMTest() {
	em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	contributorM = new ContributorM(em);
	userTest = new AccountM(em).getAccount("user@test.com");
	Category categoryTest = new CategoryM(em).getCategory("Sports");
	eventM = new EventM(em);
	eventTest = eventM.createEvent("test-event", "test", userTest, categoryTest);
    }
    
    @Test
    public void getContributor() {
	Contributor c = contributorM.getContributor(eventTest, userTest);
	assertEquals(null,c);
    }
    
    @Test
    public void getContributorsByUser() {
	List<Contributor> contributors = contributorM.getContributors(userTest);
	if(contributors.size() > 0) {
	    contributorM.deleteContributors(contributors);
	}
	contributors = contributorM.getContributors(userTest);
	assertEquals(0,contributors.size());
    }
    
    @Test
    public void getContributorsByEvent() {
	List<Contributor> contributors = contributorM.getContributors(eventTest);
	assertEquals(0,contributors.size());
    }
     
    @Test
    public void updateContributorToEvent() {
	contributorM.updateContributorToEvent(eventTest, userTest);
	Contributor c = contributorM.getContributor(eventTest,userTest);
	assertEquals(userTest,c.getUser());
	assertEquals(eventTest, c.getEvent());
	
	List<Contributor> contributors = contributorM.getContributors(userTest);
	for(Contributor contributor : contributors) {
	    assertEquals(userTest,contributor.getUser());
	}
	contributors = contributorM.getContributors(eventTest);
	for(Contributor contributor : contributors) {
	    assertEquals(eventTest,contributor.getEvent());
	}
    }
    
    @Test
    public void deleteContributors() {
	List<Contributor> contributors = contributorM.getContributors(userTest);
	contributorM.deleteContributors(contributors);
	contributors = contributorM.getContributors(userTest);
	assertEquals(0,contributors.size());
	eventM.deleteEvent(eventTest);
    }

}
