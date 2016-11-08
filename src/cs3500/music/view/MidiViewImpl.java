package cs3500.music.view;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.MusicUtils;
import cs3500.music.util.Utils;

import javax.sound.midi.*;
import java.io.*;
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
  private final IBasicMusicEditor<INote> musicEditor;

  public MidiViewImpl(IBasicMusicEditor<INote> musicEditor) {
    this.musicEditor = Utils.requireNonNull(musicEditor, "Null MusicEditor");
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
   * <li>{@link MidiSystem#getSynthesizer()}</li>
   * <li>{@link Synthesizer}
   * <ul>
   * <li>{@link Synthesizer#open()}</li>
   * <li>{@link Synthesizer#getReceiver()}</li>
   * <li>{@link Synthesizer#getChannels()}</li>
   * </ul>
   * </li>
   * <li>{@link Receiver}
   * <ul>
   * <li>{@link Receiver#send(MidiMessage, long)}</li>
   * <li>{@link Receiver#close()}</li>
   * </ul>
   * </li>
   * <li>{@link MidiMessage}</li>
   * <li>{@link ShortMessage}</li>
   * <li>{@link MidiChannel}
   * <ul>
   * <li>{@link MidiChannel#getProgram()}</li>
   * <li>{@link MidiChannel#programChange(int)}</li>
   * </ul>
   * </li>
   * </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   * https://en.wikipedia.org/wiki/General_MIDI
   * </a>
   */

  public void playNote() throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 10, 60, 110);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 10, 60, 110);

    MidiMessage start2 = new ShortMessage(ShortMessage.NOTE_ON, 3, 110, 110);
    MidiMessage stop2 = new ShortMessage(ShortMessage.NOTE_OFF, 3, 110, 110);
//
//    this.receiver.send(start, -1);
//    long timeStamp = this.synth.getMicrosecondPosition() + 200000;
//    this.receiver.send(stop, timeStamp);
//
//    this.receiver.send(start2, 0);
//    timeStamp += this.synth.getMicrosecondPosition() + 200000;
//    this.receiver.send(stop2, timeStamp);
    //this.receiver.close(); // Only call this once you're done playing *all* notes
    //long tick = (timeStamp * 300)/ (60*1000000);

    Sequence sequence = new Sequence(Sequence.PPQ, 480);
    Track track = sequence.createTrack();
    MidiEvent midiEvent = new MidiEvent(start, 350);
    MidiEvent midiEvent1 = new MidiEvent(stop, 350);

    MidiEvent midiEvent2 = new MidiEvent(start2, 700);
    MidiEvent midiEvent3 = new MidiEvent(stop2, 700);

    track.add(midiEvent);
    //track.add(midiEvent1);

    track.add(midiEvent2);
    track.add(midiEvent3);


    Patch[] p = sequence.getPatchList();
    List<Instrument> in = new ArrayList<>();
    List<Patch> lp = new ArrayList<>(Arrays.asList(p));
//    System.out.println(sequence.getResolution());
//    System.out.println(sequence.getTickLength());
    Sequence test = model(this.musicEditor, new Sequence(Sequence.PPQ, 1));
    try {
      Sequencer ss = MidiSystem.getSequencer();
      ss.setTempoInMPQ(musicEditor.getTempo());

      InputStream ip = new BufferedInputStream(new FileInputStream(new File("120c4-.mid")));
      ss.open();
      ss.setSequence(test);
//      System.out.println(ss.getSequence().getResolution());
//      System.out.println(ss.getSequence().getTickLength());
//      System.out.println(ss.getSequence().getMicrosecondLength());
//      System.out.println(ss.getTempoInBPM());
//      System.out.println(ss.getTempoInMPQ());

      Sequence rev = ss.getSequence();
      Track[] tracks = rev.getTracks();
      Patch[] patch = rev.getPatchList();
      System.out.println(tracks.length);

//      Debugging
//      Arrays.asList(tracks[1])
//              .forEach(x -> System.out.print("Midi\n" + yo(x.size(), x)));
//      Arrays.asList(tracks).forEach(x -> System.out.println(x.ticks()));
//      Arrays.asList(patch).forEach(x -> System.out.println(x.getBank()));
//      System.out.println(patch.length);
      ss.start();
      receiver.close();
      synth.close();
      TimeUnit.MICROSECONDS.sleep(ss.getSequence().getMicrosecondLength());
      //model(null, new Sequence(Sequence.PPQ, 100));

      ss.close();


    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void initialize() throws Exception {
    this.playNote();
  }

  private Sequence model(IBasicMusicEditor<INote> inote, Sequence sequence) {

    //creating 16 tracks
    Track track = sequence.createTrack();

    inote.composition().values().forEach(map -> {
      map.values().forEach(notes -> {
        notes.forEach(note -> {
          int pitch = MusicUtils.toPitch(note.getNoteName(), note.getOctave());
          //coverting 1 base index to 0 base index
          int channel = (note.getChannel() - 1) % 16;
          int volume = note.getVolume();
          System.out.println(channel);
          int startBeat = (note.getStartDuration()) + (4); //384 is the revolution
          //MusicUtils.toTrack(channel)];

          try {
            MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, channel, pitch, volume);
            MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, channel, pitch, volume);
            //change the to specific [0,127] Instrument, instead of using default [0,15] channel
            MidiMessage yo = new ShortMessage(ShortMessage.PROGRAM_CHANGE, channel, note.getChannel(), 0);

            MidiEvent midiEvent = new MidiEvent(start, startBeat);
            MidiEvent midiEvent2 = new MidiEvent(stop, startBeat + note.getBeat());
            MidiEvent yoyo = new MidiEvent(yo, startBeat);

            track.add(yoyo);
            track.add(midiEvent);
            track.add(midiEvent2);

          } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Midi broke!");
          }
        });
      });
    });
    return sequence;
  }

  private String yo(int n, Track track) {
    StringBuilder b = new StringBuilder("");
    for (int i = 0; i < n; i++) {
      MidiEvent midiEvent = track.get(i);
      b.append("" + i + "Len" + midiEvent.getMessage().getLength()
              + "  BytS" + y(midiEvent.getMessage().getMessage())
              + "  Stat" + midiEvent.getMessage().getStatus()
              + "  Tick" + midiEvent.getTick() + "\n");
    }
    return b.toString();
  }

  private String y(byte[] b) {
    StringBuilder m = new StringBuilder("");
    for (byte u : b) {
      m.append(" " + u);
    }
    return m.toString();
  }


  public void test() throws InvalidMidiDataException {
    MidiChannel chan[] = synth.getChannels();
    // Check for null; maybe not all 16 channels exist.
    if (chan[4] != null) {
      List<Integer> start = new ArrayList<>(Arrays.asList(1, 4, 9, 30, 60));
      List<Integer> end = new ArrayList<>(Arrays.asList(6, 8, 11, 35, 70));
      for (int i = 0; i < start.size(); i++) {
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
