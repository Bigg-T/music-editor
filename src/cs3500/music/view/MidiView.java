package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Created by tiger on 10/31/16.
 */
public interface MidiView {

  public void playNote() throws InvalidMidiDataException;
  //public void test() throws InvalidMidiDataException;
}
