package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue()
    private int idEvent;
    private String nameEvent;
    private String description;

    public int getId(){
        return this.idEvent;
    }

    public String getTitle(){
        return this.nameEvent;
    }

    public String getDescription(){
        return this.description;
    }

    public void setTitle(final String title){
        this.nameEvent = title;
    }

    public void setDescription(final String description){
        this.description = description;
    }

}
