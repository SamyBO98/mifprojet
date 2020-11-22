package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.InterestDAO;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Interest;

import javax.persistence.EntityManager;
import java.util.List;

public class InterestM {

    private EntityManager em;
    private InterestDAO interest;

    /**
     * Constructor of InterestM.
     * @param em
     */
    public InterestM(final EntityManager em) {
        this.em = em;
        this.interest = new InterestDAO(this.em);
    }

    /**
     * Update an interest.
     * @param category
     * @param user
     * @return Boolean depends on the interaction with the category
     */
    public boolean updateInterest(final Category category, final Account user) {
        Interest inter = interest.getInterest(category, user);
        em.getTransaction().begin();
        if (inter == null) {
            Interest inte = interest.addInterest(category, user);
            em.getTransaction().commit();
            return inte != null;
        } else {
            interest.deleteInterest(inter);
            em.getTransaction().commit();
            return getInterest(category, user) == null;
        }
    }

    /**
     * Get interest based on category and user.
     * @param category
     * @param user
     * @return interest
     */
    public Interest getInterest(final Category category, final Account user) {
        return interest.getInterest(category, user);
    }

    /**
     * Get all interests based on category.
     * @param category
     * @return list of interest
     */
    public List<Interest> getInterests(final Category category) {
        return interest.getInterests(category);
    }

    /**
     * Get all interests based on user.
     * @param user
     * @return list of interest
     */
    public List<Interest> getInterests(final Account user) {
        return interest.getInterests(user);
    }

}
