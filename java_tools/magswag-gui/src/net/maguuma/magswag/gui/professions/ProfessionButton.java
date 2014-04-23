package net.maguuma.magswag.gui.professions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;
import net.maguuma.magswag.calculator.controller.ProfessionController;
import net.maguuma.magswag.calculator.controller.listener.ProfessionChangeListener;
import net.maguuma.magswag.common.datatypes.professions.Profession;

@SuppressWarnings("serial")
public class ProfessionButton extends JToggleButton implements ProfessionChangeListener {
  private final Profession profession;

  public ProfessionButton(Profession profession) {
    super(profession.getIcon());
    this.profession = profession;
    ProfessionController.addProfessionChangeListener(this);
    this.addActionListener(createActionListener());
  }

  @Override
  protected ActionListener createActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ProfessionController.setProfession(ProfessionButton.this.profession);
      }
    };
  }

  @Override
  public void professionChanged(Profession oldProfession, Profession newProfession) {
    this.setSelected(newProfession == this.profession);
  }
}
