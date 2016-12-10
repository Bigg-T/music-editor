package cs3500.music.model;

/**
 * Represents functionality for a repeat sign.
 */
public interface IRepetition {

  /**
   * Gets the starting beat of this repetition.
   * @return Starting beat
   */
  public int getStart();

  /**
   * Gets the ending beat of this repetition.
   * @return Ending beat
   */
  public int getEnd();

  /**
   * Tells whether the repetition has played.
   * @return True if it has, false otherwise.
   */
  public boolean getHasPlayed();

  /**
   * Gets the beat to skip at.
   * @return Beat to skip at
   */
  public int getSkipAt();


}
