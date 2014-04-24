package net.maguuma.magswag.common.datatypes.items;

import net.maguuma.magswag.common.constants.EquipmentType;
import net.maguuma.magswag.common.constants.Stat;

public class StatType {
  private String name;
  private EquipmentType[] types;
  private Stat[] stats;

  public StatType() {

  }

  public String getName() {
    return this.name;
  }

  public EquipmentType[] getEquipmentTypes() {
    return this.types;
  }

  public Stat[] getStats() {
    return this.stats;
  }
}
