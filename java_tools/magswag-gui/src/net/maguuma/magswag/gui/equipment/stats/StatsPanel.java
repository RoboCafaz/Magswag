package net.maguuma.magswag.gui.equipment.stats;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import net.maguuma.magswag.common.constants.Stat;

@SuppressWarnings("serial")
public class StatsPanel extends JPanel {
  public StatsPanel() {
    initialize();
    performLayout();
  }

  private void initialize() {

  }

  private void performLayout() {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    for (Stat stat : Stat.values()) {
      this.add(new StatPane(stat));
    }
  }
}
