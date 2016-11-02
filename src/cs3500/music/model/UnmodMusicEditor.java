package cs3500.music.model;

import cs3500.music.util.Utils;

import java.util.List;

/**
 * Created by tiger on 11/2/16.
 */
public class UnmodMusicEditor implements IBasicMusicEditor<INote> {
  private final BasicMusicEditor musicEditor;
  UnmodMusicEditor(BasicMusicEditor musicEditor) {
    this.musicEditor = musicEditor;
  }
  @Override
  public boolean add(INote note) {
    return false;
  }

  @Override
  public boolean remove(INote note) {
    return false;
  }

  @Override
  public boolean edit(INote note, int duration, int volume) {
    return false;
  }

  @Override
  public void merge(IBasicMusicEditor<INote> piece, boolean isConsecutive) {

  }

  @Override
  public List<List<INote>> getAllNotesAt(int beatNum) {
    return null;
  }

  @Override
  public int getMinPitch() {
    return 0;
  }

  @Override
  public int getMaxPitch() {
    return 0;
  }

  @Override
  public int getTempo() {
    return 0;
  }

  @Override
  public int getLastStartBeat() {
    return 0;
  }

  @Override
  public int getLastBeat() {
    return 0;
  }

  @Override
  public boolean isUnmodEditor() {
    return false;
  }

  //need to change to visitor pattern
  BasicMusicEditor toBasicMusicEditor(IBasicMusicEditor<INote> musicEditor) {
    musicEditor = Utils.requireNonNull(musicEditor, "Null MusicEditor");
    if (musicEditor.isUnmodEditor()) {
      return this.musicEditor;
    }
    return (BasicMusicEditor) musicEditor;
  }
}
