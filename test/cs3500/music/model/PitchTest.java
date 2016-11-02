package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * For the purposes of testing the Pitch class.
 */
public class PitchTest {
  Pitch pitch0 = new Pitch(5);
  Pitch pitch1 = new Pitch(5);
  Pitch pitch2 = new Pitch(6);

  @Test
  public void testEquals() throws Exception {
    assertEquals(true, pitch0.equals(pitch1));
    assertEquals(false, pitch0.equals(pitch2));
  }

  @Test
  public void testHashCode() throws Exception {
    assertEquals(true, pitch1.hashCode() == pitch0.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAdd() throws Exception {
    pitch0.add(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove() throws Exception {
    pitch0.remove(null);
  }

}
