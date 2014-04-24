package net.maguuma.magswag.common.datatypes.items;

import net.maguuma.magswag.common.constants.EquipmentType;

public class Equipment {
  private final EquipmentType type;
  private final StatType stats;
  private final boolean ascended;

  public Equipment(EquipmentType type, StatType stats, boolean ascended) {
    this.type = type;
    this.stats = stats;
    this.ascended = ascended;
  }

  public EquipmentType getEquipmentType() {
    return this.type;
  }

  public StatType getStatType() {
    return this.stats;
  }

  public boolean isAscended() {
    return this.ascended;
  }
}
