package cs3500.pa05.controller;

import cs3500.pa05.view.CategoryCreationDialog;
import java.util.List;
import java.util.Optional;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;

/**
 * Add category button event handler
 */
public class AddCategoryEventHandler implements EventHandler {
  private BulletJournalController controller;
  private boolean reEnter;

  /**
   * Instantiates an add category event handler
   *
   * @param controller bullet journal controller
   */
  public AddCategoryEventHandler(BulletJournalController controller) {
    this.controller = controller;
    this.reEnter = false;
  }

  /**
   * Invoked when a specific event of the type for which this handler is
   * registered happens.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    CategoryCreationDialog categoryCreationDialog = new CategoryCreationDialog();
    List<String> list = this.controller.getCategories().getCategoriesAsString();
    if (this.reEnter) {
      categoryCreationDialog.setHeaderText(
          "YOUR FIELD IS INVALID OR EMPTY. THE CATEGORY NAME NEEDS TO BE UNIQUE. "
              + "PLEASE TRY AGAIN: ");
    }
    Optional<ButtonType> result = categoryCreationDialog.showAndWait();
    if (result.get() == ButtonType.OK) {
      if (this.validateCase(categoryCreationDialog.getCategoryTextField())
          && this.validateDuplicateCategory(categoryCreationDialog.getCategoryTextField(), list)) {
        list.add(categoryCreationDialog.getCategoryTextField().toUpperCase());
        this.reEnter = false;
      } else {
        this.reEnter = true;
        this.handle(event);
      }
    } else {
      this.reEnter = false;
    }
  }

  /**
   * Checks if the input is in all CAPS and the input is not empty
   *
   * @param category - category string
   *
   * @return true if the input is in all CAPS and input is not empty
   */
  private boolean validateCase(String category) {
    try {
      return !category.equals("");
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * checks if the inputs have already been entered
   *
   * @param category   - category String
   * @param categories - list of categories String
   * @return true if the input have not been entered before
   */
  private boolean validateDuplicateCategory(String category, List<String> categories) {
    for (String s : categories) {
      if (s.equals(category)) {
        return false;
      }
    }
    return true;
  }
}