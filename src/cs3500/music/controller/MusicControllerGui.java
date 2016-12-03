package cs3500.music.controller;

import cs3500.music.controller.AdaptedCommands.ActionRunnerGui;
import cs3500.music.controller.AdaptedCommands.JumpGuiView;
import cs3500.music.controller.AdaptedCommands.PauseActionGui;
import cs3500.music.controller.AdaptedCommands.PlayActionGui;
import cs3500.music.controller.Commands.AddKeyNumber;
import cs3500.music.controller.Commands.SetEdit;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.model.ReadOnlyModel;
import cs3500.music.provider.GuiPlayerView;

/**
 * For the purposes of representing a controller for the Music Editor.
 */
public class MusicControllerGui implements IMusicEditorController {

  /**
   * KeyHandler for controlling key events.
   */
  KeyHandler keyHandler;

  /**
   * MouseHandler for controlling mouse events.
   */
  MouseHandler mouseHandler;

  /**
   * Music editor being controlled.
   */
  IBasicMusicEditor<INote> musicEditor;

  /**
   * For the purposes of viewing the playing music.
   */
  GuiPlayerView theView;

  /**
   * Tracks controls being recieved by the controller.
   */
  ControlTracker ct;


  public MusicControllerGui(IBasicMusicEditor<INote> musicEditor, GuiPlayerView theView) {
    this.musicEditor = musicEditor;
    this.theView = theView;
    theView.setReadOnlyModel(new ReadOnlyModel(this.musicEditor));
    this.ct = new ControlTracker();
    this.keyHandler = new KeyHandler();
    this.mouseHandler = new MouseHandler();
    this.theView.addKeyListener(this.keyHandler);
    this.theView.addMouseListener(this.mouseHandler);
  }

  /**
   * Initializes standard controls for this controller.
   */
  void initStandard() {
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
    this.keyHandler.addKeyPressed(65,
            new ActionRunnerGui(this.ct, true, this.theView, this.musicEditor));
    this.keyHandler.addKeyPressed(82,
            new ActionRunnerGui(this.ct, false, this.theView, this.musicEditor));
    this.keyHandler.addKeyPressed(32, new PauseActionGui(this.theView));
    this.keyHandler.addKeyPressed(10, new PlayActionGui(this.theView));
    this.keyHandler.addKeyPressed(36, new JumpGuiView(this.theView, true));
    this.keyHandler.addKeyPressed(35, new JumpGuiView(this.theView, false));
    this.keyHandler.addKeyPressed(39, theView.getScrollHandler()::scrollLeft);
    this.keyHandler.addKeyPressed(37, theView.getScrollHandler()::scrollRight);
  }

  @Override
  public void initializeView() {
    try {
      this.theView.display();
    }
    catch (Exception e) {
      return;
    }
  }

  @Override
  public void addControl(Integer keyCode, Runnable runnable, String location) {
    switch (location) {
      case "pressed":
        this.keyHandler.addKeyPressed(keyCode, runnable);
        return;
      case "typed":
        this.keyHandler.addKeyTyped(keyCode, runnable);
        return;
      case "released":
        this.keyHandler.addKeyReleased(keyCode, runnable);
        return;
      default:
        return;
    }
  }

}
