package cs3500.music.controller.AdaptedCommands;

import cs3500.music.provider.GuiPlayerView;

/**
 * Created by robertcarney on 12/3/16.
 */
public class JumpGuiView implements Runnable {

  /**
   * The view to be used here.
   */
  GuiPlayerView theView;

  final boolean jumpBegin;

  public JumpGuiView(GuiPlayerView theView, boolean jumpBegin) {
    this.theView = theView;
    this.jumpBegin = jumpBegin;
  }

  @Override
  public void run() {
    if (jumpBegin)  {
      theView.reset();
    }
    else  {
      theView.skipToEnd();
    }
  }
}
