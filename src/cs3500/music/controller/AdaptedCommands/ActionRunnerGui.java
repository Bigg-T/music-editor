package cs3500.music.controller.AdaptedCommands;

import cs3500.music.controller.ControlTracker;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.NoteBuilder;
import cs3500.music.provider.GuiPlayerView;
import cs3500.music.util.MusicUtils;

/**
 * Created by robertcarney on 12/1/16.
 */
public class ActionRunnerGui implements Runnable {

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
  final GuiPlayerView theView;

  /**
   * The model being used here.
   */
  final IBasicMusicEditor<INote> musicEditor;

  public ActionRunnerGui(ControlTracker controlTracker, boolean isAdd, GuiPlayerView theView, IBasicMusicEditor<INote> musicEditor) {
    this.controlTracker = controlTracker;
    this.isAdd = isAdd;
    this.theView = theView;
    this.musicEditor = musicEditor;
  }

  @Override
  public void run() {
    try {
      int pitch = controlTracker.getPitch();
      int startBeat = controlTracker.getStartBeat();
      int duration = controlTracker.getDuration();
      int volume = controlTracker.getVolume();
      int channel = controlTracker.getChannel();
      INote note = new NoteBuilder().setNoteName(MusicUtils.pitchToNoteName(pitch)).
              setOctave(MusicUtils.getOctave(pitch)).setStartDuration(startBeat).setVolume(volume).
              setChannel(channel).setNumBeats(duration).buildNote();
      if (isAdd) {
        musicEditor.add(note);
        theView.refresh();
        controlTracker.reset();
      }
      else {
        musicEditor.add(note);
        theView.refresh();
        controlTracker.reset();
      }
    }
    catch (IllegalStateException e) {
      return;
    }
  }

}
