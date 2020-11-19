package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.AccountDAO;
import fr.univlyon1.mifprojetgp7.model.Account;

import javax.persistence.EntityManager;

public class AccountM {

    private EntityManager em;
    private AccountDAO account;

    public AccountM(EntityManager em){
        this.em = em;
        this.account = new AccountDAO(this.em);
    }

    public Account createAccount(final String email, final String name, final String firstName, final String password, final String salt){
        em.getTransaction().begin();
        Account acc = account.createAccount(email, name, firstName, password, salt);
        em.getTransaction().commit();
        return acc;
    }

    public Account getAccount(final String email){
        return account.getAccount(email);
    }

}