package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.*;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue()
    private int idEvent;
    private String nameEvent;
    private String description;
    @ManyToOne
    @JoinColumn(name = "coordinates")
    private Location location;
    @ManyToOne
    @JoinColumn(name = "email")
    private Account user;


    public int getId(){
        return this.getIdEvent();
    }

    public String getTitle(){
        return this.getNameEvent();
    }

    public String getDescription(){
        return this.description;
    }

    public void setTitle(final String title){
        this.setNameEvent(title);
    }

    public void setDescription(final String description){
        this.description = description;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }
}
