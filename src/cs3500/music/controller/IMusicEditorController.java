package cs3500.music.controller;

import java.awt.event.KeyListener;

/**
 * Represents a Controller of a music editor.
 */
public interface IMusicEditorController {

  /**
   * Adds an event handler at the key with the given keyCode.
   * @param keyCode  The keyCode for the key.
   * @param runnable The runnable to be used when the key is pressed.
   */
  public void addControl(Integer keyCode, Runnable runnable);

  /**
   * Initializes the view for the controller.
   */
  public void initializeView();

  /**
   * Gets the Key Handler from this controller.
   * @return the Key Handler
   */
  public KeyHandler getKeyHandler();




}
