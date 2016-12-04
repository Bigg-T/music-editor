package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The MusicEditor.
 */
public final class BasicMusicEditor implements IBasicMusicEditor<INote> {

  //Integer is a the starting beat of a note
  private TreeMap<Integer, PitchCollection> piece;
  private final int tempo;

  /**
   * Construct an instance of BasicMusic Editor.
   *
   * @param tempo the composition tempo in MPQ
   */
  BasicMusicEditor(int tempo) {
    this.piece = new TreeMap<>();
    this.tempo = tempo;
  }

  /**
   * Casting to Note.
   *
   * @param note the INote
   * @return the Note
   */
  private Note castToNote(INote note) {
    if (note == null) {
      throw new IllegalArgumentException("Note is null.");
    }
    else if (note.isViewNote()) {
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
  public void merge(IBasicMusicEditor<INote> thatPiece, boolean isConsecutive) {
    BasicMusicEditor castedPiece = toBasicMusicEditor(thatPiece);
    Set<Integer> thatKeys = castedPiece.piece.keySet();

    thatKeys.forEach(s -> {
      PitchCollection pitchCollection = castedPiece.piece.get(s);
      if (!isConsecutive) {
        //non-consecutive
        if (this.piece.get(s) != null) {
          this.piece.put(s, pitchCollection);
          return;
        }
        this.piece.get(s).merge(pitchCollection, 0);
      }
      else {
        int offset = s + getLastBeat();
        this.piece.put(offset, pitchCollection.adjustBeat(getLastBeat()));
        return;
      }

    });

  }

  /**
   * Return the casted IMusicEditor to BasicMusicEditor.
   *
   * @param musicEditor the music editor
   * @return casted to basicMusicEditor
   */
  private BasicMusicEditor toBasicMusicEditor(IBasicMusicEditor<INote> musicEditor) {
    if (musicEditor == null) {
      throw new IllegalArgumentException("Null MusicEditor.");
    }
    else if (musicEditor.isViewEditor()) {
      ViewMusicEditor viewMusicEditor = (ViewMusicEditor) musicEditor;
      return viewMusicEditor.toBasicMusicEditor(musicEditor);
    }
    return (BasicMusicEditor) musicEditor;
  }

  @Override
  public SortedMap<Integer, List<INote>> getAllNotesAt(int beatNum) {
    return this.piece.get(beatNum).getAllNote();
  }

  @Override
  public int getMinPitch() {
    return this.piece.values()
            .parallelStream().mapToInt(PitchCollection::getMinPitch).min().getAsInt();
  }

  @Override
  public int getMaxPitch() {
    return this.piece.values()
            .parallelStream().mapToInt(PitchCollection::getMaxPitch).max().getAsInt();
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public int getLastStartBeat() {
    return this.piece.lastKey();
  }

  @Override
  public int getLastBeat() {
    int lastStartPitch = this.piece.lastKey();
    return lastStartPitch + this.piece.get(lastStartPitch).longestNoteDuration();
  }

  @Override
  public TreeMap<Integer, SortedMap<Integer, List<INote>>> composition() {
    TreeMap<Integer, SortedMap<Integer, List<INote>>> compos = new TreeMap<>();
    this.piece.keySet().forEach(x -> compos.put(x, getAllNotesAt(x)));
    return compos;
  }

  TreeMap<Integer, PitchCollection> getPiece() {
    return this.piece;
  }

  @Override
  public boolean isViewEditor() {
    return false;
  }

  @Override
  public List<Note> getNotesAtBeat(int beat) throws IllegalStateException {
    if (this.piece.get(beat) == null) {
      return new ArrayList<>();
    }
    else {
      return this.piece.get(beat).getAllNote(true);
    }
  }

  @Override
  public SortedMap<Integer, SortedMap<Integer, List<INote>>> getViewComposition() {
    SortedMap<Integer, SortedMap<Integer, List<INote>>> mapSortedMap = composition();

    this.getAllNotesList()
            .forEach(x -> {

            });
    return mapSortedMap;
  }

  @Override
  public List<INote> getAllNotesList() {
    List<INote> notes = new ArrayList<>();
    this.composition().keySet()
            .forEach(x -> notes.addAll(getNotesAtBeat(x)));
    return notes;
  }

  /**
   * Builder for CompositionBuilder.
   */
  public static final class BasicCompositionBuilder
          implements CompositionBuilder<IBasicMusicEditor<INote>> {

    IBasicMusicEditor<INote> musicEditor;

    @Override
    public IBasicMusicEditor<INote> build() {
      return this.musicEditor;
    }

    @Override
    public CompositionBuilder<IBasicMusicEditor<INote>> setTempo(int tempo) {
      this.musicEditor = new BasicMusicEditor(tempo);
      return this;
    }

    @Override
    public CompositionBuilder<IBasicMusicEditor<INote>>
    addNote(int start, int end, int instrument, int pitch, int volume) {
      Note note = new NoteBuilder()
              .setNoteName(NoteName.toNoteName(pitch % 12))
              .setChannel(instrument)
              .setNumBeats(end - start)
              .setStartDuration(start)
              .setOctave((pitch - 12) / 12)
              .setVolume(volume)
              .buildNote();
      this.musicEditor.add(note);
      return this;
    }

  }

}
