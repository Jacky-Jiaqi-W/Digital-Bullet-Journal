package cs3500.pa05.model.reader;

import java.util.Scanner;

/**
 * Represents a file reader
 */
public class ReaderImpl implements Reader {
  private Readable readable;

  /**
   * Instantiates a file reader
   *
   * @param readable - readable given readable input
   */
  public ReaderImpl(Readable readable) {
    this.readable = readable;
  }

  /**
   * Reads the message
   *
   * @return message string
   */
  @Override
  public String read() {
    Scanner scanner = new Scanner(readable);
    StringBuilder output = new StringBuilder();
    output.append(scanner.nextLine());
    return output.toString();
  }
}
