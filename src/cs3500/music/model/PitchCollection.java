package cs3500.music.model;

import cs3500.music.util.Utils;
import javafx.collections.transformation.SortedList;

import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * INVARIANT: Since the is not possible to add duplicate notes, noteList will only have one
 * instance of each note.
 */
final class PitchCollection {

  //Integer is a notePitch
  private SortedMap<Integer, Pitch> pitchTreeMap;

  PitchCollection() {
    this.pitchTreeMap = new TreeMap<>();
  }

  boolean add(Note note) {
    note = Utils.requireNonNull(note, "Null note.");
    int notePitch = note.toPitch();

    if (pitchTreeMap.get(notePitch) == null) {
      Pitch pitch = new Pitch(notePitch);
      this.pitchTreeMap.put(notePitch, pitch);
      return pitch.add(note);
    }

    return pitchTreeMap.get(notePitch).add(note);
  }

  boolean remove(Note note) {
    note = Utils.requireNonNull(note, "Null note.");
    int notePitch = note.toPitch();

    return (pitchTreeMap.get(notePitch) != null) && pitchTreeMap.get(notePitch).remove(note);
  }

  boolean edit(Note note, int pitch, int volume) {
    note = Utils.requireNonNull(note, "Null note.");
    int notePitch = note.toPitch();

    return (pitchTreeMap.get(notePitch) != null)
            && pitchTreeMap.get(notePitch).edit(note, pitch, volume);
  }

  void merge(PitchCollection thatPitchCollect, int offset) {
    thatPitchCollect = Utils.requireNonNull(thatPitchCollect, "Null PitchCollection");
    Set<Integer> thatKeys = thatPitchCollect.pitchTreeMap.keySet();

    thatKeys.forEach(x -> {

    });
  }

  PitchCollection adjustBeat(int offset) {
    this.pitchTreeMap.values().forEach(x -> x.offsetStartBeat(offset));
    return this;
  }

  int longestNoteDuration() {
    try {
      return this.pitchTreeMap.values().stream()
              .mapToInt(Pitch::longestNoteDuration).max().getAsInt();
    } catch (Exception e) {
      throw new IllegalArgumentException("Number doesn't exist.");
    }
  }

  /**
   * Return the min pitch.
   *
   * @return the min pitch
   */
  int getMinPitch() {
    try {
      return this.pitchTreeMap.keySet().stream().min(Integer::compare).get();
    } catch (Exception e) {
      throw new IllegalArgumentException("List doesn't have any item.");
    }
  }

  /**
   * Return the max pitch.
   *
   * @return the max pitch
   */
  int getMaxPitch() {
    try {
      return this.pitchTreeMap.keySet().stream().max(Integer::compare).get();
    } catch (Exception e) {
      throw new IllegalArgumentException("List doesn't have any item.");
    }
  }

  SortedMap<Integer, List<INote>> getAllNote() {
    SortedMap<Integer, List<INote>> map = new TreeMap<>();
    this.pitchTreeMap.keySet().forEach(x -> map.put(x, this.pitchTreeMap.get(x).toINoteList()));
    return map;
  }

}

