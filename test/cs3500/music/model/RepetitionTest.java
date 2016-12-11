package cs3500.music.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * For the purposes of testing the Repetition class.
 */
public class RepetitionTest {


  IRepetition r1 = new Repetition(0, Arrays.asList(4));
  IRepetition r2 = new Repetition(4, Arrays.asList(8));
  IRepetition r3 = new Repetition(2, Arrays.asList(6, 8));

  @Test
  public void testGetStart()  {
    assertEquals(0, r1.getStart());
  }

  @Test
  public void testGetEnd()  {
    assertEquals(Arrays.asList(8), r2.getEnds());
  }

  @Test
  public void testGetSkip()  {
    assertEquals(4, r3.getSkipAt());
  }

  @Test
  public void testIsOverlap1()  {
    assertEquals(false, r1.isOverlap(r2));
  }

  @Test
  public void testIsOverlap2()  {
    assertEquals(true, r1.isOverlap(r3));
  }

  @Test
  public void testSorting()  {
    List<IRepetition> repetitions = new ArrayList<IRepetition>();
    repetitions.add(r1);
    repetitions.add(r2);
    repetitions.add(r3);
    List<IRepetition> repetitions1 = new ArrayList<IRepetition>();
    repetitions1.add(r1);
    repetitions1.add(r3);
    repetitions1.add(r2);
    repetitions.sort(Repetition.RepeatComparator.smallToLargeEnding);
    assertArrayEquals(repetitions.toArray(), repetitions1.toArray());
  }

}