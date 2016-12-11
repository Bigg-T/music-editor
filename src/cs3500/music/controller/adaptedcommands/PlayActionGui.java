package cs3500.music.controller.adaptedcommands;

import cs3500.music.provider.GuiPlayerView;

/**
 * The purpose of this is to resume the the player.
 */
public class PlayActionGui implements Runnable {

  /**
   * The GUI View to be used here.
   */
  GuiPlayerView theView;

  public PlayActionGui(GuiPlayerView theView) {
    this.theView = theView;
  }

  @Override
  public void run() {
    theView.play();
  }

}
