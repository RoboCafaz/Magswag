package net.maguuma.magswag.calculator.character.listener;

import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.datatypes.items.Equipment;

public interface EquipmentChangeListener {
  public void equipmentChanged(Slot slot, Equipment gear);
}
