package net.maguuma.magswag.calculator;

import net.maguuma.magswag.calculator.character.Character;
import net.maguuma.magswag.calculator.controller.WeightController;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.maps.Stats;

public class WeightCalculator {
  public static int calculateWeight(Equipment equipment) {
    return calculateWeight(equipment.getStats());
  }

  public static int calculateWeight(Character character) {
    return calculateWeight(character.getStats());
  }

  public static int calculateWeight(Stats stats) {
    int value = 0;
    for (Stat stat : stats.keySet()) {
      value += (WeightController.getValue(stat) * stats.get(stat));
    }
    return value;
  }
}
