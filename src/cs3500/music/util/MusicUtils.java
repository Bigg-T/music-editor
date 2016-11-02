package cs3500.music.util;

import cs3500.music.model.*;

/**
 * Created by tiger on 10/31/16.
 */
public final class MusicUtils {

  /**
   * Return the pitch of the the note.
   *
   * @param noteName the noteName
   * @param octave   the octave
   * @return the pitch number
   */
  public static int toPitch(NoteName noteName, int octave) {
    return noteName.toInt() + (octave * 12);
  }


}
