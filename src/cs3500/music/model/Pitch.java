package cs3500.music.model;

import cs3500.music.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by tiger on 11/1/16.
 */
class Pitch {
  private List<Note> noteList;
  private final int pitch;

  Pitch(int pitch) {
    this.noteList = new ArrayList<>();
    this.pitch = pitch;
  }

  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) {
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (!(o instanceof Pitch)) {
      return false;
    }

    Pitch that = (Pitch) o;

    return this.pitch == that.pitch;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.pitch);
  }

  /**
   * INVARIANT: The noteList will be be sorted by pitch of the note in ascending order.
   * The list of note will not contains any identical notes.
   * <p>EFFECT: The position will change based on their pitch to be in order.</p>
   * <p>Return true if the note is added. Return false if the note is already in the list and
   * will not be added. Throw an exception if the note is null.</p>
   *
   * @param note the note to be added.
   * @return return true if the note is added
   */
  boolean add(Note note) {

    if (note == null || !(note.samePitch(this.pitch))) {
      throw new IllegalArgumentException("Null note, can't add to list");
    }
    noteList.add(note);
    this.sortByDuration();
    return true;
  }

  /**
   * Remove the given list in the list.
   *
   * @param note the Note
   * @return true if the note is removed
   */
  boolean remove(Note note) {
    note = Utils.requireNonNull(note, "Null Note.");
    return noteList.contains(note) && noteList.remove(note);
  }

  /**
   * EFFECT: The note will get change, but if the changed version of the note is already existed,
   * the note will get "merge" into the existed note. This way the program invariant will hold,
   * about note having duplicate notes.
   *
   * @param note     the note to be edit
   * @param duration change note to given duration
   * @param volume   the new volume of give note
   * @return true if the new note is edited
   */
  boolean edit(Note note, int duration, int volume) {

    note = Utils.requireNonNull(note, "Null note");

    this.noteList.get(this.noteList.indexOf(note)).setBeat(duration);
    this.noteList.get(this.noteList.indexOf(note)).setVolume(volume);
    this.sortByDuration();
    return true;
  }

  void merge(Pitch pitch, int offset) {
    pitch.noteList.forEach(x -> this.add(x));
    this.sortByDuration();
  }

  void sortByDuration() {
    this.noteList.sort(Note.NoteComparators.DURATION);
  }

  /**
   * The new Pitch with all the offset.
   *
   * @param offset the offset for start duration
   * @return a new Pitch
   */
  Pitch offsetStartBeat(int offset) {
    noteList.forEach(x -> x.offsetStartBeat(offset));
    return this;
  }

  /**
   * Return the longest duration of a note in this Pitch.
   *
   * @return the longest duration
   */
  int longestNoteDuration() {
    try {
      return this.noteList.stream().mapToInt(Note::getBeat).max().getAsInt();
    } catch (Exception e) {
      throw new IllegalArgumentException("Number doesn't exist.");
    }
    //return Note.longestNoteDuration(this.noteList).getAsInt();
  }

  /**
   * Return a list of INote.
   *
   * @return list a list of INote
   */
  List<INote> toINoteList() {
    return this.noteList.stream().collect(Collectors.toList());
  }
}
