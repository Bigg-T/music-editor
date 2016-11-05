package cs3500.music.model;

import cs3500.music.util.Utils;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by tiger on 11/2/16.
 */
public class ViewMusicEditor implements IBasicMusicEditor<INote> {
  private final BasicMusicEditor musicEditor;
  ViewMusicEditor(BasicMusicEditor musicEditor) {
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
  public SortedMap<Integer, List<INote>> getAllNotesAt(int beatNum) {
    return null;
  }

  @Override
  public TreeMap<Integer, SortedMap<Integer, List<INote>>> composition() {
    TreeMap<Integer, SortedMap<Integer, List<INote>>> compos = new TreeMap<>();
    this.musicEditor.getPiece().keySet().forEach(x -> compos.put(x, getAllNotesAt(x)));
    return compos;
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
  public boolean isViewEditor() {
    return true;
  }

  //need to change to visitor pattern
  BasicMusicEditor toBasicMusicEditor(IBasicMusicEditor<INote> musicEditor) {
    musicEditor = Utils.requireNonNull(musicEditor, "Null MusicEditor");
    if (musicEditor.isViewEditor()) {
      return this.musicEditor;
    }
    return (BasicMusicEditor) musicEditor;
  }
}
