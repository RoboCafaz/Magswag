package net.maguuma.magswag.calculator.character;

import java.util.HashSet;
import java.util.Set;

import net.maguuma.magswag.calculator.character.listener.ProfessionChangeListener;
import net.maguuma.magswag.common.datatypes.professions.Profession;

public class ProfessionModel {
  // -- Profession Logic

  private final Set<ProfessionChangeListener> professionChangeListeners = new HashSet<ProfessionChangeListener>();

  private Profession profession;

  public Profession getProfession() {
    return profession;
  }

  public void setProfession(Profession profession) {
    Profession oldProfession = this.profession;
    this.profession = profession;
    fireProfessionChanged(oldProfession, profession);
  }

  private void fireProfessionChanged(Profession oldProfession, Profession newProfession) {
    for (ProfessionChangeListener listener : professionChangeListeners) {
      listener.professionChanged(oldProfession, newProfession);
    }
  }

  public void addProfessionChangeListener(ProfessionChangeListener listener) {
    professionChangeListeners.add(listener);
  }

  public void removeProfessionChangeListener(ProfessionChangeListener listener) {
    professionChangeListeners.remove(listener);
  }
}
