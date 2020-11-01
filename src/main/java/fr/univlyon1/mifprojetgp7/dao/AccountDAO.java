package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Account;

import javax.persistence.EntityManager;

public class AccountDAO {
    private final EntityManager em;

    public AccountDAO(EntityManager em){
        this.em = em;
    }

    public Account getAccount(final String email, final String password){
        Account account = em.find(Account.class, email);

        if (account == null){
            return null;
        }

        if (account.getPassword().equals(password)){
            return account;
        } else {
            return null;
        }
    }

    public Account createAccount(final String email, final String password, final String firstName, final String lastName){
        if (em.find(Account.class, email) == null) {
            Account account = new Account();
            account.setEmailUser(email);
            account.setPassword(password);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            em.persist(account);
            return account;
        } else {
            return null;
        }
    }

}
