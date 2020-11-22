package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Interest;

import javax.persistence.EntityManager;
import java.util.List;

public class InterestDAO {

    private final EntityManager em;

    /**
     * Constructor of InterestDAO.
     * @param em
     */
    public InterestDAO(final EntityManager em) {
        this.em = em;
    }

    /**
     * Add interest.
     * @param category
     * @param user
     * @return interest
     */
    public Interest addInterest(final Category category, final Account user) {
        Interest interest = new Interest();
        if (category != null && user != null) {
            interest.setCategory(category);
            interest.setUser(user);
            em.persist(interest);
            return interest;
        }
        return null;
    }

    /**
     * Get an Interest based on category and user.
     * @param category
     * @param user
     * @return interest
     */
    public Interest getInterest(final Category category, final Account user) {
        List<Interest> interests = em.createQuery("SELECT i FROM Interest i WHERE i.category = ?1 "
                + "AND i.user = ?2", Interest.class)
                .setParameter(1, category)
                .setParameter(2, user)
                .getResultList();

        if (interests != null && interests.size() == 1) {
            return interests.get(0);
        }
        return null;
    }

    /**
     * Get a list of interests based on category.
     * @param category
     * @return list of interests
     */
    public List<Interest> getInterests(final Category category) {
        return em.createQuery("SELECT i FROM Interest i WHERE i.category = ?1", Interest.class)
                .setParameter(1, category)
                .getResultList();
    }

    /**
     * Get a list of interests based on user.
     * @param user
     * @return list of interests
     */
    public List<Interest> getInterests(final Account user) {
        return em.createQuery("SELECT i FROM Interest i WHERE i.user = ?1", Interest.class)
                .setParameter(1, user)
                .getResultList();
    }

    /**
     * Delete an interest.
     * @param interest
     */
    public void deleteInterest(final Interest interest) {
        em.remove(interest);
    }

}
