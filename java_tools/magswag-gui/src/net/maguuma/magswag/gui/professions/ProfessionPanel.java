package net.maguuma.magswag.gui.professions;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.professions.Profession;

@SuppressWarnings("serial")
public class ProfessionPanel extends JPanel {

  public ProfessionPanel() {
    initialize();
    performLayout();
  }

  private void initialize() {

  }

  private void performLayout() {
    setBorder(BorderFactory.createTitledBorder("Profession"));
    setLayout(new GridLayout());
    for (Profession profession : DataHandler.PROFESSIONS.getData()) {
      this.add(new ProfessionButton(profession));
    }
  }
}
