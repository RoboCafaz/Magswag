package net.maguuma.magswag.gui;

import javax.swing.JPanel;
import net.maguuma.magswag.dataloader.json.profession.ProfessionLoader;
import net.maguuma.magswag.gui.professions.ProfessionPanel;

@SuppressWarnings("serial")
public class SwagInterface extends JPanel {

  public SwagInterface() {
    initialize();
    performLayout();
  }

  private void initialize() {
    ProfessionLoader.loadProfessions();
  }

  private void performLayout() {
    this.add(new ProfessionPanel());
  }
}