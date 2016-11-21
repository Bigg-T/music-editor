package cs3500.music.controller;

import com.sun.tools.javap.TypeAnnotationWriter;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.IGuiView;
import cs3500.music.view.IView;
import cs3500.music.view.ViewFactory;

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
   * Constructs a MusicEditorController.
   * @param theView View to be used here
   */
  public MusicEditorController(IView theView, IBasicMusicEditor<INote> musicEditor)  {
    this.theView = theView;
    this.musicEditor = musicEditor;
    this.ct = new ControlTracker();
    this.keyHandler = new KeyHandler();
    this.mouseHandler = new MouseHandler();
    this.theView.addKeyListener(this.keyHandler);
    this.theView.addMouseListener(this.mouseHandler);
    this.initStandard();
  }

  /**
   * Initializes standard controls for this controller.
   */
  void initStandard()  {
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
            new ActionRunner(this.ct, this.theView, true));
    this.keyHandler.addKeyPressed(82,
            new ActionRunner(this.ct, this.theView, false));
    this.keyHandler.addKeyPressed(32, new PausePlayAction(this.theView));
    this.keyHandler.addKeyPressed(36, new JumpView(this.theView, true));
    this.keyHandler.addKeyPressed(35, new JumpView(this.theView, false));
    this.keyHandler.addKeyPressed(39, new HorizontalScroller(true, this.theView));
    this.keyHandler.addKeyPressed(37, new HorizontalScroller(false, this.theView));
    this.keyHandler.addKeyPressed(38, new VerticalScroller(false, this.theView));
    this.keyHandler.addKeyPressed(40, new VerticalScroller(true, this.theView));
    this.mouseHandler.addMouseClick(1, new ActionRunner(this.ct, this.theView, true));
    this.mouseHandler.addMouseClick(3, new ActionRunner(this.ct, this.theView, false));
  }

  @Override
  public void initializeView()  {
    try {
      this.theView.initialize();
    } catch (Exception e)  {
      return;
    }
  }

  @Override
  public KeyHandler getKeyHandler() {
    return this.keyHandler;
  }

  @Override
  public void addControl(Integer keyCode, Runnable runnable, String location) {
    switch (location)  {
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
