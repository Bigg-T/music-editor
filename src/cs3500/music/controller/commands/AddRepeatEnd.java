package cs3500.music.controller.commands;

import cs3500.music.controller.RepeatTracker;

/**
 * Created by robertcarney on 12/12/16.
 */
public class AddRepeatEnd implements Runnable {

  private final RepeatTracker repeatTracker;

  public AddRepeatEnd(RepeatTracker repeatTracker) {
    this.repeatTracker = repeatTracker;
  }

  @Override
  public void run()  {
    repeatTracker.addCurrentEnd();
  }



}
