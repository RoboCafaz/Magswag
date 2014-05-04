package net.maguuma.magswag.gui.equipment.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import net.maguuma.magswag.calculator.WeightCalculator;
import net.maguuma.magswag.calculator.controller.CharacterController;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.common.datatypes.items.StatType;

@SuppressWarnings("serial")
public class EquipmentSelectionModel extends DefaultTableModel {
  private final List<Equipment> equipment;

  public EquipmentSelectionModel() {
    equipment = new ArrayList<Equipment>();
  }

  public void setGear(GearType gearType, Rarity rarity) {
    equipment.clear();
    for (StatType statType : DataHandler.STAT_TYPES.getData()) {
      if (statType.applicable(gearType.getEquipmentType())) {
        equipment.add(new Equipment(gearType, CharacterController.getCharacter().getProfessionModel().getProfession().getArmor(), statType, rarity));
      }
    }
  }

  @Override
  public int getColumnCount() {
    return 2 + Stat.values().length;
  }

  @Override
  public String getColumnName(int col) {
    switch (col) {
    case 0:
      return "Type";
    case 1:
      return "Value";
    }
    return Stat.values()[col - 2].name();
  }

  @Override
  public Class<?> getColumnClass(int col) {
    switch (col) {
    case 0:
      return String.class;
    }
    return Integer.class;
  }

  @Override
  public int getRowCount() {
    if (equipment == null) {
      return 0;
    }
    return equipment.size();
  }

  @Override
  public Object getValueAt(int row, int col) {
    Equipment equip = getRowValue(row);
    switch (col) {
    case 0:
      return equip.getStatType().getName();
    case 1:
      return WeightCalculator.calculateWeight(equip);
    default:
      return equip.getStats().get(Stat.values()[col - 2]);
    }
  }

  @Override
  public boolean isCellEditable(int row, int col) {
    return false;
  }

  public Equipment getRowValue(int row) {
    return equipment.get(row);
  }
}
