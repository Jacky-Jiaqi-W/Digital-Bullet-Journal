package cs3500.pa05.model.writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represent tests for WriterImpl
 */
public class WriterImplTest {
  private Appendable appendable;
  private Writer writer;
  private static final String VALUE = "input";
  // this is a constant whicb will not be changed (static and written in UPPER_SNAKE_CASE)

  @BeforeEach
  public void setUp() {
    this.appendable = new StringBuilder();
    this.writer = new WriterImpl(this.appendable);
  }

  @Test
  public void testSuccess() {
    // check empty StringBuilder
    assertEquals(this.appendable.toString(), "");

    // write to it
    this.writer.write(VALUE);

    // check only that value appears in the StringBuilder
    assertEquals(this.appendable.toString(), VALUE);
  }

  @Test
  public void testFailure() {
    this.writer = new WriterImpl(new MockAppendable());
    Exception exc = assertThrows(RuntimeException.class, () -> this.writer.write(VALUE),
        "Mock throwing an error");
    assertEquals("Mock throwing an error", exc.getMessage());
  }
}
