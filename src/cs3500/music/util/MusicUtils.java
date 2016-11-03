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

  public static String pitchToString(int pitch)  {
    int note = pitch % 12;
    int octave = (pitch - 12) / 4;
    String noteName;
    switch (note)  {
      case 0:
        noteName = "C ";
      case 1:
        noteName = "C♯";
      case 2:
        noteName = "D ";
      case 3:
        noteName = "D♯";
      case 4:
        noteName = "E ";
      case 5:
        noteName = "F ";
      case 6:
        noteName = "F♯";
      case 7:
        noteName = "G ";
      case 8:
        noteName = "G♯";
      case 9:
        noteName = "A ";
      case 10:
        noteName = "A♯";
      default:
        noteName = "B ";
    }
    return noteName + octave;
  }


}
