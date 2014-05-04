package net.maguuma.magswag.gui.traits;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import net.maguuma.magswag.calculator.character.listener.ProfessionChangeListener;
import net.maguuma.magswag.calculator.controller.CharacterController;
import net.maguuma.magswag.common.datatypes.professions.Profession;
import net.maguuma.magswag.common.datatypes.professions.Trait;

@SuppressWarnings("serial")
public class TraitsPanel extends JPanel implements ProfessionChangeListener {
  public TraitsPanel() {
    initialize();
    performLayout();
    CharacterController.getCharacter().getProfessionModel().addProfessionChangeListener(this);
  }

  private void initialize() {

  }

  private void performLayout() {
    setBorder(BorderFactory.createTitledBorder("Traits"));
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    removeAll();
    int index = 0;
    for (Trait trait : CharacterController.getCharacter().getProfessionModel().getProfession().getTraits()) {
      this.add(new TraitlinePanel(index++, trait));
    }
    revalidate();
    repaint();
  }

  @Override
  public void professionChanged(Profession oldProfession, Profession newProfession) {
    performLayout();
  }
}
