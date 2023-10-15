package cs3500.pa05.controller;

import cs3500.pa05.model.Weekday;
import cs3500.pa05.view.DeleteDialog;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;

/**
 * represent the delete event handler
 */
public class DeleteEventHandler implements EventHandler {
  private BulletJournalController controller;
  private boolean empty;
  private boolean notInteger;
  private boolean outOfBounds;

  /**
   * Instantiate the DeleteEventHandler
   *
   * @param controller - BulletJournalController
   */
  public DeleteEventHandler(BulletJournalController controller) {
    this.controller = controller;
    this.empty = false;
    this.notInteger = false;
    this.outOfBounds = false;
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    DeleteDialog deleteDialog = new DeleteDialog();
    if (this.empty) {
      deleteDialog.setHeaderText("PLEASE MAKE SURE YOU SELECT A WEEKDAY AND ENTER THE NUMBER: ");
    }
    if (this.notInteger) {
      deleteDialog.setHeaderText("YOUR INPUT IS NOT NUMBER. PLEASE TRY AGAIN: ");
    }
    if (this.outOfBounds) {
      deleteDialog.setHeaderText("YOUR INPUT IS OUT OF BOUNDS. PLEASE TRY AGAIN: ");
    }
    Optional<ButtonType> result = deleteDialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      String index = deleteDialog.getIndex();
      RadioButton weekday = deleteDialog.getSelectedWeekday();
      if (this.validateNotEmpty(index, weekday)) {
        if (this.validateInteger(index)) {
          int indexInt = Integer.parseInt(index);
          if (!this.controller.getDayToDosCollection().outOfBounds(indexInt,
              Weekday.valueOf(weekday.getText()))) {
            // if else
            this.controller.getDayToDosCollection().delete(indexInt,
                Weekday.valueOf(weekday.getText()));
            this.controller.updateWeeklySpread();
            this.empty = false;
            this.notInteger = false;
            this.outOfBounds = false;
          } else {
            this.empty = false;
            this.notInteger = false;
            this.outOfBounds = true;
            this.handle(event);
          }
        } else {
          this.empty = false;
          this.notInteger = true;
          this.outOfBounds = false;
          this.handle(event);
        }
      } else {
        this.empty = true;
        this.notInteger = false;
        this.outOfBounds = false;
        this.handle(event);
      }
    } else {
      this.empty = false;
      this.notInteger = false;
      this.outOfBounds = false;
    }
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
