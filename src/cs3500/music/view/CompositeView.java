package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * For the purposes of creating a composite view, with a syncronized MIDI and Gui.
 */
public class CompositeView implements IGuiView {
  private final IView guiView;
  private final IView midiView;


  CompositeView(IView guiView, IView midiView) {
    this.guiView = guiView;
    this.midiView = midiView;
  }

  ExecutorService executor = Executors.newFixedThreadPool(2);
  @Override
  public void initialize() throws Exception {
    Runnable v1 = () -> this.createRunnable(guiView);
    Runnable v2 = () -> this.createRunnable(midiView);

    executor.submit(v1);
    executor.submit(v2);

    this.keepMoving();
    executor.shutdownNow();
  }

  private void createRunnable(IView view) {
    try {
      view.initialize();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public long getCurrentTick() {
    return 0;
  }

  @Override
  public void move(long tick) {
    //midiView.move(guiView.getCurrentTick());
    guiView.move(midiView.getCurrentTick());
  }

  @Override
  public void pause() {
    midiView.pause();
    guiView.pause();
  }

  @Override
  public void resume() {
    ExecutorService executor = Executors.newFixedThreadPool(3);
//    Runnable r1 = () -> midiView.resume();
//    Runnable r2 = () -> guiView.resume();
    midiView.resume();
    //Runnable r3 = () -> keepMoving();
//    executor.submit(r1);
//    executor.submit(r2);
    //executor.submit(r3);

  }

  private synchronized void keepMoving() {
    long currentPosition = -1;
    System.out.println(executor.isShutdown());
    while (!executor.isTerminated()) {
      if (currentPosition != midiView.getCurrentTick()) {
        currentPosition = midiView.getCurrentTick();
        this.move(this.getCurrentTick());
        //System.out.println(currentPosition + "  " + isRunning());
      }
    }
  }

  @Override
  public void scrollHorizontal(int unit) {
    this.midiView.scrollHorizontal(unit);
    this.guiView.scrollVertical(unit);
  }

  @Override
  public void scrollVertical(int unit) {
    this.guiView.scrollVertical(unit);
    this.midiView.scrollVertical(unit);
  }

  @Override
  public boolean isRunning() {
    return midiView.isRunning();
  }

  @Override
  public void update() {
    this.guiView.update();
    this.midiView.update();
  }

  @Override
  public void jumpToBeginning() {
    this.guiView.jumpToBeginning();
    this.midiView.jumpToBeginning();
  }

  @Override
  public void jumpToEnd() {
    this.guiView.jumpToEnd();
    this.midiView.jumpToEnd();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    this.guiView.addKeyListener(listener);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    return;
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
    this.guiView.addMouseListener(mouseListener);
  }

  @Override
  public void removeMouseListener(MouseListener l) {
    return;
  }
}
