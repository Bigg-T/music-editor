package cs3500.music.util;

import org.junit.Test;
import org.junit.Assert;

import cs3500.music.model.NoteName;

/**
 * For the purpoes of testing the MusicUtils class.
 */
public class MusicUtilsTest {

  @Test
  public void testPitchToString()  {
    Assert.assertEquals(MusicUtils.pitchToString(60), "C 4");
  }

  @Test
  public void testToPitch()  {
    Assert.assertEquals(MusicUtils.toPitch(NoteName.C, 4), 60);
  }

}
