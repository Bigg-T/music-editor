package cs3500.music.view;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.MusicUtils;
import cs3500.music.util.Utils;

import javax.sound.midi.*;

import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * MIDI playback .
 */
public class MidiViewImpl implements IView {
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
    Sequence test = model(this.musicEditor, new Sequence(Sequence.PPQ, 1));
    try {
      Sequencer ss = MidiSystem.getSequencer();
      ss.setTempoInMPQ(musicEditor.getTempo());
      ss.open();
      ss.setSequence(test);

      ss.start();
      receiver.close();
      synth.close();
      TimeUnit.MICROSECONDS.sleep(ss.getSequence().getMicrosecondLength() / 6);
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

  @Override
  public void update() {

  }

  @Override
  public IBasicMusicEditor<INote> getMusicEditor() {
    return null;
  }

  @Override
  public void addKeyListener(KeyListener keyListener) {
    return;
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
            MidiMessage yo = new ShortMessage(ShortMessage.PROGRAM_CHANGE, channel,
                    note.getChannel(), 0);

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

  public static void main(String[] args) throws Exception {

    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 10, 60, 110);
  }
}
