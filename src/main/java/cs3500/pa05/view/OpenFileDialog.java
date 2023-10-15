package cs3500.pa05.view;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * represents the open file dialog
 */
public class OpenFileDialog extends Dialog {
  private Label fileLabel;
  private TextField filePathTextField;
  private GridPane gridPane;

  /**
   * Instantiate the open file dialog
   */
  public OpenFileDialog() {
    this.setTitle("Open File");
    this.setHeaderText("Please enter the full path of the existing file: ");
    this.fileLabel = new Label("Filepath: ");
    this.filePathTextField = new TextField();

    this.gridPane = new GridPane();
    this.gridPane.setHgap(10);
    this.gridPane.setVgap(10);
    this.gridPane.add(this.fileLabel, 0, 0);
    this.gridPane.add(this.filePathTextField, 1, 0);

    this.getDialogPane().setContent(this.gridPane);
    this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
  }

  /**
   * gets the file path in String format
   *
   * @return the file path
   */
  public String getFileNameTextField() {
    return this.filePathTextField.getText().trim();
  }
}
