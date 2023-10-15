package cs3500.pa05.controller;

import cs3500.pa05.model.writer.FileAppendable;
import cs3500.pa05.model.writer.WriterImpl;
import cs3500.pa05.view.MaxToDosDialog;
import cs3500.pa05.view.SaveFileDialog;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * represent the save file event handler
 */
public class SaveFileEventHandler implements EventHandler {
  private BulletJournalController controller;
  private boolean reEnter;

  /**
   * Instantiates save button ToDoEvent Handler
   *
   * @param controller - given controller
   */
  public SaveFileEventHandler(BulletJournalController controller) {
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
    SaveFileDialog saveFileDialog = new SaveFileDialog();
    if (this.reEnter) {
      saveFileDialog.setHeaderText("YOUR FIELD SEEMS TO BE EMPTY, PLEASE ENTER AGAIN: ");
    }
    Optional<ButtonType> result = saveFileDialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      if (this.validate(saveFileDialog.getFilePathField())) {
        File file = new File(saveFileDialog.getFilePathField() + ".bujo");
        try {
          // Create a new file
          boolean created = file.createNewFile();
        } catch (IOException e) {
          System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
        WriterImpl fileWriter = new WriterImpl(new FileAppendable(file));
        fileWriter.write(this.controller.generateSaveContent());
      } else {
        this.reEnter = true;
        this.handle(event);
      }
    } else {
      this.reEnter = false;
    }
  }

  /**
   * checks if the file path text field is empty
   *
   * @return true if the file path text field is not empty
   */
  public boolean validate(String filePathField) {
    return !filePathField.equals("");
  }
}
