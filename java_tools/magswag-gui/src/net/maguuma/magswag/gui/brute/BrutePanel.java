package net.maguuma.magswag.gui.brute;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.maguuma.magswag.calculator.controller.CharacterController;
import net.maguuma.magswag.gui.brute.table.CharacterViewerPanel;

@SuppressWarnings("serial")
public class BrutePanel extends JPanel {
  private JButton goButton;
  private CharacterViewerPanel charPanel;

  public BrutePanel() {
    initialize();
    performLayout();
  }

  private void initialize() {
    goButton = new JButton("BRUTE FORCE");
    goButton.addActionListener(createActionListener());
    charPanel = new CharacterViewerPanel();
  }

  private void performLayout() {
    setLayout(new BorderLayout());
    this.add(goButton, BorderLayout.NORTH);
    this.add(charPanel, BorderLayout.CENTER);
  }

  protected ActionListener createActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        CharacterController.calculateCharacters();
        charPanel.updateTableModel();
      }
    };
  }
}
