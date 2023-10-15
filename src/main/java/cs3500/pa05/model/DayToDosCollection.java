package cs3500.pa05.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of day todos
 */
public class DayToDosCollection {
  private List<DayToDos> dayToDosList;

  /**
   * Instantiates a day todos collection
   *
   * @param dayToDos day todos list
   */
  public DayToDosCollection(List<DayToDos> dayToDos) {
    this.dayToDosList = dayToDos;
  }

  /**
   * Instantiates an empty day todos collection
   */
  public DayToDosCollection() {
    this.dayToDosList = this.initDayToDosList();
  }

  /**
   * Gets the day todos list
   *
   * @return day todos list
   */
  public List<DayToDos> getDayToDosList() {

    return this.dayToDosList;
  }

  /**
   * Initializes an empty day todos list
   *
   * @return an empty day todos list
   */
  public List<DayToDos> initDayToDosList() {
    List<DayToDos> emptyDayToDosList = new ArrayList<>();
    DayToDos dayToDos;
    for (Weekday weekday : Weekday.values()) {
      dayToDos = new DayToDos(weekday, new ArrayList<>());
      emptyDayToDosList.add(dayToDos);
    }
    return emptyDayToDosList;
  }

  /**
   * Reorders the day todos list starting from the given weekday
   *
   * @param weekday given weekday
   * @return reordered list
   */
  public List<DayToDos> startFromDay(Weekday weekday) {
    List<DayToDos> newDayToDosList = new ArrayList<>();
    int index = 0;
    for (int i = 0; i < dayToDosList.size(); i++) {
      if (dayToDosList.get(i).getWeekday() == weekday) {
        index = i;
      }
    }
    for (int i = index; i < dayToDosList.size(); i++) {
      newDayToDosList.add(dayToDosList.get(i));
    }
    for (int i = 0; i < index; i++) {
      newDayToDosList.add(dayToDosList.get(i));
    }
    return newDayToDosList;
  }

  /**
   * Gets the task queue
   *
   * @return the task queue
   */
  public List<ToDo> getTaskQueue() {
    List<ToDo> tasks = new ArrayList<>();
    for (DayToDos dayToDos : this.dayToDosList) {
      tasks.addAll(dayToDos.taskList());
    }
    return tasks;
  }

  /**
   * Gets the event queue
   *
   * @return the event queue
   */
  public List<ToDo> getEventQueue() {
    List<ToDo> events = new ArrayList<>();
    for (DayToDos dayToDos : this.dayToDosList) {
      events.addAll(dayToDos.eventsList());
    }
    return events;
  }


  /**
   * counts the total tasks
   *
   * @return the total number of tasks
   */
  public int countTotalTasks() {
    int totalTasks = 0;
    totalTasks += getTaskQueue().size();
    return totalTasks;
  }

  /**
   * counts the total events
   *
   * @return the total number of events
   */
  public int countTotalEvents() {
    int totalEvents = 0;
    totalEvents += getEventQueue().size();
    return totalEvents;
  }

  /**
   * return the completed percentage in the list of DayToDos
   *
   * @return the completed percentage in String format
   */
  public String completedPercentage() {
    double totalTaskCompleted = 0.0;
    for (DayToDos dayToDos : this.dayToDosList) {
      totalTaskCompleted += dayToDos.totalTasksCompleted();
    }
    double percentage = (totalTaskCompleted / countTotalTasks()) * 100;
    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    String formattedNumber = decimalFormat.format(percentage);
    return formattedNumber;
  }

  /**
   * Checks if the provided max events and max tasks are already exceeded
   *
   * @param maxEvents max events
   * @param maxTasks max tasks
   * @return true if the provided max events and max tasks are already exceeded
   */
  public boolean alreadyExceeds(int maxEvents, int maxTasks) {
    int actualMaxEvents = 0;
    int actualMaxTasks = 0;
    for (DayToDos dayToDos : this.dayToDosList) {
      if (dayToDos.totalTasks() > actualMaxTasks) {
        actualMaxTasks = dayToDos.totalTasks();
      }
      if (dayToDos.totalEvents() > actualMaxEvents) {
        actualMaxEvents = dayToDos.totalEvents();
      }
    }
    return actualMaxEvents > maxEvents || actualMaxTasks > maxTasks;
  }

  /**
   * give a weekday and get the total number of ToDoTask at that day
   *
   * @param day  weekday
   * @return an int value
   */
  public int countDayTask(Weekday day) {
    for (DayToDos d : this.dayToDosList) {
      if (d.getWeekday().equals(day)) {
        return d.totalTasks();
      }
    }
    return -1;
  }

  /**
   * give a weekday and get the total number of ToDoEvent at that day
   *
   * @param day weekday
   * @return an int value
   */
  public int countDayEvent(Weekday day) {
    for (DayToDos d : this.dayToDosList) {
      if (d.getWeekday().equals(day)) {
        return d.totalEvents();
      }
    }
    return -1;
  }

  /**
   * checks if the todo exist
   *
   * @param t todo
   * @return true if the todo exist
   */
  public boolean checkTodoExist(ToDo t) {
    Weekday day = t.getWeekday();
    for (DayToDos d : this.dayToDosList) {
      if (d.getWeekday().equals(day)) {
        return d.containToDo(t);
      }
    }
    return false;
  }

  /**
   * Checks if the index of todo for the given weekday is out of bounds
   *
   * @param index todo index
   * @param weekday given weekday
   * @return true
   */
  public boolean outOfBounds(int index, Weekday weekday) {
    for (DayToDos d : this.dayToDosList) {
      if (d.getWeekday().equals(weekday)) {
        return index > d.getToDos().size();
      }
    }
    return false;
  }

  /**
   * Deletes the todo of the given index in the given weekday
   *
   * @param index    - todo index
   * @param weekday  - given weekday
   */
  public void delete(int index, Weekday weekday) {
    for (DayToDos d : this.dayToDosList) {
      if (d.getWeekday().equals(weekday)) {
        d.getToDos().remove(index - 1);
      }
    }
  }

  /**
   * "replace" or marks the task of the given index in the given weekday
   *
   * @param indexInt - task index
   * @param weekday  - given weekday
   *
   */
  public void replace(int indexInt, Weekday weekday) {
    for (DayToDos d : this.dayToDosList) {
      if (d.getWeekday().equals(weekday)) {
        List<ToDo> tasks = d.getToDos();
        if (indexInt > 0 && indexInt <= tasks.size()) {
          ToDoTask task = (ToDoTask) tasks.get(indexInt - 1);
          if (!task.isCompleted()) {
            task.mark(true);
          } else {
            task.mark(false);
          }
        }
        break;
      }
    }
  }



}
