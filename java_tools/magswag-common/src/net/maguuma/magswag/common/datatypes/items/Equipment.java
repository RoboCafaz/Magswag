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
    this.stats = new Stats();
    assignStats();
  }

  public GearType getGearType() {
    return this.type;
  }

  public StatType getStatType() {
    return this.statType;
  }

  public Rarity getRarity() {
    return this.rarity;
  }

  public Stats getStats() {
    return this.stats;
  }

  private void assignStats() {
    boolean celestial = (this.statType.getStats().length == 7);
    boolean secondary = false;
    for (Stat stat : this.statType.getStats()) {
      int value = 0;
      if (celestial) {
        value = this.type.getCelestialStats(this.rarity);
      } else {
        value = this.type.getStats(this.rarity, secondary);
      }
      this.stats.put(stat, value);
      secondary = true;
    }
    this.stats.put(Stat.DEFENSE, this.type.getArmor(this.rarity, this.weight));
  }
}
