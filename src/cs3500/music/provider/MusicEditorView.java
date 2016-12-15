package cs3500.music.provider;

import cs3500.music.model.ReadOnlyMusicModel;

/**
 * Represents the provider of a music editor. IMPORTANT: All Views must be initialized, then given a
 * ViewModel (I.E. ReadOnlyModel), and then displayed using display() IN THAT ORDER.
 */
public interface MusicEditorView {

  /**
   * Responsible for displaying the provider of a music editor (sound, text, visual, etc.).
   *
   * @throws IllegalStateException If called before having received a ReadOnlyModel.
   */
  void display() throws IllegalStateException;

  /**
   * Handles exiting the provider.
   */
  void exit();

  /**
   * Sets this provider's modelView.
   *
   * @param readOnlyModel the readonly model to be set
   */
  void setReadOnlyModel(ReadOnlyMusicModel readOnlyModel);

  /**
   * Refreshes whatever this provider has displayed.
   */
  void refresh();

}
