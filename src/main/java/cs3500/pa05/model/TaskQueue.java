package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;
import javafx.scene.layout.VBox;

/**
 * Represents task queue
 */
public class TaskQueue {
  private List<ToDoTask> tasks;

  /**
   * Instantiates a task queue
   *
   * @param tasks - list of tasks
   */
  @JsonCreator
  public TaskQueue(
      @JsonProperty("tasks") List<ToDoTask> tasks) {
    this.tasks = tasks;
  }
}
