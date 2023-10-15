package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Categories;
import cs3500.pa05.model.DayToDos;
import java.util.List;

/**
 * JSON format of this record:
 *
 * @param weekName      - the week name
 * @param maxEvents     - the max number of events
 * @param maxTasks      - the max number of tasks
 * @param categories    - the categories
 * @param quote         - the quote
 * @param dayToDos      - a list of daily spread
 */
public record BulletJournalJson(
    @JsonProperty("week-name") String weekName,
    @JsonProperty("max-events") int maxEvents,
    @JsonProperty("max-tasks") int maxTasks,
    @JsonProperty("categories") Categories categories,
    @JsonProperty("quote") String quote,
    @JsonProperty("daily-spreads") List<DayToDos> dayToDos) {

  /**
   * Gets the week name
   *
   * @return week name
   */
  @JsonGetter("week-name")
  public String getWeekName() {
    return this.weekName;
  }

  /**
   * Gets the max events
   *
   * @return max events
   */
  @JsonGetter("max-events")
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Gets the max tasks
   *
   * @return max tasks
   */
  @JsonGetter("max-tasks")
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Gets the categories
   *
   * @return categories
   */
  @JsonGetter("categories")
  public Categories getCategories() {
    return this.categories;
  }

  /**
   * Gets the quote
   *
   * @return quote
   */
  @JsonGetter("quote")
  public String getQuote() {
    return this.quote;
  }

  /**
   * Gets the day todos
   *
   * @return day todos
   */
  @JsonGetter("daily-spreads")
  public List<DayToDos> getDayToDos() {
    return this.dayToDos;
  }
}
