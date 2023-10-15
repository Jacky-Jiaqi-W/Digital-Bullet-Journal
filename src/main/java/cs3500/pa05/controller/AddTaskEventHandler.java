package cs3500.pa05.controller;

import cs3500.pa05.model.AutoTagUtils;
import cs3500.pa05.model.ToDo;
import cs3500.pa05.model.ToDoTask;
import cs3500.pa05.model.Weekday;
import cs3500.pa05.view.TaskCreationDialog;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;

/**
 * Add task button event handler
 */
public class AddTaskEventHandler implements EventHandler {
  private BulletJournalController controller;
  private boolean weekdayTaskName;
  private boolean category;
  private boolean number;
  private boolean repeated;
  private AutoTagUtils autoTagUtils;

  /**
   * Instantiates an event handler for adding task
   */
  public AddTaskEventHandler(BulletJournalController controller) {
    this.controller = controller;
    this.weekdayTaskName = false;
    this.category = false;
    this.number = false;
    this.repeated = false;
    this.autoTagUtils = new AutoTagUtils();
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    TaskCreationDialog taskCreationDialog = new TaskCreationDialog(this.controller.getCategories()
          .getCategoriesAsString());
    if (this.weekdayTaskName) {
      taskCreationDialog.setHeaderText("WEEKDAY OR TASK NAME IS EMPTY. PLEASE TRY AGAIN: ");
    }
    if (this.category) {
      taskCreationDialog.setHeaderText("MUST ENTER VALID AUTO TAG OR SELECT CATEGORY. "
          + "PLEASE TRY AGAIN: ");
    }
    if (this.number) {
      taskCreationDialog.setHeaderText("YOU CAN'T EXCEED THE MAXIMUM. PLEASE TRY AGAIN: ");
    }
    if (this.repeated) {
      taskCreationDialog.setHeaderText("THE TASK ALREADY EXISTS. PLEASE TRY AGAIN: ");
    }
    Optional<ButtonType> result = taskCreationDialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      RadioButton weekday = taskCreationDialog.getSelectedWeekday();
      RadioButton category = taskCreationDialog.getSelectedCategory();
      String taskName = taskCreationDialog.getToDoName();
      if (this.validateWeekdayTaskNameNotEmpty(weekday, taskName)) {
        if ((this.autoTagUtils.containsValidAutoTag(taskName)
            && !this.validateCategoryNotEmpty(category)) || (!taskName.contains("#")
            && this.validateCategoryNotEmpty(category))) {
          this.handleHelper(weekday, category, taskName, event);
        } else {
          this.weekdayTaskName = false;
          this.category = true;
          this.number = false;
          this.repeated = false;
          this.handle(event);
        }
      } else {
        this.weekdayTaskName = true;
        this.category = false;
        this.number = false;
        this.repeated = false;
        this.handle(event);
      }
    } else {
      this.weekdayTaskName = false;
      this.category = false;
      this.number = false;
      this.repeated = false;
    }
  }

  /**
   * Handle helper
   *
   * @param weekday weekday
   * @param category category
   * @param taskName task name
   * @param event event
   */
  private void handleHelper(RadioButton weekday, RadioButton category, String taskName,
                            Event event) {
    if (this.controller.availableDayTask(Weekday.valueOf(weekday.getText()))) {
      if (this.autoTagUtils.containsValidAutoTag(taskName)) {
        if (!this.controller.getCategories().contains(this.autoTagUtils
            .getCategoryName(taskName))) {
          ToDo task = new ToDoTask(this.autoTagUtils.getTaskName(taskName),
              Weekday.valueOf(weekday.getText()), this.autoTagUtils.getCategoryName(taskName));
          this.controller.addToDo(task);
          this.controller.getCategories().addCategory(this.autoTagUtils.getCategoryName(taskName));
          this.controller.addTasks(task);
          this.controller.updateWeeklySpread();
          this.weekdayTaskName = false;
          this.category = false;
          this.number = false;
          this.repeated = false;
        } else {
          ToDo task = new ToDoTask(this.autoTagUtils.getTaskName(taskName),
              Weekday.valueOf(weekday.getText()), this.autoTagUtils.getCategoryName(taskName));
          this.handleRepeated(task, event);
        }
      } else {
        ToDo task = new ToDoTask(taskName, Weekday.valueOf(weekday.getText()),
            category.getText());
        this.handleRepeated(task, event);
      }
    } else {
      this.weekdayTaskName = false;
      this.category = false;
      this.number = true;
      this.repeated = false;
      this.handle(event);
    }
  }

  /**
   * Handles the cases if the given todo already exists
   *
   * @param task given task
   * @param event event
   */
  private void handleRepeated(ToDo task, Event event) {
    if (this.controller.getDayToDosCollection().checkTodoExist(task)) {
      this.weekdayTaskName = false;
      this.category = false;
      this.number = false;
      this.repeated = true;
      this.handle(event);
    } else {
      this.controller.addToDo(task);
      this.controller.addTasks(task);
      this.controller.updateWeeklySpread();
      this.weekdayTaskName = false;
      this.category = false;
      this.number = false;
      this.repeated = false;
    }
  }

  /**
   * Checks if the given weekday radio button is not null, task name is not empty
   *
   * @param weekday  - weekday radio button
   * @param taskName - task name
   * @return true if not null or not empty
   */
  private boolean validateWeekdayTaskNameNotEmpty(RadioButton weekday, String taskName) {
    return weekday != null && !taskName.equals("");
  }

  /**
   * Checks if the category radio button is null
   *
   * @param category category radio button
   * @return true if null
   */
  private boolean validateCategoryNotEmpty(RadioButton category) {
    return category != null;
  }
}