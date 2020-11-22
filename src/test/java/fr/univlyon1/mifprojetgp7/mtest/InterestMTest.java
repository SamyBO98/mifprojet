package fr.univlyon1.mifprojetgp7.mtest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.metier.AccountM;
import fr.univlyon1.mifprojetgp7.metier.CategoryM;
import fr.univlyon1.mifprojetgp7.metier.InterestM;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Interest;

public class InterestMTest {
    
    EntityManager em;
    InterestM interestM;
    Account userTest;
    Category category;
    
    public InterestMTest() {
	em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	interestM = new InterestM(em);
	userTest = new AccountM(em).getAccount("user@test.com");
	category = new CategoryM(em).getCategory("Sport");
    }
    
    @Test
    public void getInterestsByUser() {
	List<Interest> interests = interestM.getInterests(userTest);
	assertEquals(0,interests.size());
    }
    
    @Test
    public void getInterest() {
	Interest interest = interestM.getInterest(category, userTest);
	assertEquals(null,interest);
    }
    
    @Test
    public void updateInterest() {
	interestM.updateInterest(category, userTest);
	List<Interest> interests = interestM.getInterests(userTest);
	for(Interest interest : interests) {
	    assertEquals(userTest,interest.getUser());
	    assertEquals(category,interest.getCategory());
	}
	
	interestM.updateInterest(category, userTest);
	assertEquals(null, interestM.getInterest(category, userTest));
    }
}
