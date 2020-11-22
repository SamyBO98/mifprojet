package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Account;

import javax.persistence.EntityManager;

public class AccountDAO {

    private final EntityManager em;

    /**
     * Constructor of AccountDAO.
     * @param em
     */
    public AccountDAO(final EntityManager em) {
        this.em = em;
    }

    /**
     * Account Getter.
     * @param email
     * @return account
     */
    public Account getAccount(final String email) {
        Account account = em.find(Account.class, email);

        if (account == null) {
            return null;
        } else {
            return account;
        }

    }

    /**
     * Creation of an account.
     * @param email
     * @param name
     * @param firstName
     * @param password
     * @param salt
     * @return account
     */
    public Account createAccount(final String email, final String name,
                                 final String firstName, final String password, final String salt) {
        if (em.find(Account.class, email) == null) {
            Account account = new Account();
            account.setEmailUser(email);
            account.setName(name);
            account.setFirstName(firstName);
            account.setPassword(password);
            account.setSalt(salt);
            em.persist(account);
            return account;
        } else {
            return null;
        }
    }

}
