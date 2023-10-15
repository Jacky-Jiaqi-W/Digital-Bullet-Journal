package cs3500.pa05.controller;

import cs3500.pa05.model.DayToDos;
import cs3500.pa05.model.DayToDosCollection;
import cs3500.pa05.model.Weekday;
import cs3500.pa05.view.WeekStartDialog;
import java.util.List;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;


/**
 * instantiate the week start event handler
 */
public class WeekStartEventHandler implements EventHandler {
  private BulletJournalController controller;
  private Weekday selectedWeekday;
  private boolean reEnter;

  /**
   * Instantiates save button ToDoEvent Handler
   *
   * @param controller - given controller
   */
  public WeekStartEventHandler(BulletJournalController controller) {
    this.controller = controller;
    this.reEnter = false;
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    WeekStartDialog weekStartDialog = new WeekStartDialog();
    DayToDosCollection dayToDosCollection = this.controller.getDayToDosCollection();
    if (this.reEnter) {
      weekStartDialog.setHeaderText("PLEASE MAKE SURE YOU SELECT ONE OF THE FOLLOWING: ");
    }
    Optional<ButtonType> result = weekStartDialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      this.selectedWeekday = weekStartDialog.getSelectedWeekday();
      if (this.selectedWeekday != null) {
        // create a list of sortedDayToDos to store the sorted weekday
        List<DayToDos> dayToDosList = dayToDosCollection.startFromDay(selectedWeekday);
        this.controller.setDayToDos(dayToDosList);
        this.controller.updateWeeklySpread();
      } else {
        this.reEnter = true;
        this.handle(event);
      }
    } else {
      this.reEnter = false;
    }
  }

}
