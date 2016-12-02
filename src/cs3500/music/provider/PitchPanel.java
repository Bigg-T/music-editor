package cs3500.music.provider;

import cs3500.music.model.AbsolutePitch;
import cs3500.music.model.ReadOnlyMusicModel;
import cs3500.music.util.ModelUtils;

import javax.swing.*;
import java.awt.*;

import static cs3500.music.view.GuiViewImpl.COLUMN_WIDTH;
import static cs3500.music.view.GuiViewImpl.ROW_HEIGHT;

/**
 * Represents a view panel that displays music pitches.
 */
public class PitchPanel extends JPanel {
  /**
   * Read-only model accessible by this.
   */
  private ReadOnlyMusicModel model;

  /**
   * max pitch in the model.
   */
  private int maxPitch = 0;

  /**
   * minimum pitch in the model.
   */
  private int minPitch = 0;

  /**
   * Constructs a new pitch panel with a read-only model.
   *
   * @param model read only model.
   */
  public PitchPanel(ReadOnlyMusicModel model) {
    super();
    this.model = model;
    this.minPitch = ModelUtils.getMinAbsolutePitch(model);
    this.maxPitch = ModelUtils.getMaxAbsolutePitch(model);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    for (int p = maxPitch; p >= minPitch; p--) {
      int index = maxPitch - p + 1;
      g.drawString(new AbsolutePitch(p).toString(), 0, index * ROW_HEIGHT - ROW_HEIGHT / 2 + 5);
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(COLUMN_WIDTH, ROW_HEIGHT * (maxPitch - minPitch));
  }
}
