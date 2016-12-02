package cs3500.music.provider;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * A GuiView should be able take in user inputs and send them to the listeners provided.
 */
public interface GuiView extends GraphicalView {

  /**
   * Adds a KeyListener to the set of KeyListeners that this view contains.
   *
   * @param listener The KeyListener to be added to this view.
   */
  void addKeyListener(KeyListener listener);

  /**
   * Remove the given KeyListener from this view.
   *
   * @param listener The KeyListener to be removed.
   */
  void removeKeyListener(KeyListener listener);

  /**
   * Adds the given MouseListener to this view.
   *
   * @param listener The MouseListener to be added.
   */
  void addMouseListener(MouseListener listener);

  /**
   * Removes the given MouseListener from this view.
   *
   * @param listener The MouseListener to be removed.
   */
  void removeMouseListener(MouseListener listener);

}
