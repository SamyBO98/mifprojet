package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.*;

@Entity
@IdClass(InterestPK.class)
@Table(name = "Interest")

public class Interest {
    @Id
    @ManyToOne
    @JoinColumn(name = "eventID")
    private Event event;
    @Id
    @OneToOne
    @JoinColumn(name = "categoryName")
    private Category category;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
