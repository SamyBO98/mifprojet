package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class EventDAO {

    private final EntityManager em;

    /**
     * Constructor of EventDAO.
     * @param em
     */
    public EventDAO(final EntityManager em) {
        this.em = em;
    }

    /**
     * Get all events.
     * @return List of events
     */
    public List<Event> getEvents() {
        return em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
    }

    /**
     * Get a special Event.
     * @param eventID
     * @return event
     */
    public Event getEvent(final int eventID) {
        return em.find(Event.class, eventID);

    }

    /**
     * Get a list of event.
     * @param title
     * @return List of event based on title
     */
    public List<Event> getEvent(final String title) {
        return em.createQuery("SELECT e FROM Event e WHERE e.title LIKE ?1", Event.class)
                .setParameter(1, title)
                .getResultList();
    }

    /**
     * Get a list of event.
     * @param category
     * @return List of event based on category
     */
    public List<Event> getEvent(final Category category) {
        return em.createQuery("SELECT e FROM Event e WHERE e.category = ?1", Event.class)
                .setParameter(1, category)
                .getResultList();
    }

    /**
     * Get a list of event.
     * @param user
     * @return List of event based on user
     */
    public List<Event> getEvent(final Account user) {
        return em.createQuery("SELECT e FROM Event e WHERE e.user = ?1", Event.class)
                .setParameter(1, user)
                .getResultList();
    }

    /**
     * Create an event.
     * @param title
     * @param contenu
     * @param user
     * @param category
     * @return event
     */
    public Event createEvent(final String title, final String contenu,
                             final Account user, final Category category) {
        Event event = new Event();
        event.setTitle(title);
        event.setContent(contenu);
        event.setUser(user);
        event.setCategory(category);

        em.persist(event);
        return event;
    }

    /**
     * Delete an event.
     * @param event
     */
    public void deleteEvent(final Event event) {
        em.remove(event);
    }

}
