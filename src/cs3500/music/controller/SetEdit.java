package cs3500.music.controller;

/**
 * Runnable for setting something to be edited.
 */
public class SetEdit implements Runnable {

  /**
   * Represents what is to be edited.
   */
  private final String toEdit;

  /**
   * The control tracker to use.
   */
  private final ControlTracker controlTracker;

  /**
   * Constructs a SetEdit.
   * @param controlTracker control tracker to use
   * @param toEdit         to be edited
   */
  SetEdit(ControlTracker controlTracker, String toEdit) {
    this.controlTracker = controlTracker;
    this.toEdit = toEdit;
  }

  @Override
  public void run() {
    controlTracker.setControl(toEdit);
  }
}

