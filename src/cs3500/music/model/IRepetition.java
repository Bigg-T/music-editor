package cs3500.music.model;

import java.util.List;

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
  public List<Integer> getEnds();

  /**
   * Gets the beat to skip at.
   * @return Beat to skip at
   */
  public int getSkipAt();

  /**
   * Tells whether this conflicts with the given repetition.
   * @param repetition The repetition in question
   * @return           True if
   */
  public boolean isOverlap(IRepetition repetition);



}
