package net.maguuma.magswag.calculator.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.maguuma.magswag.calculator.controller.listener.WeightChangeListener;
import net.maguuma.magswag.common.constants.Stat;

public class WeightController {
  private static final Map<Stat, Integer> values = new HashMap<Stat, Integer>();

  private static Set<WeightChangeListener> listeners = new HashSet<WeightChangeListener>();

  public static void setValue(Stat stat, int integer) {
    values.put(stat, integer);
    fireValueChanged(stat, integer);
  }

  public static int getValue(Stat stat) {
    Integer value = values.get(stat);
    if (value == null) {
      value = 0;
    }
    return value;
  }

  private static void fireValueChanged(Stat stat, int integer) {
    for (WeightChangeListener listener : listeners) {
      listener.weightChanged(stat, integer);
    }
  }

  public static void addWeightChangeListener(WeightChangeListener listener) {
    listeners.add(listener);
  }

  public static void removeWeightChangeListener(WeightChangeListener listener) {
    listeners.remove(listener);
  }
}
