package cs3500.music.controller;

/**
 * For the purposes of tracking repeat commands.
 */
public class RepeatTracker {

  private int start;

  private int end;

  private int skipAt;

  private boolean startEdit;

  private boolean endEdit;

  private boolean skipEdit;

  public RepeatTracker() {
    this.end = 0;
    this.start = 0;
    this.skipAt = 0;
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
      end *= 10;
      end += i;
    }
    else if (skipEdit)  {
      skipAt *= 10;
      skipAt += i;
    }
    else  {
      return;
    }
  }

  public int getStart()  {
    return start;
  }

  public int getEnd()  {
    return end;
  }

  public int getSkipAt()  {
    return skipAt;
  }


}
