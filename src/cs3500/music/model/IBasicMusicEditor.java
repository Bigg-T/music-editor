package cs3500.music.model;

import java.util.List;
import java.util.SortedMap;

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
   * @param note     the note
   * @param duration the the duration of the beat
   * @param volume   the volume  the note
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

  SortedMap<Integer, List<K>> getAllNotesAt(int beatNum);

  /**
   * Integer -> is the beat number.
   * SortedMap Integer -> the pitch number.
   *
   * @return the whole map of notes
   */
  public SortedMap<Integer, SortedMap<Integer, List<K>>> composition();

  /**
   * Return the minimum Pitch.
   *
   * @return min pitch
   */
  int getMinPitch();

  /**
   * Return the maximun Pitch.
   *
   * @return the max pitch
   */
  int getMaxPitch();

  /**
   * Return the Tempo in MPQ.
   *
   * @return tempo in MPQ
   */
  int getTempo();

  /**
   * Return the last starting beat.
   *
   * @return the beat
   */
  int getLastStartBeat();

  /**
   * Return the last beat in the composition.
   *
   * @return the last beat in int composition.
   */
  int getLastBeat();

  /**
   * True if the type viewEditor. Safe casting.
   *
   * @return true if an ViewEditor
   */
  boolean isViewEditor();

  /**
   * Returns all of the notes that are present during the given beat.
   *
   * @param beat The beat at which we are finding all notes present
   * @return The notes present at the given beat
   * @throws IllegalArgumentException When model has not been initialized
   */
  List<Note> getNotesAtBeat(int beat) throws IllegalStateException;

  /**
   * Integer -> is the beat number.
   * SortedMap Integer -> the pitch number.
   * This will build a repeated.
   *
   * @return the whole map of notes
   */
  public SortedMap<Integer, SortedMap<Integer, List<K>>> getViewComposition();

  /**
   * Return a list of notes.
   *
   * @return list of K
   */
  public List<K> getAllNotesList();

  /**
   * Gets all the repeats for this piece.
   *
   * @return All repeats
   */
  public List<IRepetition> getRepeats();

  /**
   * Adds a repeat with the given parameters.
   * Return true if the repeat is successfully added.
   *
   * @param start Where to start(s).
   * @param ends  Where to end
   * @return true if the repeat is successfully added.
   */
  public boolean addRepeat(int start, List<Integer> ends);



}
