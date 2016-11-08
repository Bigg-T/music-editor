package cs3500.music.model;

import cs3500.music.util.MusicUtils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

/**
 * This represent the a model cs3500.model.model.Note.
 */
class Note implements INote {

  private final NoteName noteName;
  private final int octave;
  private final Duration duration;
  private final int channel;
  private int volume;

  /**
   * Construct Note object.
   *
   * @param noteName      the name of the note
   * @param octave        the octave number
   * @param startDuration at what beat the note start
   * @param numBeat       the duration of this note
   */
  Note(NoteName noteName, int octave, int startDuration, int numBeat, int volume, int channel) {
    requireNonNull(noteName);

    this.noteName = noteName;
    this.octave = octave;
    this.duration = new Duration(startDuration, numBeat);
    this.volume = volume;
    this.channel = channel;
  }

  @Override
  public NoteName getNoteName() {
    return noteName;
  }

  @Override
  public int getOctave() {
    return octave;
  }

  @Override
  public int getChannel() {
    return channel;
  }

  @Override
  public int getVolume() {
    return volume;
  }

  @Override
  public boolean isViewNote() {
    return false;
  }

  @Override
  public int getStartDuration() {
    return this.duration.getStart();
  }

  @Override
  public int getBeat() {
    return this.duration.getBeat();
  }

  /**
   * Offset the start beat by a given offset.
   *
   * @param offset the new starting beat
   */
  void offsetStartBeat(int offset) {
    this.getDuration().setStart(this.getDuration().getStart() + offset);
  }

  /**
   * Set the volume.
   *
   * @param volume volume
   */
  void setVolume(int volume) {
    this.volume = volume;
  }

  void setBeat(int duration) {
    this.getDuration().setBeat(duration);
  }

  /**
   * Encapsulate field.
   *
   * @return this note Duration
   */
  private Duration getDuration() {
    return duration;
  }

  /**
   * Throw IllegalArgs when the object is null.
   *
   * @param o the object
   */
  private void requireNonNull(Object o) {
    if (o == null) {
      throw new IllegalArgumentException("Null object.");
    }
  }

  /**
   * EFFECT: Change the end duration to new duration.
   *
   * @param beat the new number of duration in beat
   */
  void editDuration(int beat) {
    if (beat > 0) {
      duration.setBeat(beat);
      return;
    }
    throw new IllegalArgumentException("Invalid end duration input.");
  }

  @Override
  public String toString() {
    return getNoteName().toString() + getOctave();
  }

  String toRender() {
    StringBuilder stringBuilder = new StringBuilder(NotePlay.NOTE_PLAY.toString());
    for (int i = 1; i < duration.getBeat(); i++) {
      stringBuilder.append("\n").append(NotePlay.NOTE_SUSTAIN.toString());
    }
    return stringBuilder.toString();
  }


  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) {
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (!(o instanceof Note)) {
      return false;
    }

    Note that = (Note) o;

    return this.toString().equals(that.toString())
            && this.duration.getStart() == that.duration.getStart()
            && this.duration.getBeat() == that.duration.getBeat()
            && this.channel == that.channel
            && this.volume == that.volume;
  }

  @Override
  public int hashCode() {
    return Objects.hash(noteName, duration.getBeat(), duration.getStart(), octave,
            this.toString(), this.channel, this.volume);
  }

  /**
   * Return true if the given pitch is equal to the note's pitch.
   *
   * @param pitch the pitch
   * @return true if the pitch == to derived this note pitch
   */
  boolean samePitch(int pitch) {
    return this.toPitch() == pitch;
  }

  /**
   * Return the int representation of the pitch of this note.
   *
   * @return int representation of pitch
   */
  int toPitch() {
    return noteName.toInt() + this.octave * 12;
  }


  /**
   * Get the longest note duration in the note.
   *
   * @param notes lsit of notes
   * @return the note with the longest duration.
   */
  Note longestBeat(List<Note> notes) {
    Iterator<Note> noteIter = notes.iterator();
    Note longestNote = this;
    while (noteIter.hasNext()) {
      Note note = noteIter.next();
      if (note.duration.getBeat() > this.duration.getBeat()) {
        longestNote = note;
      }
    }
    return longestNote;
  }

  /**
   * Get the longest note duration in the note.
   *
   * @param notes lsit of notes
   * @return the note with the longest duration.
   */
  static OptionalInt longestNoteDuration(List<Note> notes) {
    return notes.stream().mapToInt(Note::getBeat).max();
  }

  /**
   * Comparators for cs3500.model.model.Note.
   */
  static class NoteComparators {

    static final Comparator<Note> DURATION = (thisNote, thatNote)
            -> Integer.compare(thisNote.duration.getBeat(), thatNote.duration.getBeat());

    static final Comparator<Note> OCTAVE = (thisNote, thatNote)
            -> Integer.compare(thisNote.octave, thatNote.octave);

    static final Comparator<Note> NOTE = (thisNote, thatNote)
            -> {
      if (thisNote.getNoteName().compareTo(thatNote.getNoteName()) == 0) {
        return NoteComparators.OCTAVE.compare(thisNote, thatNote);
      }
      return thisNote.getNoteName().compareTo(thatNote.getNoteName());
    };

    //sort by pitch
    static final Comparator<Note> PITCH = (thisNote, thatNote)
            -> Integer.compare(MusicUtils.toPitch(thisNote.noteName, thisNote.octave),
            MusicUtils.toPitch(thatNote.noteName, thatNote.octave));

  }

  /**
   * Represent the duration of the note.
   * The note can last [n, m).
   */
  private static final class Duration {

    private int start;
    private int beat;

    /**
     * Construct a duration.
     *
     * @param start the start beat
     * @param beat  the number of beat the a note will last
     */
    private Duration(int start, int beat) {
      if ((0 >= beat)) {
        throw new IllegalArgumentException("Beat can't be less than 0");
      }
      this.setStart(start);
      this.setBeat(beat);
    }

    /**
     * Gets this start.
     *
     * @return This start
     */
    private int getStart() {
      return start;
    }

    /**
     * EFFECT: setting the start.
     *
     * @param start the start beat at
     */
    private void setStart(int start) {
      this.start = start;
    }

    /**
     * Encapsulate field.
     *
     * @return the duration
     */
    private int getBeat() {
      return beat;
    }

    /**
     * Encapsulate field.
     *
     * @param end setting the duration
     */
    private void setBeat(int end) {
      this.beat = end;
    }

  }

}
