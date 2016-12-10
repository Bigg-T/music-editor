package cs3500.music.controller.commands;

import cs3500.music.controller.RepeatTracker;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * Created by robertcarney on 12/10/16.
 */
public class AddRepeat implements Runnable {

  private final IBasicMusicEditor<INote> musicEditor;

  private final RepeatTracker tracker;

  public AddRepeat(IBasicMusicEditor<INote> musicEditor, RepeatTracker tracker) {
    this.musicEditor = musicEditor;
    this.tracker = tracker;
  }

  @Override
  public void run()  {
    this.musicEditor.addRepeat(tracker.getStart(), tracker.getEnd(), tracker.getSkipAt());
  }
}
