package net.maguuma.magswag.common.datatypes.items;

import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.datatypes.maps.Stats;

public class Item {
  protected Stats stats;
  protected String name;
  protected Slot slot;

  public Item(String name, Slot slot) {
    this.stats = new Stats();
    this.name = name;
    this.slot = slot;
  }

  public String getName() {
    return this.name;
  }

  public Slot getSlot() {
    return this.slot;
  }

  public void addStat(Stat stat, int value) {
    this.stats.put(stat, value);
  }

  public int getStat(Stat stat) {
    return this.stats.get(stat);
  }
}
