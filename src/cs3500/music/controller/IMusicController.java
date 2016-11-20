package cs3500.music.controller;

import cs3500.music.model.IBasicMusicEditor;
import cs3500.music.model.INote;
import cs3500.music.view.IView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tiger on 11/6/16.
 */
public class IMusicController implements ActionListener {
  private IBasicMusicEditor<INote> model;
  private IView view;

  IMusicController(IBasicMusicEditor<INote> model, IView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
