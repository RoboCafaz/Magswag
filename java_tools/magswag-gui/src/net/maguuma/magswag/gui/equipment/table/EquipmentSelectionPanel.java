package net.maguuma.magswag.gui.equipment.table;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import net.maguuma.magswag.calculator.controller.EquipmentController;
import net.maguuma.magswag.calculator.controller.WeightController;
import net.maguuma.magswag.calculator.controller.listener.WeightChangeListener;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.gui.equipment.table.controller.EquipmentSelectionController;
import net.maguuma.magswag.gui.equipment.table.controller.EquipmentSelectionListener;

@SuppressWarnings("serial")
public class EquipmentSelectionPanel extends JPanel implements EquipmentSelectionListener, WeightChangeListener {
  private Slot currentSlot;
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

    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.getSelectionModel().addListSelectionListener(createListSelectionListener());
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
	  table.getSelectionModel().clearSelection();
    this.table.repaint();
    this.sorter.sort();
  }
  
  protected ListSelectionListener createListSelectionListener(){
	  return new ListSelectionListener(){
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int row = table.getSelectedRow();
			if(row != -1){
				row = sorter.convertRowIndexToModel(row);
				Equipment equip = model.getRowValue(row);
				EquipmentController.setGear(currentSlot, equip);
			}
		}
	  };
  }

  @Override
  public void equipmentSelectionChanged(Slot slot, GearType gearType, Rarity rarity) {
	currentSlot = slot;
    this.setBorder(BorderFactory.createTitledBorder(gearType.getEquipmentType().name() + " Equipment List"));
    this.model.setGear(gearType, rarity);
    updateTable();
  }

  @Override
  public void weightChanged(Stat stat, int value) {
    updateTable();
  }
}