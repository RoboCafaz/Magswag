package net.maguuma.magswag.calculator.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.maguuma.magswag.calculator.WeightCalculator;
import net.maguuma.magswag.calculator.character.Character;
import net.maguuma.magswag.calculator.controller.listener.CharacterChangeListener;
import net.maguuma.magswag.common.constants.EquipmentType;
import net.maguuma.magswag.common.constants.Rarity;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.datatypes.datastore.DataHandler;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.items.GearType;
import net.maguuma.magswag.common.datatypes.items.StatType;
import net.maguuma.magswag.common.logging.Logger;

public class CharacterController {
  private static Character character = new Character();
  private static List<Character> characters = new ArrayList<Character>();
  private static int COUNT = 0;
  private static long TOTAL = 0;

  private static Set<CharacterChangeListener> listeners = new HashSet<CharacterChangeListener>();

  public static void fireCharacterChanged() {
    for (CharacterChangeListener listener : listeners) {
      listener.characterChanged();
    }
  }

  public static List<Character> getCharacters() {
    return characters;
  }

  public static void calculateCharacters() {
    long start = System.currentTimeMillis();
    characters.clear();
    COUNT = 0;
    List<StatType> goodTypes = getRelevantStats();
    List<Slot> emptySlots = character.getEquipmentModel().getEmptySlots();
    Map<Slot, List<Equipment>> gearCollection = createRelevantGear(emptySlots, goodTypes);
    Character baseCharacter = createHighestGearCharacter(emptySlots, gearCollection);

    TOTAL = 1;
    for (List<Equipment> gear : gearCollection.values()) {
      TOTAL *= gear.size();
    }

    Logger.info("Expecting " + TOTAL + " combinations.");

    createCharacters(baseCharacter, emptySlots, gearCollection);
    Logger.info("Created " + COUNT + " in " + (System.currentTimeMillis() - start) + "ms.");
  }

  private static List<StatType> getRelevantStats() {
    List<StatType> stats = new ArrayList<StatType>();
    for (StatType statType : DataHandler.STAT_TYPES.getData()) {
      if (WeightCalculator.relevant(statType)) {
        stats.add(statType);
        Logger.info(statType.getName() + " is relevant.");
      }
    }
    return stats;
  }

  private static Character createHighestGearCharacter(List<Slot> emptySlots, Map<Slot, List<Equipment>> gearCollection) {
    Character bestChar = createCharacter(character);
    for (Slot slot : emptySlots) {
      Equipment bestEquip = null;
      int bestWeight = 0;
      for (Equipment equip : gearCollection.get(slot)) {
        int curWeight = WeightCalculator.calculateWeight(equip);
        if (bestEquip == null || curWeight > bestWeight) {
          bestWeight = curWeight;
          bestEquip = equip;
        }
      }
      if (bestEquip != null) {
        bestChar.getEquipmentModel().setGear(slot, bestEquip);
      }
    }
    return bestChar;
  }

  private static Map<Slot, List<Equipment>> createRelevantGear(List<Slot> emptySlots, List<StatType> statTypes) {
    Map<Slot, List<Equipment>> gear = new HashMap<Slot, List<Equipment>>();
    for (Slot slot : emptySlots) {
      List<Equipment> subSet = new ArrayList<Equipment>();
      for (GearType gearType : DataHandler.GEAR_TYPES.getData()) {
        EquipmentType equipType = gearType.getEquipmentType();
        if (slot.applicable(equipType)) {
          for (StatType statType : statTypes) {
            if (statType.applicable(equipType)) {
              Equipment equip = new Equipment(gearType, character.getProfessionModel().getProfession().getArmor(), statType, Rarity.ASCENDED);
              subSet.add(equip);
            }
          }
        }
      }
      gear.put(slot, subSet);
    }
    return gear;
  }

  private static void createCharacters(Character baseCharacter, List<Slot> emptySlots, Map<Slot, List<Equipment>> collection) {
    if (emptySlots.isEmpty()) {
      characters.add(baseCharacter);
      COUNT++;
      if (COUNT % 10000 == 0) {
        Logger.trace("Added new character " + COUNT + " of " + TOTAL);
      }
    } else {
      Slot slot = emptySlots.get(0);
      for (Equipment equipment : collection.get(slot)) {
        Character dupeChar = createCharacter(baseCharacter);
        dupeChar.getEquipmentModel().setGear(slot, equipment);
        List<Slot> newEmptySlots = new ArrayList<Slot>(emptySlots);
        newEmptySlots.remove(slot);
        createCharacters(dupeChar, newEmptySlots, collection);
      }
    }
  }

  private static Character createCharacter(Character character) {
    Logger.debug("Creating new character cloned from " + character);
    Character newCharacter = new Character();
    newCharacter.getProfessionModel().setProfession(character.getProfessionModel().getProfession());
    newCharacter.getTraitModel().setTraits(character.getTraitModel().getTraits());
    for (Entry<Slot, Equipment> entry : character.getEquipmentModel().getGear().entrySet()) {
      newCharacter.getEquipmentModel().setGear(entry.getKey(), entry.getValue());
    }
    return newCharacter;
  }

  public static Character getCharacter() {
    return character;
  }

  public static void addCharacterChangeListener(CharacterChangeListener listener) {
    listeners.add(listener);
  }

  public static void removeCharacterChangeListener(CharacterChangeListener listener) {
    listeners.remove(listener);
  }
}
