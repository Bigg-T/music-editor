package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * For the purposes of testing the PitchCollection class.
 */
public class PitchCollectionTest {
  PitchCollection pc;
  PitchCollection pc1;
  Note note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  Note note1 = new NoteBuilder().setNoteName(NoteName.A).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(10).buildNote();
  Note note2 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(4).setChannel(0).setVolume(10).buildNote();
  Note note3 = new NoteBuilder().setNoteName(NoteName.A).setOctave(3).setStartDuration(1).
          setNumBeats(1).setChannel(0).setVolume(5).buildNote();

  private void setUp()  {
    pc = new PitchCollection();
    pc1 = new PitchCollection();
  }

  @Test
  public void testAdd() throws Exception {
    this.setUp();
    assertEquals(true, pc.add(note0));
  }

  @Test
  public void remove() throws Exception {
    this.setUp();
    pc.add(note0);
    assertEquals(true, pc.remove(note0));
  }

  @Test
  public void edit() throws Exception {
    this.setUp();
    pc.add(note0);
    pc.edit(note0, 4, 10);
    assertEquals(true, pc.remove(note2));
  }

  @Test
  public void merge() throws Exception {
    this.setUp();

  }

  @Test
  public void adjustBeat() throws Exception {
    this.setUp();
    pc.add(note0);
    pc.add(note1);
    pc.adjustBeat(4);
    assertEquals(6, pc.getAllNote().get(60).get(0).getStartDuration());
  }

  @Test
  public void longestNoteDuration() throws Exception {
    this.setUp();
    pc.add(note0);
    pc.add(note2);
    assertEquals(4, pc.longestNoteDuration());
  }

  @Test
  public void getMinPitch() throws Exception {
    this.setUp();
    pc.add(note0);
    pc.add(note1);
    assertEquals(60, pc.getMinPitch());
  }

  @Test
  public void getMaxPitch() throws Exception {
    this.setUp();
    pc.add(note0);
    pc.add(note1);
    assertEquals(69, pc.getMaxPitch());
  }

  @Test
  public void getAllNote() throws Exception {
    this.setUp();
    pc.add(note0);
    pc.add(note1);
    assertEquals(NoteName.C, pc.getAllNote().get(60).get(0).getNoteName());
  }

}