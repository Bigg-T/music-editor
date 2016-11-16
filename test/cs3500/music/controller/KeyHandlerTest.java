package cs3500.music.controller;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * For the purposes of testing the KeyHandler class.
 */
public class KeyHandlerTest {

  public class Tracker  {
    String result = "";

    public void add(String s)  {
      result += s;
    }
  }

  Tracker t;

  KeyHandler keyHandler;

  KeyEvent ke1 = new KeyEvent(new MockComponent(), 0, 0, 0, 0, 'a');
  KeyEvent ke2 = new KeyEvent(new MockComponent(), 0, 0, 0, 0, 'a');



  void setUp()  {
    t = new Tracker();
    ke1.setKeyCode(1);
    ke2.setKeyCode(2);
    keyHandler = new KeyHandler();
    Runnable r1 = new Runner1(this.t);
    keyHandler.addKeyPressed(1, r1);
    Runnable r2 = new Runner2(this.t);
    keyHandler.addKeyTyped(2, r2);
    keyHandler.addKeyReleased(1, r2);
  }


  @Test
  public void testKeyPressed()  {
    this.setUp();
    keyHandler.keyPressed(ke1);
    assertEquals("a", this.t.result);
  }

  @Test
  public void testKeyTyped()  {
    this.setUp();
    keyHandler.keyTyped(ke2);
    assertEquals("b", this.t.result);
  }

  @Test
  public void testKeyReleased() {
    this.setUp();
    keyHandler.keyReleased(ke1);
    assertEquals("b", this.t.result);
  }

}
