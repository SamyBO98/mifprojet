package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Contributor;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class ContributorDAO {

    private final EntityManager em;

    public ContributorDAO(EntityManager em){
        this.em = em;
    }

    public Contributor addContributor(Event event, Account user){
        Contributor contributor = new Contributor();
        contributor.setEvent(event);
        contributor.setUser(user);
        em.persist(contributor);
        return contributor;
    }

    public void deleteContributor(Contributor contributor){
        em.remove(contributor);
    }

    public Contributor getContributor(Event event, Account user){
        List<Contributor> contribs = em.createQuery("SELECT c FROM Contributor c WHERE c.event = ?1 AND c.user = ?2", Contributor.class)
                .setParameter(1, event)
                .setParameter(2, user)
                .getResultList();

        if (contribs != null && contribs.size() == 1){
            return contribs.get(0);
        }
        return null;
    }

    public List<Contributor> getContributors(Account user){
        return em.createQuery("SELECT c FROM Contributor c WHERE c.user = ?1", Contributor.class)
                .setParameter(1, user)
                .getResultList();
    }

    public List<Contributor> getContributors(Event event){
        return em.createQuery("SELECT c FROM Contributor c WHERE c.event = ?1", Contributor.class)
                .setParameter(1, event)
                .getResultList();
    }
}
