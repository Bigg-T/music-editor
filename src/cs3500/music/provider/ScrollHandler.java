package cs3500.music.provider;

/**
 * An interface for objects capable of handling scroll events.
 */
public interface ScrollHandler {
  /**
   * Scrolls to the given x value if auto-scrolling is enabled.
   *
   * @param x The X value to scroll to.
   */
  void autoScrollToX(int x);

  /**
   * Scrolls right if auto-scrolling is disabled.
   */
  void scrollRight();

  /**
   * Scrolls left if auto-scrolling is disabled.
   */
  void scrollLeft();

  /**
   * Toggles auto-scrolling.
   */
  void toggleAutoScrolling();
}
