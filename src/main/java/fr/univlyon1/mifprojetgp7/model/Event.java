package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue()
    @JoinColumn(name = "eventID")
    private int eventID;

    @JoinColumn(name = "title")
    private String title;

    @JoinColumn(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "email")
    private Account user;

    @ManyToOne
    @JoinColumn(name = "coordinates")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "categoryName")
    private Category category;


    public int getId() {
        return this.eventID;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Account getAccount() {
        return this.user;
    }

    public Location getLocation() {
        return this.location;
    }

    public Category getCategory() {
        return this.category;
    }


    public void setEventID(final int eventID) {
        this.eventID = eventID;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void setUser(final Account user) {
        this.user = user;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public boolean equals(final Object o) {
        if (o instanceof Event) {
            return ((Event) o).getId() == eventID;
        }
        return false;
    }

    public int hashCode() {
        int hash = 1;
        final int nb47 = 47;
        final int nb26 = 26;
        final int nb64 = 64;
        final int nb41 = 41;
        final int nb22 = 22;
        final int nb15 = 15;
        hash = hash * nb47 + eventID;
        hash = hash * nb26 + title.hashCode();
        hash = hash * nb64 + content.hashCode();
        hash = hash * nb41 + user.hashCode();
        hash = hash * nb22 + location.hashCode();
        hash = hash * nb15 + category.hashCode();
        return hash;
    }

}
