package net.maguuma.magswag.common.datatypes.items;

import java.util.Arrays;

import net.maguuma.magswag.common.constants.EquipmentType;
import net.maguuma.magswag.common.constants.Stat;

public class StatType {
  private String name;
  private EquipmentType[] types;
  private Stat[] stats;

  public StatType() {

  }

  public String getName() {
    return name;
  }

  public EquipmentType[] getEquipmentTypes() {
    return types;
  }

  public Stat[] getStats() {
    return stats;
  }

  public boolean applicable(EquipmentType equipmentType) {
    return Arrays.asList(types).contains(equipmentType);
  }
}
