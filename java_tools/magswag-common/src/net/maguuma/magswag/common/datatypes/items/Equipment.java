package net.maguuma.magswag.common.datatypes.items;

import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.constants.Weight;
import net.maguuma.magswag.common.datatypes.maps.Stats;

public class Equipment {
  private final Weight weight;
  private final GearType type;
  private final StatType statType;
  private final Rarity rarity;
  private final Stats stats;

  public Equipment(GearType type, Weight weight, StatType statType, Rarity rarity) {
    this.weight = weight;
    this.type = type;
    this.statType = statType;
    this.rarity = rarity;
    stats = new Stats();
    assignStats();
  }

  public GearType getGearType() {
    return type;
  }

  public StatType getStatType() {
    return statType;
  }

  public Rarity getRarity() {
    return rarity;
  }

  public Stats getStats() {
    return stats;
  }

  private void assignStats() {
    boolean celestial = (statType.getStats().length == 7);
    boolean secondary = false;
    for (Stat stat : statType.getStats()) {
      int value = 0;
      if (celestial) {
        value = type.getCelestialStats(rarity);
      } else {
        value = type.getStats(rarity, secondary);
      }
      stats.put(stat, value);
      secondary = true;
    }
    stats.put(Stat.DEFENSE, type.getArmor(rarity, weight));
  }

  @Override
  public String toString() {
    return getRarity().name() + " " + getStatType().getName() + " " + getGearType().getEquipmentType().name();
  }
}
