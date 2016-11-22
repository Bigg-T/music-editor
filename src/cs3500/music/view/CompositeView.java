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
  private final IView iView1;
  private final IView iView2;

  /**
   * Constructing composite view with Midi and Gui view.
   *
   * @param iView1 Midi view.
   * @param iView2 Gui View
   */
  CompositeView(IView iView1, IView iView2) {
    this.iView1 = iView1;
    this.iView2 = iView2;
  }

  @Override
  public void initialize() throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    Runnable v1 = () -> this.createRunnable(iView1);
    Runnable v2 = () -> this.createRunnable(iView2);

    executor.submit(v1);
    executor.submit(v2);

    long currentPosition = -1;
    while (!executor.isTerminated()) {
      if (currentPosition != iView1.getCurrentTick()) {
        currentPosition = iView1.getCurrentTick();
        //this.move(this.getCurrentTick());
        //System.out.println(currentPosition + "  " + executor.toString());
      }
      if (iView1.getCurrentTick() == 30) {
        //this.jumpToBeginning();
      }
    }

    executor.shutdownNow();
  }

  /**
   * Create a runnable that initialize the view.
   *
   * @param view the Iview.
   */
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
    iView2.move(iView1.getCurrentTick());
    iView1.move(iView2.getCurrentTick());
  }

  @Override
  public void pause() {
    iView2.pause();
    iView1.pause();
  }

  @Override
  public void resume() {
    iView1.resume();
    iView2.resume();
  }

  @Override
  public void scrollHorizontal(int unit) {
    this.iView2.scrollHorizontal(unit);
    this.iView1.scrollVertical(unit);
  }

  @Override
  public void scrollVertical(int unit) {
    this.iView1.scrollVertical(unit);
    this.iView2.scrollVertical(unit);
  }

  @Override
  public void update() {
    this.iView1.update();
    this.iView2.update();
  }

  @Override
  public void jumpToBeginning() {
    this.iView1.jumpToBeginning();
    this.iView2.jumpToBeginning();
  }

  @Override
  public void jumpToEnd() {
    this.iView1.jumpToEnd();
    this.iView2.jumpToEnd();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    return;
  }

  @Override
  public void addActionListener(ActionListener listener) {
    return;
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
    return;
  }
}
