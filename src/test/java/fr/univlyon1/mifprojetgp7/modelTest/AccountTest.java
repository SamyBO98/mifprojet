package fr.univlyon1.mifprojetgp7.modelTest;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.model.Account;

import static org.junit.Assert.assertEquals;


public class AccountTest {


    Account userT = new Account();

    @Test
    public void TestEmailUser() {
        System.out.println(">> TEST: Email User <<");

        userT.setEmailUser("test@test.fr");
        assertEquals("test@test.fr", userT.getEmailUser());
        userT.setEmailUser(null);
    }

    @Test
    public void TestName() {
        System.out.println(">> TEST: Name <<");

        userT.setName("Name");
        assertEquals("Name", userT.getName());
        userT.setName(null);
    }

    @Test
    public void TestFirstName() {
        System.out.println(">> TEST: First Name <<");

        userT.setFirstName("FirstName");
        assertEquals("FirstName", userT.getFirstName());
        userT.setFirstName(null);
    }

    @Test
    public void TestPassword() {
        System.out.println(">> TEST: Password <<");

        userT.setPassword("password");
        assertEquals("password", userT.getPassword());
        userT.setPassword(null);
    }

    @Test
    public void TestSalt() {
        System.out.println(">> TEST: Salt <<");

        userT.setSalt("salt");
        assertEquals("salt", userT.getSalt());
        userT.setSalt(null);
    }
}
