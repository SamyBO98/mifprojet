package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @OneToMany
    private List<Account> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "coordinates")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "categoryName")
    private Category category;


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

    public Category getCategory(){
        return this.category;
    }

    public List<Account> getContributors(){
        return this.users;
    }


    public void setEventID(int eventID){
        this.eventID = eventID;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCategory(Category category){
        this.category = category;
    }

}
