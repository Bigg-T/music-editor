package cs3500.music.controller;


import cs3500.music.controller.commands.ActionRunner;
import cs3500.music.controller.commands.AddKeyNumber;
import cs3500.music.controller.commands.AddRepeat;

import cs3500.music.controller.commands.AddRepeatEnd;
import cs3500.music.controller.commands.HorizontalScroller;
import cs3500.music.controller.commands.JumpView;
import cs3500.music.controller.commands.PauseAction;
import cs3500.music.controller.commands.PlayAction;
import cs3500.music.controller.commands.RepeatEdit;
import cs3500.music.controller.commands.SetEdit;
import cs3500.music.controller.commands.VerticalScroller;
import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.view.IView;

/**
 * To represent the controller for a MusicEditor.
 */
public class MusicEditorController implements IMusicEditorController {

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
   * View for the controller.
   */
  IView theView;

  /**
   * Tracks the controls being used.
   */
  ControlTracker ct;

  /**
   * Tracks the repetition commands logged.
   */
  RepeatTracker rt;

  /**
   * Constructs a MusicEditorController.
   *
   * @param theView View to be used here
   */
  public MusicEditorController(IView theView, IBasicMusicEditor<INote> musicEditor) {
    this.theView = theView;
    this.musicEditor = musicEditor;
    this.ct = new ControlTracker();
    this.rt = new RepeatTracker();
    this.keyHandler = new KeyHandler();
    this.mouseHandler = new MouseHandler();
    this.theView.addKeyListener(this.keyHandler);
    this.theView.addMouseListener(this.mouseHandler);
    this.initStandard();
    this.initializeView();
  }

  /**
   * Initializes standard controls for this controller.
   */
  void initStandard() {
    this.keyHandler.addKeyPressed(48, new AddKeyNumber(this.ct, this.rt, 0));
    this.keyHandler.addKeyPressed(49, new AddKeyNumber(this.ct, this.rt, 1));
    this.keyHandler.addKeyPressed(50, new AddKeyNumber(this.ct, this.rt, 2));
    this.keyHandler.addKeyPressed(51, new AddKeyNumber(this.ct, this.rt, 3));
    this.keyHandler.addKeyPressed(52, new AddKeyNumber(this.ct, this.rt, 4));
    this.keyHandler.addKeyPressed(53, new AddKeyNumber(this.ct, this.rt, 5));
    this.keyHandler.addKeyPressed(54, new AddKeyNumber(this.ct, this.rt, 6));
    this.keyHandler.addKeyPressed(55, new AddKeyNumber(this.ct, this.rt, 7));
    this.keyHandler.addKeyPressed(56, new AddKeyNumber(this.ct, this.rt, 8));
    this.keyHandler.addKeyPressed(57, new AddKeyNumber(this.ct, this.rt, 9));
    this.keyHandler.addKeyPressed(80, new SetEdit(this.ct, "pitch"));
    this.keyHandler.addKeyPressed(83, new SetEdit(this.ct, "start"));
    this.keyHandler.addKeyPressed(68, new SetEdit(this.ct, "duration"));
    this.keyHandler.addKeyPressed(86, new SetEdit(this.ct, "volume"));
    this.keyHandler.addKeyPressed(67, new SetEdit(this.ct, "channel"));
    this.keyHandler.addKeyPressed(65,
            new ActionRunner(this.ct, this.theView, this.musicEditor, true));
    this.keyHandler.addKeyPressed(82,
            new ActionRunner(this.ct, this.theView, this.musicEditor, false));
    this.keyHandler.addKeyPressed(32, new PauseAction(this.theView));
    this.keyHandler.addKeyPressed(10, new PlayAction(this.theView));
    this.keyHandler.addKeyPressed(36, new JumpView(this.theView, true));
    this.keyHandler.addKeyPressed(35, new JumpView(this.theView, false));
    this.keyHandler.addKeyPressed(39, new HorizontalScroller(true, this.theView));
    this.keyHandler.addKeyPressed(37, new HorizontalScroller(false, this.theView));
    this.keyHandler.addKeyPressed(38, new VerticalScroller(false, this.theView));
    this.keyHandler.addKeyPressed(40, new VerticalScroller(true, this.theView));
    this.keyHandler.addKeyPressed(81, new RepeatEdit("start", this.rt));
    this.keyHandler.addKeyPressed(87, new RepeatEdit("end", this.rt));
    this.keyHandler.addKeyPressed(84, new AddRepeat(this.musicEditor, this.rt));
    this.keyHandler.addKeyPressed(16, new AddRepeatEnd(this.rt));
    this.mouseHandler.addMouseClick(1, new ActionRunner(this.ct, this.theView,
            this.musicEditor, true));
    this.mouseHandler.addMouseClick(3, new ActionRunner(this.ct, this.theView,
            this.musicEditor, false));
  }

  @Override
  public void initializeView() {
    try {
      this.theView.initialize();
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
