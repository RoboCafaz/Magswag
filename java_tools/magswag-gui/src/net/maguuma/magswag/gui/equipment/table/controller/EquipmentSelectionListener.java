package net.maguuma.magswag.gui.equipment.table.controller;

import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.datatypes.items.GearType;

public interface EquipmentSelectionListener {
  public void equipmentSelectionChanged(Slot slot, GearType gearType, Rarity rarity);
}
