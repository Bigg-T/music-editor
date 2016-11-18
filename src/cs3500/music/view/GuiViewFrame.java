package cs3500.music.view;

import java.awt.ScrollPane;
import java.awt.Dimension;

import javax.swing.JFrame;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements IView {

  

  /**
   * Creates new GuiView.
   */
  public GuiViewFrame(IBasicMusicEditor<INote> musicEditor) {
    ConcreteGuiViewPanel displayPanel = new ConcreteGuiViewPanel(musicEditor);
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
    System.out.println(tick);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(800, 600);
  }

}
