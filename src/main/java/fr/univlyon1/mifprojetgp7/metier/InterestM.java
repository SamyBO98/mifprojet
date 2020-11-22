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

    public InterestM(EntityManager em){
        this.em = em;
        this.interest = new InterestDAO(this.em);
    }

    public boolean updateInterest(Category category, Account user){
        Interest inter = interest.getInterest(category, user);
        em.getTransaction().begin();
        if (inter == null){
            Interest inte = interest.addInterest(category, user);
            em.getTransaction().commit();
            return inte != null;
        } else {
            interest.deleteInterest(inter);
            em.getTransaction().commit();
            return getInterest(category, user) == null;
        }
    }

    public Interest getInterest(Category category, Account user){
        return interest.getInterest(category, user);
    }

    public List<Interest> getInterests(Category category){
        return interest.getInterests(category);
    }

    public List<Interest> getInterests(Account user){
        return interest.getInterests(user);
    }

}
