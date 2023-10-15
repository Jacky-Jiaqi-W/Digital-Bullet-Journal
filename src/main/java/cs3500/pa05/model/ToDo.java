package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;


/**
 * Represents an abstract todo class
 */
public abstract class ToDo {
  protected String name;
  protected Weekday weekday;
  protected String category;

  /**
   * Instantiates an abstract todo class
   *
   * @param name     - name
   * @param weekday  - weekday
   * @param category - specified category
   */
  public ToDo(String name, Weekday weekday, String category) {
    this.name = name;
    this.weekday = weekday;
    this.category = category;
  }

  /**
   * creates the ToDo JSON
   *
   * @param name the todo name
   * @param weekday the day of the week
   * @param category the todo category
   * @param duration the time it takes
   * @param startTime the time to start
   * @param completed is it completed?
   *
   * @return ToDo
   */
  @JsonCreator
  public static ToDo create(
      @JsonProperty("name") String name,
      @JsonProperty("weekday") Weekday weekday,
      @JsonProperty("category") String category,
      @JsonProperty("duration") String duration,
      @JsonProperty("start-time") String startTime,
      @JsonProperty("completed") boolean completed) {
    if (duration != null && startTime != null) {
      return new ToDoEvent(name, weekday, category, duration, startTime);
    } else {
      return new ToDoTask(name, weekday, category, completed);
    }
  }

  /**
   * gets the ToDo name
   *
   * @return ToDo name
   */
  @JsonGetter("name")
  public String getName() {
    return this.name;
  }


  /**
   * gets the day of the week
   *
   * @return the weekday
   */
  @JsonGetter("weekday")
  public Weekday getWeekday() {
    return this.weekday;
  }


  /**
   * gets the category
   *
   * @return the category
   */
  @JsonGetter("category")
  public String getCategory() {
    return this.category;
  }


  /**
   * Transfers the todo to string
   *
   * @return todo string
   */
  public abstract String toString();


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    ToDo other = (ToDo) obj;
    return Objects.equals(name, other.name)
        && weekday == other.weekday
        && Objects.equals(category, other.category);
  }
}