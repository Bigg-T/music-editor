package cs3500.music.provider;

import cs3500.music.view.ConsoleView;

import javax.sound.midi.MidiUnavailableException;

/**
 * A Factory for creating music editor views.
 */
public class MusicEditorViewFactory {

  /**
   * Private constructor to prevent initialization.
   */
  private MusicEditorViewFactory() {
  }

  /**
   * Creates a new MusicEditorView corresponding to the type given.
   *
   * @param type One of "console", "visual", or "midi"
   * @return A new View of the given type
   */
  public static MusicEditorView create(String type) {
    try {
      switch (type) {
        case "console":
          return new ConsoleView();
        case "visual":
          return new GuiViewImpl();
        case "midi":
          return new MidiView();
        default:
          return null;
      }
    } catch (MidiUnavailableException e) {
      return null;
    }
  }

}
