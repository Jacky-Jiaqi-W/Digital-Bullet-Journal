package cs3500.pa05.controller;

import cs3500.pa05.model.AutoTagUtils;
import cs3500.pa05.model.ToDo;
import cs3500.pa05.model.ToDoEvent;
import cs3500.pa05.model.ToDoTask;
import cs3500.pa05.model.Weekday;
import cs3500.pa05.view.EventCreationDialog;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;

/**
 * Add event button event handler
 */
public class AddEventEventHandler implements EventHandler {
  private BulletJournalController controller;
  private boolean weekdayNameTimeDuration;
  private boolean category;
  private boolean number;
  private boolean repeated;
  private AutoTagUtils autoTagUtils;

  /**
   * Instantiates an event handler for adding event
   */
  public AddEventEventHandler(BulletJournalController controller) {
    this.controller = controller;
    this.weekdayNameTimeDuration = false;
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
    EventCreationDialog eventCreationDialog = new EventCreationDialog(this.controller
        .getCategories().getCategoriesAsString());
    if (this.weekdayNameTimeDuration) {
      eventCreationDialog.setHeaderText("SOME FIELDS ARE EMPTY OR INVALID. PLEASE TRY AGAIN: ");
    }
    if (this.category) {
      eventCreationDialog.setHeaderText("MUST ENTER VALID AUTO TAG OR SELECT CATEGORY. "
          + "PLEASE TRY AGAIN: ");
    }
    if (this.number) {
      eventCreationDialog.setHeaderText("YOU CAN'T EXCEED THE MAXIMUM. PLEASE TRY AGAIN: ");
    }
    if (this.repeated) {
      eventCreationDialog.setHeaderText("THE EVENT ALREADY EXISTS. PLEASE TRY AGAIN: ");
    }
    Optional<ButtonType> result = eventCreationDialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      RadioButton weekday = eventCreationDialog.getSelectedWeekday();
      RadioButton category = eventCreationDialog.getSelectedCategory();
      String taskName = eventCreationDialog.getToDoName();
      String duration = eventCreationDialog.getDuration();
      String startTime = eventCreationDialog.getStartTime();
      if (this.validateWeekdayTaskNameNotEmpty(weekday, taskName)
          && this.validateStartTime(startTime) && this.validateDuration(duration)) {
        if ((this.autoTagUtils.containsValidAutoTag(taskName)
            && !this.validateCategoryNotEmpty(category)) || (!taskName.contains("#")
            && this.validateCategoryNotEmpty(category))) {
          this.handleHelper(weekday, category, taskName, duration, startTime, event);
        } else {
          this.weekdayNameTimeDuration = false;
          this.category = true;
          this.number = false;
          this.repeated = false;
          this.handle(event);
        }
      } else {
        this.weekdayNameTimeDuration = true;
        this.category = false;
        this.number = false;
        this.repeated = false;
        this.handle(event);
      }
    } else {
      this.weekdayNameTimeDuration = false;
      this.category = false;
      this.number = false;
      this.repeated = false;
    }
  }

  /**
   * Handle helper
   *
   * @param weekday weekday
   * @param category category radio button
   * @param taskName task name
   * @param duration duration
   * @param startTime start time
   * @param event event
   */
  private void handleHelper(RadioButton weekday, RadioButton category, String taskName,
                            String duration, String startTime, Event event) {
    if (this.controller.availableDayEvent(Weekday.valueOf(weekday.getText()))) {
      if (this.autoTagUtils.containsValidAutoTag(taskName)) {
        if (!this.controller.getCategories()
            .contains(this.autoTagUtils.getCategoryName(taskName))) {
          ToDo toDoEventevent = new ToDoEvent(this.autoTagUtils.getTaskName(taskName),
              Weekday.valueOf(weekday.getText()), this.autoTagUtils.getCategoryName(taskName),
              duration, startTime);
          this.controller.addToDo(toDoEventevent);
          this.controller.getCategories().addCategory(this.autoTagUtils.getCategoryName(taskName));
          this.controller.updateWeeklySpread();
          this.weekdayNameTimeDuration = false;
          this.category = false;
          this.number = false;
          this.repeated = false;
        } else {
          ToDo toDoEventevent = new ToDoEvent(this.autoTagUtils.getTaskName(taskName),
              Weekday.valueOf(weekday.getText()), this.autoTagUtils.getCategoryName(taskName),
              duration, startTime);
          this.handleRepeated(toDoEventevent, event);
        }
      } else {
        ToDo task = new ToDoTask(taskName, Weekday.valueOf(weekday.getText()),
            category.getText());
        ToDo toDoEventevent = new ToDoEvent(taskName, Weekday.valueOf(weekday.getText()),
            category.getText(), duration, startTime);
        this.handleRepeated(toDoEventevent, event);
      }
    } else {
      this.weekdayNameTimeDuration = false;
      this.category = false;
      this.number = true;
      this.repeated = false;
      this.handle(event);
    }
  }

  /**
   * Handles the cases if the given todo already exists
   *
   * @param toDo given toDo
   * @param event event
   */
  private void handleRepeated(ToDo toDo, Event event) {
    if (this.controller.getDayToDosCollection().checkTodoExist(toDo)) {
      this.weekdayNameTimeDuration = false;
      this.category = false;
      this.number = false;
      this.repeated = true;
      this.handle(event);
    } else {
      this.controller.addToDo(toDo);
      this.controller.updateWeeklySpread();
      this.weekdayNameTimeDuration = false;
      this.category = false;
      this.number = false;
      this.repeated = false;
    }
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

  /**
   * Checks if the given weekday radio button is null, task name is empty
   *
   * @param weekday weekday radio button
   * @param taskName task name
   * @return true if null or empty
   */
  private boolean validateWeekdayTaskNameNotEmpty(RadioButton weekday, String taskName) {
    return weekday != null && !taskName.equals("");
  }

  /**
   * Validates if the start time is in military format
   *
   * @param startTime start time
   * @return true if the start time is in military format
   */
  private boolean validateStartTime(String startTime) {
    String pattern = "^([0-1][0-9]|2[0-3]):([0-5][0-9])$";
    Pattern regex = Pattern.compile(pattern);
    Matcher matcher = regex.matcher(startTime.trim());
    return matcher.matches();
  }

  /**
   * Validates if the duration is valid
   *
   * @param duration given duration
   * @return true if the duration is valid
   */
  private boolean validateDuration(String duration) {
    try {
      return Integer.parseInt(duration) > 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}