package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.AccountDAO;
import fr.univlyon1.mifprojetgp7.model.Account;

import javax.persistence.EntityManager;

public class AccountM {

    private EntityManager em;
    private AccountDAO account;

    /**
     * Constructor of AccountM.
     * @param em
     */
    public AccountM(final EntityManager em) {
        this.em = em;
        this.account = new AccountDAO(this.em);
    }

    /**
     * Create an account.
     * @param email
     * @param name
     * @param firstName
     * @param password
     * @param salt
     * @return account
     */
    public Account createAccount(final String email, final String name,
                                 final String firstName, final String password, final String salt) {
        em.getTransaction().begin();
        Account acc = account.createAccount(email, name, firstName, password, salt);
        em.getTransaction().commit();
        return acc;
    }

    /**
     * Get an account based on email.
     * @param email
     * @return account
     */
    public Account getAccount(final String email) {
        return account.getAccount(email);
    }

}
