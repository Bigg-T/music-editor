package cs3500.music.model;

/**
 * For the purposes of creating an unmodifiable note.
 */
public class ViewNote implements INote {

  Note note;

  public ViewNote(Note note) {
    this.note = note;
  }

  @Override
  public NoteName getNoteName() {
    return note.getNoteName();
  }

  @Override
  public int getOctave() {
    return note.getOctave();
  }

  @Override
  public int getStartDuration() {
    return note.getStartDuration();
  }

  @Override
  public int getBeat() {
    return note.getBeat();
  }

  @Override
  public int getChannel() {
    return note.getChannel();
  }

  @Override
  public int getVolume() {
    return note.getVolume();
  }

  @Override
  public boolean isViewNote() {
    return true;
  }
}
