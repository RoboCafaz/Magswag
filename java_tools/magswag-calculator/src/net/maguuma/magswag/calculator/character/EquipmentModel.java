package net.maguuma.magswag.calculator.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.maguuma.magswag.calculator.character.listener.EquipmentChangeListener;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.logging.Logger;

public class EquipmentModel {
  private final Map<Slot, Equipment> gear = new HashMap<Slot, Equipment>();

  private final Set<EquipmentChangeListener> listeners = new HashSet<EquipmentChangeListener>();

  public Map<Slot, Equipment> getGear() {
    return gear;
  }

  public Equipment getGear(Slot slot) {
    return gear.get(slot);
  }

  public void setGear(Slot slot, Equipment equip) {
    Logger.log(slot.name() + " changed to " + equip.getRarity() + " " + equip.getStatType().getName() + " " + equip.getGearType().getEquipmentType().name());
    gear.put(slot, equip);
    fireGearChanged(slot, equip);
  }

  public int emptySlots() {
    return getEmptySlots().size();
  }

  public List<Slot> getEmptySlots() {
    List<Slot> empty = new ArrayList<Slot>();
    for (Slot slot : Slot.sortedValues()) {
      if (getGear(slot) == null) {
        empty.add(slot);
      }
    }
    return empty;
  }

  private void fireGearChanged(Slot slot, Equipment equip) {
    for (EquipmentChangeListener listener : listeners) {
      listener.equipmentChanged(slot, equip);
    }
  }

  public void addEquipmentChangeListener(EquipmentChangeListener listener) {
    listeners.add(listener);
  }

  public void removeEquipmentChangeListener(EquipmentChangeListener listener) {
    listeners.remove(listener);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Equipment equip : getGear().values()) {
      sb.append("[");
      sb.append(equip.toString());
      sb.append("]");
    }
    return sb.toString();
  }
}
