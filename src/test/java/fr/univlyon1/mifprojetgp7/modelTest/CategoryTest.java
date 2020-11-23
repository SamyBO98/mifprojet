package fr.univlyon1.mifprojetgp7.modelTest;

import fr.univlyon1.mifprojetgp7.model.Category;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CategoryTest {


    Category categoryT = new Category();

    @Test
    public void TestCategory() {
        System.out.println(">> TEST: Category <<");

        categoryT.setCategoryName("Sport");
        assertEquals("Sport", categoryT.getCategoryName());
        categoryT.setCategoryName(null);
    }
}
