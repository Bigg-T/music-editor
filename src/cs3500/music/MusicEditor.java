package cs3500.music;

import cs3500.music.controller.IMusicEditorController;
import cs3500.music.controller.MusicEditorController;
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
import java.util.Arrays;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {

  /**
   * The main to run the program.
   * @param  args                      Argument for running program
   * @throws IOException              file exception
   * @throws InvalidMidiDataException mide exception
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
//      musicEditor.addRepeat(2, Arrays.asList(20, 25));
//      musicEditor.addRepeat(25, Arrays.asList(40, 45));
      IMusicEditorController controller = new MusicEditorController(theView, musicEditor);
      controller.initializeView();
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }
}
