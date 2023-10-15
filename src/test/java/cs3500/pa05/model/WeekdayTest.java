package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * represent the tests for Weekday
 */
public class WeekdayTest {

  @Test
  public void testGetWeekday() {
    Weekday weekday = Weekday.MONDAY;
    assertEquals("MONDAY", weekday.getWeekday());
  }

  @Test
  public void testGetIndex() {
    Weekday weekday = Weekday.WEDNESDAY;
    assertEquals(3, weekday.getIndex());
  }

  @Test
  public void testGetByIndex() {
    Weekday weekday = Weekday.getByIndex(5);
    assertEquals(Weekday.FRIDAY, weekday);
  }
}