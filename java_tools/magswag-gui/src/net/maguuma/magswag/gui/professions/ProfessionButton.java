package net.maguuma.magswag.gui.professions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import net.maguuma.magswag.calculator.character.listener.ProfessionChangeListener;
import net.maguuma.magswag.calculator.controller.CharacterController;
import net.maguuma.magswag.common.datatypes.professions.Profession;

@SuppressWarnings("serial")
public class ProfessionButton extends JToggleButton implements ProfessionChangeListener {
  private final Profession profession;

  public ProfessionButton(Profession profession) {
    super(profession.getIcon());
    this.profession = profession;
    addActionListener(createActionListener());
    refresh();
  }

  @Override
  protected ActionListener createActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        CharacterController.getCharacter().getProfessionModel().setProfession(profession);
      }
    };
  }

  private void refresh() {
    setSelected(CharacterController.getCharacter().getProfessionModel().getProfession() == profession);
  }

  @Override
  public void professionChanged(Profession oldProfession, Profession newProfession) {
    refresh();
  }
}
