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
    IView gui = new GuiViewFrame(musicEditor);
    IMidiView midi = new MidiViewImpl(musicEditor);
    Reader read = new InputStreamReader(System.in);
    IView console = new ConsoleView(musicEditor, read, System.out);
    switch (view) {
      case "visual":
        return gui;
      case "midi":
        return midi;
      case "console":
        return console;
      case "midi-vis":
        return new CompositeView(midi, gui);
      case "midi-con":
        return new CompositeView(midi, console);
      default:
        throw new IllegalArgumentException("Invalid input");
    }
  }

}
