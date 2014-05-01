package net.maguuma.magswag.calculator.controller.listener;

import net.maguuma.magswag.common.constants.Stat;

public interface WeightChangeListener {
  public void weightChanged(Stat stat, int value);
}
