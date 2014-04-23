package net.maguuma.magswag.calculator.controller;

import java.util.HashSet;
import java.util.Set;
import net.maguuma.magswag.calculator.controller.listener.ProfessionChangeListener;
import net.maguuma.magswag.common.datatypes.professions.Profession;

public class ProfessionController {
  // -- Profession Logic

  private static Set<ProfessionChangeListener> professionChangeListeners = new HashSet<ProfessionChangeListener>();

  private static Profession profession;

  public static Profession getProfession() {
    return ProfessionController.profession;
  }

  public static void setProfession(Profession profession) {
    Profession oldProfession = ProfessionController.profession;
    ProfessionController.profession = profession;
    fireProfessionChanged(oldProfession, profession);
  }

  private static void fireProfessionChanged(Profession oldProfession, Profession newProfession) {
    for (ProfessionChangeListener listener : professionChangeListeners) {
      listener.professionChanged(oldProfession, newProfession);
    }
  }

  public static void addProfessionChangeListener(ProfessionChangeListener listener) {
    professionChangeListeners.add(listener);
  }

  public static void removeProfessionChangeListener(ProfessionChangeListener listener) {
    professionChangeListeners.remove(listener);
  }
}
