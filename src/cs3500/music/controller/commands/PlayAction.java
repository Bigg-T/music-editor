package cs3500.music.controller.commands;

import cs3500.music.view.IView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * For the purposes of playing the view.
 */
public class PlayAction implements Runnable {

  /**
   * View to be used here.
   */
  IView theView;

  /**
   * Creates a play action.
   * @param theView view to use
   */
  public PlayAction(IView theView) {
    this.theView = theView;
  }


  @Override
  public void run() {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    theView.resume();
    Runnable runnable = () -> theView.repeatView();
    executor.submit(runnable);
  }
}
