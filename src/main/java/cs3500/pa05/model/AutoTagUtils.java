package cs3500.pa05.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * represent the util class for Auto Tags
 */
public class AutoTagUtils {
  /**
   * Checks if the task name is in correct format
   *
   * @param taskName task name
   * @return true if the task name is in correct format
   */
  public boolean containsValidAutoTag(String taskName) {
    String pattern = "#(\\w+)\\s.+";
    Pattern regex = Pattern.compile(pattern);
    Matcher matcher = regex.matcher(taskName);
    return matcher.matches();
  }

  /**
   * Gets the category name
   *
   * @param taskName task name
   * @return category name
   */
  public String getCategoryName(String taskName) {
    String trimmedTaskName = taskName.trim();
    String pattern = "#(\\w+)\\s\\w+";
    Pattern regex = Pattern.compile(pattern);
    Matcher matcher = regex.matcher(trimmedTaskName);

    if (matcher.find()) {
      return matcher.group(1).toUpperCase();
    } else {
      throw new IllegalArgumentException("No valid auto tag found in the task name: " + taskName);
    }
  }

  /**
   * Gets the task name
   *
   * @param taskName task name
   * @return task name
   */
  public String getTaskName(String taskName) {
    String pattern = "#(\\w+)\\s(.+)";
    Pattern regex = Pattern.compile(pattern);
    Matcher matcher = regex.matcher(taskName);

    if (matcher.find()) {
      return matcher.group(2);
    } else {
      throw new IllegalArgumentException("No valid auto tag found in the task name: " + taskName);
    }
  }
}
