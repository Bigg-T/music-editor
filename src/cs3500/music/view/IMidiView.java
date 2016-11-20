package cs3500.music.view;

/**
 * Created by tiger on 11/20/16.
 */
public interface IMidiView extends IView {
  public void pause();
  public void resume();

  public void jumpToBeginning();
  public void jumpToEnd();
}
