package cs3500.pa05.view;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Category dialog
 */
public class CategoryCreationDialog extends Dialog {
  private Label categoryLabel;
  private TextField categoryTextField;
  private GridPane gridPane;

  /**
   * instantiate CategoryCreationDialog
   */
  public CategoryCreationDialog() {

    this.setTitle("Add Category");
    this.setHeaderText("Please provide your details");
    this.categoryLabel = new Label("Category: ");
    this.categoryTextField = new TextField();

    this.gridPane = new GridPane();
    this.gridPane.setHgap(10);
    this.gridPane.setVgap(10);
    this.gridPane.add(this.categoryLabel, 0, 0);
    this.gridPane.add(this.categoryTextField, 1, 0);

    this.getDialogPane().setContent(this.gridPane);
    this.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
  }

  /**
   * getter for the categoryTextField field
   *
   * @return the category text field
   */
  public String getCategoryTextField() {
    return this.categoryTextField.getText();
  }

}
