package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * For the purposes of testing a read only model.
 */
public class ReadOnlyMusicTest {

  ReadOnlyMusicModel model = new ReadOnlyModel(new
          BasicMusicEditor.BasicCompositionBuilder().setTempo(120).addNote(1, 2, 1, 60, 3).
          build());

  @Test
  public void testBPM()  {
    assertEquals(4, model.getBeatsPerMeasure());
  }

  @Test
  public void testGetNotes()  {
    assertEquals(1, model.getNotes().size());
  }

  @Test
  public void testGetNotesAt()  {
    assertEquals(1, model.getNotesAtBeat(1).size());
  }

  @Test
  public void testGetNotesAt2()  {
    assertEquals("C 4", model.getNotesAtBeat(1).get(0).toString());
  }

  @Test
  public void testGetTempo()  {
    assertEquals(500000, model.getTempo());
  }

}
