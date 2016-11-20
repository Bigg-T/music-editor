package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Created by tiger on 11/19/16.
 */
public interface IGuiView extends IView {
  /**
   * this is to force the view to have a method to set up the keyboard. The name has been chosen
   * deliberately. This is the same method signature to add a key listener in Java Swing.
   *
   * Thus our Swing-based implementation of this interface will already have such a method.
   */
  void addKeyListener(KeyListener listener);

  void addActionListener(ActionListener listener);
}
