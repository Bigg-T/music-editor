package cs3500.music.view;

import com.sun.javaws.exceptions.InvalidArgumentException;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 * A factory for creating views.
 */
public class ViewFactory {

  /**
   * Factory method for creating views.
   */
  public static IView viewFactory(String view, IBasicMusicEditor<INote> musicEditor)
          throws InvalidArgumentException {
    switch (view) {
      case "visual":
        return new GuiViewFrame(musicEditor);
      case "midi":
        return new MidiViewImpl(musicEditor);
      case "console":
        Reader read = new InputStreamReader(System.in);
        return new ConsoleView(musicEditor, read, System.out);
      default:
        throw new IllegalArgumentException("Invalid input");
    }
  }

}
