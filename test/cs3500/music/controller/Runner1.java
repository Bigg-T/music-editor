package cs3500.music.controller;

/**
 * Mock runner for testing.
 */
public class Runner1 implements Runnable {

  /**
   * For the purposes of tracking actions.
   */
  Tracker tracker;

  public Runner1(Tracker tracker) {
    this.tracker = tracker;
  }

  @Override
  public void run() {
    this.tracker.add("a");
  }
}
