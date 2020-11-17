package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.*;

@Entity
@Table(name = "User_")
public class Account {
    @Id
    private String emailUser;

    private String password;
    private String firstName;
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "coordinates")
    private Location coordinates; // clé étrangère

    public String getEmailUser() {
        return this.emailUser;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
