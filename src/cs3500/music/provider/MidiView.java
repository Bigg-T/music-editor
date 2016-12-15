package cs3500.music.provider;

import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicModel;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A skeleton for MIDI playback.
 */
public class MidiView implements MusicPlayingView {

  private static int RESOLUTION = 60;
  /**
   * The midi sequencer.
   */
  private Sequencer sequencer;

  /**
   * The sequence to be given to the sequencer.
   */
  private Sequence sequence;

  /**
   * The read-only model passed to the provider.
   */
  private ReadOnlyMusicModel readOnlyModel;

  private Set<Note> notesInSequencer = new HashSet<>();

  private Map<Note, MidiEvent> midiOnNotes = new HashMap<>();
  private Map<Note, MidiEvent> midiOffNotes = new HashMap<>();


  /**
   * The default constructor for the midi provider, initializes the sequencer.
   * Used for convenience.
   */
  public MidiView() throws MidiUnavailableException {
    this(MidiSystem.getSequencer());
  }

  /**
   * Mostly used for testing, creates this MidiView using the given sequencer.
   *
   * @param sequencer The Sequencer to which all midi messages will be sent.
   */
  public MidiView(Sequencer sequencer) {
    this.sequencer = sequencer;
  }

  @Override
  public void display() {
    if (this.readOnlyModel == null) {
      throw new IllegalStateException("Current ReadOnlyModel is null.");
    }
    try {
      sequencer.setTempoInBPM(readOnlyModel.getTempo());
      sequencer.open();
      sequence = new Sequence(Sequence.PPQ, RESOLUTION);
      Track track = sequence.createTrack();
      //notesInSequencer.addAll(readOnlyModel.getNotes());
      //for (Note n : notesInSequencer) {
      for (Note n : readOnlyModel.getNotes()) {
        ShortMessage start = new ShortMessage();
        ShortMessage stop = new ShortMessage();
        start.setMessage(ShortMessage.NOTE_ON, n.getInstrument(),
                n.getAbsolutePitch().intValue(), n.getVolume());
        stop.setMessage(ShortMessage.NOTE_OFF, n.getInstrument(),
                n.getAbsolutePitch().intValue(), n.getVolume());
        MidiEvent noteOn = new MidiEvent(start, n.getStartTime() * RESOLUTION);
        MidiEvent noteOff = new MidiEvent(stop, n.getEndTime() * RESOLUTION);

        midiOnNotes.put(n, noteOn);
        midiOffNotes.put(n, noteOff);

        track.add(noteOn);
        track.add(noteOff);
      }
      sequencer.setSequence(sequence);
      sequencer.start();
    } catch (Exception e) {
      throw new RuntimeException("midi initialization failed: " + e.toString());
    }
  }

  @Override
  public void exit() {
    sequencer.close();
  }

  @Override
  public void setReadOnlyModel(ReadOnlyMusicModel readOnlyModel) {
    this.readOnlyModel = readOnlyModel;
  }

  @Override
  public void refresh() {
    try {
      // Check for added notes.
      Set<Note> possibleNewNotes = new HashSet<Note>(readOnlyModel.getNotes());
      for (Note n : possibleNewNotes) {
        if (!notesInSequencer.contains(n)) {
          Track track = sequence.getTracks()[0];
          ShortMessage start = new ShortMessage();
          ShortMessage stop = new ShortMessage();
          start.setMessage(ShortMessage.NOTE_ON, n.getInstrument(),
                  n.getAbsolutePitch().intValue(), n.getVolume());
          stop.setMessage(ShortMessage.NOTE_OFF, n.getInstrument(),
                  n.getAbsolutePitch().intValue(), n.getVolume());
          MidiEvent noteOn = new MidiEvent(start, n.getStartTime() * RESOLUTION);
          MidiEvent noteOff = new MidiEvent(stop, n.getEndTime() * RESOLUTION);
          track.add(noteOn);
          track.add(noteOff);
        }
      }
      // Calculate notes that must be removed from the sequencer.
      Set<Note> oldNotes = new HashSet<Note>(notesInSequencer);
      oldNotes.removeAll(possibleNewNotes);

      for (Note n : oldNotes) {
        Track track = sequence.getTracks()[0];
        ShortMessage start = new ShortMessage();
        ShortMessage stop = new ShortMessage();
        start.setMessage(ShortMessage.NOTE_ON, n.getInstrument(),
                n.getAbsolutePitch().intValue(), n.getVolume());
        stop.setMessage(ShortMessage.NOTE_OFF, n.getInstrument(),
                n.getAbsolutePitch().intValue(), n.getVolume());
        MidiEvent noteOn = new MidiEvent(start, n.getStartTime() * RESOLUTION);
        MidiEvent noteOff = new MidiEvent(stop, n.getEndTime() * RESOLUTION);
        for (int i = 0; i < track.size(); i++) {
          if (track.get(i).getTick() / RESOLUTION == n.getStartTime()
                  && track.get(i).getMessage().getMessage()[1] ==
                  noteOn.getMessage().getMessage()[1]) {
            track.remove(track.get(i));
          }
          if (track.get(i).getTick() / RESOLUTION == n.getEndTime()
                  && track.get(i).getMessage().getMessage()[1] ==
                  noteOff.getMessage().getMessage()[1]) {
            track.remove(track.get(i));
          }
        }

      }

    } catch (Exception e) {
      // do nothing
    }
  }

  @Override
  public void play() {
    sequencer.start();
    sequencer.setTempoInBPM(readOnlyModel.getTempo());

  }

  @Override
  public void pause() {
    sequencer.stop();
  }

  @Override
  public void reset() {
    sequencer.setTickPosition(0);
    sequencer.setTempoInBPM(readOnlyModel.getTempo());
  }

  @Override
  public boolean isPlaying() {
    return sequencer.isRunning();
  }

  @Override
  public void skipToEnd() {
    this.sequencer.setTickPosition(sequencer.getTickLength());
  }

  @Override
  public float getCurrentBeat() {
    return (float) this.sequencer.getTickPosition() / (float) RESOLUTION;
  }

  @Override
  public void togglePlayback() {
    if (isPlaying()) {
      pause();
    } else {
      play();
    }
  }
}
