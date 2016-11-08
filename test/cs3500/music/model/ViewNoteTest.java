package cs3500.music.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * For the purposes of testing ViewNote.
 */
public class ViewNoteTest {
  private Note note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  private ViewNote viewNote0 = NoteBuilder.buildNote(note0);


  //Note is not ViewNote
  @Test
  public void isViewNote() throws Exception {
    Assert.assertEquals(false, note0.isViewNote());
  }

  //ViewNote is a ViewNote
  @Test
  public void isViewNote2() throws Exception {
    Assert.assertEquals(true, NoteBuilder.buildNote(note0).isViewNote());
  }

  @Test
  public void testGetNoteName()  {
    Assert.assertEquals(NoteName.C, viewNote0.getNoteName());
  }

  @Test
  public void testBeat()  {
    Assert.assertEquals(2, viewNote0.getBeat());
  }

  @Test
  public void testStartDuration()  {
    Assert.assertEquals(2, viewNote0.getStartDuration());
  }

  @Test
  public void testChannel()  {
    Assert.assertEquals(0, viewNote0.getChannel());
  }

  @Test
  public void testVolume()  {
    Assert.assertEquals(5, viewNote0.getVolume());
  }

}