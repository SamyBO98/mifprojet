package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Table(name = "Account")
public class Account implements Serializable {
    @Id
    @JoinColumn(name = "email")
    private String email;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "firstName")
    private String firstName;

    @JoinColumn(name = "password")
    private String password;

    @JoinColumn(name = "salt")
    private String salt;

    @ManyToOne
    @JoinColumn(name = "coordinates")
    private Location coordinates;


    public String getEmailUser() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSalt() {
        return this.salt;
    }

    public Location getCoordinates() {
        return this.coordinates;
    }


    public void setEmailUser(final String email) {
        this.email = email;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setSalt(final String salt) {
        this.salt = salt;
    }

    public void setCoordinates(final Location coordinates) {
        this.coordinates = coordinates;
    }

    public boolean equals(final Object o) {
        if (o instanceof Account) {
            return ((Account) o).getEmailUser().equals(email);
        }
        return false;
    }

    public int hashCode() {
        return email.hashCode();
    }
}
