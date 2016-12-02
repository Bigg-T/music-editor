package cs3500.music.util;

import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicModel;

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
}
