package cs3500.music.provider;

/**
 * A MusicPlayingView is a View that is capable of playing music.
 */
public interface MusicPlayingView extends MusicEditorView {

  /**
   * Plays the music.
   */
  void play();

  /**
   * Pauses the music.
   */
  void pause();

  /**
   * Resets the music to the first beat.
   */
  void reset();

  /**
   * Skips to the end of the song.
   */
  void skipToEnd();

  /**
   * @return Whether or not the music is playing.
   */
  boolean isPlaying();

  /**
   * @return The current beat of the music.
   */
  float getCurrentBeat();

  /**
   * Toggles playback of the piece of music.
   */
  void togglePlayback();
}
