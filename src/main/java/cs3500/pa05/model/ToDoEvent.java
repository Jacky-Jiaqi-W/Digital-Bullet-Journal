package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * an event for each day
 */
public class ToDoEvent extends ToDo {

  private String duration;
  private String startTime;

  /**
   * Instantiate an ToDoEvent class
   *
   * @param name      - the name of the event
   * @param day       - the day of the week
   * @param category  - personal, academic, or work category
   */
  @JsonCreator
  public ToDoEvent(
      @JsonProperty("name") String name,
      @JsonProperty("weekday") Weekday day,
      @JsonProperty("category") String category,
      @JsonProperty("duration") String duration,
      @JsonProperty("start-time") String startTime) {
    super(name, day, category);
    this.duration = duration;
    this.startTime = startTime;
  }

  /**
   * getter for the duration field
   *
   * @return the duration of the event
   */
  @JsonGetter("duration")
  public String getDuration() {
    return this.duration;
  }


  /**
   * getter for the startTime field
   *
   * @return the start time of the event
   */
  @JsonGetter("start-time")
  public String getStartTime() {
    return this.startTime;
  }

  /**
   * Transfers the task to string
   *
   * @return event string
   */
  @Override
  public String toString() {
    String event = "";
    event = this.startTime + " " + this.duration + " " + this.name + " " + this.category;
    return event;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    if (!super.equals(obj)) {
      return false;
    }
    ToDoEvent other = (ToDoEvent) obj;
    return Objects.equals(duration, other.duration)
        && Objects.equals(startTime, other.startTime);
  }
} 
