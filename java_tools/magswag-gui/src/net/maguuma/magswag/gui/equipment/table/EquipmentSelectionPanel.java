package net.maguuma.magswag.gui.equipment.table;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableRowSorter;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.gui.equipment.table.controller.EquipmentSelectionController;
import net.maguuma.magswag.gui.equipment.table.controller.EquipmentSelectionListener;

@SuppressWarnings("serial")
public class EquipmentSelectionPanel extends JPanel implements EquipmentSelectionListener {
  private JTable table;

  public EquipmentSelectionPanel() {
    initialize();
    performLayout();
    EquipmentSelectionController.addEquipmentSelectionListener(this);
  }

  private void initialize() {
    this.table = new JTable();
  }

  private void performLayout() {
    this.setLayout(new BorderLayout());
    JScrollPane scroll = new JScrollPane(this.table);
    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    this.add(scroll, BorderLayout.CENTER);
  }

  @Override
  public void equipmentSelectionChanged(Slot slot, GearType gearType, Rarity rarity) {
    this.setBorder(BorderFactory.createTitledBorder(gearType.getEquipmentType().name() + " Equipment List"));
    EquipmentModel model = new EquipmentModel(gearType, rarity);
    this.table.setModel(model);
    this.table.setRowSorter(new TableRowSorter<EquipmentModel>(model));
  }
}