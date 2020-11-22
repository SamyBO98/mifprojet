package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Contributor;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class ContributorDAO {

    private final EntityManager em;

    /**
     * Constructor of ContributorDAO.
     * @param em
     */
    public ContributorDAO(final EntityManager em) {
        this.em = em;
    }

    /**
     * Add a contributor.
     * @param event
     * @param user
     * @return contributor
     */
    public Contributor addContributor(final Event event, final Account user) {
        Contributor contributor = new Contributor();
        contributor.setEvent(event);
        contributor.setUser(user);
        em.persist(contributor);
        return contributor;
    }

    /**
     * Delete a contributor.
     * @param contributor
     */
    public void deleteContributor(final Contributor contributor) {
        em.remove(contributor);
    }

    /**
     * Get a contributor of an event.
     * @param event
     * @param user
     * @return contributor
     */
    public Contributor getContributor(final Event event, final Account user) {
        List<Contributor> contribs = em
                .createQuery("SELECT c FROM Contributor c WHERE c.event = ?1 "
                + "AND c.user = ?2", Contributor.class)
                .setParameter(1, event)
                .setParameter(2, user)
                .getResultList();

        if (contribs != null && contribs.size() == 1) {
            return contribs.get(0);
        }
        return null;
    }

    /**
     * Get all contributors.
     * @param user
     * @return List of contributor
     */
    public List<Contributor> getContributors(final Account user) {
        return em.createQuery("SELECT c FROM Contributor c WHERE c.user = ?1", Contributor.class)
                .setParameter(1, user)
                .getResultList();
    }

    /**
     * Get all contributors.
     * @param event
     * @return List of contributor
     */
    public List<Contributor> getContributors(final Event event) {
        return em.createQuery("SELECT c FROM Contributor c WHERE c.event = ?1", Contributor.class)
                .setParameter(1, event)
                .getResultList();
    }
}
