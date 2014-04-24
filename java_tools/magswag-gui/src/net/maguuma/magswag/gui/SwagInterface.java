package net.maguuma.magswag.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import net.maguuma.magswag.calculator.controller.ProfessionController;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.dataloader.json.loader.Loaders;
import net.maguuma.magswag.gui.equipment.EquipmentPanel;
import net.maguuma.magswag.gui.equipment.table.EquipmentSelectionPanel;
import net.maguuma.magswag.gui.professions.ProfessionPanel;
import net.maguuma.magswag.gui.traits.TraitsPanel;
import net.maguuma.magswag.gui.weights.WeightPanel;

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
    this.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    this.add(new ProfessionPanel(), constraints);

    constraints.gridy = 1;
    this.add(new TraitsPanel(), constraints);

    constraints.gridy = 2;
    this.add(new WeightPanel(), constraints);

    constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridheight = 3;
    this.add(new EquipmentPanel(), constraints);

    constraints.gridheight = 1;
    this.add(new EquipmentSelectionPanel(), constraints);

    constraints.gridy = 1;
    constraints.gridheight = 2;
    this.add(new WeightPanel(), constraints);
  }
}