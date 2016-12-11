package cs3500.music.model;

/**
 * Represents a relativePitch in a piece of music.
 * 12 relative pitches are possible.
 */
public enum RelativePitch {
  C("C"), CSHARP("C#"), D("D"), DSHARP("D#"), E("E"), F("F"), FSHARP("F#"), G("G"), GSHARP("G#"),
  A("A"), ASHARP("A#"), B("B");

  private final String string;

  RelativePitch(String string) {
    this.string = string;
  }

  /**
   * Computes how many notes above C this note is.
   *
   * @return Number of notes above C this note is
   */
  public int getValue() {
    return this.ordinal();
  }

  /**
   * Computes the pitch that corresponds with a number between 0 and 11.
   *
   * @param num The ordinal value for which you would like a pitch
   * @return The pitch corresponding to the ordinal number given.
   */
  public static RelativePitch fromOrdinal(int num) {
    if (num < 0 || num >= RelativePitch.values().length) {
      throw new IllegalArgumentException("Out of bounds");
    }
    return RelativePitch.values()[num];
  }

  @Override
  public String toString() {
    return this.getString();
  }

  public String getString() {
    return string;
  }
}
