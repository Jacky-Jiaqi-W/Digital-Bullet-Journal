package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;


/**
 * represent the test for Categories
 */
public class CategoriesTest {

  @Test
  public void testGetCategoriesAsString() {
    Categories categories = new Categories();
    List<String> expected = Arrays.asList("NONE");
    assertEquals(expected, categories.getCategoriesAsString());

    List<String> customCategories = Arrays.asList("Personal", "Academic", "Life");
    categories = new Categories(customCategories);
    assertEquals(customCategories, categories.getCategoriesAsString());
  }

  @Test
  public void testAddCategory() {
    Categories categories = new Categories();
    categories.addCategory("Category 1");
    assertTrue(categories.contains("Category 1"));

    categories.addCategory("Category 2");
    assertTrue(categories.contains("Category 2"));

    categories.addCategory("Category 3");
    assertTrue(categories.contains("Category 3"));
  }

  @Test
  public void testContains() {
    Categories categories = new Categories();
    assertFalse(categories.contains("Category 1"));

    categories.addCategory("Category 1");
    assertTrue(categories.contains("Category 1"));

    categories.addCategory("Category 2");
    assertTrue(categories.contains("Category 2"));

    categories.addCategory("Category 3");
    assertTrue(categories.contains("Category 3"));

    assertFalse(categories.contains("Category 4"));
  }
}