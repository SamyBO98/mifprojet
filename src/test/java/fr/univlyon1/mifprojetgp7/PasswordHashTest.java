package fr.univlyon1.mifprojetgp7;

import org.junit.Test;


import java.util.Base64;

import static fr.univlyon1.mifprojetgp7.utils.PasswordHashing.hash;
import static fr.univlyon1.mifprojetgp7.utils.PasswordHashing.verifyUserPassword;


public class PasswordHashTest {

    @Test
    public void TestPasswordHash() {
        System.out.println(">> TEST: Hashing Password <<");
        String password = "password";
        String salt = "salt";
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        String returnValue = Base64.getEncoder().encodeToString(securePassword);
        boolean test = verifyUserPassword("password", returnValue, "salt");
    }

}
