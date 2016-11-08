package cs3500.music.view;

import com.sun.javaws.exceptions.InvalidArgumentException;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * A factory for creating views.
 */
public class ViewFactory {

  public static IView viewFactory(String view, IBasicMusicEditor<INote> musicEditor)
          throws InvalidArgumentException {
    switch (view) {
      case "visual":
        return new GuiViewFrame(musicEditor);
      case "midi":
        return new MidiViewImpl(musicEditor);
      case "console":
        return new ConsoleView(musicEditor);
      default:
        throw new IllegalArgumentException("Invalid input");
    }
  }

}
