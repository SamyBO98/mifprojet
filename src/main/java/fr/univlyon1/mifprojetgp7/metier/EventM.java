package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.EventDAO;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class EventM {
    private EntityManager em;
    private EventDAO event;

    public EventM(EntityManager em){
        this.em = em;
        this.event = new EventDAO(em);
    }

    public Event createEvent(String title, String description){
        em.getTransaction().begin();
        Event res = event.createEvent(title, description);
        em.getTransaction().commit();
        return res;
    }

    public List<Event> getAllEvents(){
        return em.createQuery("SELECT ev FROM Event ev", Event.class).getResultList();
    }
}
