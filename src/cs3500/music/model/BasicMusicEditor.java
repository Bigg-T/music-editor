package cs3500.music.model;

import java.util.List;
import java.util.TreeMap;

/**
 * The MusicEditor.
 */
public final class BasicMusicEditor implements IBasicMusicEditor<INote> {
  private TreeMap<Integer, PitchCollection> piece;
  private final int tempo;

  BasicMusicEditor(int tempo) {
    this.piece = new TreeMap<>();
    this.tempo = tempo;
  }

  private Note castToNote(INote note) {
    if (note == null) {
      throw new IllegalArgumentException("Note is null.");
    }
    else if (note.isUnmodNote()){
      throw new IllegalArgumentException("Can't modified Note.");
    }
    return (Note) note;
  }

  @Override
  public boolean add(INote note) {

    Note castedNote = this.castToNote(note);
    int noteStartBeat = castedNote.getStartDuration();

    if (piece.get(noteStartBeat) == null) {
      PitchCollection pitchCollectionList = new PitchCollection();
      this.piece.put(noteStartBeat, pitchCollectionList);
      return pitchCollectionList.add(castedNote);
    }
    return piece.get(noteStartBeat).add(castedNote);
  }

  @Override
  public boolean remove(INote note) {
    Note castedNote = this.castToNote(note);
    int noteStartBeat = castedNote.getStartDuration();
    if (piece.get(noteStartBeat) == null) {
      return false;
    }
    return piece.get(noteStartBeat).remove(castedNote);
  }

  @Override
  public boolean edit(INote note, int duration, int volume) {
    Note castedNote = this.castToNote(note);
    int noteStartBeat = castedNote.getStartDuration();
    if (piece.get(noteStartBeat) == null) {
      return false;
    }
    return piece.get(noteStartBeat).edit(castedNote, duration, volume);
  }

  @Override
  public void merge(IBasicMusicEditor<INote> piece, boolean isConsecutive) {
    piece = toBasicMusicEditor(piece);

  }

  BasicMusicEditor toBasicMusicEditor(IBasicMusicEditor<INote> musicEditor) {
    if (musicEditor == null) {
      throw new IllegalArgumentException("Null MusicEditor.");
    } else if (musicEditor.isUnmodEditor()) {
      UnmodMusicEditor unmodMusicEditor = (UnmodMusicEditor) musicEditor;
      return unmodMusicEditor.toBasicMusicEditor(musicEditor);
    }
    return (BasicMusicEditor) musicEditor;
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

}
