package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * For the purposes of testing the BasicMusicEditor class.
 */
public class BasicMusicEditorTest {

  Note note0 = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).build();
  Note note0Copy = new NoteBuilder().setNoteName(NoteName.C).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).build();
  Note note1 = new NoteBuilder().setNoteName(NoteName.A).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).build();
  Note note2 = new NoteBuilder().setNoteName(NoteName.CX).setOctave(4).setStartDuration(2).
          setNumBeats(2).setChannel(0).setVolume(5).build();
  Note note3 = new NoteBuilder().setNoteName(NoteName.A).setOctave(3).setStartDuration(1).
          setNumBeats(1).setChannel(0).setVolume(5).build();

  IBasicMusicEditor<INote> musicEditor = new BasicMusicEditor(120);

  public void testAdd()  {
    assertEquals(true, musicEditor.add(note0));
  }

  public void testRemove()  {
    assertEquals(true, musicEditor.remove(note0));
    assertEquals(false, musicEditor.remove(note1));
  }





}
