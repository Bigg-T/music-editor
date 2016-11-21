package cs3500.music.controller;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.NoteBuilder;
import cs3500.music.util.MusicUtils;
import cs3500.music.view.IView;

/**
 * Runs methods on the View.
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

  final IBasicMusicEditor<INote> musicEditor;

  public ActionRunner(ControlTracker controlTracker, IView theView,
                      IBasicMusicEditor<INote> musicEditor, boolean isAdd) {
    this.controlTracker = controlTracker;
    this.theView = theView;
    this.musicEditor = musicEditor;
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
        musicEditor.add(note);
        theView.update();
        controlTracker.reset();
      }
      else  {
        musicEditor.add(note);
        theView.update();
        controlTracker.reset();
      }
    } catch (IllegalStateException e)  {
      return;
    }
  }
}





