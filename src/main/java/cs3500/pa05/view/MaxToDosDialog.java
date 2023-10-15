package cs3500.pa05.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Represents a max todos specification dialog
 */
public class MaxToDosDialog extends Dialog {
  private GridPane gridPane;
  private Label maxEventsLabel;
  private TextField maxEventsTextField;
  private Label maxTasksLabel;
  private TextField maxTasksTextField;

  /**
   * Instantiates a max todos specification dialog
   */
  public MaxToDosDialog() {
    this.setTitle("Set maximum events and tasks");
    this.setHeaderText("Please specify the maximums(positive integers)");

    this.maxEventsLabel = new Label("Maximum events:");
    this.maxEventsTextField = new TextField();
    this.maxTasksLabel = new Label("Maximum tasks:");
    this.maxTasksTextField = new TextField();

    this.gridPane = new GridPane();
    this.gridPane.setHgap(10);
    this.gridPane.setVgap(10);
    this.gridPane.setPadding(new Insets(20));
    this.gridPane.add(this.maxEventsLabel, 0, 0);
    this.gridPane.add(this.maxEventsTextField, 1, 0);
    this.gridPane.add(this.maxTasksLabel, 0, 1);
    this.gridPane.add(this.maxTasksTextField, 1, 1);

    this.getDialogPane().setContent(this.gridPane);
    this.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
  }

  /**
   * Gets the max events
   *
   * @return max events
   */
  public String getMaxEvents() {
    return this.maxEventsTextField.getText();
  }

  /**
   * Gets the max tasks
   *
   * @return max tasks
   */
  public String getMaxTasks() {
    return this.maxTasksTextField.getText();
  }
}
