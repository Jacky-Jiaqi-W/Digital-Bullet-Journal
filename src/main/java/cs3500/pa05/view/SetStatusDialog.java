package cs3500.pa05.view;

import cs3500.pa05.model.Weekday;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * represent the dialog for setStatus
 */
public class SetStatusDialog extends Dialog {
  private VBox chooseCompleteVbox;
  private Label indexLabel;
  private TextField index;
  private Label weekdayLabel;
  private VBox weekdayVbox;
  private ToggleGroup groupForWeekday;


  /**
   * instantiate the SetStatusDialog
   */
  public SetStatusDialog() {
    this.chooseCompleteVbox = new VBox();
    this.chooseCompleteVbox.setSpacing(5);
    this.setTitle("MarkComplete");
    this.setHeaderText("Mark complete/incomplete");
    this.indexLabel = new Label("Which Task would you like to change the status? "
        + "Specify the Task Number: ");
    this.index = new TextField();
    this.groupForWeekday = new ToggleGroup();
    this.weekdayLabel = new Label("Please Select the Weekday Your ToDo Corresponds to: ");
    this.weekdayVbox = createRadioButtonsForWeekday(groupForWeekday, Weekday.class);
    this.chooseCompleteVbox.getChildren().add(indexLabel);
    this.chooseCompleteVbox.getChildren().add(index);
    this.chooseCompleteVbox.getChildren().add(weekdayLabel);
    this.chooseCompleteVbox.getChildren().add(weekdayVbox);

    this.getDialogPane().setContent(this.chooseCompleteVbox);
    this.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
  }

  /**
   * get the name of the task
   *
   * @return a string value
   */
  public String getTaskName() {
    return this.index.getText().trim();
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
  private <T extends Enum<T>> VBox createRadioButtonsForWeekday(ToggleGroup group,
                                                                Class<T> enumClass) {
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
   * get the name of the todo
   *
   * @return a String value
   */
  public String getIndex() {
    return this.index.getText().trim();
  }


  /**
   * Gets the selected weekday radio button
   *
   * @return selected weekday radio button
   */
  public RadioButton getSelectedWeekday() {
    return ((RadioButton) this.groupForWeekday.getSelectedToggle());
  }


}
