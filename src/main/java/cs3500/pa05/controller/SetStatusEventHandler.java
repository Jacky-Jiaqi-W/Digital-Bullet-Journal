package cs3500.pa05.controller;

import cs3500.pa05.model.DayToDos;
import cs3500.pa05.model.DayToDosCollection;
import cs3500.pa05.model.Weekday;
import cs3500.pa05.view.SetStatusDialog;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;

/**
 * represents the event handler for marking the completion status
 */
public class SetStatusEventHandler implements EventHandler {
  private BulletJournalController controller;
  private boolean empty;
  private boolean nonInteger;
  private boolean outOfBounds;
  private boolean notTask;

  /**
   * Instantiate the SetStatusEventHandler
   *
   * @param controller - BulletJournalController
   */
  public SetStatusEventHandler(BulletJournalController controller) {
    this.controller = controller;
    this.empty = false;
    this.nonInteger = false;
    this.outOfBounds = false;
    this.notTask = false;
  }


  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event - the event which occurred
   */
  @Override
  public void handle(Event event) {
    SetStatusDialog setStatusDialog = new SetStatusDialog();
    DayToDosCollection dayToDosCollection = this.controller.getDayToDosCollection();
    if (this.empty) {
      setStatusDialog.setHeaderText("YOUR FIELDS SEEM TO BE EMPTY. PLEASE TRY AGAIN: ");
    }
    if (this.nonInteger) {
      setStatusDialog.setHeaderText("YOUR INPUT IS NOT AN INTEGER. PLEASE TRY AGAIN: ");
    }
    if (this.outOfBounds) {
      setStatusDialog.setHeaderText("YOUR INPUT IS OUT OF BOUNDS. PLEASE TRY AGAIN: ");
    }
    if (this.notTask) {
      setStatusDialog.setHeaderText("YOUR INPUT DOES NOT CORRESPOND TO A TASK. PLEASE TRY AGAIN: ");
    }
    Optional<ButtonType> result = setStatusDialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      String index = setStatusDialog.getIndex();
      RadioButton weekday = setStatusDialog.getSelectedWeekday();
      if (this.validateNotEmpty(index, weekday)) {
        if (this.validateInteger(index)) {
          int indexInt = Integer.parseInt(index);
          if (!this.controller.getDayToDosCollection().outOfBounds(indexInt,
              Weekday.valueOf(weekday.getText()))) {
            // validate if it's task
            if (this.validateTask(index, Weekday.valueOf(weekday.getText()))) {
              this.controller.getDayToDosCollection()
                  .replace(indexInt, Weekday.valueOf(weekday.getText()));
              this.controller.updateWeeklySpread();
              this.empty = false;
              this.nonInteger = false;
              this.outOfBounds = false;
              this.notTask = false;
            } else {
              this.empty = false;
              this.notTask = true;
              this.nonInteger = false;
              this.outOfBounds = false;
              this.handle(event);
            }

          } else {
            this.empty = false;
            this.nonInteger = false;
            this.outOfBounds = true;
            this.handle(event);
          }
        } else {
          this.empty = false;
          this.nonInteger = true;
          this.outOfBounds = false;
          this.notTask = false;
          this.handle(event);
        }
      } else {
        this.empty = true;
        this.nonInteger = false;
        this.outOfBounds = false;
        this.notTask = false;
        this.handle(event);
      }
    } else {
      this.empty = false;
      this.nonInteger = false;
      this.outOfBounds = false;
      this.notTask = false;
    }
  }

  /**
   * checks if the toDo at the index is a task and not an event
   *
   * @param index - index of the toDo
   * @return true if the index corresponds to a task
   */
  private boolean validateTask(String index, Weekday weekday) {
    boolean b = true;
    int indexInt = Integer.parseInt(index) - 1;
    for (DayToDos d : this.controller.getDayToDosCollection().getDayToDosList()) {
      if (d.getWeekday().equals(weekday)) {
        String todo = d.getToDos().get(indexInt).toString();
        if (d.checkTask(d.getToDos().get(indexInt))) {
          b = true;
        } else {
          b = false;
        }
        break;
      }
    }
    return b;
  }

  /**
   * Checks if the given weekday radio button is not null, index is not empty
   *
   * @param index index
   * @param weekday weekday radio button
   * @return true if null and empty
   */
  private boolean validateNotEmpty(String index, RadioButton weekday) {
    return weekday != null && !index.equals("");
  }

  /**
   * Checks if the index string is integer format
   *
   * @param index index string
   * @return true if the index string is integer format
   */
  private boolean validateInteger(String index) {
    try {
      return Integer.parseInt(index) > 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}



