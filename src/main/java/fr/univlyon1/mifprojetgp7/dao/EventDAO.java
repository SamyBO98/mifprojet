package fr.univlyon1.mifprojetgp7.dao;


import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;

public class EventDAO {

    private final EntityManager em;

    public EventDAO(EntityManager em){
        this.em = em;
    }

    public Event getEvent(final int id){
        return em.find(Event.class, id);
    }

    public Event createEvent(String title, String description){
        Event event = new Event();
        event.setTitle(title);
        event.setDescription(description);
        em.persist(event);
        return event;
    }
}
