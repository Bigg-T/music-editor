package cs3500.music.view;

import cs3500.music.model.*;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.util.Utils;

import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.StreamSupport;

/**
 * Console View.
 */
public class ConsoleView implements IView {

  private final IBasicMusicEditor<INote> musicEditor;
  private List<List<String>> view = new ArrayList<>();

  public ConsoleView(IBasicMusicEditor<INote> musicEditor) {
    this.musicEditor = musicEditor;
  }


  /**
   * The note relative position in the array.
   *
   * @param x note in string format
   * @return the number where the note belong in the array
   */
  private int notePosition(String x) {
    return (NoteName.toNoteName(x.substring(0, 2)).toInt()
            + (((Integer.parseInt(x.substring(2)))) * 12)) - musicEditor.getMinPitch();
  }

  /**
   * Produce the name for all in range.
   *
   * @return the lists of notes name in range of displaying.
   */
  private List<String> produceName() {

    int column = (musicEditor.getMaxPitch() - musicEditor.getMinPitch() + 2);
    List<String> str = new ArrayList<>();
    str.add(0, "  ");
    for (int i = 1; i < column; i++) {
      StringBuilder builder = new StringBuilder("");
      builder.append(removeSpace(NoteName.toNoteName(
              intNote(musicEditor.getMinPitch() + i - 1)).toString()));
      builder.append(octave(musicEditor.getMinPitch() + i - 1));
      str.add(i, Utils.stringCenter(builder.toString(), 5));
    }
    return str;
  }

  /**
   * Calculate the octave number based on the note in the column of the rendered grid.
   *
   * @param position the x position in the grid
   * @return the octave
   */
  private int octave(int position) {
    return (int) (Math.ceil(((double) (position - intNote(position)) / 12))) - 1;
  }

  /**
   * Calculate the NoteName in term of number based on the position in the grid.
   *
   * @param position the x position in the grid
   * @return the NoteName representation of number
   */
  private int intNote(int position) {
    return (position % 12);
  }

  /**
   * Remove the empty space place holder of natural note.
   *
   * @param string the note name in string
   * @return the string without " " placeholder
   */
  private String removeSpace(String string) {
    if (string.substring(1, 2).equals(" ")) {
      return string.substring(0, 1);
    }
    return string;
  }

  //@Todo write the getState method
  @Override
  public void initialize() throws Exception {
    int maxBeat = musicEditor.getLastBeat();
    int minPitch = musicEditor.getMinPitch();
    int maxPitch = musicEditor.getMaxPitch();
    this.view = initView(maxBeat, minPitch, maxPitch);

    for (int i = 0; i <= maxBeat; i++) {
      final int temp = i;
      try {
        musicEditor.getAllNotesAt(i).values()
                .forEach(x -> x.forEach(note -> {
                  int notePos = this.notePosition(note.toString());
                  this.view.get(temp).add(notePos, NotePlay.NOTE_PLAY.toString());
                  for (int duration = 1; duration < note.getBeat(); duration++) {
                    if (this.view.get(temp + duration).get(notePos)
                            .equals(NotePlay.NOTE_REST.toString())
                            && !this.view.get(temp + duration).get(notePos)
                            .equals(NotePlay.NOTE_PLAY.toString())) {
                      this.view.get(temp + duration).add(notePos, NotePlay.NOTE_SUSTAIN.toString());
                    }
                  }
                }));

      } catch (Exception e) {
        continue;
      }
    }
    System.out.println(toString());
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

  List<List<String>> initView(int maxBeat, int minPitch, int maxPitch) {
    //System.out.println(maxBeat);
    List<List<String>> temp = new ArrayList<>();
    for (int beat = 0; beat < maxBeat; beat++) {
      List<String> strings = new ArrayList<>();
      //System.out.println(maxPitch - minPitch);
      for (int pitch = 0; pitch <= (maxPitch - minPitch); pitch++) {
        //System.out.print(maxBeat);
        strings.add(pitch, NotePlay.NOTE_REST.toString());
      }
      temp.add(beat, strings);
    }
    return temp;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("");
    produceName().forEach(x -> builder.append(x));
    builder.append("\n");
    //System.out.println(this.view.size());
    for (int i = 0; i < this.view.size(); i++) {
      List<String> x = this.view.get(i);
      builder.append(Utils.padding(i));
      x.forEach(str -> builder.append(str));
      builder.append("\n");
    }
    return builder.toString();
  }

  public static void main(String[] args) throws Exception {
    File f = null;
    try {
      f = new File("mary-little-lamb.txt");
    } catch (Exception e) {
      return;
    }

    Readable fr = new FileReader(f);
    CompositionBuilder<IBasicMusicEditor<INote>> compBuilder =
            new BasicMusicEditor.BasicCompositionBuilder();
    IBasicMusicEditor<INote> musicEditor = MusicReader.parseFile(fr, compBuilder);
    ConsoleView test = new ConsoleView(musicEditor);
    test.initialize();
    //test.view = test.initView(musicEditor.getLastBeat(), musicEditor.getMinPitch(), musicEditor.getMaxPitch());
    //System.out.println(musicEditor.getMinPitch() - musicEditor.getMaxPitch());
    System.out.print(test.toString());
    //test.produceName().forEach(x -> System.out.print(x));
//    System.out.println("\n" + musicEditor.getMinPitch());
//    System.out.println(test.notePosition("E 4"));
  }

}
