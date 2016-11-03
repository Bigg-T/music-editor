package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;

import javax.swing.JPanel;

import cs3500.music.model.ViewMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.util.MusicUtils;

/**
 * A dummy view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {

  ViewMusicEditor musicEditor;

  ConcreteGuiViewPanel(ViewMusicEditor musicEditor)  {
    this.musicEditor = musicEditor;
  }

  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);
    int baseRight = 30;
    int baseDown = 40;
    g.setColor(Color.CYAN);
    for (int i = 0; i <= musicEditor.getLastStartBeat(); i++)  {
      int x = baseRight + (i * 20);
      SortedMap<Integer, List<INote>> notes = musicEditor.getAllNotesAt(i);
      for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++)  {
        int y = baseDown + (j * 20);
        if (notes.get(j).size() == 0)  {
          continue;
        }
        else {
          int currentMax = 0;
          for (INote note : notes.get(j))  {
            if (note.getBeat() > currentMax)  {
              currentMax = note.getBeat();
            }
            g.fillRect(x, y, 20 * currentMax, 20);
          }
        }
      }
    }
    g.setColor(Color.BLACK);
    for (int i = 0; i < musicEditor.getLastBeat(); i += 2)  {
      for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++)  {
        int x = baseRight + (i * 20);
        int y = baseDown + (j * 20);
        g.drawRect(x, y, 40, 20);
      }
    }
    for (int j = musicEditor.getMinPitch(); j <= musicEditor.getMaxPitch(); j++)  {
      g.drawString(MusicUtils.pitchToString(j), 15, 40 + (20 * j));
    }
  }

  /**
   * Gets the preferred height for the editor.
   * @return preferred height
   */
  int getEditorHeight()  {
    // Base height for the display. 40 on top, 40 on bottom.
    int baseHeight = 80;
    // 20 pixels per pitch to be represented
    return baseHeight + ((musicEditor.getMaxPitch() - musicEditor.getMinPitch()) * 20);
  }

  /**
   * Gets the preferred width for the editor.
   * @return preferred width
   */
  int getEditorWidth()  {
    // Base width for the display. 30 on left, 30 on right.
    int baseWidth = 60;
    // 20 pixels per beat
    return baseWidth + (musicEditor.getLastBeat() * 20);
  }

}
