package cs3500.music.view;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tiger on 11/17/16.
 */
public class CompositeView implements IGuiView {
  private final IMidiView iView1;
  private final IView iView2;

  CompositeView(IMidiView iView1, IView iView2) {
    this.iView1 = iView1;
    this.iView2 = iView2;
  }

  @Override
  public void initialize() throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    Runnable v1 = () -> {this.createRunnable(iView1);};
    Runnable v2 = () -> {this.createRunnable(iView2);};

    executor.submit(v1);
    executor.submit(v2);

    long currentPosition = -1;
    while (!executor.isTerminated()) {
      if (currentPosition != iView1.getCurrentTick()) {
        currentPosition = iView1.getCurrentTick();
        this.move(this.getCurrentTick());
        //System.out.println(currentPosition + "  " + executor.toString());
      }
      if (iView1.getCurrentTick() == 30) {
        //this.jumpToBeginning();
      }
    }

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
    iView2.move(iView1.getCurrentTick());
    iView1.move(iView2.getCurrentTick());
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void update() {
    this.iView1.update();
    this.iView2.update();
  }

  @Override
  public IBasicMusicEditor<INote> getMusicEditor() {
    return iView2.getMusicEditor();
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
