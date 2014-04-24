package net.maguuma.magswag.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import net.maguuma.magswag.calculator.controller.ProfessionController;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.dataloader.json.loader.Loaders;
import net.maguuma.magswag.gui.equipment.EquipmentPanel;
import net.maguuma.magswag.gui.professions.ProfessionPanel;
import net.maguuma.magswag.gui.traits.TraitsPanel;

@SuppressWarnings("serial")
public class SwagInterface extends JPanel {

  public SwagInterface() {
    initialize();
    performLayout();
  }

  private void initialize() {
    Loaders.loadData();
    ProfessionController.setProfession(DataHandler.PROFESSIONS.getData().iterator().next());
  }

  private void performLayout() {
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.add(new ProfessionPanel());
    this.add(new TraitsPanel());
    this.add(new EquipmentPanel());
  }
}