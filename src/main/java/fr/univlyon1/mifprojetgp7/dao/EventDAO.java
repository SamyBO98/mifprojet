package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class EventDAO {

    private final EntityManager em;

    public EventDAO(EntityManager em){
        this.em = em;
    }

    public List<Event> getEvents(){
        return em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
    }

    public Event getEvent(int eventID){
        Event event = em.find(Event.class, eventID);

        return event;
    }

    public List<Event> getEvent(String title){
        return em.createQuery("SELECT e FROM Event e WHERE e.title = ?1", Event.class)
                .setParameter(1, title)
                .getResultList();
    }

    public Event createEvent(String title, String contenu, Account user){
        Event event = new Event();
        event.setTitle(title);
        event.setContent(contenu);
        event.setUser(user);

        em.persist(event);
        return event;
    }

}
