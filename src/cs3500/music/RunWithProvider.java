package cs3500.music;

import cs3500.music.controller.IMusicEditorController;
import cs3500.music.controller.MusicControllerGui;
import cs3500.music.model.BasicMusicEditor;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.ReadOnlyModel;
import cs3500.music.model.ReadOnlyMusicModel;
import cs3500.music.provider.GuiPlayerView;
import cs3500.music.provider.GuiViewImpl;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by tiger on 12/3/16.
 */
public class RunWithProvider {

  /**
   * The main to run the program.
   * @param  args                      Argument for running program
   * @throws IOException              file exception
   * @throws InvalidMidiDataException mide exception
   */
  public static void main(String[] args) throws Exception {
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
    ReadOnlyMusicModel musicAdaptedModel = new ReadOnlyModel(musicEditor);

    GuiPlayerView view = new GuiViewImpl();

    IMusicEditorController controller = new MusicControllerGui(musicEditor, view);
    controller.initializeView();
//    view.setReadOnlyModel(musicAdaptedModel);
//    view.display();


  }
}
