package net.maguuma.magswag.gui.equipment.table;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.items.GearType;

@SuppressWarnings("serial")
public class EquipmentSelectionPanel extends JPanel {
  private JTable table;

  public EquipmentSelectionPanel() {
    initialize();
    performLayout();
  }

  private void initialize() {
  }

  private void performLayout() {
    this.setBorder(BorderFactory.createTitledBorder("Equipment List"));
    this.setLayout(new BorderLayout());

    loadNewTable(DataHandler.GEAR_TYPES.getData().get(0), Rarity.EXOTIC);
  }

  private void loadNewTable(GearType gearType, Rarity rarity) {
    this.removeAll();
    this.table = new JTable();
    this.table.setModel(new EquipmentModel(gearType, rarity));
    JScrollPane scroll = new JScrollPane(this.table);
    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    this.add(scroll, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }
}
