package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * categories for tasks and events
 */
public class Categories {
  private List<String> categories;

  /**
   * instantiate the categories
   */
  public Categories() {
    String none = "NONE";
    this.categories = new ArrayList<>();
    this.categories.add(none);
  }

  /**
   * instantiate the categories for json
   */
  @JsonCreator
  public Categories(@JsonProperty("categories") List<String> categories) {
    this.categories = categories;
  }

  /**
   * getter for the categories field
   *
   * @return the list of categories
   */
  @JsonGetter("categories")
  public List<String> getCategoriesAsString() {
    return this.categories;
  }


  /**
   * adds the category to the list of categories
   *
   * @param category - category to add
   */
  public void addCategory(String category) {
    this.categories.add(category);
  }

  /**
   * Checks if the given category exists
   *
   * @param category given category
   * @return true if the given category exists
   */
  public boolean contains(String category) {
    boolean result = false;
    for (String actual : this.categories) {
      if (actual.equals(category)) {
        result = true;
      }
    }
    return result;
  }
}
