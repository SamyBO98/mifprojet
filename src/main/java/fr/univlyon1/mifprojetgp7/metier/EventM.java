package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.EventDAO;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Contributor;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EventM {

    private EntityManager em;
    private EventDAO event;

    /**
     * Constructor of EventM.
     * @param em
     */
    public EventM(final EntityManager em) {
        this.em = em;
        this.event = new EventDAO(this.em);
    }

    /**
     * Get all events.
     * @return list of events
     */
    public List<Event> getEvents() {
        return event.getEvents();
    }

    /**
     * Get an event based on ID.
     * @param eventID
     * @return event
     */
    public Event getEvent(final int eventID) {
        return event.getEvent(eventID);
    }

    /**
     * Get events based on title.
     * @param title
     * @return list of event
     */
    public List<Event> getEvent(final String title) {
        return event.getEvent(title);
    }

    /**
     * Get events based on category.
     * @param category
     * @return list of event
     */
    public List<Event> getEvent(final Category category) {
        return event.getEvent(category);
    }

    /**
     * Get events based on user.
     * @param user
     * @return list of event
     */
    public List<Event> getEvent(final Account user) {
        return event.getEvent(user);
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
        em.getTransaction().begin();
        Event ev = event.createEvent(title, contenu, user, category);
        em.getTransaction().commit();
        return ev;
    }

    /**
     * Get events based on contributors.
     * @param contributors
     * @return list of events
     */
    public List<Event> getEvents(final List<Contributor> contributors) {
        List<Event> events = new ArrayList<>();
        for (Contributor contributor: contributors) {
            events.add(getEvent(contributor.getEvent().getId()));
        }
        return events;
    }


    /**
     * Delete an event.
     * @param ev
     */
    public void deleteEvent(final Event ev) {
        em.getTransaction().begin();
        event.deleteEvent(ev);
        em.getTransaction().commit();
    }

}
