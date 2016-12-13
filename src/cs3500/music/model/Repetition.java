package cs3500.music.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * For the purpose of representing a musical repetition.
 */
public class Repetition implements IRepetition {

  private final int start;

  private final List<Integer> ends;

  private final int skipAt;

  private boolean hasPlayed;

  public Repetition(int start, List<Integer> ends) throws IllegalArgumentException {
    Collections.sort(ends);
    for (Integer end : ends) {
      if (start > end || ends.size() < 1) {
        throw new IllegalArgumentException("Invalid repetition parameters");
      }
    }
    this.start = start;
    this.ends = ends;
    if (ends.size() < 2)  {
      this.skipAt = this.ends.get(0);
    }
    else  {
      this.skipAt = this.ends.get(0) - (this.ends.get(1) - this.ends.get(0));
    }
    this.hasPlayed = false;
  }

  @Override
  public int getStart() {
    return this.start;
  }

  @Override
  public List<Integer> getEnds() {
    return this.ends;
  }

  @Override
  public boolean getHasPlayed() {
    return this.hasPlayed;
  }

  @Override
  public void setHasPlayed(boolean hasPlayed) {
    this.hasPlayed = hasPlayed;
  }

  @Override
  public int getSkipAt() {
    return this.skipAt;
  }

  @Override
  public boolean isOverlap(IRepetition repetition) {
    try  {
      Repetition r = (Repetition) repetition;
      if (r.start >= this.ends.get(this.ends.size() - 1) ||
              r.ends.get(r.ends.size() - 1) <= this.start)  {
        return false;
      }
      return true;
    } catch (Exception e)  {
      return true;
    }
  }

  static class RepeatComparator {
    static final Comparator<IRepetition> smallToLargeEnding = (thisRep, thatRep) -> {
      return Integer.compare(thisRep.getStart(), thatRep.getStart());
    };
  }
}
