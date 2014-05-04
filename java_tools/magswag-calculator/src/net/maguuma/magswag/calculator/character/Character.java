package net.maguuma.magswag.calculator.character;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import net.maguuma.magswag.calculator.character.listener.EquipmentChangeListener;
import net.maguuma.magswag.calculator.character.listener.ProfessionChangeListener;
import net.maguuma.magswag.calculator.character.listener.TraitChangeListener;
import net.maguuma.magswag.calculator.controller.listener.CharacterStatChangeListener;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.maps.Stats;
import net.maguuma.magswag.common.datatypes.professions.Profession;

public class Character implements TraitChangeListener, ProfessionChangeListener, EquipmentChangeListener {
  private Stats stats;
  private final ProfessionModel professionModel;
  private final TraitModel traitModel;
  private final EquipmentModel equipmentModel;

  private final Set<CharacterStatChangeListener> listeners = new HashSet<CharacterStatChangeListener>();

  public Character() {
    stats = new Stats();
    professionModel = new ProfessionModel();
    professionModel.addProfessionChangeListener(this);
    traitModel = new TraitModel();
    traitModel.addTraitChangeListener(this);
    equipmentModel = new EquipmentModel();
    equipmentModel.addEquipmentChangeListener(this);

    professionModel.setProfession(DataHandler.PROFESSIONS.getData().iterator().next());
  }

  public ProfessionModel getProfessionModel() {
    return professionModel;
  }

  public TraitModel getTraitModel() {
    return traitModel;
  }

  public EquipmentModel getEquipmentModel() {
    return equipmentModel;
  }

  public void recalculate() {
    Stats newStats = new Stats();

    // TODO: Add traits
    newStats.add(Stat.POWER, 916);
    newStats.add(Stat.PRECISION, 916);
    newStats.add(Stat.TOUGHNESS, 916);
    newStats.add(Stat.VITALITY, 916);
    for (Equipment equip : equipmentModel.getGear().values()) {
      for (Entry<Stat, Integer> stat : equip.getStats().entrySet()) {
        if (!stat.getKey().isComplex()) {
          newStats.add(stat.getKey(), stat.getValue());
        }
      }
    }
    newStats.add(Stat.HEALTH, professionModel.getProfession().getHealth());
    newStats.add(Stat.ARMOR, newStats.get(Stat.TOUGHNESS) + newStats.get(Stat.DEFENSE));
    newStats.add(Stat.CRITICAL_CHANCE, (int) (Math.floor((newStats.get(Stat.PRECISION) - 822) / 21.0)));
    newStats.add(Stat.CRITICAL_DAMAGE, (int) (Math.floor(newStats.get(Stat.FEROCITY) / 15.0) + 150));
    newStats.add(Stat.HEALTH, newStats.get(Stat.VITALITY) * 10);
    newStats.add(Stat.EFFECTIVE_HEALTH, (int) Math.floor((newStats.get(Stat.ARMOR) * newStats.get(Stat.HEALTH)) / 1000));

    double baseDamage = newStats.get(Stat.POWER);
    double critChance = (100 + newStats.get(Stat.CRITICAL_CHANCE)) / 100.0;
    double critDamage = (newStats.get(Stat.CRITICAL_DAMAGE)) / 100.0;
    newStats.add(Stat.EFFECTIVE_POWER, (int) Math.floor(baseDamage * (critChance * critDamage)));

    // TODO: Add percentages

    stats = newStats;
    fireCharacterStatChanged();
  }

  public Stats getStats() {
    if (stats == null) {
      recalculate();
    }
    return stats;
  }

  public void fireCharacterStatChanged() {
    for (CharacterStatChangeListener listener : listeners) {
      listener.characterStatsChanged();
    }
  }

  public void addCharacterStatChangeListener(CharacterStatChangeListener listener) {
    listeners.add(listener);
  }

  public void removeCharacterStatChangeListener(CharacterStatChangeListener listener) {
    listeners.remove(listener);
  }

  @Override
  public void equipmentChanged(Slot slot, Equipment gear) {
    recalculate();
  }

  @Override
  public void professionChanged(Profession oldProfession, Profession newProfession) {
    recalculate();
  }

  @Override
  public void traitChanged(int traitLine, int newValue) {
    recalculate();
  }

  @Override
  public String toString() {
    return equipmentModel.toString();
  }
}
