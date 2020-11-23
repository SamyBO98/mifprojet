package fr.univlyon1.mifprojetgp7.daottest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

import fr.univlyon1.mifprojetgp7.dao.CategoryDAO;
import fr.univlyon1.mifprojetgp7.model.Category;

public class CategoryTestDAO {


    private EntityManager em;
    private CategoryDAO dao;
    
    public CategoryTestDAO() {
	em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
	dao = new CategoryDAO(em);
    }

    @Test
    public void getCategories() {
	List<Category> categories = dao.getCategories();
	for(Category category : categories) {
	    assertNotEquals(null, dao.getCategory(category.getCategoryName()));
	}
    }

    @Test
    public void getCategory() {
	Category c = dao.getCategory("Sports");
	assertEquals("Sports", c.getCategoryName());
    }
    
    
}
