package net.maguuma.magswag.gui.equipment.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import net.maguuma.magswag.calculator.controller.ProfessionController;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.common.datatypes.items.StatType;

@SuppressWarnings("serial")
public class EquipmentModel extends DefaultTableModel {
  private final GearType gearType;
  private final Rarity rarity;

  private final List<Equipment> equipment;

  public EquipmentModel(GearType gearType, Rarity rarity) {
    this.equipment = new ArrayList<Equipment>();
    this.gearType = gearType;
    this.rarity = rarity;

    initialize();
  }

  private void initialize() {
    for (StatType statType : DataHandler.STAT_TYPES.getData()) {
      this.equipment.add(new Equipment(this.gearType, ProfessionController.getProfession().getArmor(), statType, this.rarity));
    }
  }

  @Override
  public int getColumnCount() {
    return 1 + Stat.values().length;
  }

  @Override
  public Class<?> getColumnClass(int col) {
    return String.class;
  }

  @Override
  public String getColumnName(int col) {
    switch (col) {
    case 0:
      return "Type";
    default:
      return Stat.values()[col - 1].name();
    }
  }

  @Override
  public int getRowCount() {
    if (this.equipment == null) {
      return 0;
    }
    return this.equipment.size();
  }

  @Override
  public Object getValueAt(int row, int col) {
    Equipment equip = getRowValue(row);
    switch (col) {
    case 0:
      return equip.getStatType().getName();
    default:
      return equip.getStats().get(Stat.values()[col - 1]);
    }
  }

  @Override
  public boolean isCellEditable(int row, int col) {
    return false;
  }

  public Equipment getRowValue(int row) {
    return this.equipment.get(row);
  }
}
