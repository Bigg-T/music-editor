package cs3500.music.util;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * Created by tiger on 10/31/16.
 */
public class CompositionBuild implements CompositionBuilder<IBasicMusicEditor<INote>> {


  @Override
  public IBasicMusicEditor<INote> build() {
    return null;
  }

  @Override
  public CompositionBuilder<IBasicMusicEditor<INote>> setTempo(int tempo) {
    return null;
  }

  @Override
  public CompositionBuilder<IBasicMusicEditor<INote>> addNote(int start, int end, int instrument, int pitch, int volume) {
    return null;
  }

}
