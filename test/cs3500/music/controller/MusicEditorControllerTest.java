package cs3500.music.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.music.model.BasicMusicEditor;
import cs3500.music.view.IView;

/**
 * For the purposes of testing the controller.
 */
public class MusicEditorControllerTest {

  MusicEditorController controller;

  /**
   * Sets up for testing.
   */
  MockView setUp()  {
    IView view = new MockView();
    this.controller = new MusicEditorController(view, new BasicMusicEditor.
            BasicCompositionBuilder().build());
    return (MockView)  view;
  }

  @Test
  public void testInitialize()  {
    MockView view = this.setUp();
    // add key and mouse will be called in constructor
    controller.initializeView();
    assertEquals("key mouse init ", view.result.toString());
  }

  @Test
  public void testAddControlPress()  {
    this.setUp();
    Runnable r1 = new Runnable() {
      @Override
      public void run() {
        return;
      }
    };
    this.controller.addControl(9, r1, "pressed");
    assertEquals(r1, this.controller.keyHandler.keysToBePressed.get(9));
  }

  @Test
  public void testAddControlTyped()  {
    this.setUp();
    Runnable r1 = new Runnable() {
      @Override
      public void run() {
        return;
      }
    };
    this.controller.addControl(9, r1, "typed");
    assertEquals(r1, this.controller.keyHandler.keysToBeTyped.get(9));
  }

  @Test
  public void testAddControlRelease()  {
    this.setUp();
    Runnable r1 = new Runnable() {
      @Override
      public void run() {
        return;
      }
    };
    this.controller.addControl(9, r1, "release");
    assertEquals(r1, this.controller.keyHandler.keysToBeReleased.get(9));
  }

  @Test
  public void testAddControlWrong()  {
    this.setUp();
    Runnable r1 = new Runnable() {
      @Override
      public void run() {
        return;
      }
    };
    this.controller.addControl(9, r1, "none");
    assertEquals(null, this.controller.keyHandler.keysToBeReleased.get(9));
  }

}
