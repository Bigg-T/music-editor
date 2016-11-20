package cs3500.music.controller;

import cs3500.music.model.INote;
import cs3500.music.model.NoteBuilder;
import cs3500.music.util.MusicUtils;
import cs3500.music.view.IView;

/**
 * Runs methods on the GuiView.
 */
public class ActionRunner implements Runnable {
  /**
   * Controls tracked for the current runner.
   */
  final ControlTracker controlTracker;

  /**
   * True if adding, false if removing. For abstraction purposes.
   */
  final boolean isAdd;

  /**
   * The view being used here.
   */
  final IView theView;

  public ActionRunner(ControlTracker controlTracker,
                      IView theView, boolean isAdd) {
    this.controlTracker = controlTracker;
    this.theView = theView;
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
      INote note = new NoteBuilder().setNoteName(MusicUtils.pitchToNoteName(pitch)).
              setOctave(MusicUtils.getOctave(pitch)).setStartDuration(startBeat).setVolume(volume).
              setChannel(channel).setNumBeats(duration).buildNote();
      if (isAdd)  {
        theView.getMusicEditor().add(note);
        theView.update();
        controlTracker.reset();
      }
      else  {
        theView.getMusicEditor().add(note);
        theView.update();
        controlTracker.reset();
      }
    } catch (IllegalStateException e)  {
      return;
    }


  }




}
