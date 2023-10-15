package cs3500.pa05.view;

import cs3500.pa05.model.Weekday;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * Represents a week start dialog
 */
public class WeekStartDialog extends Dialog {
  private VBox vboxForWeekday;
  private ToggleGroup groupForWeekday;

  /**
   * Instantiates task creation dialog
   */
  public WeekStartDialog() {
    this.setTitle("Choose Week Start Day");
    this.setHeaderText("Please choose which day you want the week start at");
    this.groupForWeekday = new ToggleGroup();
    this.vboxForWeekday = createRadioButtonVbox(groupForWeekday, Weekday.class);
    this.getDialogPane().setContent(this.vboxForWeekday);
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
   * getter for the Weekday
   *
   * @return Weekday
   */
  public Weekday getSelectedWeekday() {
    RadioButton selectedButton = (RadioButton) groupForWeekday.getSelectedToggle();
    if (selectedButton != null) {
      String weekday = selectedButton.getText();
      return Weekday.valueOf(weekday);
    }
    return null;
  }
}
