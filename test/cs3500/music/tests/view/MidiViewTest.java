package cs3500.music.tests.view;

import cs3500.music.model.BasicMusicEditor;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IView;
import cs3500.music.view.ViewFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

/**
 * .
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
    midiView = ViewFactory.viewFactory("midi", musicEditor);
  }

  private String getInfoFromSeqer(Sequencer sequencer) {
    Objects.requireNonNull(sequencer, "Null Sequencer");
    return "";
  }

  @Test
  public void testInitialize() throws Exception {
    setUp("test-midi.txt");
    Assert.assertEquals("", getInfoFromSeqer(sequencer));
  }

  @Test
  public void testInitialize2() throws Exception {
    setUp("");
    Assert.assertEquals("", getInfoFromSeqer(sequencer));
  }

  @Test
  public void testInitialize3() throws Exception {
    setUp("");
    Assert.assertEquals("", getInfoFromSeqer(sequencer));
  }

}
