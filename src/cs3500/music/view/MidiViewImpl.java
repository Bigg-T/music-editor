package cs3500.music.view;

import javax.sound.midi.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements MidiView {
  private final Synthesizer synth;
  private final Receiver receiver;

  public MidiViewImpl() {
    Synthesizer synth = null;
    Receiver receiver = null;
    try {
      synth = MidiSystem.getSynthesizer();
      receiver = synth.getReceiver();
      synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    this.synth = synth;
    this.receiver = receiver;
  }
  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */

  public void playNote() throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 3, 60, 69);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 3, 60, 69);

    MidiMessage start2 = new ShortMessage(ShortMessage.NOTE_ON, 3, 90, 100);
    MidiMessage stop2 = new ShortMessage(ShortMessage.NOTE_OFF, 3, 90, 100);

    this.receiver.send(start, -1);
    long timeStamp = this.synth.getMicrosecondPosition() + 200000;
    this.receiver.send(stop, timeStamp);
    this.receiver.close(); // Only call this once you're done playing *all* notes
    long tick = (timeStamp * 300)/ (60*1000000);

    Sequence sequence = new Sequence(Sequence.PPQ, 1);
    Track track = sequence.createTrack();
    MidiEvent midiEvent = new MidiEvent(start, tick);
    MidiEvent midiEvent1 = new MidiEvent(stop, tick);

    MidiEvent midiEvent2 = new MidiEvent(start, tick);
    MidiEvent midiEvent3 = new MidiEvent(stop, tick);

    track.add(midiEvent);
    //track.add(midiEvent1);

    track.add(midiEvent2);
    //track.add(midiEvent3);


    Patch[] p = sequence.getPatchList();
    List<Instrument> in = new ArrayList<>();
    List<Patch> lp = new ArrayList<>(Arrays.asList(p));
    System.out.println(sequence.getResolution());
    System.out.println(sequence.getTickLength());

    try {
      Sequencer ss = MidiSystem.getSequencer();
      InputStream ip = new BufferedInputStream(new FileInputStream(new File("test.mid")));
      ss.open();
      ss.setSequence(ip);
      System.out.println(ss.getSequence().getResolution());
      System.out.println(ss.getSequence().getTickLength());
      System.out.println(ss.getSequence().getMicrosecondLength());
      System.out.println(ss.getTempoInBPM());
      System.out.println(ss.getTempoInMPQ());
      ss.start();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void test() throws InvalidMidiDataException {
    MidiChannel chan[] = synth.getChannels();
    // Check for null; maybe not all 16 channels exist.
    if (chan[4] != null) {
      List<Integer> start = new ArrayList<>(Arrays.asList(1, 4, 9, 30, 60));
      List<Integer> end = new ArrayList<>(Arrays.asList(6, 8, 11, 35, 70));
      for (int i = 0; i < start.size(); i ++) {
        MidiMessage start2 = new ShortMessage(ShortMessage.NOTE_ON, 3, start.get(i), end.get(i));
        try {
          TimeUnit.MICROSECONDS.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 3, start.get(i), end.get(i));
        this.receiver.send(start2, i);
        this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
        chan[4].noteOn(start.get(i), end.get(i));
      }
      //chan[6].noteOn(60, 61);
    }
  }
}
