package cs3500.music.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tiger on 10/31/16.
 */
public class PitchTest {

  private Pitch setUp(int pitch) {
    return new Pitch(pitch);
  }

  @Test
  public void testEquals() throws Exception {
    Pitch p = setUp(12);
    Pitch p2 = setUp(12);
    Assert.assertEquals(p2, p);
  }

  @Test
  public void testEquals2() throws Exception {
    Pitch p = setUp(-1);
    Pitch p2 = setUp(12);
    Assert.assertEquals(false, p.equals(p2));
  }

  @Test
  public void testEquals3() throws Exception {
    Pitch p = setUp(12);
    Pitch p2 = setUp(12);
    Assert.assertEquals(true, p.equals(p2));
  }

  @Test
  public void testEquals4() throws Exception {
    Pitch p = setUp(-12);
    Pitch p2 = setUp(-12);
    Assert.assertEquals(true, p.equals(p2));
  }

  @Test
  public void testHashCode() throws Exception {
    Pitch p = setUp(-12);
    Pitch p2 = setUp(-12);
    Assert.assertEquals(true, p.hashCode() == p2.hashCode());
  }

  @Test
  public void testHashCode2() throws Exception {
    Pitch p = setUp(-12);
    Pitch p2 = setUp(2);
    Assert.assertEquals(false, p.hashCode() == p2.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddEx() throws Exception {
    Pitch pitchCollection = setUp(12);
    Note note = new Note(NoteName.A, 1, 12, 2, 4, 3);
    pitchCollection.add(note);
  }

  @Test //is item added?
  public void testAdd() throws Exception {
    Pitch pitchCollection = setUp(12);
    Note note = new Note(NoteName.C, 0, 12, 2, 4, 3);
    Assert.assertEquals(true, pitchCollection.add(note));
    // duplicate so does not needed to add
    Assert.assertEquals(false, pitchCollection.add(note));
  }

  @Test
  public void testRemove() throws Exception {

  }

}