package cs3500.music.model;

import cs3500.music.util.Utils;

import java.util.TreeMap;

/**
 * INVARIANT: Since the is not possible to add duplicate notes, noteList will only have one
 * instance of each note.
 */
final class PitchCollection {

  //Integer
  TreeMap<Integer, Pitch> pitchTreeMap;

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

}
