package cs3500.music.controller.commands;

import cs3500.music.controller.ControlTracker;
import cs3500.music.controller.RepeatTracker;

/**
 * For the purposes of setting a number from the keyboard.
 */
public class AddKeyNumber implements Runnable {

  /**
   * Tracking repeat edits.
   */
  RepeatTracker rt;

  /**
   * Tracking edits.
   */
  ControlTracker controlTracker;

  /**
   * Add on signle-digit number.
   */
  int toAdd;

  public AddKeyNumber(ControlTracker controlTracker, RepeatTracker rt, int toAdd) {
    this.rt = rt;
    this.controlTracker = controlTracker;
    this.toAdd = toAdd;
  }

  @Override
  public void run() {
    this.controlTracker.editAddOn(toAdd);
    this.rt.editAddOn(toAdd);
  }
}
