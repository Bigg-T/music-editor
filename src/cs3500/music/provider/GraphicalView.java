package cs3500.music.provider;

/**
 * A Graphical provider of a MusicEditor (May not have the ability to take in user input).
 */
public interface GraphicalView extends MusicEditorView {

  /**
   * @return The width of a note in this provider.
   */
  int noteBoundingWidth();

  /**
   * @return The height of a note in this provider.
   */
  int noteBoundingHeight();

}
