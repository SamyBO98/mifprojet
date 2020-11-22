package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @JoinColumn(name = "categoryName")
    private String categoryName;

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean equals(final Object o) {
        if (o instanceof Category) {
            return ((Category) o).getCategoryName().equals(categoryName);
        }
        return false;
    }

    public int hashCode() {
        return categoryName.hashCode();
    }
}
