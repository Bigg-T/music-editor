package cs3500.music.view;

import com.sun.javaws.exceptions.InvalidArgumentException;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

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
    Sequencer sequencer = null;
    try {
      sequencer = MidiSystem.getSequencer();
    }
    catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    IMidi midi = new MidiViewImpl(musicEditor, sequencer);
    Reader read = new InputStreamReader(System.in);
    IView console = new ConsoleView(musicEditor, read, System.out);
    switch (view) {
      case "visual":
        return gui;
      case "midi":
        return midi;
      case "console":
        return console;
      case "composite":
        return new CompositeView(gui, midi);
      default:
        throw new IllegalArgumentException("Invalid input");
    }
  }

}
