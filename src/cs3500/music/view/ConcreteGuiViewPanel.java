package cs3500.music.view;


import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import javax.swing.JPanel;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.MusicUtils;

/**
 * A dummy view that simply draws a string.
 */
public class ConcreteGuiViewPanel extends JPanel {

  private IBasicMusicEditor<INote> musicEditor;
  // Define constants for the various dimensions
  private static final Color LINE_COLOR = Color.GREEN;
  private static final int NOTEWIDTH = 25;
  private static final int NOTEHEIGHT = 25;

  // The moving line from (x1, y1) to (x2, y2), initially position at the center
  private int x1;
  private int y1;
  private int x2;
  private int y2;//55 + (NOTEHEIGHT * (musicEditor.getMaxPitch() - musicEditor.getMinPitch()));

  ConcreteGuiViewPanel(IBasicMusicEditor<INote> musicEditor) {

    // The moving line from (x1, y1) to (x2, y2), initially position at the center
    x1 = 50;
    y1 = 40;
    x2 = x1;
    y2 = 65 + (NOTEHEIGHT * (musicEditor.getMaxPitch() - musicEditor.getMinPitch()));

    this.musicEditor = musicEditor;
    int width = (NOTEWIDTH * (musicEditor.getMaxPitch() - musicEditor.getMinPitch())) + 100;
    int height = (NOTEHEIGHT * musicEditor.getLastBeat()) + 80;
    this.setPreferredSize(new Dimension(height, width));
  }

  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);
    int baseRight = 50;
    int baseDown = 40;
    g.setColor(Color.BLUE);
//    ExecutorService executor = Executors.newFixedThreadPool(3);
//    Runnable draws = () -> drawPitch(g);
//    executor.submit(draws);
    //drawNotes(g, baseRight, baseDown);
    g.setColor(Color.BLACK);
    verticalLine(g);
    horizontalLine(g, baseRight, baseDown);
    drawPitch(g);

    g.setColor(LINE_COLOR);
    g.drawLine(x1, y1, x2, y2); // Draw the line
  }

  /**
   * Draw the vertical line.
   *
   * @param g the graphics.
   */
  private void verticalLine(Graphics g) {
    for (int i = 0; i <= musicEditor.getLastBeat(); i += 2) {

      int x1 = 50 + (i * NOTEWIDTH);
      int y1 = this.y1;
      int y2 = this.y2;

      g.drawLine(x1, y1, x1, y2);
    }
  }

  private void horizontalLine(Graphics g, int baseRight, int baseDown) {
    for (int j = 0; j <= musicEditor.getMaxPitch() - musicEditor.getMinPitch() + 1; j++) {
      int x = baseRight;
      int y = baseDown + (j * NOTEHEIGHT);
      int x2 = (musicEditor.getLastBeat() * NOTEWIDTH) + baseRight;
      g.drawLine(x, y, x2, y);
    }
  }

  void move(long tick) {
    if (tick > 4) {
      //System.out.println(tick);
      x1 += NOTEWIDTH;
      x2 += NOTEHEIGHT;
      repaint();
    }
  }

  private void drawNotes(Graphics g, int baseRight, int baseDown) {
    SortedMap<Integer, SortedMap<Integer, List<INote>>> comp = musicEditor.composition();
    Set<Integer> beats = comp.keySet();
    beats.forEach(beatAt -> drawAllNotesAtPitch(g, baseRight, baseDown, beatAt));
  }

  private Graphics drawAllNotesAtPitch(Graphics g, int baseRight, int baseDown, int beatAt) {
    SortedMap<Integer, SortedMap<Integer, List<INote>>> comp = musicEditor.composition();
    int x = baseRight + (beatAt * NOTEWIDTH);
    Set<Integer> pitches = comp.get(beatAt).keySet();
    pitches.forEach(j ->
    {
      int y = baseDown + ((musicEditor.getMaxPitch() - j) * NOTEHEIGHT);
      int currentMax = 0;
      for (INote note : comp.get(beatAt).get(j)) {
        if (note.getBeat() > currentMax) {
          currentMax = note.getBeat();
        }
        g.fillRect(x, y, (NOTEWIDTH * currentMax) - 1, NOTEHEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(x, y, NOTEWIDTH / 2, NOTEHEIGHT);
        g.setColor(Color.BLUE);
      }
    });

    return g;
  }

  private Graphics drawPitch(Graphics pitchGraphic) {
    for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++) {
      pitchGraphic.drawString(MusicUtils.pitchToString(j), 15, 55 +
              (NOTEHEIGHT * (musicEditor.getMaxPitch() - j)));
    }
    pitchGraphic.setColor(Color.BLACK);
    return pitchGraphic;
  }

}
