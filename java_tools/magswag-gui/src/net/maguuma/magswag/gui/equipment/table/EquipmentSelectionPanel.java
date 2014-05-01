package net.maguuma.magswag.gui.equipment.table;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableRowSorter;
import net.maguuma.magswag.calculator.controller.WeightController;
import net.maguuma.magswag.calculator.controller.listener.WeightChangeListener;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.gui.equipment.table.controller.EquipmentSelectionController;
import net.maguuma.magswag.gui.equipment.table.controller.EquipmentSelectionListener;

@SuppressWarnings("serial")
public class EquipmentSelectionPanel extends JPanel implements EquipmentSelectionListener, WeightChangeListener {
  private JTable table;
  private EquipmentSelectionModel model;
  private TableRowSorter<EquipmentSelectionModel> sorter;

  public EquipmentSelectionPanel() {
    initialize();
    performLayout();
  }

  private void initialize() {
    this.table = new JTable();
    this.model = new EquipmentSelectionModel();
    this.sorter = new TableRowSorter<EquipmentSelectionModel>(this.model);
    this.table.setModel(this.model);
    this.table.setRowSorter(this.sorter);

    EquipmentSelectionController.addEquipmentSelectionListener(this);
    WeightController.addWeightChangeListener(this);
  }

  private void performLayout() {
    this.setLayout(new BorderLayout());
    JScrollPane scroll = new JScrollPane(this.table);
    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    this.add(scroll, BorderLayout.CENTER);
  }

  protected void updateTable() {
    this.table.repaint();
    this.sorter.sort();
  }

  @Override
  public void equipmentSelectionChanged(Slot slot, GearType gearType, Rarity rarity) {
    this.setBorder(BorderFactory.createTitledBorder(gearType.getEquipmentType().name() + " Equipment List"));
    this.model.setGear(gearType, rarity);
    updateTable();
  }

  @Override
  public void weightChanged(Stat stat, int value) {
    updateTable();
  }
}