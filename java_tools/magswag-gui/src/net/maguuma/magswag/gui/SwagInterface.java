package net.maguuma.magswag.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import net.maguuma.magswag.calculator.controller.ProfessionController;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.dataloader.json.loader.Loaders;
import net.maguuma.magswag.gui.equipment.EquipmentPanel;
import net.maguuma.magswag.gui.equipment.stats.StatsPanel;
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
    JTabbedPane tabbed = new JTabbedPane();
    JPanel profs = new JPanel(new BorderLayout());
    profs.add(new ProfessionPanel(), BorderLayout.NORTH);
    profs.add(new TraitsPanel(), BorderLayout.CENTER);
    tabbed.addTab("Profession and Traits", profs);
    
    JPanel stats = new JPanel(new BorderLayout());
    stats.add(new WeightPanel(), BorderLayout.WEST);
    stats.add(new EquipmentPanel(), BorderLayout.CENTER);
    stats.add(new StatsPanel(), BorderLayout.EAST);
    stats.add(new EquipmentSelectionPanel(), BorderLayout.SOUTH);
    tabbed.addTab("Gear and Stats", stats);
    
    this.add(tabbed,BorderLayout.CENTER);
  }
}