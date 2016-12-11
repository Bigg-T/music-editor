package cs3500.music.model;

import java.util.List;

/**
 * Represents an Object Adapter for a read only music editor model.
 */
public interface ReadOnlyMusicModel {

  /**
   * Returns the number of beats in this model.
   *
   * @return The number of beats in this model.
   */
  int totalBeats() throws IllegalStateException;

  /**
   * Returns all of the notes that are in this model.
   *
   * @return The notes that are in this model.
   */
  List<Note> getNotes() throws IllegalStateException;

  /**
   * Returns all of the notes that are present during the given beat.
   *
   * @param beat The beat at which we are finding all notes present
   * @return The notes present at the given beat
   * @throws IllegalArgumentException When model has not been initialized
   */
  List<Note> getNotesAtBeat(int beat) throws IllegalStateException;

  /**
   * Returns the beats per measure with which this model was initialized.
   *
   * @return The current beats per measure of this model.
   */
  int getBeatsPerMeasure() throws IllegalStateException;

  /**
   * Returns the tempo of this piece of music.
   */
  int getTempo();

}
