package cs3500.music.provider;

/**
 * A Graphical view of a MusicEditor (May not have the ability to take in user input).
 */
public interface GraphicalView extends MusicEditorView {

  /**
   * @return The width of a note in this view.
   */
  int noteBoundingWidth();

  /**
   * @return The height of a note in this view.
   */
  int noteBoundingHeight();

}
