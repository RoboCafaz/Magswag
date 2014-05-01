package net.maguuma.magswag.common.constants;

import java.util.ArrayList;
import java.util.List;

public enum Stat {
  DEFENSE,
  POWER,
  PRECISION,
  TOUGHNESS,
  VITALITY,
  FEROCITY,
  CONDITION_DAMAGE,
  HEALING_POWER,
  CONDITION_DURATION,
  BOON_DURATION,
  BONUS,
  ARMOR(true),
  CRITICAL_CHANCE(true),
  CRITICAL_DAMAGE(true),
  HEALTH(true),
  EFFECTIVE_HEALTH(true),
  EFFECTIVE_POWER(true),
  DAMAGE_PER_SECOND(true);

  private boolean complex;

  private Stat() {
    this(false);
  }

  private Stat(boolean complex) {
    this.complex = complex;
  }

  public boolean isComplex() {
    return this.complex;
  }

  private static Stat[] simpleStats;

  public static Stat[] simpleStats() {
    if (simpleStats == null) {
      List<Stat> stats = new ArrayList<Stat>();
      for (Stat stat : Stat.values()) {
        if (!stat.isComplex()) {
          stats.add(stat);
        }
      }
      simpleStats = stats.toArray(new Stat[] {});
    }
    return simpleStats;
  }
}
