package cs3500.music.controller.adaptedcommands;

import cs3500.music.provider.GuiPlayerView;

/**
 * For the purposes of jumping to the beginning or end via a runnable.
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
