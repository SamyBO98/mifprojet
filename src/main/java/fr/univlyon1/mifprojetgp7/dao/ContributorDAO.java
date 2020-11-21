package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Contributor;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;

public class ContributorDAO {

    private final EntityManager em;

    public ContributorDAO(EntityManager em){
        this.em = em;
    }

    public Contributor addContributor(Event event, Account user){
        Contributor contributor = new Contributor();
        contributor.setEvent(event);
        contributor.setUser(user);
        event.getContributors().add(user);
        user.getEvents().add(event);
        em.persist(contributor);
        return contributor;
    }

    public int deleteContributor(Event event, Account user){
        int res = em.createQuery("DELETE FROM Contributor c WHERE c.event = ?1 AND c.user = ?2", Contributor.class)
                .setParameter(1, event)
                .setParameter(2, user)
                .executeUpdate();

        event.getContributors().remove(user);
        user.getEvents().remove(event);

        return res;

    }

    public Contributor getContributor(Event event, Account user){
        return em.createQuery("SELECT c FROM Contributor c WHERE c.event = ?1 AND c.user = ?2", Contributor.class)
                .setParameter(1, event)
                .setParameter(2, user)
                .getResultList().get(0);
    }
}
