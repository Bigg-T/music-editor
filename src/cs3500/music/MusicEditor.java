package cs3500.music;

import cs3500.music.model.*;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiView;
import cs3500.music.view.MidiViewImpl;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    IBasicMusicEditor<INote> test = new BasicMusicEditor(1000);
    INote note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(0).setStartDuration(0).
            setNumBeats(5).setChannel(0).setVolume(0).buildNote();
    INote note0Copy = new NoteBuilder().setNoteName(NoteName.C).setOctave(0).setStartDuration(2).
            setNumBeats(2).setChannel(0).setVolume(1).buildNote();
    INote note1 = new NoteBuilder().setNoteName(NoteName.C).setOctave(0).setStartDuration(2).
            setNumBeats(2).setChannel(0).setVolume(2).buildNote();
    test.add(note0);
    //GuiViewFrame view = new GuiViewFrame(test);
    MidiView midiView = new MidiViewImpl();
    //view.initialize();
    midiView.playNote();
    //midiView.test();
    // You probably need to connect these views to your model, too...
  }
}
