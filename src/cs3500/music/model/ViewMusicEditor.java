package cs3500.music.model;

import cs3500.music.util.Utils;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The view model that can't get mutate.
 */
public class ViewMusicEditor implements IBasicMusicEditor<INote> {

  private final BasicMusicEditor musicEditor;

  /**
   * Construct the music editor.
   *
   * @param musicEditor the music editor.
   */
  public ViewMusicEditor(BasicMusicEditor musicEditor) {
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
    return;
  }

  @Override
  public SortedMap<Integer, List<INote>> getAllNotesAt(int beatNum) {
    return musicEditor.getAllNotesAt(beatNum);
  }

  @Override
  public TreeMap<Integer, SortedMap<Integer, List<INote>>> composition() {
    TreeMap<Integer, SortedMap<Integer, List<INote>>> compos = new TreeMap<>();
    this.musicEditor.getPiece().keySet().forEach(x -> compos.put(x, getAllNotesAt(x)));
    return compos;
  }

  @Override
  public int getMinPitch() {
    return musicEditor.getMinPitch();
  }

  @Override
  public int getMaxPitch() {
    return musicEditor.getMaxPitch();
  }

  @Override
  public int getTempo() {
    return musicEditor.getTempo();
  }

  @Override
  public int getLastStartBeat() {
    return musicEditor.getLastStartBeat();
  }

  @Override
  public int getLastBeat() {
    return musicEditor.getLastBeat();
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
