package cs3500.music.provider;

import cs3500.music.model.AbsolutePitch;
import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicModel;
import cs3500.music.util.NotePitchComparator;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a textual provider for a music editor that prints to System.out or whatever appendable is
 * given.
 */
public class ConsoleView implements MusicEditorView {

  /**
   * The model that this provider will draw.
   */
  private ReadOnlyMusicModel model;
  private Appendable out;

  /**
   * Create a new ConsoleView printing to the given appendable.
   *
   * @param app The appendable that this ConsoleView will print to.
   */
  public ConsoleView(Appendable app) {
    this.out = app;
  }

  /**
   * Default Constructor that prints to System.out
   */
  public ConsoleView() {
    this(System.out);
  }

  @Override
  public void display() {
    if (this.model == null) {
      throw new IllegalStateException("Current ReadOnlyModel is null.");
    }
    try {
      this.out.append(parseModel() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void exit() {
    // Do nothing.
  }

  /**
   * Sets the readOnlyModel of this provider.
   *
   * @param readOnlyModel the readonly model to be set.
   */
  public void setReadOnlyModel(ReadOnlyMusicModel readOnlyModel) {
    Objects.requireNonNull(readOnlyModel);
    this.model = readOnlyModel;
  }

  @Override
  public void refresh() {
    // do nothing
  }

  /**
   * Parses the data in the model into a single String.
   *
   * @return A String representation of the model in this provider
   */
  private String parseModel() {
    NotePitchComparator npc = new NotePitchComparator();
    Optional<Note> min = model.getNotes().stream().min(npc);
    Optional<Note> max = model.getNotes().stream().max(npc);
    int minAbsolutePitch = min.isPresent() ? min.get().getAbsolutePitch().intValue() : 0;
    int maxAbsolutePitch = max.isPresent() ? max.get().getAbsolutePitch().intValue() : -1;
    int totalBeats = model.totalBeats();
    int longestBeatsStringLength = Integer.toString(totalBeats).length();
    int outputWidth = ((maxAbsolutePitch - minAbsolutePitch + 1)) * 5
            + longestBeatsStringLength + 2;
    StringBuilder builder = new StringBuilder();
    // add pitches
    appendNTimes(" ", longestBeatsStringLength, builder);
    for (int i = minAbsolutePitch; i <= maxAbsolutePitch; i++) {
      builder.append(parsePitchAndOctave(i));
    }
    builder.append("\n");
    // add notes
    for (int i = 0; i < model.totalBeats(); i++) {
      appendBeat(builder, i, longestBeatsStringLength, minAbsolutePitch, maxAbsolutePitch);
    }
    // add end row
    builder.append("\n");
    return builder.toString();
  }

  /**
   * Appends to the given builder a line that represents one beat in the model.
   *
   * @param builder                  The builder to be appended onto.
   * @param beat                     The beat of the line to be appended.
   * @param longestBeatsStringLength The string length of the longest beat in the model.
   * @param minAbsolutePitch         The minimum pitch that appears in the model.
   * @param maxAbsolutePitch         The maximum pitch that appears in the model.
   */
  private void appendBeat(StringBuilder builder, int beat, int longestBeatsStringLength,
                          int minAbsolutePitch, int maxAbsolutePitch) {
    builder.append(beat);
    appendNTimes(" ", longestBeatsStringLength - Integer.toString(beat).length(),
            builder);
    List<Note> notes = model.getNotesAtBeat(beat);
    for (int j = minAbsolutePitch; j <= maxAbsolutePitch; j++) {
      String adding = " ";
      for (Note n : notes) {
        if (n.getStartTime() == beat && n.getAbsolutePitch().intValue() == j) {
          adding = "X";
          break;
        } else if (n.getStartTime() < beat && n.getEndTime() >= beat
                && n.getAbsolutePitch().intValue() == j) {
          adding = "|";
        }
      }
      builder.append("  ").append(adding).append("  ");
    }
    builder.append("\n");
  }

  /**
   * Parses and returns a string representation of an absolute pitch with proper padding
   * on either side.
   *
   * @param absolutePitch The value of an absolute pitch
   * @return Padded string of the absolute pitch
   */
  private static String parsePitchAndOctave(int absolutePitch) {
    String s = " " + new AbsolutePitch(absolutePitch).toString();
    if (s.length() == 4) {
      return s + " ";
    }
    return " " + s + " ";
  }

  /**
   * Creates a string of the given string repeated n times and appends it to the given builder.
   *
   * @param s       The string to be repeated.
   * @param n       The number of times the given string should be repeated.
   * @param builder The builder to be appended to.
   */
  private static void appendNTimes(String s, int n, StringBuilder builder) {
    for (int i = 0; i < n; i++) {
      builder.append(s);
    }
  }
}
