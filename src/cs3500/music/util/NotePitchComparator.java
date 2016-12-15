package cs3500.music.util;

import cs3500.music.model.Note;

import java.util.Comparator;

/**
 * A Comparator for finding the greater/lesser of two notes using their pitches.
 */
public class NotePitchComparator implements Comparator<Note> {


  @Override
  public int compare(Note note1, Note note2) {
    return Integer.compare(note1.getAbsolutePitch().intValue(),
            note2.getAbsolutePitch().intValue());
  }
}
