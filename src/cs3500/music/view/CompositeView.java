package cs3500.music.view;

import cs3500.music.model.INote;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tiger on 11/17/16.
 */
public class CompositeView implements IView {
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
        iView2.move(iView1.getCurrentTick());
        iView1.move(iView2.getCurrentTick());
        //System.out.println(currentPosition + "  " + executor.toString());
      }
      if (iView1.getCurrentTick() == 30) {
        iView1.resume();
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

  }

  @Override
  public void update() {
    this.iView1.update();
    this.iView2.update();
  }

}
