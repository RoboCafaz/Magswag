package net.maguuma.magswag.calculator.character;

import java.util.HashSet;
import java.util.Set;

import net.maguuma.magswag.calculator.character.listener.TraitChangeListener;
import net.maguuma.magswag.common.logging.Logger;

public class TraitModel {
  public static int MAX_TRAITS = 14;
  public static int MAX_TRAIT_LINE = 6;

  private final Set<TraitChangeListener> traitChangeListeners = new HashSet<TraitChangeListener>();
  private final int[] traits = new int[5];

  public void setTraits(int[] traits) {
    for (int i = 0; i < traits.length; i++) {
      this.traits[i] = traits[i];
    }
  }

  public int[] getTraits() {
    return traits;
  }

  public int getTraitValue(int index) {
    return getTraits()[index];
  }

  public void incrementTrait(int index) {
    if (pointsRemaining() <= 0) {
      Logger.log("Could not increment trait line #" + index + " because there are no traits left to spend!");
      return;
    }
    if (traits[index] >= MAX_TRAIT_LINE) {
      Logger.log("Could not increment trait line #" + index + " because this line is already maxed out!");
      return;
    }

    traits[index]++;
    Logger.log("Incremented trait line #" + index + " to " + traits[index] + ". " + pointsRemaining() + " point(s) left.");
    fireTraitChanged(index, traits[index]);
  }

  public void decrementTrait(int index) {
    if (traits[index] <= 0) {
      Logger.log("Could not decrement trait line #" + index + " because there are no traits left to refund!");
      return;
    }
    traits[index]--;
    Logger.log("Decremented trait line #" + index + " to " + traits[index] + ". " + pointsRemaining() + " point(s) left.");
    fireTraitChanged(index, traits[index]);
  }

  public int pointsRemaining() {
    int value = MAX_TRAITS;
    for (int i : traits) {
      value -= i;
    }
    return value;
  }

  private void fireTraitChanged(int traitLine, int newValue) {
    for (TraitChangeListener listener : traitChangeListeners) {
      listener.traitChanged(traitLine, newValue);
    }
  }

  public void addTraitChangeListener(TraitChangeListener listener) {
    traitChangeListeners.add(listener);
  }

  public void removeTraitChangeListener(TraitChangeListener listener) {
    traitChangeListeners.remove(listener);
  }
}
