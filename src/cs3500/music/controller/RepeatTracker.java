package cs3500.music.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * For the purposes of tracking repeat commands.
 */
public class RepeatTracker {

  private int start;

  private List<Integer> ends;

  private int skipAt;

  private int currentEnd;

  private boolean startEdit;

  private boolean endEdit;

  private boolean skipEdit;

  public RepeatTracker() {
    this.ends = new ArrayList<Integer>();
    this.start = 0;
    this.skipAt = 0;
    this.currentEnd = 0;
    this.endEdit = false;
    this.startEdit = false;
    this.skipEdit = false;
  }

  /**
   * Sets the editing preferences.
   * @param toEdit What is to be edited
   */
  public void setEdit(String toEdit)  {
    this.startEdit = false;
    this.endEdit = false;
    this.skipEdit = false;
    switch (toEdit)  {
      case "start":
        this.startEdit = true;
        break;
      case "end":
        this.endEdit = true;
        break;
      case "skip":
        this.skipEdit = true;
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

  }

  public int getStart()  {
    return start;
  }

  public List<Integer> getEnds()  {
    return ends;
  }

  public int getSkipAt()  {
    return skipAt;
  }


}
