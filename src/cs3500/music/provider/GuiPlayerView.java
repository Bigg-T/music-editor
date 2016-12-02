package cs3500.music.provider;

/**
 * A Combination interface for MusicPlayingView and GuiView.
 */
public interface GuiPlayerView extends GuiView, MusicPlayingView {

  /**
   * @return This GuiPlayerView's ScrollHandler.
   */
  ScrollHandler getScrollHandler();
}
