package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToDoTaskTest {

  @Test
  public void isCompleted() {
    ToDoTask task = new ToDoTask("Task 1", Weekday.MONDAY, "Personal");
    assertFalse(task.isCompleted());
  }

  @Test
  public void setCompleted() {
    ToDoTask task = new ToDoTask("Task 1", Weekday.MONDAY, "Personal");
    assertFalse(task.isCompleted());

    task.mark(true);
    assertTrue(task.isCompleted());

    task.mark(false);
    assertFalse(task.isCompleted());
  }

  @Test
  public void toStringTest() {
    ToDoTask task = new ToDoTask("Task 1", Weekday.MONDAY, "Personal");
    String expected = "Not Completed  Task 1 Personal";
    assertEquals(expected, task.toString());

    task.mark(true);
    expected = "Completed  Task 1 Personal";
    assertEquals(expected, task.toString());
  }

  @Test
  public void testEquals() {
    ToDoTask task1 = new ToDoTask("Task 1", Weekday.MONDAY, "Personal");
    ToDoTask task2 = new ToDoTask("Task 1", Weekday.MONDAY, "Personal");
    ToDoTask task3 = new ToDoTask("Task 2", Weekday.TUESDAY, "Work");

    assertEquals(task1, task2);
    assertNotEquals(task1, task3);
  }

  @Test
  public void testToDoTaskEquals_Reflexivity() {
    ToDoTask task = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");

    assertTrue(task.equals(task));
  }

  @Test
  public void testToDoTaskEquals_NullObject() {
    ToDoTask task = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");

    assertFalse(task.equals(null));
  }

  @Test
  public void testToDoTaskEquals_DifferentClass() {
    ToDoTask task = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    String other = "Not a task";

    assertFalse(task.equals(other));
  }

  @Test
  public void testToDoTaskEquals_DifferentAttributes() {
    ToDoTask task1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    ToDoTask task2 = new ToDoTask("Task 2", Weekday.TUESDAY, "Category 2");

    assertFalse(task1.equals(task2));
  }

  @Test
  public void testToDoTaskEquals_SameAttributes() {
    ToDoTask task1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    ToDoTask task2 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");

    assertTrue(task1.equals(task2));
  }
}