package cs3500.music.controller;

import cs3500.music.view.IView;

/**
 * Runnable for scrolling vertically.
 */
public class VerticalScroller implements Runnable {

  /**
   * Displacement of the scroll.
   */
  private static final int TOSCROLL = 25;

  /**
   * True for down, false for up.
   */
  private final boolean direction;

  /**
   * The view to be scrolled.
   */
  IView theView;

  public VerticalScroller(boolean direction, IView theView) {
    this.direction = direction;
    this.theView = theView;
  }

  @Override
  public void run() {
    if (!direction)  {
      theView.scrollVertical(-1 * TOSCROLL);
      return;
    }
    theView.scrollVertical(TOSCROLL);
  }
}
