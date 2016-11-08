package cs3500.music;

import cs3500.music.model.BasicMusicEditor;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.ViewFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  
  /**
   * Main method.
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    File f = null;
    try {
      f = new File(args[0]);
    } catch (Exception e) {
      return;
    }

    Readable fr = new FileReader(f);
    CompositionBuilder<IBasicMusicEditor<INote>> compBuilder =
            new BasicMusicEditor.BasicCompositionBuilder();
    IBasicMusicEditor<INote> musicEditor = MusicReader.parseFile(fr, compBuilder);

    try {
      IView theView = ViewFactory.viewFactory(args[1], musicEditor);
      theView.initialize();
    } catch (Exception e) {
      return;
    }
  }


}
