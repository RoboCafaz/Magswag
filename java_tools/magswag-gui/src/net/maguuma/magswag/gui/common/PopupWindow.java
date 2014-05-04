package net.maguuma.magswag.gui.common;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;

import net.maguuma.magswag.common.logging.Logger;

@SuppressWarnings("serial")
public class PopupWindow extends JDialog {
  public PopupWindow() {
    initialize();
    performLayout();
  }

  private void initialize() {
    addFocusListener(createFocusListener());
  }

  private void performLayout() {
    setUndecorated(true);
    ((JPanel) getContentPane()).setBorder(BorderFactory.createEtchedBorder());
  }

  public void showPopup(Point point) {
    showPopup((int) point.getX(), (int) point.getY());
  }

  public void showPopup(int x, int y) {
    pack();
    this.setLocation(x, y);
    setVisible(true);
  }

  public void showPopup(Component component) {
    this.showPopup(component.getLocationOnScreen());
  }

  public void showPopup(Component component, int xOffset, int yOffset) {
    Point point = component.getLocationOnScreen();
    this.showPopup((int) (point.getX() + xOffset), (int) (point.getY() + yOffset));
  }

  protected FocusListener createFocusListener() {
    return new FocusListener() {
      @Override
      public void focusGained(FocusEvent paramFocusEvent) {
        Logger.log("Focus gained.");
      }

      @Override
      public void focusLost(FocusEvent paramFocusEvent) {
        Logger.log("Focus lost.");
        PopupWindow.this.setVisible(false);
      }
    };
  };
}
