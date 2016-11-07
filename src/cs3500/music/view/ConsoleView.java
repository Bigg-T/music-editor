package cs3500.music.view;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * Created by robertcarney on 11/6/16.
 */
public class ConsoleView implements IView {

  public final IBasicMusicEditor<INote> musicEditor;

  public ConsoleView(IBasicMusicEditor<INote> musicEditor)  {
    this.musicEditor = musicEditor;
  }

  //@Todo write the getState method
  @Override
  public void initialize() throws Exception {
    System.out.println();
  }
}
