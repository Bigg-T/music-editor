package cs3500.music.controller.Commands;

import cs3500.music.view.IView;

/**
 * Runnable for scrolling horizontally.
 */
public class HorizontalScroller implements Runnable {

  /**
   * Displacement of the scroll.
   */
  private static final int TOSCROLL = 25;

  /**
   * True for right, false for left.
   */
  private final boolean direction;

  /**
   * The view to be scrolled.
   */
  IView theView;

  public HorizontalScroller(boolean direction, IView theView) {
    this.direction = direction;
    this.theView = theView;
  }

  @Override
  public void run() {
    if (!direction)  {
      theView.scrollHorizontal(-1 * TOSCROLL);
      return;
    }
    theView.scrollHorizontal(TOSCROLL);
  }
}
