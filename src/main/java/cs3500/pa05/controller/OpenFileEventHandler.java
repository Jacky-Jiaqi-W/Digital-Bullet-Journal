package cs3500.pa05.controller;

import cs3500.pa05.model.reader.ReaderImpl;
import cs3500.pa05.view.OpenFileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;


/**
 * represent the open file event handler
 */
public class OpenFileEventHandler implements EventHandler {
  private BulletJournalController controller;
  private boolean reEnter;

  /**
   * initialize the OpenFileEventHandler
   *
   * @param controller - bullet journal controller
   */
  public OpenFileEventHandler(BulletJournalController controller) {
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
    OpenFileDialog openFileDialog = new OpenFileDialog();
    if (this.reEnter) {
      openFileDialog.setHeaderText("YOUR FILE DOES NOT EXIST. PLEASE TRY AGAIN: ");
    }
    Optional<ButtonType> result = openFileDialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      if (this.validate(openFileDialog.getFileNameTextField())) {
        String filePath = openFileDialog.getFileNameTextField() + ".bujo";
        ReaderImpl readerImpl;
        try {
          readerImpl = new ReaderImpl(new FileReader(filePath));
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
        this.controller.initFromFile(readerImpl.read());
        this.reEnter = false;
      } else {
        this.reEnter = true;
        this.handle(null);
      }
    } else {
      this.reEnter = false;
      NewOrOpenEventHandler newOrOpenEventHandler = new NewOrOpenEventHandler(this.controller);
      newOrOpenEventHandler.handle(null);
    }
  }

  /**
   * Checks if the provided file path is not empty and exists
   *
   * @param filePath - file path that the user input
   * @return true if the filepath is not empty and exists
   */
  public boolean validate(String filePath) {
    return !filePath.equals("") && new File(filePath + ".bujo").exists();
  }
}
