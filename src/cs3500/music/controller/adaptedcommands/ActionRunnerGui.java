package cs3500.music.controller.adaptedcommands;

import cs3500.music.controller.ControlTracker;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.NoteBuilder;
import cs3500.music.provider.GuiPlayerView;
import cs3500.music.util.MusicUtils;

/**
 * For the purposes of adding/removing notes via a runnable.
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

  /**
   * Constructs an instance of the runner.
   * @param controlTracker Control tracker to be used
   * @param isAdd          Whether to add or remove
   * @param theView        View to be used
   * @param musicEditor    Music editor to be used
   */
  public ActionRunnerGui(ControlTracker controlTracker, boolean isAdd, GuiPlayerView theView,
                         IBasicMusicEditor<INote> musicEditor) {
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
