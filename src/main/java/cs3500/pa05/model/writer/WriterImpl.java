package cs3500.pa05.model.writer;

import java.io.IOException;

/**
 * represents a file writer
 */
public class WriterImpl implements Writer {
  private Appendable appendable;

  public WriterImpl(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * Writes the given phrase
   *
   * @param phrase - phrase needs to be written
   */
  public void write(String phrase) {
    try {
      appendable.append(phrase); // this may fail, hence the try-catch
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
