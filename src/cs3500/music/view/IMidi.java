package cs3500.music.view;

/**
 * Created by tiger on 12/10/16.
 */
public interface IMidi extends IView {

  /**
   *
   */
  //public void startMidi();

  /**
   * Initialize View.
   *
   * @throws Exception if view can't not be initiallize.
   */
  public void initialize(int playAt) throws Exception;
}
