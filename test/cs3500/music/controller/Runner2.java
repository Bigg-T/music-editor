package cs3500.music.controller;

/**
 * Mock runner for testing.
 */
public class Runner2 implements Runnable {

  /**
   * For the purposes of tracking actions.
   */
  Tracker tracker;

  public Runner2(Tracker tracker) {
    this.tracker = tracker;
  }

  @Override
  public void run() {
    this.tracker.add("b");
  }
}
