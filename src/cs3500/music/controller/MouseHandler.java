package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Map;
import java.util.HashMap;

/**
 * Class for handling mouse events.
 */
public class MouseHandler implements MouseListener {

  /**
   * Represents a map of function objects for different mouse clicks.
   */
  Map<Integer, Runnable> mouseClickEvent;

  /**
   * Constructs a Mouse Handler.
   */
  public MouseHandler() {
    this.mouseClickEvent = new HashMap<Integer, Runnable>();
  }

  /**
   * Adds a mouse clicked event to the handler.
   * @param buttonCode Code for the button
   * @param action     To run when clicked
   */
  public void addMouseClick(int buttonCode, Runnable action)  {
    this.mouseClickEvent.put(buttonCode, action);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    try {
      this.mouseClickEvent.get(e.getButton()).run();
    } catch (Exception ex)  {
      return;
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    return;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    return;
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    return;
  }

  @Override
  public void mouseExited(MouseEvent e) {
    return;
  }
}
