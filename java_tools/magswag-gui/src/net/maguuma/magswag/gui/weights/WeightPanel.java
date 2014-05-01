package net.maguuma.magswag.gui.weights;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import net.maguuma.magswag.common.constants.Stat;

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
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    for (Stat stat : Stat.values()) {
      this.add(new WeightInput(stat));
    }
  }
}
