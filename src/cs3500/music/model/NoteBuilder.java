package cs3500.music.model;

/**
 * For the purposes of building a Note.
 */
public class NoteBuilder {

  private NoteName noteName = NoteName.C;
  private int octave = 4;
  private int startDuration = 0;
  private int numBeats = 1;
  private int channel = 1;
  private int volume = 50;

  /**
   * Sets this noteName value.
   *
   * @param noteName desired noteName value
   * @return updated NoteBuilder
   */
  NoteBuilder setNoteName(NoteName noteName) {
    this.noteName = noteName;
    return this;
  }

  /**
   * Sets this octave value.
   *
   * @param octave desired NoteName value
   * @return updated NoteBuilder
   */
  NoteBuilder setOctave(int octave) {
    this.octave = octave;
    return this;
  }

  /**
   * Sets this NoteName value.
   *
   * @param startDuration desired NoteName value
   * @return updated NoteBuilder
   */
  NoteBuilder setStartDuration(int startDuration) {
    this.startDuration = startDuration;
    return this;
  }

  /**
   * Sets this NoteName value.
   *
   * @param numBeats desired number of beats value
   * @return updated NoteBuilder
   */
  NoteBuilder setNumBeats(int numBeats) {
    this.numBeats = numBeats;
    return this;
  }

  /**
   * Sets this channel value.
   *
   * @param channel desired channel value
   * @return updated NoteBuilder
   */
  NoteBuilder setChannel(int channel) {
    this.channel = channel;
    return this;
  }

  /**
   * Sets this volume value.
   *
   * @param volume desired volume value
   * @return updated NoteBuilder
   */
  NoteBuilder setVolume(int volume) {
    this.volume = volume;
    return this;
  }

  /**
   * Builds a Note object.
   *
   * @return Note object
   */
  public Note buildNote() {
    try {
      return new Note(this.noteName, this.octave, this.startDuration,
              this.numBeats, this.volume, this.channel);
    } catch (Exception e) {
      throw new IllegalArgumentException("Note didn't construct properly.");
    }

  }

  static ViewNote buildNote(Note note) {
    try {
      return new ViewNote(note);
    } catch (Exception e) {
      throw new IllegalArgumentException("Note didn't construct properly.");
    }
  }


}
