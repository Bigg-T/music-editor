package cs3500.music.model;

import cs3500.music.util.Utils;

/**
 * Created by tiger on 10/31/16.
 */
public class NoteFields {

  private final NoteName noteName;
  private final int octave;
  private final int startDuration;
  private final int numBeat;
  private final int volume;
  private final int channel;


  NoteFields(NoteName noteName, int octave, int startDuration,
             int numBeat, int volume, int channel) {
    this.noteName = Utils.requireNonNull(noteName, "Null Note.");
    this.octave = octave;
    this.startDuration = startDuration;
    this.numBeat = numBeat;
    this.volume = volume;
    this.channel = channel;
  }


  NoteName getNoteName() {
    return noteName;
  }

  int getOctave() {
    return octave;
  }

  int getStartDuration() {
    return startDuration;
  }

  int getNumBeat() {
    return numBeat;
  }

  int getVolume() {
    return volume;
  }

  int getChannel() {
    return channel;
  }
}
