package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.ContributorDAO;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Contributor;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class ContributorM {

    private EntityManager em;
    private ContributorDAO contributor;

    /**
     * Constructor od ContributorM.
     * @param em
     */
    public ContributorM(final EntityManager em) {
        this.em = em;
        this.contributor = new ContributorDAO(this.em);
    }

    /**
     * Update a contributor to an event.
     * @param event
     * @param user
     * @return Boolean depends on if he participates or not
     */
    public boolean updateContributorToEvent(final Event event, final Account user) {
        Contributor contribu = getContributor(event, user);
        em.getTransaction().begin();
        if (contribu == null) {
            Contributor contrib = contributor.addContributor(event, user);
            em.getTransaction().commit();
            return contrib != null;
        } else {
            contributor.deleteContributor(contribu);
            em.getTransaction().commit();
            return getContributor(event, user) == null;
        }
    }

    /**
     * Get a contributor based on event,user.
     * @param event
     * @param user
     * @return contributor
     */
    public Contributor getContributor(final Event event, final Account user) {
        return contributor.getContributor(event, user);
    }

    /**
     * Get a list of contributor based on user.
     * @param user
     * @return list of contributor
     */
    public List<Contributor> getContributors(final Account user) {
        return contributor.getContributors(user);
    }

    /**
     * Get a list of contributor based on event.
     * @param event
     * @return list of contributor
     */
    public List<Contributor> getContributors(final Event event) {
        return contributor.getContributors(event);
    }

    /**
     * Delete a contributor.
     * @param contributors
     */
    public void deleteContributors(final List<Contributor> contributors) {
        em.getTransaction().begin();
        for (Contributor contrib: contributors) {
            contributor.deleteContributor(contrib);
        }
        em.getTransaction().commit();
    }
}
