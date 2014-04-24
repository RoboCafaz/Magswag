package net.maguuma.magswag.gui.equipment;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import net.maguuma.magswag.common.constants.Slot;

@SuppressWarnings("serial")
public class EquipmentPanel extends JPanel {
  public EquipmentPanel() {
    initialize();
    performLayout();
  }

  private void initialize() {

  }

  private void performLayout() {
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.setBorder(BorderFactory.createTitledBorder("Equipment"));
    for (Slot slot : Slot.values()) {
      this.add(new SlotPanel(slot));
    }
  }
}
