package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Testing for Note class.
 */
public class NoteTest {

  private Note note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  private Note note0Copy = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  private Note note1 = new NoteBuilder().setNoteName(NoteName.A).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  private Note note2 = new NoteBuilder().setNoteName(NoteName.CX).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  private Note note3 = new NoteBuilder().setNoteName(NoteName.A).setOctave(3).setStartDuration(1).
          setNumBeats(1).setChannel(0).setVolume(5).buildNote();

  @Test
  public void testNoteToString() {
    assertEquals("C 4", note0.toString());
    assertEquals("A 4", note1.toString());
    assertEquals("C♯4", note2.toString());
    assertEquals("A 3", note3.toString());
  }

  @Test
  public void testNoteEquals() {
    assertEquals(true, note0.equals(note0Copy));
    assertEquals(false, note0.equals(note1));
  }

  //@TODO need to separate theses getter to its own test
  @Test
  public void testGetters() {
    assertEquals(NoteName.C, note0.getNoteName());
    assertEquals(4, note0.getOctave());
    assertEquals(2, note0.getStartDuration());
    assertEquals(2, note0.getBeat());
    assertEquals(0, note0.getChannel());
    assertEquals(5, note0.getVolume());
  }

  @Test
  public void testIsViewNote() {
    assertEquals(false, note0.isViewNote());
  }

  //Note is not ViewNote
  @Test
  public void testIsViewNote2() throws Exception {
    assertEquals(false, note0.isViewNote());
  }

  //ViewNote is a ViewNote
  @Test
  public void testIsViewNote3() throws Exception {
    assertEquals(true, NoteBuilder.buildNote(note0).isViewNote());
  }

}
