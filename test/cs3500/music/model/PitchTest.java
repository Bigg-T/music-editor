package cs3500.music.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test Pitch.
 */
public class PitchTest {

  private Note note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(0).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(0).buildNote();
  private Note note1 = new NoteBuilder().setNoteName(NoteName.C).setOctave(0).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(2).buildNote();
  private Note note2 = new NoteBuilder().setNoteName(NoteName.C).setOctave(0).setStartDuration(2).
          setNumBeats(4).setChannel(0).setVolume(10).buildNote();
  private Note note3 = new NoteBuilder().setNoteName(NoteName.C).setOctave(0).setStartDuration(6).
          setNumBeats(2).setChannel(0).setVolume(0).buildNote();


  private Pitch setUp(int pitch) {
    return new Pitch(pitch);
  }

  @Test
  public void testEquals() throws Exception {
    Pitch p = setUp(12);
    Pitch p2 = setUp(12);
    Assert.assertEquals(p2, p);
  }

  @Test
  public void testEquals2() throws Exception {
    Pitch p = setUp(-1);
    Pitch p2 = setUp(12);
    Assert.assertEquals(false, p.equals(p2));
  }

  @Test
  public void testEquals3() throws Exception {
    Pitch p = setUp(12);
    Pitch p2 = setUp(12);
    Assert.assertEquals(true, p.equals(p2));
  }

  @Test
  public void testEquals4() throws Exception {
    Pitch p = setUp(-12);
    Pitch p2 = setUp(-12);
    Assert.assertEquals(true, p.equals(p2));
  }

  @Test
  public void testHashCode() throws Exception {
    Pitch p = setUp(-12);
    Pitch p2 = setUp(-12);
    Assert.assertEquals(true, p.hashCode() == p2.hashCode());
  }

  @Test
  public void testHashCode2() throws Exception {
    Pitch p = setUp(-12);
    Pitch p2 = setUp(2);
    Assert.assertEquals(false, p.hashCode() == p2.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddEx() throws Exception {
    Pitch pitchCollection = setUp(12);
    Note note = new Note(NoteName.A, 1, 12, 2, 4, 3);
    pitchCollection.add(note);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddEx2() throws Exception {
    Pitch pitchCollection = setUp(12);
    pitchCollection.add(null);
  }

  //throw error, try to remove null note
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveEx() throws Exception {
    Pitch pitch = setUp(12);
    Assert.assertNull("Null Note.", pitch.remove(null));

  }

  //able to remove
  @Test
  public void testRemove() throws Exception {
    Pitch pitch = setUp(12);
    pitch.add(note0);
    Assert.assertEquals(true, pitch.remove(note0));
  }

  //try to remove note that doesn't exist
  @Test
  public void testRemove2() throws Exception {
    Pitch pitch = setUp(12);
    pitch.add(note0);
    Assert.assertEquals(false, pitch.remove(note1));
  }

  @Test
  public void edit() throws Exception {
    Pitch pitch = setUp(12);
    pitch.add(note0);
    pitch.edit(note0, 4, 10);
    Assert.assertEquals(true, pitch.remove(note2));
  }

  @Test
  public void offsetStartBeat() throws Exception {
    Pitch pitch = setUp(12);
    pitch.add(note0);
    pitch.offsetStartBeat(4);
    Assert.assertEquals(true, pitch.remove(note3));
  }

  @Test
  public void longestNoteDuration() throws Exception {
    Pitch pitch = setUp(12);
    pitch.add(note0);
    pitch.add(note2);
    Assert.assertEquals(4, pitch.longestNoteDuration());
  }

  @Test
  public void toINoteList() throws Exception {
    Pitch pitch = setUp(12);
    pitch.add(note0);
    pitch.add(note2);
    Assert.assertEquals(NoteName.C, pitch.toINoteList().get(0).getNoteName());
  }


}