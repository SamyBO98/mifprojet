package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.*;

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

    public void setEvent(Event event) {
        this.event = event;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }
}
