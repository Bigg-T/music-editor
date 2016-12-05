package cs3500.music.controller.adaptedcommands;

import cs3500.music.provider.GuiPlayerView;

/**
 * Created by robertcarney on 12/3/16.
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
    System.out.println("ran");
    theView.pause();
  }
}
