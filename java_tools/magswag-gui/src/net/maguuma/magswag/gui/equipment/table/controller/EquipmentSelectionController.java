package net.maguuma.magswag.gui.equipment.table.controller;

import java.util.HashSet;
import java.util.Set;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.datatypes.items.GearType;

public class EquipmentSelectionController {
  private static GearType gearType;
  private static Rarity rarity;

  private static Set<EquipmentSelectionListener> listeners = new HashSet<EquipmentSelectionListener>();

  public static void setGearType(Slot slot, GearType newGearType, Rarity newRarity) {
    gearType = newGearType;
    rarity = newRarity;
    for (EquipmentSelectionListener listener : listeners) {
      listener.equipmentSelectionChanged(slot, gearType, rarity);
    }
  }

  public static void addEquipmentSelectionListener(EquipmentSelectionListener listener) {
    listeners.add(listener);
  }

  public static void removeEquipmentSelectionListener(EquipmentSelectionListener listener) {
    listeners.remove(listener);
  }
}
