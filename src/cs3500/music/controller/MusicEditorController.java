package cs3500.music.controller;

import cs3500.music.view.IGuiView;

/**
 * To represent the controller for a MusicEditor.
 */
public class MusicEditorController implements IMusicEditorController {

  KeyHandler keyHandler;
  IGuiView guiView;
  ControlTracker ct;

  public MusicEditorController(IGuiView theView)  {
    this.guiView = theView;
    this.ct = new ControlTracker();
    this.keyHandler = new KeyHandler();
    this.keyHandler.addKeyPressed(48, new AddKeyNumber(this.ct, 0));
    this.keyHandler.addKeyPressed(49, new AddKeyNumber(this.ct, 1));
    this.keyHandler.addKeyPressed(50, new AddKeyNumber(this.ct, 2));
    this.keyHandler.addKeyPressed(51, new AddKeyNumber(this.ct, 3));
    this.keyHandler.addKeyPressed(52, new AddKeyNumber(this.ct, 4));
    this.keyHandler.addKeyPressed(53, new AddKeyNumber(this.ct, 5));
    this.keyHandler.addKeyPressed(54, new AddKeyNumber(this.ct, 6));
    this.keyHandler.addKeyPressed(55, new AddKeyNumber(this.ct, 7));
    this.keyHandler.addKeyPressed(56, new AddKeyNumber(this.ct, 8));
    this.keyHandler.addKeyPressed(57, new AddKeyNumber(this.ct, 9));
    this.keyHandler.addKeyPressed(80, new SetEdit(this.ct, "pitch"));
    this.keyHandler.addKeyPressed(83, new SetEdit(this.ct, "start"));
    this.keyHandler.addKeyPressed(68, new SetEdit(this.ct, "duration"));
    this.keyHandler.addKeyPressed(86, new SetEdit(this.ct, "volume"));
    this.keyHandler.addKeyPressed(67, new SetEdit(this.ct, "channel"));
    this.keyHandler.addKeyPressed(65, new GuiActionRunner(this.guiView, this.ct, true));
    this.keyHandler.addKeyPressed(82, new GuiActionRunner(this.guiView, this.ct, false));
    this.guiView.addKeyListener(keyHandler);
  }


  @Override
  public void addControl(Integer keyCode, Runnable runnable) {

  }
}
