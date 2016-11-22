package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * A skeleton Frame (i.e., a window) in Swing.
 * The GuiView JFrame that composes all the components.
 */
public class GuiViewFrame extends JFrame implements IGuiView {
  // Define constants for the various dimensions
  private static final int CANVAS_WIDTH = 1400;
  private static final int CANVAS_HEIGHT = 1000;

  private final ConcreteGuiViewPanel displayPanel;
  private final ScrollPane scr;

  /**
   * Creates new GuiView.
   */
  protected GuiViewFrame(IBasicMusicEditor<INote> musicEditor) {
    displayPanel = new ConcreteGuiViewPanel(musicEditor);
    this.scr = new ScrollPane();
    scr.add(displayPanel);
    this.setTitle("Music Editor");

    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.add(scr);
    this.pack();
  }

  @Override
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public long getCurrentTick() {
    return 0;
  }

  @Override
  public void move(long tick) {
    if (tick % 50 == 0) {
      scr.setScrollPosition((int) tick * 25, 0);

      System.out.println(scr.getScrollPosition().getX() + " " + scr.getScrollPosition().getY());
    }
    this.displayPanel.paintRec(displayPanel.getVisibleRect());
    this.displayPanel.current(scr.getScrollPosition().getX());
    System.out.println(scr.getBounds().width + " " + scr.getBounds().height + " "
            + scr.getBounds().getMinX() + " " + scr.getBounds().getMaxX() + " " + scr.getBounds().getX());
    this.displayPanel.move(tick);
  }

  @Override
  public void pause() {
    return;
  }

  @Override
  public void resume() {
    return;
  }

  @Override
  public void scrollHorizontal(int unit) {
    this.displayPanel.paintRec(displayPanel.getVisibleRect());
    scr.setScrollPosition(scr.getX(), scr.getY() + unit);
  }

  @Override
  public void scrollVertical(int unit) {
    this.displayPanel.paintRec(displayPanel.getVisibleRect());
    scr.setScrollPosition(scr.getX() + unit, scr.getY());
  }

  @Override
  public void update() {
    this.displayPanel.paintRec(displayPanel.getVisibleRect());
    displayPanel.update();
  }

  @Override
  public void jumpToBeginning() {
    this.displayPanel.paintRec(displayPanel.getVisibleRect());
    displayPanel.jumpToBeginning();
  }

  @Override
  public void jumpToEnd() {
    this.displayPanel.paintRec(displayPanel.getVisibleRect());
    displayPanel.jumpToEnd();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
  }

  @Override
  public void addActionListener(ActionListener listener) {

  }

}
