package cs3500.music.tests.model;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.NoteBuilder;
import cs3500.music.model.INote;
import cs3500.music.model.NoteName;
import cs3500.music.model.BasicMusicEditor;

import java.util.Arrays;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import java.util.SortedMap;

import static org.junit.Assert.assertEquals;

/**
 * For the purposes of testing the BasicMusicEditor class.
 */
public class BasicModelTest {

  INote note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  INote note1 = new NoteBuilder().setNoteName(NoteName.A).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).buildNote();
  INote note2 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(4).setChannel(0).setVolume(10).buildNote();
  INote note3 = new NoteBuilder().setNoteName(NoteName.A).setOctave(3).setStartDuration(1).
          setNumBeats(1).setChannel(0).setVolume(5).buildNote();

  private IBasicMusicEditor<INote> musicEditor;

  @Before
  public void setUp() throws Exception {
    this.musicEditor = new BasicMusicEditor.BasicCompositionBuilder().setTempo(120).build();
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
  }

  // tests removing a note
  @Test
  public void testRemove1() throws Exception {
    this.setUp();
    musicEditor.add(note0);
    assertEquals(false, musicEditor.remove(note1));
  }

  // tests editing a note
  @Test
  public void testEdit() throws Exception  {
    this.setUp();
    musicEditor.add(note0);
    musicEditor.edit(note0, 4, 10);
    assertEquals(true, musicEditor.remove(note2));
  }


  // tests merging two editors consecutively
  @Test
  public void testMergeConsec() throws Exception  {
    this.setUp();
    IBasicMusicEditor<INote> newEditor = new BasicMusicEditor.
            BasicCompositionBuilder().setTempo(120).build();
    musicEditor.add(note0);
    newEditor.add(note1);
    INote noteAfterMove = new NoteBuilder().setNoteName(NoteName.A).setOctave(4).setChannel(0).
            setVolume(5).setStartDuration(6).setNumBeats(2).buildNote();
    musicEditor.merge(newEditor, true);
    assertEquals(true, musicEditor.remove(noteAfterMove));
  }

  // tests merging two editors simultaneously
  @Test
  public void testMergeSimul() throws Exception  {
    this.setUp();
    IBasicMusicEditor<INote> newEditor = new BasicMusicEditor.
            BasicCompositionBuilder().setTempo(120).build();
    musicEditor.add(note0);
    newEditor.add(note1);
    musicEditor.merge(newEditor, false);
    assertEquals(true, musicEditor.remove(note1));
  }


  // tests the getAllNotesAt method
  @Test
  public void testGetAllNotesAt() throws Exception  {
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
  public void testGetLastBeat() throws Exception  {
    this.setUp();
    musicEditor.add(note0);
    assertEquals(4, musicEditor.getLastBeat());
  }

  // tests the getLastStartBeat method
  @Test
  public void testGetLastStartBeat() throws Exception  {
    this.setUp();
    musicEditor.add(note0);
    assertEquals(2, musicEditor.getLastStartBeat());
  }

  // tests the isUnmodEditor method
  @Test
  public void testIsViewEditor() throws Exception  {
    this.setUp();
    assertEquals(false, musicEditor.isViewEditor());
  }

  @Test
  public void testAddRepeat() throws Exception {
    setUp();
    assertEquals(true, musicEditor.addRepeat(0, Arrays.asList(30, 40)));
    assertEquals(false, musicEditor.addRepeat(20, Arrays.asList(35, 40)));
    assertEquals(true, musicEditor.addRepeat(40, Arrays.asList(60, 65)));
  }

}
