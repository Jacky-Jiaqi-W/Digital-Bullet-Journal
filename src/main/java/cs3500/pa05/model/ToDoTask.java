package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * tasks for each day
 */
public class ToDoTask extends ToDo {
  private boolean completed;

  /**
   * Instantiate a ToDoTask class
   *
   * @param name      - the name of the task
   * @param weekday   - the weekday of the week
   * @param category  - personal, academic, or work category
   */
  @JsonCreator
  public ToDoTask(
      @JsonProperty("name") String name,
      @JsonProperty("weekday") Weekday weekday,
      @JsonProperty("categories") String category,
      @JsonProperty ("completed") boolean completed) {
    super(name, weekday, category);
    this.completed = completed;
  }

  public ToDoTask(String name, Weekday weekday, String category) {
    this(name, weekday, category, false);
  }

  /**
   * Transfers the task to string
   *
   * @return task string
   */
  @Override
  public String toString() {
    String task = "";
    if (this.completed) {
      task = task + "Completed ";
    } else {
      task = task + "Not Completed ";
    }
    task = task + " " + name + " " + this.category;
    return task;
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
    ToDoTask other = (ToDoTask) obj;
    return completed == other.completed;
  }


  /**
   * is the task completed?
   *
   * @return true if task is completed
   */
  @JsonGetter("completed")
  public boolean isCompleted() {
    return this.completed;
  }

  /**
   * mark the task completed
   *
   */
  public void mark(boolean b) {
    this.completed = b;
  }
}
