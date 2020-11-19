package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.*;

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


    public int getId(){
        return this.eventID;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public Account getAccount(){
        return this.user;
    }

    public Location getLocation(){
        return this.location;
    }

    public void setEventID(final int eventID){
        this.eventID = eventID;
    }

    public void setTitle(final String title){
        this.title = title;
    }

    public void setContent(final String content){
        this.content = content;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
