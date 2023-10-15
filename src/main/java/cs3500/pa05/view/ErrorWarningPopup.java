package cs3500.pa05.view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;

/**
 * Represents an error warning popup
 */
public class ErrorWarningPopup extends Popup {
  private Button okButton;

  /**
   * Instantiates an error warning popup
   *
   * @param errorMessage - given error message
   */
  public ErrorWarningPopup(String errorMessage) {
    Label errorMessageLabel = new Label(errorMessage);
    this.okButton = new Button("OK");
    this.getContent().addAll(errorMessageLabel, this.okButton);
  }

  /**
   * sets the OK button on action
   *
   * @param handler - event handler
   */
  public void setOkButtonOnAction(EventHandler handler) {
    this.okButton.setOnAction(handler);
  }
}
