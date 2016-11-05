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

  /**
   * Returns String representation of the given pitch
   * @param pitch Pitch to be represented
   * @return      String representation
   */
  public static String pitchToString(int pitch)  {
    int note = pitch % 12;
    int octave = (pitch / 12) - 1;
    String noteName;
    switch (note)  {
      case 0:
        noteName = "C ";
        break;
      case 1:
        noteName = "C♯";
        break;
      case 2:
        noteName = "D ";
        break;
      case 3:
        noteName = "D♯";
        break;
      case 4:
        noteName = "E ";
        break;
      case 5:
        noteName = "F ";
        break;
      case 6:
        noteName = "F♯";
        break;
      case 7:
        noteName = "G ";
        break;
      case 8:
        noteName = "G♯";
        break;
      case 9:
        noteName = "A ";
        break;
      case 10:
        noteName = "A♯";
        break;
      default:
        noteName = "B ";
        break;
    }
    return noteName + octave;
  }

  public static int toTrack(int channel) {
    return channel % 16;
  }

}
