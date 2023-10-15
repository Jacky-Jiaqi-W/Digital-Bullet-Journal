package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Categories;
import cs3500.pa05.model.DayToDos;
import cs3500.pa05.model.DayToDosCollection;
import cs3500.pa05.model.ToDo;
import cs3500.pa05.model.Weekday;
import cs3500.pa05.model.json.BulletJournalJson;
import cs3500.pa05.view.DailySpreadVbox;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Represents a bullet journal controller
 */
public class BulletJournalController implements Controller {
  @FXML
  private TextField weekName;
  @FXML
  private TextField quote;
  @FXML
  private MenuItem setMax;
  @FXML
  private MenuItem addTask;
  @FXML
  private MenuItem addEvent;
  @FXML
  private MenuItem addCategory;
  @FXML
  private MenuItem saveFile;
  @FXML
  private MenuItem setWeekStart;
  @FXML
  private MenuItem setStatus;
  @FXML
  private Button delete;
  @FXML
  private GridPane weeklyOverview;
  @FXML
  private Label totalEvents;
  @FXML
  private Label totalTasks;
  @FXML
  private Label percentCompleted;

  private Stage stage;
  private int maxEvents;
  private int maxTasks;
  private List<DayToDos> dayToDosList;
  private DayToDosCollection dayToDosCollection;
  private Categories categories;
  private List<ToDo> tasks;
  private NewOrOpenEventHandler newOrOpenEventHandler;
  private ObjectMapper mapper = new ObjectMapper();

  /**
   * Instantiates a Bullet Journal controller
   */
  public BulletJournalController(Stage stage) {
    this.maxEvents = 5; // default new bullet journal max events
    this.maxTasks = 5; // default new bullet journal max tasks
    this.categories = new Categories();
    this.dayToDosCollection = new DayToDosCollection();
    this.dayToDosList = this.dayToDosCollection.getDayToDosList();
    this.tasks = new ArrayList<>();
  }

  /**
   * Runs the program
   */
  @Override
  @FXML
  public void run() {
    this.newOrOpenEventHandler = new NewOrOpenEventHandler(this);
    this.newOrOpenEventHandler.handle(null);
    this.updateWeeklySpread();
    this.setWeekStart.setOnAction(new WeekStartEventHandler(this));
    this.saveFile.setOnAction(new SaveFileEventHandler(this));
    this.setMax.setOnAction(new SetMaxButtonEventHandler(this));
    this.addTask.setOnAction(new AddTaskEventHandler(this));
    this.addEvent.setOnAction(new AddEventEventHandler(this));
    this.addCategory.setOnAction(new AddCategoryEventHandler(this));
    this.delete.setOnAction(new DeleteEventHandler(this));
    this.setStatus.setOnAction(new SetStatusEventHandler(this));
  }

  /**
   * Updates the weekly spread
   */
  @FXML
  public void updateWeeklySpread() {
    this.weeklyOverview.getChildren().clear();
    incrementWeeklyOverview();
    DayToDos dayToDos;
    for (int i = 0; i < 8; i++) {
      if (i != 7) {
        dayToDos = dayToDosList.get(i);
        DailySpreadVbox dailySpreadVbox =
            new DailySpreadVbox(dayToDos.getWeekday().toString(), dayToDos.getToDos());
        this.weeklyOverview.add(dailySpreadVbox, i % 4, i / 4);
        dailySpreadVbox.setStyle("-fx-background-color: #8fb2d0");
      } else {
        DailySpreadVbox dailySpreadVbox = new DailySpreadVbox("Tasks", this.tasks);
        this.weeklyOverview.add(dailySpreadVbox, i % 4, i / 4);
      }
    }
  }

  /**
   * Initializes the state given the read input
   *
   * @param input read input
   */
  public void initFromFile(String input) {
    BulletJournalJson bulletJournalJson = null;
    try {
      bulletJournalJson = this.mapper.readValue(input, BulletJournalJson.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e); // Check back
    }
    this.weekName.setText(bulletJournalJson.getWeekName());
    this.quote.setText(bulletJournalJson.getQuote());
    this.maxEvents = bulletJournalJson.getMaxEvents();
    this.maxTasks = bulletJournalJson.getMaxTasks();
    this.categories = bulletJournalJson.getCategories();
    this.dayToDosList = bulletJournalJson.getDayToDos();
    this.dayToDosCollection = new DayToDosCollection(this.dayToDosList);
    this.tasks = this.dayToDosCollection.getTaskQueue();
    this.totalEvents.setText(String.valueOf(this.dayToDosCollection.countTotalEvents()));
    this.totalTasks.setText(String.valueOf(this.dayToDosCollection.countTotalTasks()));
    this.percentCompleted.setText(this.dayToDosCollection.completedPercentage());
  }

  /**
   * Sets the max events as the given number
   *
   * @param maxEvents given max events number
   */
  public void setMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
  }

  /**
   * Sets the max tasks as the given number
   *
   * @param maxTasks given max tasks number
   */
  public void setMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
  }

  /**
   * Adds the given todo to the day todos list
   *
   * @param todo given todo
   */
  public void addToDo(ToDo todo) {
    Weekday weekday = todo.getWeekday();
    for (DayToDos dayToDos : this.dayToDosList) {
      if (dayToDos.getWeekday().equals(weekday)) {
        dayToDos.addToDo(todo);
        break;
      }
    }
  }

  /**
   * Adds the given task to the tasks list
   *
   * @param task given task
   */
  public void addTasks(ToDo task) {
    this.tasks.add(task);
  }

  /**
   * getter for the dayToDos field
   *
   * @return get dayToDos
   */
  public List<DayToDos> getDayToDos() {
    return this.dayToDosList;
  }

  /**
   * sets the day toDos to a new list of toDos
   *
   * @param newDaysToDos - new list of days toDos
   */
  public void setDayToDos(List<DayToDos> newDaysToDos) {
    this.dayToDosList = newDaysToDos;
  }

  /**
   * Gets the categories
   *
   * @return categories
   */
  public Categories getCategories() {
    return this.categories;
  }

  /**
   * save the content
   *
   * @return the generative content in String format
   */
  public String generateSaveContent() {
    BulletJournalJson bulletJournalJson =
        new BulletJournalJson(this.weekName.getText(), maxEvents, maxTasks, categories,
            this.quote.getText(), this.dayToDosList);
    try {
      return mapper.writeValueAsString(bulletJournalJson);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e); // Check back
    }
  }

  /**
   * getter for the total events overview
   *
   * @return the totalEvents
   *
   */
  public Label getTotalEventsOverview() {
    return this.totalEvents;
  }

  /**
   * getter for the total tasks overview
   *
   * @return the totalTasks
   *
   */
  public Label getTotalTasksOverview() {
    return this.totalTasks;
  }

  /**
   * getter for the percentCompletedOverview field
   *
   * @return the percent completed overview statistic
   */
  public Label getPercentCompletedOverview() {
    return this.percentCompleted;
  }

  /**
   * increment the weekly overview statistics
   */
  public void incrementWeeklyOverview() {
    int totalTasks = this.dayToDosCollection.countTotalTasks();
    int totalEvents = this.dayToDosCollection.countTotalEvents();
    double totalTaskCompleted = 0.0;
    for (DayToDos dayToDos : this.dayToDosList) {
      totalTaskCompleted += dayToDos.totalTasksCompleted();
    }
    double completedPercentage = (totalTaskCompleted / totalTasks) * 100;
    // makes it two decimal places
    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    String formattedNumber = decimalFormat.format(completedPercentage);
    getTotalEventsOverview().setText(String.valueOf(totalEvents));

    getTotalTasksOverview().setText(String.valueOf(totalTasks));
    getTotalEventsOverview().setText(String.valueOf(totalEvents));
    getPercentCompletedOverview().setText(formattedNumber);
  }

  /**
   * Checks if the provided max events and max tasks are already exceeded
   *
   * @param maxEvents max events
   * @param maxTasks max tasks
   * @return true if the provided max events and max tasks are already exceeded
   */
  public boolean alreadyExceeds(int maxEvents, int maxTasks) {
    return this.dayToDosCollection.alreadyExceeds(maxEvents, maxTasks);
  }

  /**
   * Checks if the given weekday has available event
   *
   * @param weekday given weekday
   * @return true if the given weekday has available event
   */
  public boolean availableDayEvent(Weekday weekday) {
    return this.dayToDosCollection.countDayEvent(weekday) < this.maxEvents;
  }

  /**
   * Checks if the given weekday has available task
   *
   * @param weekday given weekday
   * @return true if the given weekday has available task
   */
  public boolean availableDayTask(Weekday weekday) {
    return this.dayToDosCollection.countDayTask(weekday) < this.maxTasks;
  }

  /**
   * getter for the task list field
   *
   * @return tasks
   */
  public List<ToDo> getTasks() {
    return this.tasks;
  }

  /**
   * getter for the dayToDosCollection field
   *
   * @return the dayToDoCollection
   */
  public DayToDosCollection getDayToDosCollection() {
    return this.dayToDosCollection;
  }

}
