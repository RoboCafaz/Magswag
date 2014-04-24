package net.maguuma.magswag.common.constants;

import java.util.Arrays;
import java.util.List;

public enum Slot {
  HELMET(EquipmentType.HELMET),
  SHOULDERS(EquipmentType.SHOULDERS),
  CHEST(EquipmentType.CHEST),
  GLOVES(EquipmentType.GLOVES),
  LEGS(EquipmentType.LEGS),
  BOOTS(EquipmentType.BOOTS),
  BACK(EquipmentType.BACK),
  ACCESSORY_1(EquipmentType.ACCESSORY),
  ACCESSORY_2(EquipmentType.ACCESSORY),
  RING_1(EquipmentType.RING),
  RING_2(EquipmentType.RING),
  AMULET(EquipmentType.AMULET),
  MAIN_HAND(EquipmentType.ONE_HANDED_WEAPON, EquipmentType.TWO_HANDED_WEAPON),
  OFF_HAND(EquipmentType.ONE_HANDED_WEAPON, EquipmentType.SHIELD);

  private final List<EquipmentType> types;

  private Slot(EquipmentType... types) {
    this.types = Arrays.asList(types);
  }

  public boolean applicable(EquipmentType type) {
    return this.types.contains(type);
  }

  public List<EquipmentType> getTypes() {
    return this.types;
  }
}
