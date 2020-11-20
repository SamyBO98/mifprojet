package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.CategoryDAO;
import fr.univlyon1.mifprojetgp7.dao.ContributorDAO;
import fr.univlyon1.mifprojetgp7.dao.EventDAO;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class EventM {

    private EntityManager em;
    private EventDAO event;
    private ContributorDAO contributor;

    public EventM(EntityManager em){
        this.em = em;
        this.event = new EventDAO(this.em);
        this.contributor = new ContributorDAO(this.em);
    }

    public List<Event> getEvents(){
        return event.getEvents();
    }

    public Event getEvent(int eventID){
        return event.getEvent(eventID);
    }

    public List<Event> getEvent(String title){
        return event.getEvent(title);
    }

    public List<Event> getEvent(Category category){
        return event.getEvent(category);
    }

    public List<Event> getEvent(Account user){
        return event.getEvent(user);
    }

    public Event createEvent(String title, String contenu, Account user, Category category){
        em.getTransaction().begin();
        Event ev = event.createEvent(title, contenu, user, category);
        em.getTransaction().commit();
        return ev;
    }

    public boolean updateContributorToEvent(Event event, Account user){
        if (contributor.getContributor(event, user) == null){
            if (contributor.addContributor(event, user) != null){
                return true;
            } else {
                return false;
            }
        } else {
            if (contributor.deleteContributor(event, user) > 0){
                return true;
            } else {
                return false;
            }
        }
    }

}
