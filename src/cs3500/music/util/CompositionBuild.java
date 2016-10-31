package cs3500.music.util;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.Note;

/**
 * Created by tiger on 10/31/16.
 */
public class CompositionBuild implements CompositionBuilder<IBasicMusicEditor<Note>> {


  @Override
  public IBasicMusicEditor<Note> build() {
    return null;
  }

  @Override
  public CompositionBuilder<IBasicMusicEditor<Note>> setTempo(int tempo) {
    return null;
  }

  @Override
  public CompositionBuilder<IBasicMusicEditor<Note>> addNote(int start, int end, int instrument, int pitch, int volume) {
    return null;
  }

}
