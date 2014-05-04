package net.maguuma.magswag.gui.brute.table;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import net.maguuma.magswag.calculator.character.Character;
import net.maguuma.magswag.calculator.controller.CharacterController;
import net.maguuma.magswag.calculator.controller.WeightController;
import net.maguuma.magswag.calculator.controller.listener.WeightChangeListener;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.logging.Logger;

@SuppressWarnings("serial")
public class CharacterViewerPanel extends JPanel implements WeightChangeListener {
  private JTable table;
  private CharacterViewerModel model;
  private TableRowSorter<CharacterViewerModel> sorter;

  public CharacterViewerPanel() {
    initialize();
    performLayout();
  }

  private void initialize() {
    table = new JTable();
    model = new CharacterViewerModel();
    sorter = new TableRowSorter<CharacterViewerModel>(model);
    table.setModel(model);
    table.setRowSorter(sorter);

    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.getSelectionModel().addListSelectionListener(createListSelectionListener());
    WeightController.addWeightChangeListener(this);
  }

  private void performLayout() {
    setLayout(new BorderLayout());
    JScrollPane scroll = new JScrollPane(table);
    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    this.add(scroll, BorderLayout.CENTER);
  }

  protected void updateTableValues() {
    sorter.sort();
    table.repaint();
  }

  public void updateTableModel() {
    model.setCharacters(CharacterController.getCharacters());
    table.getSelectionModel().clearSelection();
    updateTableValues();
  }

  protected ListSelectionListener createListSelectionListener() {
    return new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        int row = table.getSelectedRow();
        if (row != -1) {
          row = sorter.convertRowIndexToModel(row);
          Character character = model.getRowValue(row);
          Logger.info(character.toString());
        }
      }
    };
  }

  @Override
  public void weightChanged(Stat stat, int value) {
    updateTableValues();
  }
}