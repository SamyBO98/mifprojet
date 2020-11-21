package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
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
        return em.createQuery("SELECT e FROM Event e WHERE e.title LIKE ?1", Event.class)
                .setParameter(1, title)
                .getResultList();
    }

    public List<Event> getEvent(Category category){
        return em.createQuery("SELECT e FROM Event e WHERE e.category = ?1", Event.class)
                .setParameter(1, category)
                .getResultList();
    }

    public List<Event> getEvent(Account user){
        return em.createQuery("SELECT e FROM Event e WHERE e.user = ?1", Event.class)
                .setParameter(1, user)
                .getResultList();
    }

    public Event createEvent(String title, String contenu, Account user, Category category){
        Event event = new Event();
        event.setTitle(title);
        event.setContent(contenu);
        event.setUser(user);
        event.setCategory(category);

        em.persist(event);
        return event;
    }

    public void deleteEvent(Event event){
        em.remove(event);
    }

}
