package cs3500.music.controller;

/**
 * For the purposes of setting a number from the keyboard.
 */
public class AddKeyNumber implements Runnable {

  /**
   * Tracking edits.
   */
  ControlTracker controlTracker;

  /**
   * Add on signle-digit number.
   */
  int toAdd;

  public AddKeyNumber(ControlTracker controlTracker, int toAdd) {
    this.controlTracker = controlTracker;
    this.toAdd = toAdd;
  }

  @Override
  public void run() {
    this.controlTracker.editAddOn(toAdd);
  }
}
