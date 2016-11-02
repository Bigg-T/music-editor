package cs3500.music.model;

/**
 * The INote. [Need more explanation and design choice]
 */
public interface INote {

  /**
   * Gets the NoteName.
   * @return  The NoteName.
   */
  public NoteName getNoteName();

  /**
   * Gets the octave.
   * @return The octave
   */
  public int getOctave();

  /**
   * Gets the startDuration.
   * @return The startDuration.
   */
  public int getStartDuration();

  /**
   * Gets the beats.
   * @return The beats.
   */
  public int getBeat();

  /**
   * Gets the channel.
   * @return The channel
   */
  public int getChannel();

  /**
   * Gets the volume.
   * @return  The volume.
   */
  public int getVolume();

  /**
   * Tells whether this is an UnmodNote.
   * @return  True if this is an UnmodNote, false otherwise.
   */
  public boolean isUnmodNote();



}
