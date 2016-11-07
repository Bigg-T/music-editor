package cs3500.music.view;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.NotePlay;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * Created by robertcarney on 11/6/16.
 */
public class ConsoleView implements IView {

  private final IBasicMusicEditor<INote> musicEditor;
  private List<List<String>> view = new ArrayList<>();

  public ConsoleView(IBasicMusicEditor<INote> musicEditor) {
    this.musicEditor = musicEditor;
  }


  //@Todo write the getState method
  @Override
  public void initialize() throws Exception {
    TreeMap<Integer, SortedMap<Integer, List<INote>>> composition = musicEditor.composition();
    Set<Integer> keys = composition.keySet();

    int lastBeat = musicEditor.getLastBeat();
    int differenceInNote = musicEditor.getMaxPitch() - musicEditor.getMinPitch();

    int maxBeat = this.musicEditor.getLastBeat();
    int minPitch = this.musicEditor.getMinPitch();
    int maxPitch = this.musicEditor.getMaxPitch();
    this.view = initView(maxBeat, minPitch , maxPitch);

    keys.forEach(startbeat ->
    {
      composition.get(startbeat).keySet().forEach(pitch -> {
        INote note = composition.get(startbeat).get(pitch).get(0);
        int duration = note.getBeat();
        int startBeat = note.getStartDuration();
        this.view.get(pitch - minPitch).add(startBeat, NotePlay.NOTE_PLAY.toString());
        try {
          this.view.remove(startBeat + 1);
        } catch (Exception e) {

        }
        for (int i = 0; i < note.getBeat(); i++) {
          this.view.get(i + startBeat).add(pitch, NotePlay.NOTE_SUSTAIN.toString());
          this.view.get(pitch - minPitch).add(startBeat, NotePlay.NOTE_PLAY.toString());
          try {
            this.view.remove(startBeat + 1);
          } catch (Exception e) {

          }
        }

      });
    });

  }

  List<List<String>> initView(int maxBeat, int minPitch, int maxPitch) {
    List<List<String>> temp = new ArrayList<>();
    for (int beat = 0; beat <= maxBeat; beat++) {
      for (int pitch = 0; pitch <= maxPitch - minPitch; pitch++) {
        List<String> strings = new ArrayList<>(Collections
                .singletonList(NotePlay.NOTE_REST.toString()));
        temp.add(strings);
      }
    }
    return temp;
  }

}
