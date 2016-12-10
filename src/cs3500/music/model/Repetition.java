package cs3500.music.model;

/**
 * For the purpose of representing a musical repetition.
 */
public class Repetition implements IRepetition {

  private final int start;

  private final int end;

  private final int skipAt;

  private boolean hasPlayed;

  public Repetition(int start, int end, int skipAt) {
    this.start = start;
    this.end = end;
    this.skipAt = skipAt;
    this.hasPlayed = false;
  }

  @Override
  public int getStart() {
    return this.start;
  }

  @Override
  public int getEnd() {
    return this.end;
  }

  @Override
  public boolean getHasPlayed() {
    return this.hasPlayed;
  }

  @Override
  public int getSkipAt() {
    return this.skipAt;
  }
}
