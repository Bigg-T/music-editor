package cs3500.music.view;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements IView {
  // Define constants for the various dimensions
  public static final int CANVAS_WIDTH = 800;
  public static final int CANVAS_HEIGHT = 600;
  public static final Color LINE_COLOR = Color.GREEN;
  public static final Color CANVAS_BACKGROUND = Color.CYAN;

  // The moving line from (x1, y1) to (x2, y2), initially position at the center
  private int x1 = CANVAS_WIDTH / 2;
  private int y1 = CANVAS_HEIGHT / 8;
  private int x2 = x1;
  private int y2 = CANVAS_HEIGHT / 8 * 7;
  ConcreteGuiViewPanel displayPanel;
  /**
   * Creates new GuiView.
   */
  public GuiViewFrame(IBasicMusicEditor<INote> musicEditor) {
    displayPanel = new ConcreteGuiViewPanel(musicEditor);
    ScrollPane scr = new ScrollPane();
    scr.add(displayPanel);
    this.setTitle("Music Editor");

//    // Add both panels to this JFrame's content-pane
//    Container cp = getContentPane();
//    cp.setLayout(new BorderLayout());
//    cp.add(displayPanel, BorderLayout.CENTER);


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
