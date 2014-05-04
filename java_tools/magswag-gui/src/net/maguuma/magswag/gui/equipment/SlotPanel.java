package net.maguuma.magswag.gui.equipment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.maguuma.magswag.calculator.character.listener.EquipmentChangeListener;
import net.maguuma.magswag.calculator.controller.CharacterController;
import net.maguuma.magswag.common.constants.EquipmentType;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.gui.equipment.table.controller.EquipmentSelectionController;
import net.maguuma.magswag.gui.equipment.table.controller.EquipmentSelectionListener;

@SuppressWarnings("serial")
public class SlotPanel extends JPanel implements EquipmentChangeListener, EquipmentSelectionListener {
  private final Slot slot;

  private JCheckBox ascended;
  private JComboBox<EquipmentType> typeSelection;
  private JToggleButton selectionButton;

  public SlotPanel(Slot slot) {
    this.slot = slot;
    initialize();
    performLayout();
  }

  private void initialize() {
    ascended = new JCheckBox("Ascended");
    ascended.addActionListener(createChangeListener());
    typeSelection = new JComboBox<EquipmentType>(slot.getTypes());
    typeSelection.addActionListener(createChangeListener());
    selectionButton = new JToggleButton("None");
    selectionButton.addActionListener(createChangeListener());
    CharacterController.getCharacter().getEquipmentModel().addEquipmentChangeListener(this);
    EquipmentSelectionController.addEquipmentSelectionListener(this);
  }

  private void performLayout() {
    setBorder(BorderFactory.createTitledBorder(slot.name()));
    this.add(ascended);
    this.add(typeSelection);
    this.add(selectionButton);
  }

  private ActionListener createChangeListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent paramActionEvent) {
        updateGearTable();
      }
    };
  }

  private void updateGearTable() {
    Rarity rarity;
    if (SlotPanel.this.ascended.isSelected()) {
      rarity = Rarity.ASCENDED;
    } else {
      rarity = Rarity.EXOTIC;
    }
    EquipmentSelectionController.setGearType(SlotPanel.this.slot, DataHandler.GEAR_TYPES.get((EquipmentType) SlotPanel.this.typeSelection.getSelectedItem()), rarity);
  }

  @Override
  public void equipmentSelectionChanged(Slot slot, GearType gearType, Rarity rarity) {
    selectionButton.setSelected(slot.equals(this.slot));
  }

  @Override
  public void equipmentChanged(Slot slot, Equipment gear) {
    if (slot.equals(this.slot)) {
      selectionButton.setText(gear.getStatType().getName());
      typeSelection.setSelectedItem(gear.getGearType());
      ascended.setSelected(gear.getRarity().equals(Rarity.ASCENDED));
    }
  }
}
