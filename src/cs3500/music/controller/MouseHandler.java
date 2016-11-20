package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cs3500.music.view.IGuiView;

/**
 * For the purposes of handling mouse events.
 */
public class MouseHandler implements MouseListener {

  IGuiView guiView;
  ControlTracker controlTracker;

  /**
   * Constructs a MouseHandler.
   * @param guiView        the GuiView to be used
   * @param controlTracker the ControlTracker to be used
   */
  public MouseHandler(IGuiView guiView, ControlTracker controlTracker) {
    this.guiView = guiView;

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    if ((x < guiView.getEditorTopLeft().getX()) || (x > (guiView.getEditorTopLeft().getX() +
            guiView.getEditorDimensions().getWidth())))  {
      return;
    }
    if ((y < guiView.getEditorTopLeft().getY()) || (y > (guiView.getEditorTopLeft().getY() +
            guiView.getEditorTopLeft().getY())))  {
      return;
    }
    int regX = ((int) (x - guiView.getEditorTopLeft().getX())) /
            ((int) guiView.getNoteSize().getWidth());
    int regY = ((int) (x - guiView.getEditorTopLeft().getY())) /
            ((int) guiView.getNoteSize().getHeight());
    controlTracker.setControl("pitch");
    controlTracker.editEntire(regX);
    controlTracker.setControl("start");
    controlTracker.editEntire(regY);
    controlTracker.setControl("none");
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
