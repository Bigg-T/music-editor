package cs3500.music.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * For the purposes of testing the BasicMusicEditor class.
 */
public class BasicMusicEditorTest {

  Note note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  Note note1 = new NoteBuilder().setNoteName(NoteName.A).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  Note note2 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(4).setChannel(0).setVolume(10).buildNote();
  Note note3 = new NoteBuilder().setNoteName(NoteName.A).setOctave(3).setStartDuration(1).
          setNumBeats(1).setChannel(0).setVolume(5).buildNote();

  private BasicMusicEditor musicEditor;

  @Before
  public void setUp() throws Exception {
    this.musicEditor = new BasicMusicEditor(120);
  }

  // tests adding a note
  @Test
  public void testAdd() throws Exception {
    this.setUp();
    assertEquals(true, musicEditor.add(note0));
  }

  // tests removing a note
  @Test
  public void testRemove() throws Exception {
    this.setUp();
    musicEditor.add(note0);
    assertEquals(true, musicEditor.remove(note0));
    assertEquals(false, musicEditor.remove(note1));
  }

  // tests editing a note
  @Test
  public void testEdit() throws Exception {
    this.setUp();
    musicEditor.add(note0);
    musicEditor.edit(note0, 4, 10);
    assertEquals(true, musicEditor.remove(note2));
  }

  // tests the getMinPitch method
  @Test
  public void testMinPitch() throws Exception {
    this.setUp();
    musicEditor.add(note0);
    musicEditor.add(note1);
    assertEquals(60, musicEditor.getMinPitch());
  }

  // tests the getMaxPitch method
  @Test
  public void testMaxPitch() throws Exception {
    this.setUp();
    musicEditor.add(note0);
    musicEditor.add(note1);
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
    musicEditor.add(note0);
    assertEquals(4, musicEditor.getLastBeat());
  }

  // tests the getLastStartBeat method
  @Test
  public void testGetLastStartBeat() throws Exception {
    this.setUp();
    musicEditor.add(note0);
    assertEquals(2, musicEditor.getLastStartBeat());
  }

  // tests the isUnmodEditor method
  @Test
  public void testIsViewEditor() throws Exception {
    this.setUp();
    assertEquals(false, musicEditor.isViewEditor());
  }


}
