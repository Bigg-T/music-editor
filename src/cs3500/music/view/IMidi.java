package cs3500.music.view;

/**
 * The midi interface.
 */
public interface IMidi extends IView {

  /**
   * T
   */
  public void startView();

  /**
   * Initialize View.
   *
   * @throws Exception if view can't not be initiallize.
   */
  public void initialize(int playAt) throws Exception;


}
