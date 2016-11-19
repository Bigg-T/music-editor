package cs3500.music.view;

import java.awt.*;

import javax.swing.JFrame;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements IView {
  // Define constants for the various dimensions
  private static final int CANVAS_WIDTH = 800;
  private static final int CANVAS_HEIGHT = 600;

  private final ConcreteGuiViewPanel displayPanel;
  /**
   * Creates new GuiView.
   */
  protected GuiViewFrame(IBasicMusicEditor<INote> musicEditor) {
    displayPanel = new ConcreteGuiViewPanel(musicEditor);
    ScrollPane scr = new ScrollPane();
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
  public Dimension getPreferredSize() {
    return new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
  }

}
