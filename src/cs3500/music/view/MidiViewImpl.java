package cs3500.music.view;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.IRepetition;
import cs3500.music.util.MusicUtils;
import cs3500.music.util.Utils;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Objects;

/**
 * MIDI playback.
 */
public class MidiViewImpl implements IMidi, IView {

  private final IBasicMusicEditor<INote> musicEditor;
  private Sequencer ss;

  /**
   * Creates MidiViewImp, for testing.
   */
  MidiViewImpl(IBasicMusicEditor<INote> musicEditor, Sequencer ss) {
    Objects.requireNonNull(musicEditor, "Null music editor");
    Objects.requireNonNull(ss, "Null sequencer");
    this.musicEditor = Utils.requireNonNull(musicEditor, "Null MusicEditor");
    this.ss = ss;
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

  void playNote() throws InvalidMidiDataException {
    Sequence sequence = model(this.musicEditor, new Sequence(Sequence.PPQ, 1));

    try {
      ss.open();
      ss.setTempoInMPQ(musicEditor.getTempo());
      ss.setSequence(sequence);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void initialize() throws Exception {
    playNote();
    initialize(0);
  }

  @Override
  public long getCurrentTick() {
    return this.ss.getTickPosition();
  }

  @Override
  public void move(long tick) {
    return;
  }

  @Override
  public void pause() {
    ss.stop();
  }

  @Override
  public void resume() {
    ss.setTickPosition(ss.getTickPosition());
    ss.start();
    ss.setTempoInMPQ(musicEditor.getTempo());
    //startView();
  }

  @Override
  public void scrollHorizontal(int unit) {
    return;
  }

  @Override
  public void scrollVertical(int unit) {
    return;
  }

  @Override
  public boolean isRunning() {
    return ss.isRunning();
  }

  @Override
  public void jumpToBeginning() {
    ss.setTickPosition(0);
  }

  @Override
  public void jumpToEnd() {
    ss.setTickPosition(musicEditor.getLastBeat());
  }

  @Override
  public void update() {
    try {
      //ss.setSequence(this.model(musicEditor, new Sequence(Sequence.PPQ, 1)));
      playNote();
      ss.setTickPosition(this.getCurrentTick());
      ss.start();
      ss.setTempoInMPQ(musicEditor.getTempo());
      //ss.setTempoInMPQ(musicEditor.getTempo());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public void addKeyListener(KeyListener keyListener) {
    return;
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
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
          //System.out.println(channel);
          int startBeat = (note.getStartDuration()); //384 is the revolution
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

          }
          catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Midi broke!");
          }
        });
      });
    });
    return sequence;
  }

  @Override
  public synchronized void initialize(int playAt) throws Exception {
    ss.setTickPosition(playAt);
    ss.start();
    ss.setTempoInMPQ(musicEditor.getTempo());
    startView();
    //System.out.println("Quite!!!!");
  }

  @Override
  public synchronized void startView() {
    if (ss.isRunning()) {
      //filter out the repeats that has pasted
      Iterator<IRepetition> repetition = musicEditor.getRepeats()
              .stream()
              .filter(x -> (x.getEnds().get(x.getEnds().size() - 1) > ss.getTickPosition()))
              .iterator();
      //set the iteration
      while (repetition.hasNext()) {
        IRepetition iRepetition = repetition.next();
        setRepetition(iRepetition);
      }
    }
  }

  /**
   * Repeat the based on the repetition information.
   *
   * @param repetition the Repetition object to repeat
   */
  private synchronized void setRepetition(IRepetition repetition) {
    int rep = 0;
    while (rep < repetition.getEnds().size()) {
      if (repetition.getEnds().get(rep) == ss.getTickPosition()) {
        switch (rep) {
          case 0:
            System.out.println("size of " + repetition.getEnds().size() + " " + rep);
            setTickPosition(repetition.getStart());
            break;
          default:
            System.out.println("size of " + repetition.getEnds().size() + " " + rep);
            setTickPosition(repetition.getStart());
            while (isRunning()) {
              //does the skipping, if repeats share the starting
              if (ss.getTickPosition() == repetition.getSkipAt()) {
                //System.out.println("speciallllllllll" + repetition.getSkipAt() + ""
                // + repetition.getEnds().get(rep - 1));
                setTickPosition(repetition.getEnds().get(rep - 1));
                break;
              }
            }
            break;
        }
        rep += 1;
      }
    }
  }

  @Override
  public void setTickPosition(long position) {
    ss.stop();
    ss.setTickPosition(position);
    ss.start();
    ss.setTempoInMPQ(musicEditor.getTempo());
  }

  @Override
  public void repeatView() {
    startView();
  }
}
