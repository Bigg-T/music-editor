package cs3500.music.controller;

import org.junit.Test;

import java.awt.event.MouseEvent;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * For the purposes of testing the MouseHandler class.
 */
public class MouseHandlerTest {


  MouseHandler mouseHandler = new MouseHandler();

  MouseEvent mouseEvent1 = new MouseEvent(new MockComponent(), 0, 0, 0, 0, 0, 0, false, 1);
  MouseEvent mouseEvent2 = new MouseEvent(new MockComponent(), 0, 0, 0, 0, 0, 0, false, 3);

  Tracker setUp()  {
    Tracker t = new Tracker();
    Runnable r1 = new Runner1(t);
    Runnable r2 = new Runner2(t);
    mouseHandler.addMouseClick(1, r1);
    mouseHandler.addMouseClick(3, r2);
    return t;
  }

  @Test
  public void testLeftClick()  {
    Tracker t = this.setUp();
    mouseHandler.mouseClicked(mouseEvent1);
    assertEquals("a", t.result.toString());
  }

  @Test
  public void testRightClick()  {
    Tracker t = this.setUp();
    mouseHandler.mouseClicked(mouseEvent2);
    assertEquals("b", t.result.toString());
  }

  @Test
  public void testMultClick()  {
    Tracker t = this.setUp();
    mouseHandler.mouseClicked(mouseEvent2);
    mouseHandler.mouseClicked(mouseEvent1);
    assertEquals("ba", t.result.toString());
  }


}
