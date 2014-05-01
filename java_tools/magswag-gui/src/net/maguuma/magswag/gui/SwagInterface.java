package net.maguuma.magswag.gui;

import java.awt.BorderLayout;
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
    this.setLayout(new BorderLayout());
    JPanel topPanel = new JPanel();
    JPanel classPanel = new JPanel(new BorderLayout());
    classPanel.add(new ProfessionPanel(), BorderLayout.NORTH);
    classPanel.add(new TraitsPanel(), BorderLayout.CENTER);
    topPanel.add(classPanel);
    topPanel.add(new WeightPanel());
    topPanel.add(new EquipmentPanel());
    add(topPanel, BorderLayout.NORTH);
    add(new EquipmentSelectionPanel(), BorderLayout.CENTER);
  }
}