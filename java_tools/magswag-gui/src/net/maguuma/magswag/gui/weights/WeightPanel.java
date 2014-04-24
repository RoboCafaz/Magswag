package net.maguuma.magswag.gui.weights;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WeightPanel extends JPanel {
  public WeightPanel() {
    initialize();
    performLayout();
  }

  private void initialize() {

  }

  private void performLayout() {
    this.setBorder(BorderFactory.createTitledBorder("Stat Weighting"));
  }
}
