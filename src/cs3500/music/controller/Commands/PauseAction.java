package cs3500.music.controller.Commands;

import cs3500.music.view.IView;

/**
 * Runnable for the purpose of pausing and playing.
 */
public class PauseAction implements Runnable {

  /**
   * View to be paused or played.
   */
  IView theView;

  public PauseAction(IView theView) {
    this.theView = theView;
  }

  @Override
  public void run()  {
    theView.pause();
  }

}
