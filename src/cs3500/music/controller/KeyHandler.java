package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * For the purposes of handling Key Events.
 */
public class KeyHandler implements KeyListener {

  Map<Integer, Runnable> keysToBeTyped = new HashMap<Integer, Runnable>();
  Map<Integer, Runnable> keysToBePressed = new HashMap<Integer, Runnable>();
  Map<Integer, Runnable> keysToBeReleased = new HashMap<Integer, Runnable>();

  /**
   * Adds a Runnable for the given keyCode when the key is typed.
   * @param keyCode     KeyCode for the key
   * @param runnable    Action when key is hit
   */
  void addKeyTyped(int keyCode, Runnable runnable)  {
    keysToBeTyped.put(keyCode, runnable);
  }

  /**
   * Adds a Runnable for the given keyCode when the key is pressed.
   * @param keyCode     KeyCode for the key
   * @param runnable    Action when key is hit
   */
  void addKeyPressed(int keyCode, Runnable runnable)  {
    keysToBePressed.put(keyCode, runnable);
  }

  /**
   * Adds a Runnable for the given keyCode when the key is released.
   * @param keyCode     KeyCode for the key
   * @param runnable    Action when key is hit
   */
  void addKeyReleased(int keyCode, Runnable runnable)  {
    keysToBeReleased.put(keyCode, runnable);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    try  {
      Objects.requireNonNull(keysToBeTyped.get(e.getKeyCode()));
      keysToBeTyped.get(e.getKeyCode()).run();
      return;
    } catch (Exception ex)  {
      return;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("RAN!!!");
    try  {
      Objects.requireNonNull(keysToBePressed.get(e.getKeyCode()));
      keysToBePressed.get(e.getKeyCode()).run();
      return;
    } catch (Exception ex)  {
      return;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    try  {
      Objects.requireNonNull(keysToBeReleased.get(e.getKeyCode()));
      keysToBeReleased.get(e.getKeyCode()).run();
      return;
    } catch (Exception ex)  {
      return;
    }
  }
}
