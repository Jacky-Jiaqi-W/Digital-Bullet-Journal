package cs3500.pa05.view;

import cs3500.pa05.model.ToDo;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Represents a daily spread
 */
public class DailySpreadVbox extends VBox {
  private Label header;
  private ScrollPane scrollPane;
  private Label toDo;

  /**
   * Instantiates a daily spread VBox
   *
   * @param text  - text
   * @param todos - list of todos
   */
  public DailySpreadVbox(String text, List<ToDo> todos) {
    this.header = new Label(text);
    this.header.setFont(Font.font("Chalkboard SE", FontWeight.LIGHT, 25));
    VBox vbox = new VBox();
    for (int i = 1; i  <= todos.size(); i++) {
      this.toDo = new Label(i + ". " + todos.get(i - 1).toString());
      vbox.getChildren().add(this.toDo);
    }
    this.scrollPane = new ScrollPane();
    this.scrollPane.setPrefSize(256, 290);
    this.scrollPane.setContent(vbox);
    this.getChildren().addAll(this.header, this.scrollPane);
  }
}
