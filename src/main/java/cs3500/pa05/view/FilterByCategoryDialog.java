package cs3500.pa05.view;

import cs3500.pa05.controller.BulletJournalController;
import java.util.List;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * represent the filter category dialog
 */
public class FilterByCategoryDialog extends Dialog {
  private BulletJournalController controller;
  private VBox vboxForFilter;
  private ToggleGroup groupForFilter;
  private List<String> catgories;


  /**
   * instantiate the FilterByCategoryDialog
   */
  public FilterByCategoryDialog(List<String> categories) {
    this.catgories = categories;
    this.setTitle("Filter By Category");
    this.setHeaderText("Select The Category You Would Like To Filter By: ");
    this.groupForFilter = new ToggleGroup();
    this.vboxForFilter = createRadioButtonVboxForFilter(groupForFilter, catgories);
    this.getDialogPane().setContent(this.vboxForFilter);
    this.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);

  }

  /**
   * creates the radio button for the category filters
   *
   * @param groupForFilter - toggle group
   * @param categories     - list of categories
   * @return VBox with radio buttons created
   */
  public VBox createRadioButtonVboxForFilter(ToggleGroup groupForFilter, List<String> categories) {
    VBox vbox = new VBox();
    vbox.setSpacing(5);
    for (String category : categories) {
      RadioButton button = new RadioButton(category);
      button.setToggleGroup(groupForFilter);
      vbox.getChildren().add(button);
    }
    return vbox;
  }

  /**
   * gets user's selected button
   *
   * @return the selected button
   */
  public RadioButton getSelectedButton() {
    RadioButton selectedButton = (RadioButton) groupForFilter.getSelectedToggle();
    return selectedButton;
  }
}
