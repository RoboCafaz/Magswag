package net.maguuma.magswag.common.datatypes.items;

import net.maguuma.magswag.common.constants.Weight;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;

public class Armor extends Equipment {
  protected Weight weight;

  public Armor(String name, Slot slot, Rarity rarity, Weight weight) {
    super(name, slot, rarity);
    this.weight = weight;
  }

  public Weight getArmorClass() {
    return this.weight;
  }
}
