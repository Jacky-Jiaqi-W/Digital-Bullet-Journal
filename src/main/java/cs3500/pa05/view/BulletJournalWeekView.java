package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a GUI week view for Bullet Journal
 */
public class BulletJournalWeekView implements BulletJournalGuiView {
  private FXMLLoader loader;

  /**
   * Instantiates a Bullet Journal week view
   *
   * @param controller - given controller
   */
  public BulletJournalWeekView(Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("weekView.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from a Bullet Journal week view GUI layout.
   *
   * @return layout
   */
  @Override
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
