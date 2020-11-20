package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.CategoryDAO;
import fr.univlyon1.mifprojetgp7.dao.ContributorDAO;
import fr.univlyon1.mifprojetgp7.dao.EventDAO;
import fr.univlyon1.mifprojetgp7.model.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryM {

    private EntityManager em;
    private CategoryDAO category;

    public CategoryM(EntityManager em){
        this.em = em;
        this.category = new CategoryDAO(this.em);
    }

    public List<Category> getCategories(){
        return category.getCategories();
    }

    public Category getCategory(String categoryName){
        return category.getCategory(categoryName);
    }

}
