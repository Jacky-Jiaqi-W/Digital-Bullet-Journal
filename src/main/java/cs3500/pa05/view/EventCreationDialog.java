package cs3500.pa05.view;

import java.util.List;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * represents the event creation dialog
 */
public class EventCreationDialog extends TaskCreationDialog {
  private Label startTimeLabel;
  private TextField startTimeTextField;
  private Label durationLabel;
  private TextField durationTextField;

  /**
   * Instantiate the EventCreationDialog
   *
   * @param categories - the event's category
   */
  public EventCreationDialog(List<String> categories) {
    super(categories);
    this.startTimeLabel = new Label("Start Time (military format 00:00) : ");
    this.startTimeTextField = new TextField();
    this.durationLabel = new Label("Duration (in mins): ");
    this.durationTextField = new TextField();

    GridPane gridPane = this.getGridPane();
    gridPane.add(this.startTimeLabel, 0, 3);
    gridPane.add(this.startTimeTextField, 1, 3);
    gridPane.add(this.durationLabel, 0, 4);
    gridPane.add(this.durationTextField, 1, 4);

    DialogPane dialogPane = new DialogPane();
    dialogPane.setContent(gridPane);
    dialogPane.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
    this.setDialogPane(dialogPane);
    this.setTitle("Add Event");
  }


  /**
   * getter for the startTimeTExtField field
   *
   * @return the start time
   */
  public String getStartTime() {
    return this.startTimeTextField.getText();
  }

  /**
   * getter for the durationTExtField field
   *
   * @return the duration
   */
  public String getDuration() {
    return this.durationTextField.getText();
  }
}
