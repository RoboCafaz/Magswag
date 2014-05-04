package net.maguuma.magswag.gui.brute.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import net.maguuma.magswag.calculator.WeightCalculator;
import net.maguuma.magswag.calculator.character.Character;
import net.maguuma.magswag.common.constants.Stat;

@SuppressWarnings("serial")
public class CharacterViewerModel extends DefaultTableModel {

  private final List<Character> characters;

  public CharacterViewerModel() {
    characters = new ArrayList<Character>();
  }

  public void setCharacters(List<Character> characters) {
    this.characters.clear();
    this.characters.addAll(characters);
  }

  @Override
  public int getColumnCount() {
    return 1 + Stat.values().length;
  }

  @Override
  public String getColumnName(int col) {
    switch (col) {
    case 0:
      return "Value";
    }
    return Stat.values()[col - 1].name();
  }

  @Override
  public Class<?> getColumnClass(int col) {
    return Integer.class;
  }

  @Override
  public int getRowCount() {
    if (characters == null) {
      return 0;
    }
    return characters.size();
  }

  @Override
  public Object getValueAt(int row, int col) {
    Character character = getRowValue(row);
    switch (col) {
    case 0:
      return WeightCalculator.calculateWeight(character);
    default:
      return character.getStats().get(Stat.values()[col - 1]);
    }
  }

  @Override
  public boolean isCellEditable(int row, int col) {
    return false;
  }

  public Character getRowValue(int row) {
    return characters.get(row);
  }
}
