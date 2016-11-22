package cs3500.music.tests.view;

import cs3500.music.model.BasicMusicEditor;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ViewFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

/**
 * Test the midiView.
 */
public class MidiViewTest {

  Sequencer sequencer;
  IView midiView;

  private void setUp(String str) throws Exception {
    sequencer = MidiSystem.getSequencer();
    File f = new File(str);
    Readable fr = new FileReader(f);
    CompositionBuilder<IBasicMusicEditor<INote>> compBuilder =
            new BasicMusicEditor.BasicCompositionBuilder();
    IBasicMusicEditor<INote> musicEditor = MusicReader.parseFile(fr, compBuilder);
    midiView = new MidiViewImpl(musicEditor, sequencer);
  }

  @Test
  public void testInitialize() throws Exception {
    setUp("test-midi.txt");
    midiView.initialize();
    Assert.assertEquals(15, sequencer.getTickLength());
    Assert.assertEquals("", "");
  }

  @Test
  public void testInitialize2() throws Exception {
    setUp("test-midi2.txt");
    midiView.initialize();
    Assert.assertEquals(0, sequencer.getTickLength());
  }

  @Test
  public void testInitialize3() throws Exception {
    setUp("test-midi.txt");
    midiView.initialize();
    Assert.assertEquals(10, sequencer.getSequence().getTracks()[0].size());
  }

}
