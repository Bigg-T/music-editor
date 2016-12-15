package cs3500.music.provider;

import cs3500.music.model.ReadOnlyMusicModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static cs3500.music.provider.GuiViewImpl.COLUMN_WIDTH;
import static cs3500.music.provider.GuiViewImpl.ROW_HEIGHT;

/**
 * A Notes Panel that also draws a red line representing the current position of playback.
 */
public class NotesAndLinePanel extends NotesPanel implements ActionListener {
  private final Timer timer = new Timer(30, this);

  protected MusicPlayingView player;

  protected ScrollHandler scrollHandler;

  private float lastRedLineXPos = 0;

  private boolean autoScrollingEnabled = true;

  /**
   * Constructs a new NotesPanel with the given read-only model.
   */
  public NotesAndLinePanel(ReadOnlyMusicModel model, MusicPlayingView player,
                           ScrollHandler handler) {
    super(model);
    this.player = player;
    this.scrollHandler = handler;
  }

  /**
   * Begins an asynchronous timer that redraws the red line at about 30 FPS.
   */
  public void beginLinePainting() {
    timer.start();
  }

  /**
   * Repaint this panel in all sections that might be overlapping with the old red line from the
   * previous frame.
   */
  public void repaintPlayLine() {
    if (scrollIfNeeded()) {
      return;
    }
    int minX = Integer.min((int) lastRedLineXPos, (int) (player.getCurrentBeat() * COLUMN_WIDTH));
    int maxX = Integer.max((int) lastRedLineXPos, (int) (player.getCurrentBeat() * COLUMN_WIDTH));
    this.repaint(minX - COLUMN_WIDTH, 0,
            maxX - minX + 2*COLUMN_WIDTH,
            (maxPitch - minPitch + 1) * ROW_HEIGHT);
  }

  private boolean scrollIfNeeded() {
    if (this.firstVisibleBeat + this.numberVisibleBeats < player.getCurrentBeat()) {
      scrollHandler.autoScrollToX((int) (player.getCurrentBeat() * COLUMN_WIDTH));
      return true;
    }
    return false;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.drawPlayLine(g);
  }

  /**
   * Draws a red line at the current beat of the player.
   * @param g Graphic to be drawn on.
   */
  private void drawPlayLine(Graphics g) {
    g.setColor(Color.RED);
    this.lastRedLineXPos = player.getCurrentBeat() * COLUMN_WIDTH;
    g.fillRect((int) (lastRedLineXPos), 0, 4, Integer.min((int) g.getClipBounds()
            .getHeight(), (maxPitch - minPitch + 1) * ROW_HEIGHT));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.repaintPlayLine();
  }

}
