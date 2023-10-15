package cs3500.pa05.controller;

import cs3500.pa05.view.NewFileAndOpenFileDialog;
import cs3500.pa05.view.OpenFileDialog;
import java.util.NoSuchElementException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;

/**
 * represent the new/open event handler
 */
public class NewOrOpenEventHandler implements EventHandler {
  private BulletJournalController controller;
  private boolean reEnter;

  /**
   * instantiate the new file or open file event handler
   *
   * @param controller - the bullet journal controller
   */
  public NewOrOpenEventHandler(BulletJournalController controller) {
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
    NewFileAndOpenFileDialog newFileAndOpenFileDialog = new NewFileAndOpenFileDialog();
    if (this.reEnter) {
      newFileAndOpenFileDialog.setHeaderText("PLEASE MAKE SURE YOU SELECT ONE OF THE FOLLOWING: ");
    }
    try {
      Optional<ButtonType> result = newFileAndOpenFileDialog.showAndWait();
      if (result.get() == ButtonType.OK) {
        if (newFileAndOpenFileDialog.getOpenFileButton().isSelected()) {
          OpenFileEventHandler openFileEventHandler = new OpenFileEventHandler(this.controller);
          openFileEventHandler.handle(null);
          this.reEnter = false;
        } else if (newFileAndOpenFileDialog.getCreateNewFileButton().isSelected()) {
          // do nothing
          this.reEnter = false;
        } else {
          this.reEnter = true;
          this.handle(null);
        }
      }
    } catch (NoSuchElementException e) {
      Platform.exit();
    }
  }
}