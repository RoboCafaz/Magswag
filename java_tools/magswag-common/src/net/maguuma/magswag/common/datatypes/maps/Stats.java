package net.maguuma.magswag.common.datatypes.maps;

import java.util.HashMap;

import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.logging.Logger;

@SuppressWarnings("serial")
public class Stats extends HashMap<Stat, Integer> {
  public Integer add(Stat stat, Integer value) {
    value += get(stat);
    return super.put(stat, value);
  }

  @Override
  public Integer put(Stat stat, Integer value) {
    if (stat.isComplex()) {
      Logger.error("Cannot directly value of complex stat " + stat);
      return 0;
    }
    Integer output = super.put(stat, value);
    recalculateStats();
    return output;
  }

  @Override
  public Integer get(Object stat) {
    Integer bonus = super.get(stat);
    if (bonus == null) {
      return 0;
    }
    return bonus;
  }

  private void recalculateStats() {
    super.put(Stat.ARMOR, get(Stat.TOUGHNESS) + get(Stat.DEFENSE));
    super.put(Stat.CRITICAL_CHANCE, (int) (Math.floor(get(Stat.PRECISION) / 4)));
    super.put(Stat.CRITICAL_DAMAGE, (int) (Math.floor(get(Stat.FEROCITY) / 15)));
    super.put(Stat.HEALTH, get(Stat.VITALITY) * 10);
    super.put(Stat.EFFECTIVE_HEALTH, (int) Math.floor((Math.max(1, get(Stat.ARMOR)) * Math.max(1, get(Stat.HEALTH))) / 1000));

    double baseDamage = get(Stat.POWER);
    double critChance = (100 + get(Stat.CRITICAL_CHANCE)) / 100.0;
    double critDamage = (150 + get(Stat.CRITICAL_DAMAGE)) / 100.0;
    super.put(Stat.EFFECTIVE_POWER, (int) Math.floor(baseDamage * (critChance * critDamage)));
  }
}
