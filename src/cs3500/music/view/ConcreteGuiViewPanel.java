package cs3500.music.view;


import cs3500.music.model.IRepetition;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.MusicUtils;

/**
 * A dummy view that simply draws a string.
 */
public class ConcreteGuiViewPanel extends JPanel implements Scrollable {

  private IBasicMusicEditor<INote> musicEditor;
  // Define constants for the various dimensions
  private static final Color LINE_COLOR = Color.RED;
  private static final int NOTEWIDTH = 25;
  private static final int NOTEHEIGHT = 25;
  private static final int BASEDOWN = 40;
  private static final int BASERIGHT = 50;

  // The moving line from (x1, y1) to (x2, y2), initially position at the center
  private int x1;
  private int y1;
  private int x2;
  private int y2;//55 + (NOTEHEIGHT * (musicEditor.getMaxPitch() - musicEditor.getMinPitch()));

  // on screen, based on scroll (topLeftX,topLeftX), (botRightX, botRightY)
  private int topLeftX;
  private int topLeftY;
  private int botRightX;
  private int botRightY;

  private int currentX = 0;
  private int currentY = 0;
  private Rectangle rectangle = new Rectangle(0, 0, 1200, 900);

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
    drawNotes(g, baseRight, baseDown, currentX - 30, currentX + 60);
    g.setColor(Color.BLACK);
    verticalLine(g, currentX - 15, currentX + 70);
    horizontalLine(g, baseRight, baseDown, currentX - 15, currentX + 70);
    drawPitch(g);
    drawRepeats(g);
    g.setColor(LINE_COLOR);
    g.drawLine(x1, y1, x2, y2); // Draw the line
  }

  void update() {
    paintRec(rectangle);
    this.repaint(rectangle);
  }

  private void drawRepeats(Graphics g) {
    Iterator<IRepetition> repList = musicEditor.getRepeats().iterator();
    while (repList.hasNext()) {
      IRepetition repetition = repList.next();
      for (int i = 0; i < repetition.getEnds().size(); i++) {
        int lineX = repetition.getEnds().get(i);
        g.setColor(Color.MAGENTA);
        //draw the ending
        g.drawLine(doMathForX(lineX), y1, doMathForX(lineX), y2 + 30);
        if (!(repetition.getEnds().size() == 1)) {
          g.drawString(Integer.toString(i + 2), doMathForX(lineX) - 10, y2 + 40);
        }
        g.drawOval(doMathForX(lineX) - 5, y2 + 20 , 5, 5);
        g.drawOval(doMathForX(lineX) - 5, y2 + 10 , 5, 5);
      }
      g.setColor(Color.MAGENTA);
      if (repetition.getEnds().size() > 1) {
        g.drawLine(doMathForX(repetition.getSkipAt()), y1,
                doMathForX(repetition.getSkipAt()), y2 + 30);
        g.drawString(Integer.toString(1), doMathForX(repetition.getSkipAt()) - 10, y2 + 40);
      }
      //Draw the start
      g.drawLine(doMathForX(repetition.getStart()), y1,
              doMathForX(repetition.getStart()), y2 + 30);
      g.drawOval(doMathForX(repetition.getStart()) + 5, y2 + 10 , 5, 5);
      g.drawOval(doMathForX(repetition.getStart()) + 5, y2 + 20 , 5, 5);
    }

  }

  private int doMathForX(int rightX) {
    return rightX * NOTEWIDTH + 50;
  }

  /**
   * Repaint with the rectangle
   *
   * @param rectangle the rect to paint in.
   */
  void paintRec(Rectangle rectangle) {
    this.rectangle = rectangle;
  }


  /**
   * Draw the vertical line.
   *
   * @param g the graphics.
   */
  private void verticalLine(Graphics g, int top, int bottom) {
    if (top < 0) {
      top = 0;
    }
    if (bottom > musicEditor.getLastBeat()) {
      bottom = musicEditor.getLastBeat();
    }
    for (int i = top; i <= bottom; i += 2) {

      int x1 = 50 + (i * NOTEWIDTH);
      int y1 = this.y1;
      int y2 = this.y2;

      g.drawLine(x1, y1, x1, y2);
    }
  }

  private void horizontalLine(Graphics g, int baseRight, int baseDown, int left, int right) {

    if (left < 0) {
      left = 0;
    }
    if (right > musicEditor.getLastBeat()) {
      right = musicEditor.getLastBeat() + NOTEWIDTH + right;
    }
    for (int j = 0; j <= musicEditor.getMaxPitch() - musicEditor.getMinPitch() + 1; j++) {
      int x = baseRight + (left * NOTEWIDTH);
      int y = baseDown + (j * NOTEHEIGHT);
      int x2;
      if (right < (musicEditor.getLastBeat())) {
        x2 = right * NOTEWIDTH;
      }
      else {
        x2 = (musicEditor.getLastBeat() * NOTEWIDTH) + baseRight;
      }
      g.drawLine(x, y, x2, y);
    }
  }

  void move(long tick) {
    if (tick >= 0) {
      //System.out.println(tick);
      x1 = (int) tick * NOTEWIDTH + 50;
      x2 = (int) tick * NOTEHEIGHT + 50;
      //System.out.println(x1+ " x2:" + x2);
      repaint();
    }
  }

  private void drawNotes(Graphics g, int baseRight, int baseDown,
                         int leftOnScreen, int rightOnScreen) {
    SortedMap<Integer, SortedMap<Integer, List<INote>>> comp = musicEditor.composition();
    Set<Integer> beats = comp.keySet();
    beats.stream()
            .filter(x -> (x < rightOnScreen) && (x > leftOnScreen))
            .forEach(beatAt -> drawAllNotesAtPitch(g, baseRight, baseDown, beatAt));
  }

  private Graphics drawAllNotesAtPitch(Graphics g, int baseRight, int baseDown, int beatAt) {
    SortedMap<Integer, SortedMap<Integer, List<INote>>> comp = musicEditor.composition();
    int x = baseRight + (beatAt * NOTEWIDTH);
    Set<Integer> pitches = comp.get(beatAt).keySet();
    pitches.forEach(j -> {
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

  void current(double botLeftX) {
    currentX = (int) (botLeftX - 50) / 25;
  }

  void jumpToBeginning() {
    this.x1 = BASERIGHT;
    this.x2 = this.x1;
  }

  void jumpToEnd() {
    this.x1 = this.musicEditor.getLastBeat();
  }

  @Override
  public Dimension getPreferredScrollableViewportSize() {
    return getPreferredSize();
  }

  @Override
  public int getScrollableUnitIncrement(Rectangle visibleRect,
                                        int orientation,
                                        int direction) {
    //Get the current position.
    int currentPosition = 0;
    if (orientation == SwingConstants.HORIZONTAL) {
      currentPosition = visibleRect.x;
    }
    else {
      currentPosition = visibleRect.y;
    }
    //Return the number of pixels between currentPosition
    //and the nearest tick mark in the indicated direction.
    if (direction < 0) {
      int newPosition = currentPosition -
              (currentPosition / NOTEWIDTH)
                      * NOTEWIDTH;
      return (newPosition == 0) ? NOTEWIDTH : newPosition;
    }
    else {
      return ((currentPosition / NOTEWIDTH) + 1)
              * NOTEWIDTH
              - currentPosition;
    }
  }

  @Override
  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
    if (orientation == SwingConstants.HORIZONTAL) {
      return visibleRect.width - NOTEWIDTH;
    }
    else {
      return visibleRect.height - NOTEHEIGHT;
    }
  }

  @Override
  public boolean getScrollableTracksViewportWidth() {
    return false;
  }

  @Override
  public boolean getScrollableTracksViewportHeight() {
    return false;
  }
}
