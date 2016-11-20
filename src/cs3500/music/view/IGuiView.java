package cs3500.music.view;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.model.INote;
import cs3500.music.model.ViewNote;

/**
 * Interface for Gui views.
 */
public interface IGuiView extends IView {

  /**
   * Adds a mouse listener.
   */
  public void addMouseListener(MouseListener mouseListener);

  /**
   * Adds a key listener.
   */
  public void addKeyListener(KeyListener keyListener);

  /**
   * Scrolls the screen by the given amount if possible.
   * @param toScroll Amount to be scrolled
   */
  public void scroll(int toScroll);

  /**
   * Gets the point at which the top left of the editor resides.
   * @return point of top left of editor
   */
  public Point getEditorTopLeft();

  /**
   * Gets the dimension of the editor.
   * @return the dimension of the editor
   */
  public Dimension getEditorDimensions();

  /**
   * Gets the dimention of the note representations.
   * @return Dimention, representing size of notes in the view.
   */
  public Dimension getNoteSize();


}
