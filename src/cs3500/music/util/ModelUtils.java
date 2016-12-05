package cs3500.music.util;

import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicModel;
import cs3500.music.model.RelativePitch;

import java.util.Optional;

/**
 * A Utility class for finding various values given a model.
 */
public final class ModelUtils {

  /**
   * Private constructor to prevent initialization.
   */
  private ModelUtils() {
  }

  /**
   * Finds the minimum pitch in a given model.
   *
   * @param model The model in which to find a minimum pitch.
   * @return The integer value of the minimum pitch found.
   */
  public static int getMinAbsolutePitch(ReadOnlyMusicModel model) {
    NotePitchComparator npc = new NotePitchComparator();
    Optional<Note> min = model.getNotes().stream().min(npc);
    return min.isPresent() ? min.get().getAbsolutePitch().intValue() : 0;
  }

  /**
   * Finds the maximum pitch in a given model.
   *
   * @param model The model in which to find a maximum pitch.
   * @return The integer value of the maximum pitch found.
   */
  public static int getMaxAbsolutePitch(ReadOnlyMusicModel model) {
    NotePitchComparator npc = new NotePitchComparator();
    Optional<Note> max = model.getNotes().stream().max(npc);
    return max.isPresent() ? max.get().getAbsolutePitch().intValue() : -1;
  }

  /**
   * Convert to the correct notename.
   *
   * @param pitch the pitch number
   * @return correct note mane.
   */
  public static RelativePitch convertRelativeEnum(int pitch) {
    int note = pitch % 12;
    switch (note) {
      case 0:
        return RelativePitch.C;
      case 1:
        return RelativePitch.CSHARP;
      case 2:
        return RelativePitch.D;
      case 3:
        return RelativePitch.DSHARP;
      case 4:
        return RelativePitch.E;
      case 5:
        return RelativePitch.F;
      case 6:
        return RelativePitch.FSHARP;
      case 7:
        return RelativePitch.G;
      case 8:
        return RelativePitch.GSHARP;
      case 9:
        return RelativePitch.A;
      case 10:
        return RelativePitch.ASHARP;
      default:
        return RelativePitch.B;
    }
  }
}
