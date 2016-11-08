package cs3500.music.tests.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.SortedMap;

import cs3500.music.model.BasicMusicEditor;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.NoteBuilder;
import cs3500.music.model.NoteName;
import cs3500.music.model.ViewMusicEditor;

import static org.junit.Assert.assertEquals;

/**
 * For the purposes of testing the view model class.
 */
public class ViewModelTest {

  INote note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  INote note1 = new NoteBuilder().setNoteName(NoteName.A).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  INote note2 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(4).setChannel(0).setVolume(10).buildNote();
  INote note3 = new NoteBuilder().setNoteName(NoteName.A).setOctave(3).setStartDuration(1).
          setNumBeats(1).setChannel(0).setVolume(5).buildNote();

  private IBasicMusicEditor<INote> musicEditor;

  /**
   * Handles setup.
   */
  @Before
  public void setUp() throws Exception {
    IBasicMusicEditor<INote> mE = new BasicMusicEditor.BasicCompositionBuilder().
            setTempo(120).build();
    mE.add(note0);
    mE.add(note1);
    this.musicEditor = new ViewMusicEditor((BasicMusicEditor) mE);
  }

  // tests the getAllNotesAt method
  @Test
  public void testGetAllNotesAt() throws Exception {
    this.setUp();
    musicEditor.add(note0);
    musicEditor.add(note1);
    SortedMap<Integer, List<INote>> notes = musicEditor.getAllNotesAt(2);
    assertEquals(notes.get(60).get(0).getNoteName(), NoteName.C);
  }

  // tests the getMinPitch method
  @Test
  public void testMinPitch() throws Exception {
    this.setUp();
    assertEquals(60, musicEditor.getMinPitch());
  }

  // tests the getMaxPitch method
  @Test
  public void testMaxPitch() throws Exception {
    this.setUp();
    assertEquals(69, musicEditor.getMaxPitch());
  }

  // tests the getTempo method
  @Test
  public void testGetTempo() throws Exception {
    this.setUp();
    assertEquals(120, musicEditor.getTempo());
  }

  // tests the getLastBeat method
  @Test
  public void testGetLastBeat() throws Exception {
    this.setUp();
    assertEquals(4, musicEditor.getLastBeat());
  }

  // tests the getLastStartBeat method
  @Test
  public void testGetLastStartBeat() throws Exception {
    this.setUp();
    assertEquals(2, musicEditor.getLastStartBeat());
  }

  // tests the isUnmodEditor method
  @Test
  public void testIsViewEditor() throws Exception {
    this.setUp();
    assertEquals(true, musicEditor.isViewEditor());
  }

}
