package cs3500.pa05.view;

import cs3500.pa05.model.Weekday;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Represents a task creation dialog
 */
public class TaskCreationDialog extends Dialog {
  private GridPane gridPane;
  private Label weekdayLabel;
  private Label categoryLabel;
  private Label taskLabel;
  private TextField taskTextField;
  private VBox vboxForWeekday;
  private VBox vboxForCategory;
  private ToggleGroup groupForWeekday;
  private ToggleGroup groupForCategory;


  /**
   * Instantiates task creation dialog
   */
  public TaskCreationDialog(List<String> categories) {
    this.setTitle("Add Task");
    this.setHeaderText("Please provide your details");
    this.weekdayLabel = new Label("Weekday:");
    this.groupForWeekday = new ToggleGroup();
    this.vboxForWeekday = createRadioButtonVbox(groupForWeekday, Weekday.class);

    this.taskLabel = new Label("Name (Auto tag format: #CategoryName Task Name): ");
    this.taskTextField = new TextField();
    this.categoryLabel = new Label("Select a Category: ");
    this.groupForCategory = new ToggleGroup();
    this.vboxForCategory = createCategoryVbox(groupForCategory, categories);
    this.gridPane = new GridPane();
    this.gridPane.setHgap(10);
    this.gridPane.setVgap(10);
    this.gridPane.setPadding(new Insets(20));

    this.gridPane.add(this.weekdayLabel, 0, 0);
    this.gridPane.add(this.vboxForWeekday, 1, 0);
    this.gridPane.add(this.taskLabel, 0, 1);
    this.gridPane.add(this.taskTextField, 1, 1);
    this.gridPane.add(this.categoryLabel, 0, 2);
    this.gridPane.add(this.vboxForCategory, 1, 2);
    this.getDialogPane().setContent(this.gridPane);
    this.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
  }

  /**
   * Creates a VBox that contains a toggle group of the radio buttons where each one of them is one
   * enumeration constant in the enumeration class
   *
   * @param group      - toggle group
   * @param enumClass  - enumeration class
   * @param <T>        - any enumeration class
   * @return VBox
   */
  private <T extends Enum<T>> VBox createRadioButtonVbox(ToggleGroup group, Class<T> enumClass) {
    VBox vbox = new VBox();
    vbox.setSpacing(5);
    for (T enumValue : enumClass.getEnumConstants()) {
      RadioButton button = new RadioButton(enumValue.toString());
      button.setToggleGroup(group);
      vbox.getChildren().add(button);
    }
    return vbox;
  }

  /**
   * Creates a VBox that contains a toggle group of the radio buttons where each one of them is a
   * String in the list of String.
   *
   * @param group       - toggle group
   * @param categories  - list of String
   * @return VBox
   */
  private VBox createCategoryVbox(ToggleGroup group, List<String> categories) {
    VBox vbox = new VBox();
    vbox.setSpacing(5);
    for (String category : categories) {
      RadioButton button = new RadioButton(category);
      button.setToggleGroup(group);
      vbox.getChildren().add(button);
    }
    return vbox;
  }

  /**
   * Gets the task name
   *
   * @return task name
   */
  public String getToDoName() {
    return this.taskTextField.getText().trim().toUpperCase();
  }

  /**
   * getter for the gridPane field
   *
   * @return the grid pane of the dialog
   */
  public GridPane getGridPane() {
    return this.gridPane;
  }

  /**
   * Gets the selected weekday radio button
   *
   * @return selected weekday radio button
   */
  public RadioButton getSelectedWeekday() {
    return ((RadioButton) this.groupForWeekday.getSelectedToggle());
  }

  /**
   * Gets the selected category radio button
   *
   * @return selected category radio button
   */
  public RadioButton getSelectedCategory() {
    return ((RadioButton) this.groupForCategory.getSelectedToggle());
  }
}