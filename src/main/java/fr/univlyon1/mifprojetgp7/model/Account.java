package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {
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
        return this.firstName;
    }

    public String getFirstName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSalt(){
        return this.salt;
    }

    public Location getCoordinates(){
        return this.coordinates;
    }


    public void setEmailUser(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public void setCoordinates(Location coordinates){
        this.coordinates = coordinates;
    }

}
