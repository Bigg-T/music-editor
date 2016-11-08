package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The Music editor.
 */
public interface IBasicMusicEditor<K> {

  /**
   * EFFECT: Add a note to a the editor.
   *
   * @param note the note
   * @return true if the beat is successfully added
   */
  public boolean add(K note);

  /**
   * EFFECT: Remove will remove all the note at start at a starting Duraiton.
   * This position is suppose to allow the client to remove a note at a in the stack of
   * same starting point. This simple model will not support that. Therefore position
   * field will be ignored at this point.
   *
   * @param note the noteFields
   * @return the list of notes are being removed
   */
  public boolean remove(K note);

  /**
   * EFFECT: Change the duration of the note.
   * Return true if the operation is completed successfully.
   *
   * @param note the note
   * @param duration    the the duration of the beat
   * @param volume the volume  the note
   * @return return the true if the beat is successfully added
   */
  public boolean edit(K note, int duration, int volume);

  /**
   * EFFECT: Merge the other piece into one piece.
   *
   * @param piece         the IBasicMusic editor
   * @param isConsecutive play consecutive with the
   */
  public void merge(IBasicMusicEditor<K> piece, boolean isConsecutive);

  public SortedMap<Integer, List<INote>> getAllNotesAt(int beatNum);

  /**
   * Integer -> is the beat number
   * SortedMap Integer -> the pitch number
   * @return
   */
  public TreeMap<Integer, SortedMap<Integer, List<INote>>> composition();

  int getMinPitch();

  int getMaxPitch();

  int getTempo();

  int getLastStartBeat();

  int getLastBeat();

  boolean isViewEditor();
}
