package net.maguuma.magswag.calculator.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.maguuma.magswag.calculator.controller.listener.EquipmentChangeListener;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.logging.Logger;

public class EquipmentController {
  private static Map<Slot, Equipment> gear = new HashMap<Slot, Equipment>();

  private static Set<EquipmentChangeListener> listeners = new HashSet<EquipmentChangeListener>();

  public static Map<Slot, Equipment> getGear() {
    return gear;
  }

  public static Equipment getGear(Slot slot) {
    return gear.get(slot);
  }

  public static void setGear(Slot slot, Equipment equip) {
	  Logger.log(slot.name() + " changed to " + equip.getRarity() + " " + equip.getStatType().getName() + " " + equip.getGearType().getEquipmentType().name());
    gear.put(slot, equip);
    fireGearChanged(slot, equip);
  }

  private static void fireGearChanged(Slot slot, Equipment equip) {
    for (EquipmentChangeListener listener : listeners) {
      listener.equipmentChanged(slot, equip);
    }
  }

  public static void addEquipmentChangeListener(EquipmentChangeListener listener) {
    listeners.add(listener);
  }

  public static void removeEquipmentChangeListener(EquipmentChangeListener listener) {
    listeners.remove(listener);
  }
}
