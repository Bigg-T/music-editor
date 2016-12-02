package cs3500.music.model;

import java.util.Objects;

/**
 * Represents an Absolute Pitch in a piece of music. An Absolute Pitch should not be mutable.
 */
public class AbsolutePitch {
  private final int octave;

  private final RelativePitch relativePitch;

  /**
   * Default constructor for an Absolute Pitch.
   *
   * @param relativePitch Relative pitch of this pitch
   * @param octave        Octave of this pitch.
   */
  public AbsolutePitch(RelativePitch relativePitch, int octave) {
    this.relativePitch = relativePitch;
    this.octave = octave;
  }

  /**
   * Convenience constructor for an Absolute Pitch.
   *
   * @param value The numeric value of this pitch
   */
  public AbsolutePitch(int value) {
    this(RelativePitch.fromOrdinal(value % 12), value / 12);
  }

  /**
   * Computes the numeric value for this pitch.
   *
   * @return The numeric value for this pitch
   */
  public int intValue() {
    return this.getOctave() * 12 + getRelativePitch().getValue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(intValue());
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof AbsolutePitch)) {
      return false;
    }
    return ((AbsolutePitch) other).intValue() == this.intValue();
  }

  @Override
  public String toString() {
    return this.getRelativePitch().toString() + (getOctave() - 1);
  }

  /**
   * The octave of this pitch.
   */
  public int getOctave() {
    return octave;
  }

  /**
   * The Relative Pitch of his pitch.
   */
  public RelativePitch getRelativePitch() {
    return relativePitch;
  }
}
