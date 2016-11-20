package cs3500.music.view;

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.ViewMusicEditor;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements IGuiView {

  private final ConcreteGuiViewPanel displayPanel;

  private final ScrollPane scr = new ScrollPane();

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(IBasicMusicEditor<INote> musicEditor) {
    this.displayPanel = new ConcreteGuiViewPanel(musicEditor);
    scr.add(displayPanel);
    this.setTitle("Music Editor");
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.add(scr);
    this.setFocusable(true);
    this.pack();
  }

  @Override
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(800, 600);
  }

  @Override
  public void update()  {
    this.displayPanel.repaint();
  }

  @Override
  public IBasicMusicEditor<INote> getMusicEditor() {
    return displayPanel.musicEditor;
  }

  @Override
  public void scroll(int toScroll)  {

  }

  @Override
  public Point getEditorTopLeft() {
    return null;
  }

  @Override
  public Dimension getEditorDimensions() {
    return null;
  }

  @Override
  public Dimension getNoteSize() {
    return new Dimension(ConcreteGuiViewPanel.NOTEWIDTH, ConcreteGuiViewPanel.NOTEHEIGHT);
  }
}
