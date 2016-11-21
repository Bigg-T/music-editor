package cs3500.music.view;

import com.sun.javaws.exceptions.InvalidArgumentException;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
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
          throws IllegalArgumentException {
    IView gui = new GuiViewFrame(musicEditor);
    Sequencer ss = null;
    try {
      ss = MidiSystem.getSequencer();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Can't init MIDI");
    }
    IMidiView midi = new MidiViewImpl(musicEditor, ss);
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
