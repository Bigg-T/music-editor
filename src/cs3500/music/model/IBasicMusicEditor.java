package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

import java.util.List;

/**
 * The Music editor.
 */
public interface IBasicMusicEditor<K> {

  /**
   * EFFECT: Add a note to a the editor.
   *
   * @param noteName      name of the music note
   * @param octave        the note octave
   * @param startDuration the starting beat
   * @param numBeat       the duration of the beat
   * @return true if the beat is successfully added
   */
  public boolean add(NoteName noteName, int octave,
                     int startDuration, int numBeat);

  /**
   * EFFECT: Remove will remove all the note at start at a starting Duraiton.
   * This position is suppose to allow the client to remove a note at a in the stack of
   * same starting point. This simple model will not support that. Therefore position
   * field will be ignored at this point.
   *
   * @param noteName      name of the music note
   * @param octave        the note octave
   * @param startDuration the beat that a note started at
   * @param position      the duration of the beat
   * @return the list of notes are being removed
   */
  public List<K> remove(NoteName noteName, int octave, int startDuration, int position);

  /**
   * EFFECT: Change the duration of the note.
   * Return true if the operation is completed successfully.
   *
   * @param noteName      name of the music note
   * @param octave        the note octave
   * @param startDuration the beat that a note started at
   * @param changeBeat    the the duration of the beat
   * @param position      the position on the stack
   * @return return the true if the beat is successfully added
   */
  public boolean edit(NoteName noteName, int octave,
                      int startDuration, int changeBeat, int position);

  /**
   * EFFECT: Merge the other piece into one piece.
   *
   * @param piece         the IBasicMusic editor
   * @param isConsecutive play consecutive with the
   */
  public void merge(IBasicMusicEditor<K> piece, boolean isConsecutive);

  /**
   * For now I am doing this so I can do safe casting. I might create use the visitor pattern
   * if there are more classes implementing this interface.
   *
   * @return return true if this is a basic editor
   */
  public boolean isBasicMusicEditor();
}
