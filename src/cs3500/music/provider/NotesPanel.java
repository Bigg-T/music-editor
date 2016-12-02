package cs3500.music.provider;

import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicModel;
import cs3500.music.util.ModelUtils;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static cs3500.music.view.GuiViewImpl.COLUMN_WIDTH;
import static cs3500.music.view.GuiViewImpl.ROW_HEIGHT;

/**
 * Represents a view panel that renders notes.
 */
public class NotesPanel extends JPanel {
  /**
   * A read-only model accessible by this.
   */
  protected ReadOnlyMusicModel model;

  /**
   * the minimum Pitch in the model.
   */
  protected int minPitch = 0;
  /**
   * the maximum pitch in the model.
   */
  protected int maxPitch = 0;


  protected int firstVisibleBeat = 0;
  protected int numberVisibleBeats = 0;

  /**
   * Constructs a new NotesPanel with the given read-only model.
   */
  public NotesPanel(ReadOnlyMusicModel model) {
    super();
    this.model = model;
    computeMinMax();
  }

  public void computeMinMax() {
    this.minPitch = ModelUtils.getMinAbsolutePitch(model);
    this.maxPitch = ModelUtils.getMaxAbsolutePitch(model);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.drawGrid(g);
    this.drawNotes(g);
  }


  /**
   * Draws a grid on the panel.
   *
   * @param g Graphics to store grid lines.
   */
  private void drawGrid(Graphics g) {

    // Calculate lines that need drawing.
    firstVisibleBeat = (int) g.getClipBounds().getX() / COLUMN_WIDTH; // correct
    numberVisibleBeats = ((int) g.getClipBounds().getWidth() / COLUMN_WIDTH) + model.getBeatsPerMeasure();

    // Draw Vertical Lines
    for (int i = firstVisibleBeat;
         i <= Integer.min(firstVisibleBeat + numberVisibleBeats, model.totalBeats());
         i += i % model.getBeatsPerMeasure() == 0 ? model.getBeatsPerMeasure() : 1) {
      if (i % model.getBeatsPerMeasure() == 0) {
        g.drawLine(i * COLUMN_WIDTH, 0, i * COLUMN_WIDTH, (maxPitch - minPitch + 1) * ROW_HEIGHT);
      }
    }

    // Calculate lines that need drawing.
    int firstVisiblePitch = (int) g.getClipBounds().getY() / ROW_HEIGHT; // correct
    int numberVisiblePitches = ((int) g.getClipBounds().getHeight() / ROW_HEIGHT) + 1;

    // Draw Horizontal Lines
    for (int j = firstVisiblePitch; j <= firstVisiblePitch + numberVisiblePitches && j <= maxPitch - minPitch + 1; j++) {
      g.drawLine(0, j * ROW_HEIGHT, (model.totalBeats() - 1) * COLUMN_WIDTH, j * ROW_HEIGHT);
    }
  }

  /**
   * draws the notes on the panel.
   *
   * @param g Graphics to store drawn notes.
   */
  private void drawNotes(Graphics g) {
    // Calculate beats that need drawing
    firstVisibleBeat = (int) g.getClipBounds().getX() / COLUMN_WIDTH; // correct
    numberVisibleBeats = ((int) g.getClipBounds().getWidth() / COLUMN_WIDTH) + model.getBeatsPerMeasure();
    Set<Note> notes = new HashSet<Note>();
    for (int i = firstVisibleBeat; i < firstVisibleBeat + numberVisibleBeats; i++) {
      notes.addAll(model.getNotesAtBeat(i));
    }
    // Draw green lines for notes.
    g.setColor(Color.GREEN);
    for(Note n : notes) {
      g.fillRect(n.getStartTime() * COLUMN_WIDTH, ROW_HEIGHT * (maxPitch - n.getAbsolutePitch().intValue()),
              n.getDurationTime() * COLUMN_WIDTH, ROW_HEIGHT);
    }
    // Draw black squares.
    g.setColor(Color.BLACK);
    for (Note n : notes) {
      g.fillRect(n.getStartTime() * COLUMN_WIDTH, ROW_HEIGHT * (maxPitch - n.getAbsolutePitch().intValue()),
              COLUMN_WIDTH, ROW_HEIGHT);
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(COLUMN_WIDTH * (model.totalBeats() - 1), ROW_HEIGHT
            * (maxPitch - minPitch));
  }

}