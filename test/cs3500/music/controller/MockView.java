package cs3500.music.controller;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.view.IView;

/**
 * A fake view, for testing.
 */
public class MockView implements IView {

  public StringBuilder result = new StringBuilder();

  @Override
  public void initialize() throws Exception {
    result.append("init ");
  }

  @Override
  public void update() {
    result.append("update ");
  }

  @Override
  public void addKeyListener(KeyListener keyListener) {
    result.append("key ");
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
    result.append("mouse ");
  }

  @Override
  public long getCurrentTick() {
    return 5;
  }

  @Override
  public void jumpToBeginning() {
    result.append("begin ");
  }

  @Override
  public void jumpToEnd() {
    result.append("end ");
  }

  @Override
  public void move(long tick) {
    result.append("move ");
  }

  @Override
  public void pause() {
    result.append("pause ");
  }

  @Override
  public void resume() {
    result.append("resume ");
  }

  @Override
  public void scrollHorizontal(int unit) {
    result.append("horiz ");
  }

  @Override
  public void scrollVertical(int unit) {
    result.append("verti ");
  }

  @Override
  public boolean isRunning() {
    return false;
  }

  @Override
  public void setTickPosition(long position) {
    return;
  }

  @Override
  public void repeatView() {
    return;
  }
}
