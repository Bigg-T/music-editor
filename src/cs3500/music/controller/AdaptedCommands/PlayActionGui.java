package cs3500.music.controller.AdaptedCommands;

import cs3500.music.provider.GuiPlayerView;

/**
 * Created by robertcarney on 12/3/16.
 */
public class PlayActionGui implements Runnable {

  /**
   * The GUI View to be used here
   */
  GuiPlayerView theView;

  public PlayActionGui(GuiPlayerView theView) {
    this.theView = theView;
  }

  @Override
  public void run() {
    theView.pause();
  }

}
