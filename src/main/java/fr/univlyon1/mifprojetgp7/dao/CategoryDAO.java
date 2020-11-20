package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDAO {

    private final EntityManager em;

    public CategoryDAO(EntityManager em){
        this.em = em;
    }

    public List<Category> getCategories(){
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    public Category getCategory(String categoryName){
        Category category = new Category();

        category = em.find(Category.class, categoryName);

       return category;
    }
}
