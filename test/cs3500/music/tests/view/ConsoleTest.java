package cs3500.music.tests.view;

import cs3500.music.model.BasicMusicEditor;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * .
 */
public class ConsoleTest {
  File f = null;
  Readable fr = null;
  ConsoleView test;
  IBasicMusicEditor<INote> musicEditor;
  private StringBuffer appendable;
  private Reader readable;

  @Before
  public void setUp() throws Exception {
    try {
      f = new File("mary-little-lamb.txt");
    } catch (Exception e) {
      return;
    }

    fr = new FileReader(f);
    CompositionBuilder<IBasicMusicEditor<INote>> compBuilder =
            new BasicMusicEditor.BasicCompositionBuilder();
    musicEditor = MusicReader.parseFile(fr, compBuilder);
    appendable = new StringBuffer();

    test = new ConsoleView(musicEditor, readable, appendable);
  }

  private void setReadable(String args) {
    readable = new StringReader(args);
  }

  private String retAppendable() {
    return appendable.toString();
  }

  private String getLastAppend() {
    int qIndex = appendable.toString().lastIndexOf("\n");
    String quit = "";
    if (qIndex >= 0) {
      quit = appendable.toString().substring(qIndex);
    }
    return quit;
  }

  @Test
  public void testInit() throws Exception {
    setUp();
    test.initialize();
    Assert.assertEquals("\n" + test.toString(), retAppendable());
  }
}
