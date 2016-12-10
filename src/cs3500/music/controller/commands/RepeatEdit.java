package cs3500.music.controller.commands;

import cs3500.music.controller.RepeatTracker;

/**
 * Created by robertcarney on 12/10/16.
 */
public class RepeatEdit implements Runnable {

  private final String toEdit;

  private final RepeatTracker tracker;

  public RepeatEdit(String toEdit, RepeatTracker tracker) {
    this.toEdit = toEdit;
    this.tracker = tracker;
  }


  @Override
  public void run() {
    tracker.setEdit(toEdit);
  }
}
