package cs3500.music;

import cs3500.music.model.BasicMusicEditor;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.ViewMusicEditor;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiView;
import cs3500.music.view.MidiViewImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    File f = new File("lnl.txt");
    Readable fr = new FileReader(f);
    CompositionBuilder<IBasicMusicEditor<INote>> compBuilder = new BasicMusicEditor.BasicCompositionBuilder();
    IBasicMusicEditor<INote> musicEditor = MusicReader.parseFile(fr, compBuilder);
//    GuiViewFrame theView = new GuiViewFrame(musicEditor);
//    theView.initialize();
    MidiView midiView = new MidiViewImpl(musicEditor);
    //view.initialize();

    midiView.playNote();

  }
}
