package net.maguuma.magswag.gui.equipment;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import net.maguuma.magswag.common.constants.EquipmentType;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.items.StatType;

@SuppressWarnings("serial")
public class SlotPanel extends JPanel {
  private final Slot slot;

  private JCheckBox ascended;
  private JComboBox<EquipmentType> typeSelection;
  private JComboBox<StatType> statSelection;

  public SlotPanel(Slot slot) {
    this.slot = slot;
    initialize();
    performLayout();
  }

  private void initialize() {
    this.ascended = new JCheckBox("Ascended");
    this.typeSelection = new JComboBox<EquipmentType>(this.slot.getTypes());
    this.statSelection = new JComboBox<StatType>(DataHandler.STAT_TYPES.getData().toArray(new StatType[] {}));

  }

  private void performLayout() {
    this.setBorder(BorderFactory.createTitledBorder(this.slot.name()));
    this.add(this.ascended);
    this.add(this.typeSelection);
    this.add(this.statSelection);
  }
}
