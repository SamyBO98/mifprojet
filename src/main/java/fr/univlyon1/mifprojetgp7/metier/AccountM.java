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

    public Account createAccount(final String email, final String password, final String firstName, final String lastName){
        em.getTransaction().begin();
        Account acc = account.createAccount(email, password, firstName, lastName);
        em.getTransaction().commit();
        return acc;
    }

    public Account getAccount(final String email, final String password){
        return account.getAccount(email, password);
    }
}
