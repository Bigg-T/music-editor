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
import cs3500.music.model.NoteBuilder;
import cs3500.music.model.NoteName;
import cs3500.music.model.ViewMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.MusicUtils;

/**
 * A dummy view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {

  public static final int NOTEWIDTH = 18;
  public static final int NOTEHEIGHT = 18;
  public static final int VDISPLACEMENT = 80;
  public static final int HDISPLACEMENT = 100;

  IBasicMusicEditor<INote> musicEditor;

  ConcreteGuiViewPanel(IBasicMusicEditor<INote> musicEditor) {
    this.musicEditor = musicEditor;
    int width = (NOTEWIDTH * (musicEditor.getMaxPitch() - musicEditor.getMinPitch())) + 80;
    int height = (NOTEHEIGHT * musicEditor.getLastBeat()) + 100;
    this.setPreferredSize(new Dimension(height, width));

  }

  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);
    int baseRight = 50;
    int baseDown = 40;
    g.setColor(Color.BLACK);
    for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++) {
      g.drawString(MusicUtils.pitchToString(j), 15, 55 +
              (NOTEHEIGHT * (musicEditor.getMaxPitch() - j)));
    }
    for (int i = 0; i < musicEditor.getLastBeat(); i += 2) {
      for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++) {
        int x = baseRight + (i * NOTEWIDTH);
        int y = baseDown + ((musicEditor.getMaxPitch() - j) * NOTEHEIGHT);
        g.drawRect(x, y, NOTEWIDTH * 2, NOTEHEIGHT);
      }
    }
    g.setColor(Color.BLUE);
    for (int i = 0; i <= musicEditor.getLastStartBeat(); i++) {
      int x = baseRight + (i * NOTEWIDTH);
      SortedMap<Integer, List<INote>> notes = new TreeMap();
      try {
        notes = musicEditor.getAllNotesAt(i);
      } catch (NullPointerException npe) {
        continue;
      }
      for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++) {
        int y = baseDown + ((musicEditor.getMaxPitch() - j) * NOTEHEIGHT);
        if (notes.get(j) == null) {
          continue;
        } else {
          int currentMax = 0;
          for (INote note : notes.get(j)) {
            if (note.getBeat() > currentMax) {
              currentMax = note.getBeat();
            }
            g.fillRect(x, y, (NOTEWIDTH * currentMax) - 1, NOTEHEIGHT);
            g.setColor(Color.BLACK);
            g.fillRect(x, y, NOTEWIDTH / 2, NOTEHEIGHT);
            g.setColor(Color.BLUE);
          }
        }
      }
    }
  }

}
