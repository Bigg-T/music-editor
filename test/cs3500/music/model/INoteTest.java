package cs3500.music.model;

import cs3500.music.util.Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Integration test for INote.
 */
public abstract class INoteTest {

  private Note note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  private Note note0Copy = new NoteBuilder().setNoteName(NoteName.C).
          setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  private Note note1 = new NoteBuilder().setNoteName(NoteName.A).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  private Note note2 = new NoteBuilder().setNoteName(NoteName.CX).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  private Note note3 = new NoteBuilder().setNoteName(NoteName.A).setOctave(3).setStartDuration(1).
          setNumBeats(1).setChannel(0).setVolume(5).buildNote();


  protected abstract INote iNote(Note noteBuilder);

  public static final class AViewNote extends INoteTest {
    //Building a concrete class for Note
    @Override
    protected INote iNote(Note note) {
      return Utils.requireNonNull(note, "Note is null.");
    }

  }

  public static final class ANote extends INoteTest {
    //Building a concrete class for ViewNote
    @Override
    protected INote iNote(Note note) {
      return NoteBuilder.buildNote(Utils.requireNonNull(note, "Note is null."));
    }
  }

  //Test to make sure the testing concrete class is working properly
  @Test(expected = IllegalArgumentException.class)
  public void iNoteEX() throws Exception {
    iNote(null);
  }

  //Test that INote (ViewNote and Note) are working properly
  @Test
  public void getNoteName() throws Exception {
    Assert.assertEquals(NoteName.C, iNote(note0).getNoteName());
  }

  @Test
  public void getOctave() throws Exception {
    Assert.assertEquals(3, iNote(note3).getOctave());
  }

  @Test
  public void getStartDuration() throws Exception {
    Assert.assertEquals(2, iNote(note2).getStartDuration());
  }

  @Test
  public void getBeat() throws Exception {
    Assert.assertEquals(1, iNote(note3).getBeat());
  }

  @Test
  public void getChannel() throws Exception {
    Assert.assertEquals(0, iNote(note1).getChannel());
  }

  @Test
  public void getVolume() throws Exception {
    Assert.assertEquals(5, iNote(note0Copy).getVolume());
  }

}
