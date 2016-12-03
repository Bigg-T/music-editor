package cs3500.music.controller.Commands;

import cs3500.music.view.IView;

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
    theView.resume();
  }
}
