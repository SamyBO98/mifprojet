package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.*;

@Entity
@IdClass(ContributorPK.class)
@Table(name = "Contributor")
public class Contributor {

    /**
     * Ce code ne fonctionne pas
     * Violation de contrainte: quand on ajoute un utilisateur en contributeur à l'id 1, plus personne ne peut y participer
     * A modifier... Au plus vite :(
     */

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
