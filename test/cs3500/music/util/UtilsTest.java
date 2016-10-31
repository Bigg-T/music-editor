package cs3500.music.util;

import org.junit.Assert;
import org.junit.Test;

import static cs3500.music.util.Utils.padding;
import static cs3500.music.util.Utils.stringCenter;
import static cs3500.music.util.Utils.toPosMod;

/**
 * Testing Utils.
 */
public class UtilsTest {
  @Test
  public void testStringCenter() throws Exception {
    Assert.assertEquals(" 0 ", stringCenter("0", 3));
  }

  @Test
  public void testStringCenter2() throws Exception {
    Assert.assertEquals("aasdf", stringCenter("aasdf", 3));
  }

  @Test
  public void testStringCenter3() throws Exception {
    Assert.assertEquals(" a ", stringCenter("a", 3));
  }

  @Test
  public void testStringCenter4() throws Exception {
    Assert.assertEquals("a", stringCenter("a", 0));
  }

  @Test
  public void testStringCenter5() throws Exception {
    Assert.assertEquals("a", stringCenter("a", -3));
  }

  @Test
  public void testPadding() throws Exception {
    Assert.assertEquals(" 0", padding(0));
  }

  @Test
  public void testPadding2() throws Exception {
    Assert.assertEquals("10", padding(10));
  }

  @Test
  public void testPadding3() throws Exception {
    Assert.assertEquals("100", padding(100));
  }

  @Test
  public void testToPosMod() throws Exception {
    Assert.assertEquals(1, toPosMod(-1, 10));
  }

  @Test
  public void testToPosMod2() throws Exception {
    Assert.assertEquals(1, toPosMod(1, 10));
  }

  @Test
  public void testToPosMod3() throws Exception {
    Assert.assertEquals(1, toPosMod(11, 10));
  }

  @Test
  public void testToPosMod4() throws Exception {
    Assert.assertEquals(1, toPosMod(-11, 10));
  }
}