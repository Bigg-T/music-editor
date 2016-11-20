package cs3500.music.controller;

import cs3500.music.view.IGuiView;

/**
 * Runs methods on the GuiView.
 */
public class GuiActionRunner implements Runnable {

  final IGuiView theView;
  final ControlTracker controlTracker;
  final boolean isAdd;

  public GuiActionRunner(IGuiView theView, ControlTracker controlTracker, boolean isAdd) {
    this.theView = theView;
    this.controlTracker = controlTracker;
    this.isAdd = isAdd;
  }


  @Override
  public void run()  {
    try  {
      int pitch = controlTracker.getPitch();
      int startBeat = controlTracker.getStartBeat();
      int duration = controlTracker.getDuration();
      int volume = controlTracker.getVolume();
      int channel = controlTracker.getChannel();
      if (isAdd)  {
        theView.addNote(pitch, startBeat, duration, volume, channel);
        controlTracker.reset();
      }
      else  {
        theView.removeNote(pitch, startBeat, duration, volume, channel);
        controlTracker.reset();
      }
    } catch (IllegalStateException e)  {
      System.out.println(e.getMessage());
      return;
    }


  }




}
