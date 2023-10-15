package cs3500.pa05.model;

import java.util.List;

/**
 * Represents categories
 */
public enum Category {
  PERSONAL(0),
  ACADEMIC(1),
  LIFE(2),
  NONE(3);

  private int index;

  Category(int index) {
    this.index = index;
  }


  /**
   * Gets the weekday with the specified index
   *
   * @param index - index of the weekday
   * @return weekday with the specified index
   */
  public static Category getByIndex(int index) {
    Category value = Category.values()[index];
    return value;
  }



}


