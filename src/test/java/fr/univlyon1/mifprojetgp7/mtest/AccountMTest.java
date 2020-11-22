package fr.univlyon1.mifprojetgp7.mtest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.metier.AccountM;
import fr.univlyon1.mifprojetgp7.model.Account;

public class AccountMTest {

   EntityManager em;
    AccountM accountM;

    public AccountMTest() {
	this.em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	this.accountM = new AccountM(em);
    }

    @Test
    public void getUserTest() {
	Account userTest = accountM.getAccount("user@test.com");
	assertEquals(userTest.getEmailUser(), "user@test.com");
    }

}
