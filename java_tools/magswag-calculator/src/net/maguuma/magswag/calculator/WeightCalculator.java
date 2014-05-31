package net.maguuma.magswag.calculator;

import net.maguuma.magswag.calculator.character.Character;
import net.maguuma.magswag.calculator.controller.WeightController;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.items.StatType;
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

  public static boolean relevant(StatType statType) {
    int count = 0;
    for (Stat stat : statType.getStats()) {
      if (WeightController.getValue(stat) > 0) {
        count++;
      } else {
        switch (stat) {
        case VITALITY:
          if (WeightController.getValue(Stat.EFFECTIVE_HEALTH) + WeightController.getValue(Stat.HEALTH) > 0) {
            count++;
          }
          break;
        case TOUGHNESS:
          if (WeightController.getValue(Stat.EFFECTIVE_HEALTH) + WeightController.getValue(Stat.ARMOR) > 0) {
            count++;
          }
          break;
        case POWER:
          if (WeightController.getValue(Stat.EFFECTIVE_POWER) + WeightController.getValue(Stat.DAMAGE_PER_SECOND) > 0) {
            count++;
          }
          break;
        case FEROCITY:
          if (WeightController.getValue(Stat.EFFECTIVE_POWER) + WeightController.getValue(Stat.DAMAGE_PER_SECOND) + WeightController.getValue(Stat.CRITICAL_DAMAGE) > 0) {
            count++;
          }
          break;
        case PRECISION:
          if (WeightController.getValue(Stat.EFFECTIVE_POWER) + WeightController.getValue(Stat.DAMAGE_PER_SECOND) + WeightController.getValue(Stat.CRITICAL_CHANCE) > 0) {
            count++;
          }
          break;
        default:
          break;
        }
      }
    }
    return (count >= 3);
  }
}
