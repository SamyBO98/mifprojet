package fr.univlyon1.mifprojetgp7.dao;

import fr.univlyon1.mifprojetgp7.model.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDAO {

    private final EntityManager em;

    /**
     * Constructor of CategoryDAO.
     * @param em
     */
    public CategoryDAO(final EntityManager em) {
        this.em = em;
    }

    /**
     * Get all categories.
     * @return all categories
     */
    public List<Category> getCategories() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    /**
     * Get a particular category.
     * @param categoryName
     * @return category
     */
    public Category getCategory(final String categoryName) {
        Category category;

        category = em.find(Category.class, categoryName);

       return category;
    }
}
