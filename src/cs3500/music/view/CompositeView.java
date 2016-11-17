package cs3500.music.view;

import cs3500.music.model.INote;

/**
 * Created by tiger on 11/17/16.
 */
public class CompositeView implements IView {
  private final IView iView1;
  private final IView iView2;
  public CompositeView(IView iView1, IView iView2) {
    this.iView1 = iView1;
    this.iView2 = iView2;
  }


  @Override
  public void initialize() throws Exception {
    iView1.initialize();
    iView2.initialize();
  }

  @Override
  public long getCurrentTick() {
    return 0;
  }
}
