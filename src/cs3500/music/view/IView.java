package cs3500.music.view;

/**
 * The IView interface to  run view.
 */
public interface IView {

  /**
   * Initialize View.
   *
   * @throws Exception if view can't not be initiallize.
   */
  public void initialize() throws Exception;

  /**
   * The current tick in the piece that the composition is playing.
   * place holder, assuming that getTickPosition() will not produce a 0 at start,
   * -1 mean that the midi is over.
   */
  public long getCurrentTick();

  /**
   *
   */
  public void move(long tick);


}
