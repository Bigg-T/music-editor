package cs3500.music.controller;

/**
 * Represents a Controller of a music editor.
 */
public interface IMusicEditorController {

  /**
   * Adds an event handler at the key with the given keyCode.
   * @param keyCode  The keyCode for the key
   * @param runnable The runnable to be used when the key is pressed
   * @param location Where the command should be stored
   */
  public void addControl(Integer keyCode, Runnable runnable, String location);

  /**
   * Initializes the view of this controller.
   */
  public void initializeView();




}
