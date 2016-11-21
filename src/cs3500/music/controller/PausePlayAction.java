package cs3500.music.controller;

import cs3500.music.view.IView;

/**
 * Runnable for the purpose of pausing and playing.
 */
public class PausePlayAction implements Runnable {

  /**
   * Tracks the calls, for the purpose of deciding to pause or play.
   */
  private int callTrack;

  /**
   * View to be paused or played.
   */
  IView theView;

  public PausePlayAction(IView theView) {
    this.theView = theView;
    this.callTrack = 0;
  }

  @Override
  public void run()  {
    if (callTrack % 2  == 0)  {
      theView.pause();
      callTrack++;
    }
    else  {
      theView.resume();
      callTrack++;
    }
  }

}
