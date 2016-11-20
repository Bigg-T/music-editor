package cs3500.music.view;

import java.awt.event.KeyListener;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * Represents a View.
 */
public interface IView {

  /**
   * Initialize View.
   *
   * @throws Exception if view can't not be initiallize.
   */
  public void initialize() throws Exception;

  /**
   * Updates the IView after an action has taken place.
   */
  public void update();

  /**
   * Gets the music editor from this view.
   *
   * @return the music editor from this view
   */
  public IBasicMusicEditor<INote> getMusicEditor();

  /**
   * Adds the key listener if possible
   *
   * @param keyListener the key listener to be added
   */
  public void addKeyListener(KeyListener keyListener);

  public long getCurrentTick();

  public void jumpToBeginning();

  public void jumpToEnd();

  public void move(long tick);

  public void pause();
  public void resume();
}
