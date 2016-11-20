package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * A skeleton Frame (i.e., a window) in Swing
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
      scr.setScrollPosition((int)tick * 25, 0);

      this.displayPanel.current(scr.getScrollPosition().getX());
      System.out.println(scr.getScrollPosition().getX() + " " + scr.getScrollPosition().getY());
    }
    this.displayPanel.move(tick);
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void update() {
    displayPanel.update();
  }

  @Override
  public IBasicMusicEditor<INote> getMusicEditor() {
    return this.displayPanel.getMusicEditor();
  }

  @Override
  public void jumpToBeginning() {
    displayPanel.jumpToBeginning();
  }

  @Override
  public void jumpToEnd() {
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
