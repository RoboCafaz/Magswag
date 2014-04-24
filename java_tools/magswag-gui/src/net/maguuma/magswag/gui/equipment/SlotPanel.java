package net.maguuma.magswag.gui.equipment;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import net.maguuma.magswag.common.constants.Slot;

@SuppressWarnings("serial")
public class SlotPanel extends JPanel {
  private final Slot slot;

  public SlotPanel(Slot slot) {
    this.slot = slot;
    initialize();
    performLayout();
  }

  private void initialize() {

  }

  private void performLayout() {
    this.setBorder(BorderFactory.createTitledBorder(this.slot.name()));
  }
}
