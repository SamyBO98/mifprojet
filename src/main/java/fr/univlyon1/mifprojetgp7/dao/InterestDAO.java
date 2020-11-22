package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Interest;

import javax.persistence.EntityManager;
import java.util.List;

public class InterestDAO {

    private final EntityManager em;

    public InterestDAO(EntityManager em){
        this.em = em;
    }

    public Interest addInterest(Category category, Account user){
        Interest interest = new Interest();
        if (category != null && user != null){
            interest.setCategory(category);
            interest.setUser(user);
            em.persist(interest);
            return interest;
        }
        return null;
    }

    public Interest getInterest(Category category, Account user){
        List<Interest> interests = em.createQuery("SELECT i FROM Interest i WHERE i.category = ?1 AND i.user = ?2", Interest.class)
                .setParameter(1, category)
                .setParameter(2, user)
                .getResultList();

        if (interests != null && interests.size() == 1){
            return interests.get(0);
        }
        return null;
    }

    public List<Interest> getInterests(Category category){
        return em.createQuery("SELECT i FROM Interest i WHERE i.category = ?1", Interest.class)
                .setParameter(1, category)
                .getResultList();
    }

    public List<Interest> getInterests(Account user){
        return em.createQuery("SELECT i FROM Interest i WHERE i.user = ?1", Interest.class)
                .setParameter(1, user)
                .getResultList();
    }

    public void deleteInterest(Interest interest){
        em.remove(interest);
    }

}
