package net.maguuma.magswag.calculator.controller.listener;

import net.maguuma.magswag.common.datatypes.professions.Profession;

public interface ProfessionChangeListener {
  public void professionChanged(Profession oldProfession, Profession newProfession);
}
