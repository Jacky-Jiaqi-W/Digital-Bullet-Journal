package cs3500.pa05.model.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Categories;
import cs3500.pa05.model.DayToDos;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;



/**
 * tests for BulletJournalJson
 */
public class BulletJournalJsonTest {

  @Test
  public void testBulletJournalJson() {
    Categories categories = new Categories();
    categories.addCategory("Work");
    categories.addCategory("Personal");

    List<DayToDos> dayToDosList = new ArrayList<>();

    BulletJournalJson bulletJournalJson = new BulletJournalJson("Week 1", 10, 5, categories,
        "This is a quote", dayToDosList);

    assertEquals("Week 1", bulletJournalJson.getWeekName());
    assertEquals(10, bulletJournalJson.getMaxEvents());
    assertEquals(5, bulletJournalJson.getMaxTasks());
    assertEquals(categories, bulletJournalJson.getCategories());
    assertEquals("This is a quote", bulletJournalJson.getQuote());
    assertEquals(dayToDosList, bulletJournalJson.getDayToDos());
  }
}
