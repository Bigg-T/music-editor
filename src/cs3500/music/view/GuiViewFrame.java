package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements IGuiView, Scrollable {
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
    this.displayPanel.move(tick);
  }

  @Override
  public void pause() {

  }


  @Override
  public Dimension getPreferredSize() {
    return new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
  }

  @Override
  public void addActionListener(ActionListener listener) {

  }

  @Override
  public Dimension getPreferredScrollableViewportSize() {
    return getPreferredSize();
  }

  @Override
  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
    return 0;
  }

  @Override
  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
    return 0;
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
