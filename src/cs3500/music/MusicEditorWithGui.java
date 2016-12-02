package cs3500.music;

import cs3500.music.controller.GuiPlayerController;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.SimpleMusicEditorModel;
import cs3500.music.util.MusicReader;
import cs3500.music.provider.GuiViewImpl;

import javax.sound.midi.MidiUnavailableException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Convenience class for quickly creating music editors with Gui's
 */
public class MusicEditorWithGui {

  /**
   * Convenience Main method.
   */
  public static void main(String[] args) throws FileNotFoundException, MidiUnavailableException {
    String fileName = args[0];
    MusicEditorModel model = MusicReader.parseFile(new FileReader(fileName),
            new SimpleMusicEditorModel.Builder());
    GuiPlayerController controller = new GuiPlayerController(model, new GuiViewImpl());
    controller.run();
  }
}
