package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiger on 12/2/16.
 */
public class ReadOnlyModel implements ReadOnlyMusicModel {
  private final IBasicMusicEditor<INote> model;

  public ReadOnlyModel(IBasicMusicEditor<INote> model) {
    this.model = model;
  }

  @Override
  public int totalBeats() throws IllegalStateException {
    return this.model.getLastBeat();
  }

  @Override
  public List<Note> getNotes() throws IllegalStateException {
    List<Note> notes = new ArrayList<>();
    this.model.composition().keySet()
            .forEach(x -> notes.addAll(getNotesAtBeat(x)));
    return notes;
  }

  @Override
  public List<Note> getNotesAtBeat(int beat) throws IllegalStateException {
    return model.getNotesAtBeat(beat);
  }

  @Override
  public int getBeatsPerMeasure() throws IllegalStateException {
    return 4;
  }

  @Override
  public int getTempo() {
    return 60000000 / model.getTempo();
  }
}
