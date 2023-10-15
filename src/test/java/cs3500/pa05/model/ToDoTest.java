package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;


/**
 * test for ToDo class
 */
public class ToDoTest {

  @Test
  public void testGetName() {
    ToDo todo = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1", false);
    assertEquals("Task 1", todo.getName());
  }


  @Test
  public void testGetCategory() {
    ToDo todo = new ToDoTask("Task 2", Weekday.FRIDAY, "Category 3", true);
    assertEquals("Category 3", todo.getCategory());
  }



  @Test
  public void testCreateForTask() {
    ToDo todo = ToDo.create("Task 4", Weekday.WEDNESDAY, "Category 6", null, null, true);
    assertTrue(todo instanceof ToDoTask);
    assertEquals("Task 4", todo.getName());
    assertEquals(Weekday.WEDNESDAY, todo.getWeekday());
    assertEquals("Category 6", todo.getCategory());
  }

  @Test
  public void testCreateForEvent() {
    ToDo todo = ToDo.create("Event 3", Weekday.THURSDAY, "Category 7", "2 hours", "9:00 AM", false);
    assertTrue(todo instanceof ToDoEvent);
    assertEquals("Event 3", todo.getName());
    assertEquals(Weekday.THURSDAY, todo.getWeekday());
    assertEquals("Category 7", todo.getCategory());
  }

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
  public void testGetByIndex() {
    assertEquals(Category.PERSONAL, Category.getByIndex(0));
    assertEquals(Category.ACADEMIC, Category.getByIndex(1));
    assertEquals(Category.LIFE, Category.getByIndex(2));
    assertEquals(Category.NONE, Category.getByIndex(3));
  }

  @Test
  public void testGetIndex() {
    assertEquals(0, Weekday.SUNDAY.getIndex());
    assertEquals(1, Weekday.MONDAY.getIndex());
    assertEquals(2, Weekday.TUESDAY.getIndex());
    assertEquals(3, Weekday.WEDNESDAY.getIndex());
    assertEquals(4, Weekday.THURSDAY.getIndex());
    assertEquals(5, Weekday.FRIDAY.getIndex());
    assertEquals(6, Weekday.SATURDAY.getIndex());
  }

  @Test
  public void testGetWeekday() {
    assertEquals("SUNDAY", Weekday.SUNDAY.getWeekday());
    assertEquals("MONDAY", Weekday.MONDAY.getWeekday());
    assertEquals("TUESDAY", Weekday.TUESDAY.getWeekday());
    assertEquals("WEDNESDAY", Weekday.WEDNESDAY.getWeekday());
    assertEquals("THURSDAY", Weekday.THURSDAY.getWeekday());
    assertEquals("FRIDAY", Weekday.FRIDAY.getWeekday());
    assertEquals("SATURDAY", Weekday.SATURDAY.getWeekday());
  }

  @Test
  public void testEquals() {
    ToDo task1 = new ToDoTask("Task 1", Weekday.MONDAY, "Personal");
    ToDo task2 = new ToDoTask("Task 1", Weekday.MONDAY, "Personal");
    ToDo task3 = new ToDoTask("Task 2", Weekday.TUESDAY, "Work");

    assertEquals(task1, task2);
    assertNotEquals(task1, task3);
  }

  @Test
  public void testEquals_SameObject() {
    ToDo todo1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    assertTrue(todo1.equals(todo1));
  }

  @Test
  public void testEquals_NullObject() {
    ToDo todo1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    assertFalse(todo1.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    ToDo todo1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    String notaTodo = "Not a ToDo object";
    assertFalse(todo1.equals(notaTodo));
  }

  @Test
  public void testEquals_EqualObjects() {
    ToDo todo1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    ToDo todo2 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    assertTrue(todo1.equals(todo2));
  }

  @Test
  public void testEquals_DifferentName() {
    ToDo todo1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    ToDo todo2 = new ToDoTask("Task 2", Weekday.MONDAY, "Category 1");
    assertFalse(todo1.equals(todo2));
  }

  @Test
  public void testEquals_DifferentWeekday() {
    ToDo todo1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    ToDo todo2 = new ToDoTask("Task 1", Weekday.TUESDAY, "Category 1");
    assertFalse(todo1.equals(todo2));
  }

  @Test
  public void testEquals_DifferentCategory() {
    ToDo todo1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    ToDo todo2 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 2");
    assertFalse(todo1.equals(todo2));
  }
}
