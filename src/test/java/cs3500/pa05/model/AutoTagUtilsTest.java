package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AutoTagUtilsTest {
  @Test
  public void testContainsValidAutoTag() {
    AutoTagUtils autoTagUtils = new AutoTagUtils();

    // Valid auto tags
    assertTrue(autoTagUtils.containsValidAutoTag("#tag1 Task Name"));
    assertTrue(autoTagUtils.containsValidAutoTag("#tag2 Another Task"));
    assertTrue(autoTagUtils.containsValidAutoTag("#tag_3 Third Task"));

    // Invalid auto tags
    assertFalse(autoTagUtils.containsValidAutoTag("Task Name"));
    assertFalse(autoTagUtils.containsValidAutoTag("#"));
    assertFalse(autoTagUtils.containsValidAutoTag("#tag1"));
    assertFalse(autoTagUtils.containsValidAutoTag("#tag1 "));
  }

  @Test
  public void testGetCategoryName() {
    AutoTagUtils autoTagUtils = new AutoTagUtils();

    assertEquals("TAG1", autoTagUtils.getCategoryName("#tag1 Task Name"));
    assertEquals("TAG2", autoTagUtils.getCategoryName("#tag2 Another Task"));
    assertEquals("TAG_3", autoTagUtils.getCategoryName("#tag_3 Third Task"));
  }

  @Test
  public void testGetTaskName() {
    AutoTagUtils autoTagUtils = new AutoTagUtils();

    assertEquals("Task Name", autoTagUtils.getTaskName("#tag1 Task Name"));
    assertEquals("Another Task", autoTagUtils.getTaskName("#tag2 Another Task"));
    assertEquals("Third Task", autoTagUtils.getTaskName("#tag_3 Third Task"));
  }
}