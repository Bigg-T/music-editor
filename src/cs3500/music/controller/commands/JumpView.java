package cs3500.music.controller.commands;

import cs3500.music.view.IView;

/**
 * Runnable for jumping to the beginning or end of a piece in the view.
 */
public class JumpView implements Runnable {

  /**
   * The view to be jumped.
   */
  IView theView;

  /**
   * True if jumping to begining, false if jumping to end.
   */
  private final boolean jumpBegin;

  public JumpView(IView theView, boolean jumpBegin) {
    this.theView = theView;
    this.jumpBegin = jumpBegin;
  }

  @Override
  public void run()  {
    if (jumpBegin)  {
      theView.jumpToBeginning();
    }
    else  {
      theView.jumpToEnd();
    }
  }
}
