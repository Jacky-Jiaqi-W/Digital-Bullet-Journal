package cs3500.pa05.controller;

import cs3500.pa05.view.MaxToDosDialog;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;

/**
 * Set max button event handler
 */
public class SetMaxButtonEventHandler implements EventHandler {
  private BulletJournalController controller;
  private boolean reEnter;

  /**
   * Instantiates set max button ToDoEvent Handler
   *
   * @param controller - given controller
   */
  public SetMaxButtonEventHandler(BulletJournalController controller) {
    this.controller = controller;
    this.reEnter = false;
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event - the event which occurred
   */
  @Override
  public void handle(Event event) {
    MaxToDosDialog maxToDosDialog = new MaxToDosDialog();
    if (this.reEnter) {
      maxToDosDialog.setHeaderText("PLEASE RE-ENTER THE VALID MAXIMUMS: ");
    }
    Optional<ButtonType> result = maxToDosDialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      if (this.validate(maxToDosDialog.getMaxEvents(), maxToDosDialog.getMaxTasks())) {
        int maxEvents = Integer.parseInt(maxToDosDialog.getMaxEvents());
        int maxTasks = Integer.parseInt(maxToDosDialog.getMaxTasks());
        if (!this.controller.alreadyExceeds(maxEvents, maxTasks)) {
          this.controller.setMaxEvents(maxEvents);
          this.controller.setMaxTasks(maxTasks);
          this.reEnter = false;
        } else {
          this.reEnter = true;
          this.handle(event);
        }
      } else {
        this.reEnter = true;
        this.handle(event);
      }
    } else {
      this.reEnter = false;
    }
  }

  /**
   * Checks if the inputs are positive integers
   *
   * @param maxEvents - max events string
   * @param maxTasks  - max tasks string
   * @return true if the inputs are positive integers
   */
  private boolean validate(String maxEvents, String maxTasks) {
    try {
      return Integer.parseInt(maxEvents) > 0 && Integer.parseInt(maxTasks) > 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
