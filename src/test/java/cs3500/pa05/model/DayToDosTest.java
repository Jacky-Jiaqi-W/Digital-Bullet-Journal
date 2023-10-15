package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DayToDosTest {
  private DayToDos dayToDos;
  private DayToDos dayToDos2;
  private DayToDos dayToDos3;
  private DayToDos dayToDos4;
  private List<ToDo> toDos;
  private List<ToDo> toDos2;
  private List<ToDo> toDos3;
  private List<ToDo> toDos4;

  @BeforeEach
  public void setUp() {
    toDos = new ArrayList<>();
    toDos.add(new ToDoTask("Task 1", Weekday.MONDAY, "Personal"));
    toDos.add(new ToDoTask("Task 2", Weekday.MONDAY, "Work"));
    toDos.add(new ToDoEvent("Event 1", Weekday.MONDAY, "Academic", "2 hours", "10:00 AM"));
    toDos.add(new ToDoEvent("Event 2", Weekday.MONDAY, "Personal", "1 hour", "2:00 PM"));
    toDos2 = new ArrayList<>();
    ToDoTask t = new ToDoTask("Task 1", Weekday.MONDAY, "Personal");
    t.mark(true);
    toDos2.add(t);
    toDos2.add(new ToDoTask("Task 2", Weekday.MONDAY, "Work"));
    toDos2.add(new ToDoEvent("Event 1", Weekday.MONDAY, "Academic", "2 hours", "10:00 AM"));
    toDos2.add(new ToDoEvent("Event 2", Weekday.MONDAY, "Personal", "1 hour", "2:00 PM"));
    toDos3 = new ArrayList<>();
    toDos3.add(new ToDoTask("Task 1", Weekday.MONDAY, "Personal"));
    toDos4 = new ArrayList<>();
    toDos4.add(new ToDoEvent("Event 2", Weekday.MONDAY, "Personal", "1 hour", "2:00 PM"));


    dayToDos = new DayToDos(Weekday.MONDAY, toDos);
    dayToDos2 = new DayToDos(Weekday.MONDAY, toDos2);
    dayToDos3 = new DayToDos(Weekday.MONDAY, toDos3);
    dayToDos4 = new DayToDos(Weekday.MONDAY, toDos4);
  }

  @Test
  public void getWeekday() {
    assertEquals(Weekday.MONDAY, dayToDos.getWeekday());
  }

  @Test
  public void getToDos() {
    assertEquals(toDos, dayToDos.getToDos());
  }

  @Test
  public void addToDo() {
    ToDo newToDo = new ToDoTask("Task 3", Weekday.MONDAY, "Work");
    dayToDos.addToDo(newToDo);
    assertTrue(dayToDos.getToDos().contains(newToDo));
  }

  @Test
  public void getTasks() {
    List<ToDo> tasks = dayToDos.taskList();
    assertEquals(2, tasks.size());
    assertTrue(tasks.get(0) instanceof ToDoTask);
    assertTrue(tasks.get(1) instanceof ToDoTask);
  }

  @Test
  public void getEvents() {
    List<ToDo> events = dayToDos.eventsList();
    assertEquals(2, events.size());
    assertTrue(events.get(0) instanceof ToDoEvent);
    assertTrue(events.get(1) instanceof ToDoEvent);
  }

  @Test
  public void totalEvents() {
    assertEquals(2, dayToDos.totalEvents());
    assertEquals(0, dayToDos3.totalEvents());
  }

  @Test
  public void totalTasks() {
    assertEquals(0, dayToDos4.totalTasks());
    assertEquals(2, dayToDos.totalTasks());
  }

  @Test
  public void completePercent() {
    String expected = "0.00%";
    String expected2 = "50.00%";
    assertEquals(expected, dayToDos.completedPercentage());
    assertEquals(expected2, dayToDos2.completedPercentage());
  }

  @Test
  public void testContainToDo() {
    ToDoTask task1 = new ToDoTask("Task 1", Weekday.MONDAY, "Personal");
    ToDoEvent event1 = new ToDoEvent("Event 1", Weekday.MONDAY, "Academic", "2 hours", "10:00 AM");

    assertTrue(dayToDos.containToDo(task1));
    assertTrue(dayToDos.containToDo(event1));

    assertFalse(dayToDos3.containToDo(event1));
    assertFalse(dayToDos4.containToDo(task1));
  }

  @Test
  public void testCompleted() {

    assertEquals(1.0, dayToDos2.totalTasksCompleted());
  }

  @Test
  public void testTotalTasksCompleted() {
    assertEquals(0.0, dayToDos.totalTasksCompleted()); // No completed tasks
    assertEquals(1.0, dayToDos2.totalTasksCompleted()); // 1 completed task
    assertEquals(0.0, dayToDos3.totalTasksCompleted()); // No tasks
  }


}