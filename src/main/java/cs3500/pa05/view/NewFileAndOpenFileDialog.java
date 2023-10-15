package cs3500.pa05.view;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * represents the new file and open file dialog
 */
public class NewFileAndOpenFileDialog extends Dialog {
  private RadioButton createNewFileButton;
  private RadioButton openFileButton;
  private ToggleGroup groupForNewFileAndOpenFile;
  private VBox vbox;


  /**
   * Instantiate the new file and open file dialog
   */
  public NewFileAndOpenFileDialog() {
    this.setTitle("New File/Open File");
    this.setHeaderText("please select one of the following: ");
    this.groupForNewFileAndOpenFile = new ToggleGroup();
    this.vbox = createRadioButtonVbox(groupForNewFileAndOpenFile);
    this.getDialogPane().setContent(this.vbox);
    this.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
  }

  /**
   * create the radio buttons for the createNewFileButton and openFileButton
   *
   * @param groupForNewFileAndOpenFile - toggle group
   *
   * @return vBox
   */
  private VBox createRadioButtonVbox(ToggleGroup groupForNewFileAndOpenFile) {
    VBox vbox = new VBox();
    vbox.setSpacing(5);
    this.createNewFileButton = new RadioButton("Create New File");
    this.openFileButton = new RadioButton("Open Existing File");
    createNewFileButton.setToggleGroup(groupForNewFileAndOpenFile);
    openFileButton.setToggleGroup(groupForNewFileAndOpenFile);
    vbox.getChildren().addAll(createNewFileButton, openFileButton);
    return vbox;
  }

  /**
   * getter for the open file button
   *
   * @return the open file button
   */
  public RadioButton getOpenFileButton() {

    return this.openFileButton;
  }

  /**
   * getter for the open file button
   *
   * @return the open file button
   */
  public RadioButton getCreateNewFileButton() {

    return this.createNewFileButton;
  }


}
