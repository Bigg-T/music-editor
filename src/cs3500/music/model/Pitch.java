package cs3500.music.model;

import cs3500.music.util.Utils;
import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.IntStream;

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
   * <p>
   * EFFECT: The position will change based on their pitch to be in order.
   * <p>
   * Return true if the note is added. Return false if the note is already in the list and
   * will not be added. Throw an exception if the note is null.
   *
   * @param note the note to be added.
   * @return return true if the note is added
   */
  boolean add(Note note) {

    if (note == null || !(note.samePitch(this.pitch))) {
      throw new IllegalArgumentException("Null note, can't add to list");
    }

    if (this.noteList.contains(note)) {
      return false;
    }

    noteList.add(note);
    noteList.sort(Note.NoteComparators.PITCH);
    return true;
  }

  /**
   * Remove the note.
   *
   * @param note
   * @return
   */
  boolean remove(Note note) {
    note = Utils.requireNonNull(note, "Null Note.");
    return noteList.contains(note) && noteList.remove(note);
  }

  boolean edit(Note note, int pitch, int volume) {
    return false;
  }

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
}
