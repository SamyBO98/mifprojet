package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.*;

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

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public boolean equals(Object o) {
	if (o instanceof Interest) {
	    boolean userB = ((Interest) o).getUser().equals(user);
	    boolean categoryB = ((Interest) o).getCategory().equals(category);
	    return userB && categoryB;
	}
	return false;
    }

}

