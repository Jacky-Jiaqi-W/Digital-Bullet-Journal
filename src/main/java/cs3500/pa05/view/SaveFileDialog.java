package cs3500.pa05.view;

import java.io.File;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * represent the save file dialog
 */
public class SaveFileDialog extends Dialog {
  private Label fileLabel;
  private TextField filePathField;
  private GridPane gridPane;

  /**
   * Instantiate the save file dialog
   */
  public SaveFileDialog() {
    this.setTitle("Save to File");
    this.setHeaderText("Please enter the name of the file you want to save as");
    this.fileLabel = new Label("Filepath: ");
    this.filePathField = new TextField();

    this.gridPane = new GridPane();
    this.gridPane.setHgap(10);
    this.gridPane.setVgap(10);
    this.gridPane.add(this.fileLabel, 0, 0);
    this.gridPane.add(this.filePathField, 1, 0);

    this.getDialogPane().setContent(this.gridPane);
    this.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
  }


  /**
   * gets the filePathField in String format
   *
   * @return the filePathField in String format
   */
  public String getFilePathField() {
    return this.filePathField.getText().trim();
  }
}

