package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.EventDAO;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;

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
}
