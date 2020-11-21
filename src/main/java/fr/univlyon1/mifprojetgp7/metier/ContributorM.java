package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.ContributorDAO;
import fr.univlyon1.mifprojetgp7.dao.EventDAO;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Contributor;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;

public class ContributorM {

    private EntityManager em;
    private ContributorDAO contributor;

    public ContributorM(EntityManager em){
        this.em = em;
        this.contributor = new ContributorDAO(this.em);
    }

    public boolean updateContributorToEvent(Event event, Account user){
        if (!event.getContributors().contains(user)){
            em.getTransaction().begin();
            Contributor contrib = contributor.addContributor(event, user);
            em.getTransaction().commit();
            return contrib != null;
        }
        return false;
    }
}
