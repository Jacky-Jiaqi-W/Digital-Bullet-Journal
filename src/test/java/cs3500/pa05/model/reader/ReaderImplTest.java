package cs3500.pa05.model.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import org.junit.jupiter.api.Test;


/**
 * test for ReaderImpl
 */
public class ReaderImplTest {

  @Test
  public void testRead() {
    String input = "Hello, World!";
    Readable readable = new StringReader(input);
    ReaderImpl reader = new ReaderImpl(readable);

    String result = reader.read();

    assertEquals(input, result);
  }
}

