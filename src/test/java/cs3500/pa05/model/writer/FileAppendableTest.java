package cs3500.pa05.model.writer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * tests for FileAppendable
 */
public class FileAppendableTest {

  @Test
  public void testAppend_String() throws IOException {
    File file = new File("test.txt");
    FileAppendable fileAppendable = new FileAppendable(file);
    fileAppendable.append("Hello, World!");

    String content = FileReaderHelper.readFile(file);
    assertEquals("Hello, World!", content);
  }

  @Test
  public void testAppend_CharSequence() throws IOException {
    File file = new File("test.txt");
    FileAppendable fileAppendable = new FileAppendable(file);
    fileAppendable.append(new StringBuilder("Hello, World!"));

    String content = FileReaderHelper.readFile(file);
    assertEquals("Hello, World!", content);
  }

  @Test
  public void testAppend_CharSequenceWithRange() throws IOException {
    File file = new File("test.txt");
    FileAppendable fileAppendable = new FileAppendable(file);
    CharSequence sequence = new StringBuilder("Hello, World!");

    fileAppendable.append(sequence, 7, 12); // Appending "World"

    String content = FileReaderHelper.readFile(file);
    assertEquals("World", content);
  }

  @Test
  public void testAppend_Char() throws IOException {
    File file = new File("test.txt");
    FileAppendable fileAppendable = new FileAppendable(file);
    fileAppendable.append('A');

    String content = FileReaderHelper.readFile(file);
    assertEquals("A", content);
  }

  private static class FileReaderHelper {
    static String readFile(File file) throws IOException {
      byte[] bytes = java.nio.file.Files.readAllBytes(file.toPath());
      return new String(bytes);
    }
  }

}