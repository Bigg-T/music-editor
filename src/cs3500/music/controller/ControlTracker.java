package cs3500.music.controller;

/**
 * For the purposes of tracking commands in progress.
 * Serves to store information about the note being acted upon.
 * CLASS INVARIANT: Only one of editPitch, editStartBeat, and editDuration can be true at
 *   any given time.
 */
public class ControlTracker {

  /**
   * The pitch currently being tracked.
   */
  private int pitch;

  /**
   * The start beat currently being tracked.
   */
  private int startBeat;

  /**
   * The duration currently being tracked.
   */
  private int duration;

  /**
   * The volume currently being tracked.
   */
  private int volume;

  /**
   * The channel currently being tracked.
   */
  private int channel;

  /**
   * States whether the pitch is being edited.
   */
  private boolean editPitch;

  /**
   * States whether the start beat is being edited.
   */
  private boolean editStartBeat;

  /**
   * States whether the duration is being edited.
   */
  private boolean editDuration;

  /**
   * States whether the volume is being edited.
   */
  private boolean editVolume;

  /**
   * States whether the channel is being edited.
   */
  private boolean editChannel;

  /**
   * For the purposes of construction a ControlTracker.
   */
  ControlTracker()  {
    this.pitch = -1;
    this.startBeat = -1;
    this.duration = -1;
    this.channel = -1;
    this.volume = -1;
    this.editPitch = false;
    this.editStartBeat = false;
    this.editDuration = false;
    this.editChannel = false;
    this.editVolume = false;
  }

  /**
   * Sets edit control to either pitch, duration, or start beat.
   *   Enforces class invariant.
   * @param toEdit Which aspect to put into editing mode.
   */
  public void setControl(String toEdit)  {
    switch (toEdit)  {
      case "pitch":  {
        this.editPitch = true;
        this.editDuration = false;
        this.editStartBeat = false;
        this.editChannel = false;
        this.editVolume = false;
        return;
      }
      case "duration":  {
        this.editPitch = false;
        this.editDuration = true;
        this.editStartBeat = false;
        this.editChannel = false;
        this.editVolume = false;
        return;
      }
      case "start":  {
        this.editPitch = false;
        this.editDuration = false;
        this.editStartBeat = true;
        this.editChannel = false;
        this.editVolume = false;
        return;
      }
      case "channel":  {
        this.editPitch = false;
        this.editDuration = false;
        this.editStartBeat = false;
        this.editChannel = true;
        this.editVolume = false;
        return;
      }
      case "volume":  {
        this.editPitch = false;
        this.editDuration = false;
        this.editStartBeat = false;
        this.editChannel = false;
        this.editVolume = true;
        return;
      }
      case "none":  {
        this.editPitch = false;
        this.editDuration = false;
        this.editStartBeat = false;
        this.editChannel = false;
        this.editVolume = false;
        return;
      }
      default:
        return;
    }
  }

  /**
   * Edits whichever pitch is currently to be edited by adding the given int to the end.
   *   To be used for keyboard events limited to one digit.
   * @param i number to be added on
   */
  public void editAddOn(int i)  {
    if (i < 0 || i > 9)  {
      return;
    }
    else if (this.editPitch)  {
      if (this.pitch != -1) {
        this.pitch *= 10;
        this.pitch += i;
      }
      else  { this.pitch = i; }
      return;
    }
    else if (this.editDuration)  {
      if (this.duration != -1) {
        this.duration *= 10;
        this.duration += i;
      }
      else  { this.duration = i; }
      return;
    }
    else if (this.editStartBeat)  {
      if (this.startBeat != -1) {
        this.startBeat *= 10;
        this.startBeat += i;
      }
      else  { this.startBeat = i; }
      return;
    }
    else if (this.editChannel)  {
      if (this.channel != -1) {
        this.channel *= 10;
        this.channel += i;
      }
      else  { this.channel = i; }
      return;
    }
    else if (this.editVolume)  {
      if (this.volume != -1) {
        this.volume *= 10;
        this.volume += i;
      }
      else  { this.volume = i; }
      return;
    }
    else  {
      return;
    }
  }

  /**
   * Edits whichever pitch is currently to be edited by makingit the given int.
   * @param i number to set to
   */
  public void editEntire(int i)  {
    if (i < 0 || i > 9)  {
      return;
    }
    else if (this.editPitch)  {
      this.pitch = i;
      return;
    }
    else if (this.editDuration)  {
      this.duration = i;
      return;
    }
    else if (this.editStartBeat)  {
      this.startBeat = i;
      return;
    }
    else  {
      return;
    }
  }

  /**
   * Resets the state to its default parameters.
   */
  public void reset()  {
    this.pitch = -1;
    this.startBeat = -1;
    this.duration = -1;
    this.channel = -1;
    this.volume = -1;
    this.editPitch = false;
    this.editStartBeat = false;
    this.editDuration = false;
    this.editChannel = false;
    this.editVolume = false;
  }


  /**
   * To get the current pitch.
   * @throws IllegalStateException if pitch has not been set
   * @return current pitch
   */
  public int getPitch() throws IllegalStateException {
    if (this.pitch == -1)  {
      throw new IllegalStateException("Pitch has not been set");
    }
    return this.pitch;
  }

  /**
   * To get the current beat.
   * @throws IllegalStateException if beat has not been set
   * @return current beat
   */
  public int getStartBeat() throws IllegalStateException {
    if (this.startBeat == -1)  {
      throw new IllegalStateException("Start beat has not been set");
    }
    return this.startBeat;
  }

  /**
   * To get the current volume.
   * @throws IllegalStateException if volume has not been set
   * @return current volume
   */
  public int getDuration() throws IllegalStateException {
    if (this.duration == -1)  {
      throw new IllegalStateException("Duration has not been set");
    }
    return this.duration;
  }

  /**
   * To get the current volume.
   * @throws IllegalStateException if volume has not been set
   * @return current volume
   */
  public int getVolume() throws IllegalStateException {
    if (this.volume == -1)  {
      throw new IllegalStateException("Volume has not been set");
    }
    return this.volume;
  }

  /**
   * To get the current channel.
   * @throws IllegalStateException if channel has not been set
   * @return current channel
   */
  public int getChannel() throws IllegalStateException {
    if (this.channel == -1)  {
      throw new IllegalStateException("Channel has not been set");
    }
    return this.channel;
  }
}
