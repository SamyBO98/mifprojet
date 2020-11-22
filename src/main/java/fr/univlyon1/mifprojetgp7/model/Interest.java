package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@IdClass(InterestPK.class)
@Table(name = "Interest")
public class Interest {

    @Id
    @ManyToOne
    @JoinColumn(name = "categoryName")
    private Category category;

    @Id
    @ManyToOne
    @JoinColumn(name = "email")
    private Account user;

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(final Account user) {
        this.user = user;
    }

    public boolean equals(final Object o) {
        if (o instanceof Interest) {
            boolean userB = ((Interest) o).getUser().equals(user);
            boolean categoryB = ((Interest) o).getCategory().equals(category);
            return userB && categoryB;
        }
        return false;
    }

    public int hashCode() {
        int hash = 1;
        final int nb47 = 47;
        final int nb26 = 26;
        hash = hash * nb47 + category.hashCode();
        hash = hash * nb26 + user.hashCode();
        return hash;
    }

}

