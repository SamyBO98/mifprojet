package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@IdClass(ContributorPK.class)
@Table(name = "Contributor")
public class Contributor {

    @Id
    @ManyToOne
    @JoinColumn(name = "eventID")
    private Event event;

    @Id
    @ManyToOne
    @JoinColumn(name = "email")
    private Account user;

    public Event getEvent() {
        return event;
    }

    public void setEvent(final Event event) {
        this.event = event;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(final Account user) {
        this.user = user;
    }

    /**
     * Test if user/event from contributor is equal to user/event.
     * @param o
     * @return Boolean
     */
    public boolean equals(final Object o) {
        if (o instanceof Contributor) {
            boolean userB = ((Contributor) o).getUser().equals(user);
            boolean eventB = ((Contributor) o).getEvent().equals(event);
            return userB && eventB;
        }
        return false;
    }
    /**
     * Event hashed and user hashed.
     * @return event hashed and user hashed
     */
    public int hashCode() {
        int hash = 1;
        final int nb47 = 47;
        final int nb26 = 26;
        hash = hash * nb47 + event.hashCode();
        hash = hash * nb26 + user.hashCode();
        return hash;
    }
}
