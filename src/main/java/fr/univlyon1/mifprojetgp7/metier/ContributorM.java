package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.ContributorDAO;
import fr.univlyon1.mifprojetgp7.dao.EventDAO;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Contributor;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class ContributorM {

    private EntityManager em;
    private ContributorDAO contributor;

    public ContributorM(EntityManager em){
        this.em = em;
        this.contributor = new ContributorDAO(this.em);
    }

    public boolean updateContributorToEvent(Event event, Account user){
        Contributor contribu = getContributor(event, user);
        em.getTransaction().begin();
        if (contribu == null){
            Contributor contrib = contributor.addContributor(event, user);
            em.getTransaction().commit();
            return contrib != null;
        } else {
            contributor.deleteContributor(contribu);
            em.getTransaction().commit();
            return getContributor(event, user) == null;
        }
    }

    public Contributor getContributor(Event event, Account user){
        return contributor.getContributor(event, user);
    }

    public List<Contributor> getContributors(Account user){
        return contributor.getContributors(user);
    }

    public List<Contributor> getContributors(Event event){
        return contributor.getContributors(event);
    }

    public void deleteContributors(List<Contributor> contributors){
        em.getTransaction().begin();
        for (Contributor contrib: contributors){
            contributor.deleteContributor(contrib);
        }
        em.getTransaction().commit();
    }
}
