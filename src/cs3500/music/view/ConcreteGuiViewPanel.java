package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JPanel;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.ViewMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.MusicUtils;

/**
 * A dummy view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {

  public static final int noteWidth = 18;
  public static final int noteHeight = 18;

  IBasicMusicEditor<INote> musicEditor;

  ConcreteGuiViewPanel(IBasicMusicEditor<INote> musicEditor) {
    this.musicEditor = musicEditor;
    int width = (noteWidth * (musicEditor.getMaxPitch() - musicEditor.getMinPitch())) + 100;
    int height = (noteHeight * musicEditor.getLastBeat()) + 80;
    this.setPreferredSize(new Dimension(height, width));
  }

  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);
    int baseRight = 50;
    int baseDown = 40;
    g.setColor(Color.BLUE);
    for (int i = 0; i <= musicEditor.getLastStartBeat(); i++) {
      int x = baseRight + (i * noteWidth);
      SortedMap<Integer, List<INote>> notes = new TreeMap();
      try {
        notes = musicEditor.getAllNotesAt(i);
      } catch (NullPointerException npe) {
        continue;
      }
      for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++) {
        int y = baseDown + ((musicEditor.getMaxPitch() - j) * noteHeight);
        if (notes.get(j) == null) {
          continue;
        } else {
          int currentMax = 0;
          for (INote note : notes.get(j)) {
            if (note.getBeat() > currentMax) {
              currentMax = note.getBeat();
            }
            g.fillRect(x, y, (noteWidth * currentMax) - 1, noteHeight);
            g.setColor(Color.BLACK);
            g.fillRect(x, y, noteWidth / 2, noteHeight);
            g.setColor(Color.BLUE);
          }
        }
      }
    }
    g.setColor(Color.BLACK);
    for (int i = 0; i < musicEditor.getLastBeat(); i += 2) {
      for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++) {
        int x = baseRight + (i * noteWidth);
        int y = baseDown + ((musicEditor.getMaxPitch() - j) * noteHeight);
        g.drawRect(x, y, noteWidth * 2, noteHeight);
      }
    }
    for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++) {
      g.drawString(MusicUtils.pitchToString(j), 15, 55 +
              (noteHeight * (musicEditor.getMaxPitch() - j)));
    }
  }

}
