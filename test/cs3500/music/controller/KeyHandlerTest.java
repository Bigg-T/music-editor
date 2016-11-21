package cs3500.music.controller;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * For the purposes of testing the KeyHandler class.
 */
public class KeyHandlerTest {


  KeyHandler keyHandler;

  KeyEvent ke1 = new KeyEvent(new MockComponent(), 0, 0, 0, 0, 'a');
  KeyEvent ke2 = new KeyEvent(new MockComponent(), 0, 0, 0, 0, 'a');


  /**
   * Sets up for tests.
   * @return Tracker to be used
   */
  Tracker setUp()  {
    Tracker t = new Tracker();
    ke1.setKeyCode(1);
    ke2.setKeyCode(2);
    keyHandler = new KeyHandler();
    Runnable r1 = new Runner1(t);
    keyHandler.addKeyPressed(1, r1);
    Runnable r2 = new Runner2(t);
    keyHandler.addKeyTyped(2, r2);
    keyHandler.addKeyReleased(1, r2);
    return t;
  }


  @Test
  public void testKeyPressed()  {
    Tracker t = this.setUp();
    keyHandler.keyPressed(ke1);
    assertEquals("a", t.result.toString());
  }

  @Test
  public void testKeyTyped()  {
    Tracker t = this.setUp();
    keyHandler.keyTyped(ke2);
    assertEquals("b", t.result.toString());
  }

  @Test
  public void testKeyReleased() {
    Tracker t = this.setUp();
    keyHandler.keyReleased(ke1);
    assertEquals("b", t.result.toString());
  }

}
