package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ToDoEventTest {

  @Test
  public void getDuration() {
    ToDoEvent event = new ToDoEvent("Event 1", Weekday.MONDAY, "Academic",
        "2 hours", "10:00 AM");
    assertEquals("2 hours", event.getDuration());
  }

  @Test
  public void getStartTime() {
    ToDoEvent event = new ToDoEvent("Event 1", Weekday.MONDAY, "Academic",
        "2 hours", "10:00 AM");
    assertEquals("10:00 AM", event.getStartTime());
  }

  @Test
  public void toStringTest() {
    ToDoEvent event = new ToDoEvent("Event 1", Weekday.MONDAY, "Academic",
        "2 hours", "10:00 AM");
    String expected = "10:00 AM 2 hours Event 1 Academic";
    assertEquals(expected, event.toString());
  }

  @Test
  public void testEquals() {
    ToDoEvent event1 = new ToDoEvent("Event 1", Weekday.MONDAY, "Academic",
        "2 hours", "10:00 AM");
    ToDoEvent event2 = new ToDoEvent("Event 1", Weekday.MONDAY, "Academic",
        "2 hours", "10:00 AM");
    ToDoEvent event3 = new ToDoEvent("Event 2", Weekday.TUESDAY, "Personal",
        "1 hour", "9:00 AM");

    assertEquals(event1, event2);
    assertNotEquals(event1, event3);
  }

  @Test
  public void testToDoEventEquals_Reflexivity() {
    ToDoEvent event = new ToDoEvent("Event 1", Weekday.MONDAY, "Category 1",
        "2 hours", "10:00 AM");

    assertTrue(event.equals(event));
  }

  @Test
  public void testToDoEventEquals_NullObject() {
    ToDoEvent event = new ToDoEvent("Event 1", Weekday.MONDAY, "Category 1",
        "2 hours", "10:00 AM");

    assertFalse(event.equals(null));
  }

  @Test
  public void testToDoEventEquals_DifferentClass() {
    ToDoEvent event = new ToDoEvent("Event 1", Weekday.MONDAY, "Category 1",
        "2 hours", "10:00 AM");
    String other = "Not an event";

    assertFalse(event.equals(other));
  }

  @Test
  public void testToDoEventEquals_DifferentAttributes() {
    ToDoEvent event1 = new ToDoEvent("Event 1", Weekday.MONDAY,
        "Category 1", "2 hours", "10:00 AM");
    ToDoEvent event2 = new ToDoEvent("Event 2", Weekday.TUESDAY,
        "Category 2", "1 hour", "2:00 PM");

    assertFalse(event1.equals(event2));
  }

  @Test
  public void testToDoEventEquals_SameAttributes() {
    ToDoEvent event1 = new ToDoEvent("Event 1", Weekday.MONDAY,
        "Category 1", "2 hours", "10:00 AM");
    ToDoEvent event2 = new ToDoEvent("Event 1", Weekday.MONDAY,
        "Category 1", "2 hours", "10:00 AM");

    assertTrue(event1.equals(event2));
  }
}