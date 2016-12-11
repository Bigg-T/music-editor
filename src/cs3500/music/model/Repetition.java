package cs3500.music.model;

import java.util.Comparator;

/**
 * For the purpose of representing a musical repetition.
 */
public class Repetition implements IRepetition {

  private final int start;

  private final int end;

  private final int skipAt;

  private boolean hasPlayed;

  public Repetition(int start, int end, int skipAt) throws IllegalArgumentException {
    if (start > end || start > skipAt || skipAt > end)  {
      throw new IllegalArgumentException("Invalid repetition parameters");
    }
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

  @Override
  public boolean isOverlap(IRepetition repetition) {
    try  {
      Repetition r = (Repetition) repetition;
      if (r.start >= this.end || r.end <= this.start || r.start == this.start)  {
        return false;
      }
      return true;
    } catch (Exception e)  {
      return true;
    }
  }

  static class RepeatComparator {
    static final Comparator<IRepetition> smallToLargeEnding = (thisRep, thatRep) -> {
      return Integer.compare(thisRep.getEnd(), thatRep.getEnd());
    };
  }
}
