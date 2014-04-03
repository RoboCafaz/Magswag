package net.maguuma.magswag.common.datatypes.items;

import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;

public class Equipment extends Item {
  protected Rarity rarity;

  public Equipment(String name, Slot slot, Rarity rarity) {
    super(name, slot);
    this.rarity = rarity;
  }

  public Rarity getRarity() {
    return this.rarity;
  }
}
