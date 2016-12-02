package cs3500.music.provider;


import cs3500.music.model.ReadOnlyMusicModel;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * A Graphical user interface view of a piece of music.
 */
public class GuiViewImpl extends JFrame implements GuiPlayerView {

  private final JPanel displayPanel;
  public static final int ROW_HEIGHT = 25;
  public static final int COLUMN_WIDTH = 25;
  private ReadOnlyMusicModel model;
  private MidiView midiView;
  private ScrollHandler scrollHandler = new GuiScrollHandler();
  private MouseListener mouseListener;

  /**
   * Pitch Panel displaying relevant range of pitches.
   */
  private PitchPanel pitches;

  /**
   * note panel displaying notes in the track.
   */
  private NotesAndLinePanel notes;

  /**
   * allows note panel to be scrollable.
   */
  private JScrollPane scrollPane;

  /**
   * Creates new GuiView.
   */
  public GuiViewImpl(Sequencer s) {
    this.displayPanel = new JPanel(new BorderLayout());
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);
    this.midiView = new MidiView(s);
  }

  /**
   * Creates new GuiView.
   */
  public GuiViewImpl() throws MidiUnavailableException {
    this(MidiSystem.getSequencer());
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(800, 800);
  }

  @Override
  public void display() {
    if (this.model == null) {
      throw new IllegalStateException("Model not yet set.");
    }
    this.resetFocus();
    this.midiView.display();
    this.pitches = new PitchPanel(this.model);
    this.notes = new NotesAndLinePanel(this.model, this, scrollHandler);
    if (this.mouseListener != null) {
      this.notes.addMouseListener(this.mouseListener);
    }
    scrollPane = new JScrollPane(notes);
    scrollPane.setWheelScrollingEnabled(true);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    JViewport port = new JViewport();
    port.setView(pitches);
    scrollPane.setRowHeader(port);
    displayPanel.add(scrollPane, BorderLayout.CENTER);
    notes.beginLinePainting();
    this.setVisible(true);
    this.pack();
  }

  /**
   * Resets the the focuses in this view to this view.
   */
  private void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void exit() {
    this.pitches.setVisible(false);
    this.notes.setVisible(false);
    this.scrollPane.setVisible(false);
    midiView.exit();
  }

  @Override
  public void setReadOnlyModel(ReadOnlyMusicModel readOnlyModel) {
    this.model = readOnlyModel;
    midiView.setReadOnlyModel(readOnlyModel);
  }

  @Override
  public ScrollHandler getScrollHandler() {
    return this.scrollHandler;
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
    this.mouseListener = mouseListener;
    if (this.notes != null) {
      this.notes.addMouseListener(mouseListener);
    }
  }

  @Override
  public void removeMouseListener(MouseListener mouseListener) {
    if (this.notes != null) {
      this.notes.removeMouseListener(mouseListener);
    }
  }

  @Override
  public int noteBoundingWidth() {
    return this.COLUMN_WIDTH;
  }

  @Override
  public int noteBoundingHeight() {
    return this.ROW_HEIGHT;
  }

  @Override
  public void refresh() {
    this.repaint();
    this.midiView.refresh();
  }

  @Override
  public void play() {
    this.midiView.play();
  }

  @Override
  public void pause() {
    this.midiView.pause();
  }

  @Override
  public void reset() {
    this.midiView.reset();
    scrollHandler.toggleAutoScrolling();
    scrollHandler.toggleAutoScrolling();
  }

  @Override
  public void skipToEnd() {
    this.midiView.skipToEnd();
  }

  @Override
  public boolean isPlaying() {
    return this.midiView.isPlaying();
  }

  @Override
  public float getCurrentBeat() {
    return this.midiView.getCurrentBeat();
  }

  @Override
  public void togglePlayback() {
    this.midiView.togglePlayback();
  }

  /**
   * A Private inner class for a ScrollHandler that keeps track of how this view should handle
   * scrolling.
   */
  private class GuiScrollHandler implements ScrollHandler {

    // Whether or not autoscrolling is enabled.
    private boolean autoScrollingEnabled = true;

    @Override
    public void autoScrollToX(int x) {
      if (autoScrollingEnabled) {
        scrollToX(x);
      }
    }

    /**
     * Scrolls to the given X value.
     * @param x The X value to be scrolled to.
     */
    private void scrollToX(int x) {
      scrollPane.getHorizontalScrollBar().setValue(x);
    }

    @Override
    public void scrollRight() {
      if (!autoScrollingEnabled) {
        this.scrollToX(scrollPane.getHorizontalScrollBar().getValue() + COLUMN_WIDTH);
      }
    }

    @Override
    public void scrollLeft() {
      if (!autoScrollingEnabled) {
        this.scrollToX(scrollPane.getHorizontalScrollBar().getValue() - COLUMN_WIDTH);
      }
    }

    @Override
    public void toggleAutoScrolling() {
      this.autoScrollingEnabled = !autoScrollingEnabled;
      if (autoScrollingEnabled) {
        scrollToX((int) (getCurrentBeat() * COLUMN_WIDTH));
      }
    }
  }
}