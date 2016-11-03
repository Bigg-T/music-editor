package cs3500.music.view;

import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.ViewMusicEditor;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements IGuiViewFrame {

  private final ConcreteGuiViewPanel displayPanel;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(IBasicMusicEditor<INote> musicEditor) {
    this.displayPanel = new ConcreteGuiViewPanel(musicEditor);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);
    this.pack();
  }

  @Override
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(displayPanel.getEditorWidth(), displayPanel.getEditorHeight());
  }

}
