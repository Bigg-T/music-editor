package cs3500.music.view;


import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
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
  public static final Color LINE_COLOR = Color.GREEN;
  private int y;
  // The moving line from (x1, y1) to (x2, y2), initially position at the center
  private int x1 = 50;
  private int y1 = 650;
  private int x2 = x1;
  private int y2 = y * NOTEWIDTH;
  public static final int NOTEWIDTH = 25;
  public static final int NOTEHEIGHT = 25;


  ConcreteGuiViewPanel(IBasicMusicEditor<INote> musicEditor) {
    this.musicEditor = musicEditor;
    this.y = (musicEditor.getMaxPitch() - musicEditor.getMinPitch()) * NOTEHEIGHT;
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
    drawNotes(g, baseRight, baseDown);
    g.setColor(Color.BLACK);
    for (int i = 0; i < musicEditor.getLastBeat(); i += 2) {
      for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++) {
        int x = baseRight + (i * NOTEWIDTH);
        int y = baseDown + ((musicEditor.getMaxPitch() - j) * NOTEHEIGHT);
        g.drawRect(x, y, NOTEWIDTH * 2, NOTEHEIGHT);
      }
    }
    drawPitch(g);

    g.setColor(LINE_COLOR);
    g.drawLine(x1, y1, x2, y2); // Draw the line
  }

  void move(long tick) {
    if (tick > 4) {
      System.out.println(tick);
      x1 += NOTEWIDTH;
      x2 += NOTEHEIGHT;
      repaint();
    }
  }

  void drawNotes(Graphics g, int baseRight, int baseDown) {
    SortedMap<Integer, SortedMap<Integer, List<INote>>> comp = musicEditor.composition();
    Set<Integer> beats = comp.keySet();

    beats.forEach(i ->
    {
      int x = baseRight + (i * NOTEWIDTH);
      Set<Integer> pitches = comp.get(i).keySet();
      pitches.forEach(j ->
      {
        int y = baseDown + ((musicEditor.getMaxPitch() - j) * NOTEHEIGHT);
        int currentMax = 0;
        for (INote note : comp.get(i).get(j)) {
          if (note.getBeat() > currentMax) {
            currentMax = note.getBeat();
          }
          g.fillRect(x, y, (NOTEWIDTH * currentMax) - 1, NOTEHEIGHT);
          g.setColor(Color.BLACK);
          g.fillRect(x, y, NOTEWIDTH / 2, NOTEHEIGHT);
          g.setColor(Color.BLUE);
        }
      });
    });
  }

  Graphics drawPitch(Graphics pitchGraphic) {
    for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++) {
      pitchGraphic.drawString(MusicUtils.pitchToString(j), 15, 55 +
              (NOTEHEIGHT * (musicEditor.getMaxPitch() - j)));
    }
    return pitchGraphic;
  }

}
