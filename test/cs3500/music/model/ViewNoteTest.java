package cs3500.music.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tiger on 11/3/16.
 */
public class ViewNoteTest {
  private Note note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();

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


}