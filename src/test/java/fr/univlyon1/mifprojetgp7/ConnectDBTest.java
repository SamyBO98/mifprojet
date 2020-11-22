package fr.univlyon1.mifprojetgp7;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.servlets.ConnectDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ConnectDBTest {
    
    ConnectDB connectDB;
    
    public ConnectDBTest() {
	connectDB = new ConnectDB();
    }

    @Test
    public void setupConnectionDB(){
        System.out.println(">> TEST: Setup Connection DB <<");
        EntityManager em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
        em.close();
    }
    
    @Test
    public void getEm() {
	EntityManager manager = connectDB.getEm();
	assertEquals(null, manager);
    }
    
    @Test
    public void setEm() {
	EntityManager em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	connectDB.setEm(em);
	assertEquals(em, connectDB.getEm());
    }
    
}
