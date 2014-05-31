package net.maguuma.magswag.common.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
  MAIN_HAND(EquipmentType.ONE_HANDED_WEAPON), // ,
                                              // EquipmentType.TWO_HANDED_WEAPON),
  OFF_HAND(EquipmentType.ONE_HANDED_WEAPON, EquipmentType.SHIELD);

  private static Slot[] sortedValues;

  public static Slot[] sortedValues() {
    if (sortedValues == null) {
      List<Slot> vals = Arrays.asList(Slot.values());
      Collections.sort(vals, new Comparator<Slot>() {
        @Override
        public int compare(Slot s1, Slot s2) {
          return s1.compareTo(s2);
        }
      });
      sortedValues = vals.toArray(new Slot[] {});
    }
    return sortedValues;
  }

  EquipmentType[] types;

  private Slot(EquipmentType... types) {
    this.types = types;
  }

  public boolean applicable(EquipmentType type) {
    return Arrays.asList(types).contains(type);
  }

  public EquipmentType[] getTypes() {
    return types;
  }
}
