package cs3500.music.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * For the purposes of tracking repeat commands.
 */
public class RepeatTracker {

  private int start;

  private List<Integer> ends;

  private int currentEnd;

  private boolean startEdit;

  private boolean endEdit;

  public RepeatTracker() {
    this.ends = new ArrayList<Integer>();
    this.start = 0;
    this.currentEnd = 0;
    this.endEdit = false;
    this.startEdit = false;
  }

  /**
   * Sets the editing preferences.
   * @param toEdit What is to be edited
   */
  public void setEdit(String toEdit)  {
    System.out.println("edit attempt ");
    this.startEdit = false;
    this.endEdit = false;
    switch (toEdit)  {
      case "start":
        this.startEdit = true;
        this.endEdit = false;
        break;
      case "end":
        this.endEdit = true;
        this.startEdit = false;
        break;
      default:
        break;
    }
  }

  public void editAddOn(int i)  {
    if (i < 0 || i > 9)  {
      return;
    }
    else if (startEdit)  {
      start *= 10;
      start += i;
    }
    else if (endEdit)  {
      currentEnd *= 10;
      currentEnd += i;
    }
    else  {
      return;
    }
  }

  public void addCurrentEnd()  {
    this.ends.add(this.currentEnd);
    Collections.sort(this.ends);
    this.currentEnd = 0;
  }

  public int getStart()  {
    return start;
  }

  public List<Integer> getEnds()  {
    return ends;
  }

  public void reset()  {
    this.ends = new ArrayList<Integer>();
    this.start = 0;
    this.currentEnd = 0;
    this.endEdit = false;
    this.startEdit = false;
  }
}
