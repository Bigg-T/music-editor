package cs3500.music.controller.adaptedcommands;

import cs3500.music.provider.GuiPlayerView;

/**
 * Purpose of this is to pause the music.
 */
public class PauseActionGui implements Runnable {

  /**
   * The GUI View to be used here.
   */
  GuiPlayerView theView;

  public PauseActionGui(GuiPlayerView theView) {
    this.theView = theView;
  }

  @Override
  public void run() {
    theView.pause();
  }
}
