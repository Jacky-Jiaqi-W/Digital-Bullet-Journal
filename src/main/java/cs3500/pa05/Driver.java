package cs3500.pa05;

import static javafx.application.Application.launch;

import cs3500.pa05.controller.BulletJournalController;
import cs3500.pa05.controller.Controller;
import cs3500.pa05.view.BulletJournalGuiView;
import cs3500.pa05.view.BulletJournalWeekView;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Represents a driver for the Bullet Journal
 */
public class Driver extends Application {
  /**
   * Entry point for a Bullet Journal.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * Starts the GUI for a Bullet Journal
   *
   * @param primaryStage the JavaFX stage to add elements to
   */
  @Override
  public void start(Stage primaryStage) {
    Controller controller = new BulletJournalController(primaryStage);
    BulletJournalGuiView bulletJournalWeekView = new BulletJournalWeekView(controller);

    try {
      // load and place the cs3500.pa05.view's scene onto the stage
      primaryStage.setScene(bulletJournalWeekView.load());
      controller.run();
      primaryStage.setTitle("Bullet Journal");
      // render the stage
      primaryStage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }
}
