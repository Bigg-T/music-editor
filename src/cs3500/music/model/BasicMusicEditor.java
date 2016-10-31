package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by tiger on 10/31/16.
 */
public class BasicMusicEditor implements IBasicMusicEditor<Note> {
  private TreeMap<Integer, ArrayList<Pitch>> piece;
  private final int tempo;

  BasicMusicEditor(int tempo) {
    this.piece = new TreeMap<>();
    this.tempo = tempo;
  }

  @Override
  public boolean add(NoteName noteName, int octave, int startDuration, int numBeat) {
    return false;
  }

  @Override
  public List<Note> remove(NoteName noteName, int octave, int startDuration, int position) {
    return null;
  }

  @Override
  public boolean edit(NoteName noteName, int octave, int startDuration, int changeBeat, int position) {
    return false;
  }

  @Override
  public void merge(IBasicMusicEditor<Note> piece, boolean isConsecutive) {

  }

  @Override
  public boolean isBasicMusicEditor() {
    return false;
  }
}
