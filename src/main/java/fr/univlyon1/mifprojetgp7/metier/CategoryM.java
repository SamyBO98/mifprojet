package fr.univlyon1.mifprojetgp7.metier;

import fr.univlyon1.mifprojetgp7.dao.CategoryDAO;
import fr.univlyon1.mifprojetgp7.model.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryM {

    private EntityManager em;
    private CategoryDAO category;

    /**
     * Constructor of CategoryM.
     * @param em
     */
    public CategoryM(final EntityManager em) {
        this.em = em;
        this.category = new CategoryDAO(this.em);
    }

    /**
     * Get a list of categories.
     * @return list of categories
     */
    public List<Category> getCategories() {
        return category.getCategories();
    }

    /**
     * Get a category based on her name.
     * @param categoryName
     * @return category
     */
    public Category getCategory(final String categoryName) {
        return category.getCategory(categoryName);
    }

}
