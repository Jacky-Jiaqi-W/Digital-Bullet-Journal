package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the weekday
 */
public enum Weekday {
  SUNDAY(0),
  MONDAY(1),
  TUESDAY(2),
  WEDNESDAY(3),
  THURSDAY(4),
  FRIDAY(5),
  SATURDAY(6);

  private int index;

  /**
   * instantiate the Weekday enum
   *
   * @param index - Weekday enum
   */
  Weekday(int index) {
    this.index = index;
  }

  @JsonCreator
  Weekday(@JsonProperty("weekday") String weekday) {
    this.index = Weekday.valueOf(weekday.toUpperCase()).getIndex(); // check later
  }

  /**
   * getter for the name field
   *
   * @return String
   */
  @JsonGetter("weekday")
  public String getWeekday() {
    return this.name();
  }

  /**
   * Gets the index of this weekday
   *
   * @return index
   */
  public int getIndex() {
    return this.index;
  }

  /**
   * Gets the weekday with the specified index
   *
   * @param index index of the weekday
   * @return weekday with the specified index
   */
  public static Weekday getByIndex(int index) {
    Weekday value = Weekday.values()[index];
    return value;
  }
}


