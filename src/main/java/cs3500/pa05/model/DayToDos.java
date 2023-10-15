package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a day todos
 */
public class DayToDos {
  private Weekday weekday;
  private List<ToDo> toDos;

  /**
   * Instantiates a weekday todos
   *
   * @param weekday - weekday
   * @param toDos   - todos
   */
  @JsonCreator
  public DayToDos(
      @JsonProperty("weekday") Weekday weekday,
      @JsonProperty("todos") List<ToDo> toDos) {
    this.weekday = weekday;
    this.toDos = toDos;
  }

  /**
   * Gets the weekday
   *
   * @return weekday
   */
  @JsonGetter("weekday")
  public Weekday getWeekday() {
    return this.weekday;
  }

  /**
   * Gets the list of todos
   *
   * @return list of todos
   */
  @JsonGetter("todos")
  public List<ToDo> getToDos() {
    return this.toDos;
  }

  /**
   * Adds the given todo
   *
   * @param todo given todo
   */
  public void addToDo(ToDo todo) {
    this.toDos.add(todo);
  }

  /**
   * get all tasks from the days todo list
   *
   * @return List of TodoTask
   */
  public List<ToDo> taskList() {
    ArrayList<ToDo> tasks = new ArrayList<>();
    for (ToDo t : toDos) {
      if (checkTask(t)) {
        tasks.add(t);
      }
    }
    return tasks;
  }


  /**
   * get all events from the days todo list
   *
   * @return List of Todo events
   */
  public List<ToDo> eventsList() {
    ArrayList<ToDo> events = new ArrayList<>();
    for (ToDo t : toDos) {
      if (!checkTask(t)) {
        events.add(t);
      }
    }
    return events;
  }


  /**
   * help check if the todo is a ToDoTask or not
   *
   * @param todo given todo
   * @return a boolean value
   */
  public boolean checkTask(ToDo todo) {
    String todoString = todo.toString();
    if (todoString.contains("Completed")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * get the total number of events
   *
   * @return an integer value
   */
  public int totalEvents() {
    int totalEvents = 0;
    for (ToDo t : toDos) {
      if (!checkTask(t)) {
        totalEvents++;
      }
    }
    return totalEvents;
  }

  /**
   * get the total number of tasks
   *
   * @return an integer value
   */
  public int totalTasks() {
    int totalTasks = 0;
    for (ToDo t : toDos) {
      if (checkTask(t)) {
        totalTasks++;
      }
    }
    return totalTasks;
  }

  /**
   * return the total tasks completed
   *
   * @return the total tasks completed in double format
   */
  public double totalTasksCompleted() {
    double completedTasks = 0.0;
    for (ToDo toDo : this.taskList()) {
      String s = toDo.toString();
      if (!s.contains("Not Completed")) {
        completedTasks++;
      }
    }
    return completedTasks;
  }

  /**
   * get the percent of completed tasks
   *
   * @return a string value
   */
  public String completedPercentage() {
    double percent = (totalTasksCompleted() / taskList().size()) * 100;
    DecimalFormat df = new DecimalFormat("0.00");
    return df.format(percent) + "%";
  }

  /**
   * checks if the DayToDos contain ToDo
   *
   * @param t - ToDo
   * @return true if it does contain
   */
  public boolean containToDo(ToDo t) {
    boolean result = false;
    for (ToDo todo : this.toDos) {
      if (todo.equals(t)) {
        result = true;
        break;
      }
    }
    return result;
  }

}
