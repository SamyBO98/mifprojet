package fr.univlyon1.mifprojetgp7.daottest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.dao.AccountDAO;
import fr.univlyon1.mifprojetgp7.model.Account;

public class AccountDAOTest {
    
    private EntityManager em;
    private AccountDAO dao;
    
    public AccountDAOTest() {
	em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	dao = new AccountDAO(em);
    }
    
    @Test
    public void getAccount() {
	Account userTest = dao.getAccount("user@test.com");
	assertEquals("user@test.com", userTest.getEmailUser());
    }
    
}
