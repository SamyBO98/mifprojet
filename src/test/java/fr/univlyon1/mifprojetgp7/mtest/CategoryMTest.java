package fr.univlyon1.mifprojetgp7.mtest;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.metier.CategoryM;
import fr.univlyon1.mifprojetgp7.model.Category;

public class CategoryMTest {


    EntityManager em;
    CategoryM categoryM;
    
    public CategoryMTest() {
	this.em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	this.categoryM = new CategoryM(em);
    }
    
    @Test
    public void getCategoryFromCategoryM() {
	Category c = categoryM.getCategory("Sports");
	assertEquals("Sports", c.getCategoryName());
    }
}
