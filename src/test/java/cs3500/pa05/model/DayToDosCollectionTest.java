package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * test for DayToDosCollection
 */
public class DayToDosCollectionTest {

  private DayToDosCollection dayToDosCollection;
  private List<DayToDos> dayToDosList;

  /**
   * setup()
   */
  @BeforeEach
  public void setUp() {
    dayToDosList = new ArrayList<>();
    for (Weekday weekday : Weekday.values()) {
      dayToDosList.add(new DayToDos(weekday, new ArrayList<>()));
    }
    dayToDosCollection = new DayToDosCollection(dayToDosList);
  }


  @Test
  public void testGetDayToDosList() {
    assertEquals(dayToDosList, dayToDosCollection.getDayToDosList());
  }

  @Test
  public void testInitDayToDosList() {
    List<DayToDos> emptyDayToDosList = dayToDosCollection.initDayToDosList();
    assertEquals(7, emptyDayToDosList.size());
    for (DayToDos dayToDos : emptyDayToDosList) {
      assertTrue(dayToDos.getToDos().isEmpty());
    }
  }

  @Test
  public void testStartFromDay() {
    List<DayToDos> reorderedList = dayToDosCollection.startFromDay(Weekday.THURSDAY);
    assertEquals(Weekday.THURSDAY, reorderedList.get(0).getWeekday());
    assertEquals(Weekday.FRIDAY, reorderedList.get(1).getWeekday());
    assertEquals(Weekday.SATURDAY, reorderedList.get(2).getWeekday());
    assertEquals(Weekday.SUNDAY, reorderedList.get(3).getWeekday());
    assertEquals(Weekday.MONDAY, reorderedList.get(4).getWeekday());
    assertEquals(Weekday.TUESDAY, reorderedList.get(5).getWeekday());
    assertEquals(Weekday.WEDNESDAY, reorderedList.get(6).getWeekday());
  }

  @Test
  public void testGetTaskQueue() {
    ToDoTask task1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    ToDoTask task2 = new ToDoTask("Task 2", Weekday.TUESDAY, "Category 2");
    dayToDosList.get(0).addToDo(task1);
    dayToDosList.get(1).addToDo(task2);

    List<ToDo> taskQueue = dayToDosCollection.getTaskQueue();
    assertEquals(2, taskQueue.size());
    assertTrue(taskQueue.contains(task1));
    assertTrue(taskQueue.contains(task2));
  }

  @Test
  public void testGetEventQueue() {
    ToDoEvent event1 = new ToDoEvent("Event 1", Weekday.WEDNESDAY,
        "Category 1", "2 hours", "10:00 AM");
    ToDoEvent event2 = new ToDoEvent("Event 2", Weekday.THURSDAY,
        "Category 2", "1 hour", "2:00 PM");
    dayToDosList.get(2).addToDo(event1);
    dayToDosList.get(3).addToDo(event2);

    List<ToDo> eventQueue = dayToDosCollection.getEventQueue();
    assertEquals(2, eventQueue.size());
    assertTrue(eventQueue.contains(event1));
    assertTrue(eventQueue.contains(event2));
  }

  @Test
  public void testCountTotalTasks() {
    dayToDosList.get(0).addToDo(new ToDoTask("Task 1", Weekday.MONDAY, "Category 1"));
    dayToDosList.get(1).addToDo(new ToDoTask("Task 2", Weekday.TUESDAY, "Category 2"));
    dayToDosList.get(2).addToDo(new ToDoTask("Task 3", Weekday.WEDNESDAY, "Category 3"));

    int totalTasks = dayToDosCollection.countTotalTasks();
    assertEquals(3, totalTasks);
  }

  @Test
  public void testCountTotalEvents() {
    dayToDosList.get(0).addToDo(new ToDoEvent("Event 1", Weekday.MONDAY,
        "Category 1", "2 hours", "10:00 AM"));
    dayToDosList.get(1).addToDo(new ToDoEvent("Event 2", Weekday.TUESDAY,
        "Category 2", "1 hour", "2:00 PM"));
    dayToDosList.get(2).addToDo(new ToDoEvent("Event 3", Weekday.WEDNESDAY,
        "Category 3", "3 hours", "4:00 PM"));

    int totalEvents = dayToDosCollection.countTotalEvents();
    assertEquals(3, totalEvents);
  }

  @Test
  public void testCompletedPercentage() {
    ToDoTask task1 = new ToDoTask("Task 1", Weekday.MONDAY, "Category 1");
    ToDoTask task2 = new ToDoTask("Task 2", Weekday.MONDAY, "Category 2");
    task2.mark(true);
    ToDoTask task3 = new ToDoTask("Task 3", Weekday.TUESDAY, "Category 3");
    dayToDosList.get(0).addToDo(task1);
    dayToDosList.get(0).addToDo(task2);
    dayToDosList.get(1).addToDo(task3);

    String percentage = dayToDosCollection.completedPercentage();
    assertEquals("33.33", percentage);
  }

  @Test
  public void testAlreadyExceeds() {
    dayToDosList.get(0).addToDo(new ToDoTask("Task 1", Weekday.MONDAY, "Category 1"));
    dayToDosList.get(1).addToDo(new ToDoTask("Task 2", Weekday.MONDAY, "Category 2"));
    dayToDosList.get(2).addToDo(new ToDoEvent("Event 1", Weekday.MONDAY,
        "Category 3", "2 hours", "10:00 AM"));

    assertTrue(dayToDosCollection.alreadyExceeds(0, 0));
    assertFalse(dayToDosCollection.alreadyExceeds(3, 2));
  }

  @Test
  public void testCountDayTask() {
    DayToDos dayToDos = dayToDosList.get(0);
    dayToDos.addToDo(new ToDoTask("Task 1", dayToDos.getWeekday(), "Category 1"));
    dayToDos.addToDo(new ToDoTask("Task 2", dayToDos.getWeekday(), "Category 2"));
    dayToDos.addToDo(new ToDoTask("Task 3", dayToDos.getWeekday(), "Category 3"));

    int count = dayToDosCollection.countDayTask(dayToDos.getWeekday());
    assertEquals(3, count);
  }

  @Test
  public void testCountDayEvent() {
    DayToDos dayToDos = dayToDosList.get(0);
    dayToDos.addToDo(new ToDoEvent("Event 1", dayToDos.getWeekday(),
        "Category 1", "2 hours", "10:00 AM"));
    dayToDos.addToDo(new ToDoEvent("Event 2", dayToDos.getWeekday(),
        "Category 2", "1 hour", "2:00 PM"));
    dayToDos.addToDo(new ToDoEvent("Event 3", dayToDos.getWeekday(),
        "Category 3", "3 hours", "4:00 PM"));

    int count = dayToDosCollection.countDayEvent(dayToDos.getWeekday());
    assertEquals(3, count);
  }

  @Test
  public void testCheckToDoExist() {
    DayToDos dayToDos = dayToDosList.get(0);
    ToDoTask task1 = new ToDoTask("Task 1", dayToDos.getWeekday(), "Category 1");
    ToDoTask task2 = new ToDoTask("Task 2", dayToDos.getWeekday(), "Category 2");
    dayToDos.addToDo(task1);

    assertTrue(dayToDosCollection.checkTodoExist(task1));
    assertFalse(dayToDosCollection.checkTodoExist(task2));
  }


  /**
   *
   */
  @Test
  public void testOutOfBounds() {
    DayToDos dayToDos = dayToDosList.get(0);
    ToDoTask task1 = new ToDoTask("Task 1", dayToDos.getWeekday(), "Category 1");
    ToDoTask task2 = new ToDoTask("Task 2", dayToDos.getWeekday(), "Category 2");
    dayToDos.addToDo(task1);
    dayToDos.addToDo(task2);

    assertFalse(dayToDosCollection.outOfBounds(0, dayToDos.getWeekday()));
    assertFalse(dayToDosCollection.outOfBounds(1, dayToDos.getWeekday()));
    assertTrue(dayToDosCollection.outOfBounds(3, dayToDos.getWeekday()));
  }

  @Test
  public void testDelete() {
    DayToDos dayToDos = dayToDosList.get(0);
    ToDoTask task1 = new ToDoTask("Task 1", dayToDos.getWeekday(), "Category 1");
    ToDoTask task2 = new ToDoTask("Task 2", dayToDos.getWeekday(), "Category 2");
    dayToDos.addToDo(task1);
    dayToDos.addToDo(task2);

    assertTrue(dayToDosCollection.checkTodoExist(task1));
    assertTrue(dayToDosCollection.checkTodoExist(task2));

    dayToDosCollection.delete(1, dayToDos.getWeekday());

    assertFalse(dayToDosCollection.checkTodoExist(task1));
    assertTrue(dayToDosCollection.checkTodoExist(task2));
  }

  @Test
  public void testReplace() {
    DayToDos dayToDos = dayToDosList.get(0);
    ToDoTask task1 = new ToDoTask("Task 1", dayToDos.getWeekday(), "Category 1");
    dayToDos.addToDo(task1);

    assertEquals(1, dayToDos.getToDos().size());
    assertTrue(dayToDos.getToDos().contains(task1));

    dayToDosCollection.replace(0, dayToDos.getWeekday());

    assertEquals(1, dayToDos.getToDos().size());
    assertTrue(dayToDos.getToDos().contains(task1));
    ToDoTask task2 = new ToDoTask("Task 2", dayToDos.getWeekday(), "Category 2");
    assertFalse(dayToDos.getToDos().contains(task2));
  }

  @Test
  public void testReplace_IncompleteTask() {
    ToDoTask task = new ToDoTask("Task", Weekday.MONDAY, "Category");
    dayToDosList.get(0).addToDo(task);

    dayToDosCollection.replace(1, Weekday.MONDAY);

    assertEquals(false, task.isCompleted());
  }

  @Test
  public void testReplace_CompletedTask() {
    ToDoTask task = new ToDoTask("Task", Weekday.MONDAY, "Category");
    task.mark(true);
    dayToDosList.get(0).addToDo(task);

    dayToDosCollection.replace(1, Weekday.MONDAY);

    assertEquals(true, task.isCompleted());
  }

  @Test
  public void testReplace_OutOfBoundsIndex() {
    ToDoTask task = new ToDoTask("Task", Weekday.MONDAY, "Category");
    dayToDosList.get(0).addToDo(task);

    dayToDosCollection.replace(2, Weekday.MONDAY);

    assertEquals(false, task.isCompleted());
  }

  @Test
  public void testReplace_NonexistentWeekday() {
    ToDoTask task = new ToDoTask("Task", Weekday.MONDAY, "Category");
    dayToDosList.get(0).addToDo(task);

    dayToDosCollection.replace(1, Weekday.FRIDAY);

    assertEquals(false, task.isCompleted());
  }

  /**
   * Checks if the index of todo for the given weekday is out of bounds
   *
   * @param index   todo index
   * @param weekday given weekday
   * @return true if the index is out of bounds, false otherwise
   */
  public boolean outOfBounds(int index, Weekday weekday) {
    for (DayToDos d : this.dayToDosList) {
      if (d.getWeekday().equals(weekday)) {
        return index >= d.getToDos().size(); // Modified condition
      }
    }
    return true; // Return true if the weekday is not found
  }

  /**
   * Checks if the todo exists
   *
   * @param t todo
   * @return true if the todo exists, false otherwise
   */
  public boolean checkTodoExist(ToDo t) {
    Weekday day = t.getWeekday();
    for (DayToDos d : this.dayToDosList) {
      if (d.getWeekday().equals(day)) {
        return d.containToDo(t);
      }
    }
    return false; // Return false if the weekday is not found
  }





}

