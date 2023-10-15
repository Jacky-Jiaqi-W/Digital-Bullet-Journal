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
 * represent the delete dialog
 */
public class DeleteDialog extends Dialog {

  private VBox deleteVbox;
  private Label weekdayLabel;
  private VBox weekdayVbox;
  private Label indexLabel;
  private TextField index;
  private ToggleGroup groupForWeekday;

  /**
   * instantiate the DeleteDialog()
   */
  public DeleteDialog() {
    this.deleteVbox = new VBox();
    deleteVbox.setSpacing(5);
    this.setTitle("delete");
    this.setHeaderText("Delete task/event");
    this.indexLabel = new Label("Which ToDo Would you Like to Delete? Specify the Todo Number: ");
    this.index = new TextField();
    this.weekdayLabel = new Label("Please Select the Weekday Your ToDo Corresponds to: ");
    this.groupForWeekday = new ToggleGroup();
    this.weekdayVbox = createRadioButtonsForWeekday(groupForWeekday, Weekday.class);
    deleteVbox.getChildren().add(indexLabel);
    deleteVbox.getChildren().add(index);
    deleteVbox.getChildren().add(weekdayLabel);
    deleteVbox.getChildren().add(weekdayVbox);

    this.getDialogPane().setContent(this.deleteVbox);
    this.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
  }

  /**
   * get the name of the todo
   *
   * @return a string value
   */
  public String getIndex() {
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
   * Gets the selected weekday radio button
   *
   * @return selected weekday radio button
   */
  public RadioButton getSelectedWeekday() {
    return ((RadioButton) this.groupForWeekday.getSelectedToggle());
  }


}
