package net.maguuma.magswag.gui.runner;

import javax.swing.JFrame;

import net.maguuma.magswag.gui.SwagInterface;

public class Runner {
  public static void main(String... args) {
    JFrame frame = new JFrame();
    frame.setTitle("#magswag Character Planning Tool");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new SwagInterface());
    frame.pack();
    frame.setVisible(true);
    frame.setMinimumSize(frame.getPreferredSize());
  }
}