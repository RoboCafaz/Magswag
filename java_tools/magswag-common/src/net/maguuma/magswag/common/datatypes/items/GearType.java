package net.maguuma.magswag.common.datatypes.items;

import net.maguuma.magswag.common.constants.EquipmentType;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Weight;

public class GearType {
  private EquipmentType type;
  private int[][] armor;
  private int[][] stats;
  private int[] celestial;

  public EquipmentType getSlot() {
    return this.type;
  }

  public int getArmor(Rarity rarity, Weight weight) {
    return this.armor[rarity.ordinal()][weight.ordinal()];
  }

  public int getStats(Rarity rarity, boolean secondary) {
    int ordinal = 0;
    if (secondary) {
      ordinal = 1;
    }
    return this.stats[rarity.ordinal()][ordinal];
  }

  public int getCelestialSTats(Rarity rarity) {
    return this.celestial[rarity.ordinal()];
  }
}
